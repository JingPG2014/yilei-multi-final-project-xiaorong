package alg;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import model.Video;

public class ColorVectorProcessor extends Algorithm {

	private static final int precision = 3;
	private int startPoint;
	private List<long[]> vectorList;
	private long[] lastVector;
	private double lastAngle;

	public ColorVectorProcessor(Algorithm nextAlgorithm, Context context) {
		super(nextAlgorithm, context);

		startPoint = 0;
		vectorList = new ArrayList<long[]>();
	}

	private long[] getColorVector(Image img) {
		int pre = 256 >> precision;
		int bucket = 1 << precision;

		long[] out = new long[3 * (bucket)];

		if (img instanceof BufferedImage) {
			BufferedImage image = (BufferedImage) img;
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					int rgb = image.getRGB(x, y);
					int r = (rgb & 0x00ff0000) >> 16;
					int g = (rgb & 0x0000ff00) >> 8;
					int b = (rgb & 0x000000ff);

					out[r / pre]++;
					out[g / pre + bucket]++;
					out[b / pre + 2 * bucket]++;
				}
			}

		}
		return out;
	}

	private double getCosAngle(long[] img1, long[] img2) {
		if (img1.length != img2.length) {
			return -1;
		}

		double sum = 0.0;
		for (int i = 0; i < img1.length; i++) {
			sum += img1[i] * img2[i];
			// System.out.print(img1[i] +" " +img2[i]+" ");
			// System.out.println((long)img1[i] * img2[i]);
		}

		// System.out.println(getVectorLength(img1));
		// System.out.println(getVectorLength(img2));
		return Math.acos(1.0 * sum / getVectorLength(img1)
				/ getVectorLength(img2))
				/ Math.PI * 180;
	}

	private double getVectorLength(long[] img) {
		long out = 0;
		for (int i = 0; i < img.length; i++) {
			out += img[i] * img[i];
		}
		return Math.sqrt(out);
	}

	private void newShot(int timestamp, double endAngle) {
		Video video = context.getVideo();
		if (startPoint % 4 != 0) {
			startPoint = startPoint / 4 * 4 + 4;
		}
		video.addShot(startPoint, timestamp / 4 * 4, endAngle);

		vectorList.clear();

		startPoint = timestamp;
	}

	@Override
	public void processAll() {
		processAll(context.getVideo().getLength());
	}

	@Override
	protected void preProcess(int timestamp) {
		// if (timestamp < 2400) {
		if (timestamp % 240 == 0) {
			System.out.println("Process: " + timestamp);
		}
		Video video = context.getVideo();
		BufferedImage image = video.getFrame(timestamp).getImage();
		long[] vector = this.getColorVector(image);
		if (timestamp != 0) {
			double angle = getCosAngle(vector, lastVector);
			if (lastAngle != 0.0) {
				if ((angle / lastAngle < 0.15 || lastAngle / angle < 0.15)
						&& (angle > 2.0 || lastAngle > 2.0)
						&& (timestamp - startPoint) >= 24) {
					// System.out.println(timestamp);
					// System.out.println(angle);
					// System.out.println(lastAngle);
					newShot(timestamp, Math.max(angle, lastAngle));
					startPoint = timestamp;
					lastAngle = 0.0;
				}
			} else {
				lastAngle = angle;
			}
		}
		lastVector = vector;

		// }
	}

	@Override
	protected void proProcess(int timestamp) {
		// TODO Auto-generated method stub

	}

	public static void main(String args[]) {
		int[] a = { 3, 0, 0, 0 };
		int[] b = { 1, 1, 1, 1 };
		ColorVectorProcessor cvp = new ColorVectorProcessor(null, null);
		// System.out.println(cvp.getCosAngle(a, b));
	}

}
