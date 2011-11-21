package alg;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class ColorVectorProcessor extends Algorithm {
	public ColorVectorProcessor(Algorithm nextAlgorithm, Context context) {
		super(nextAlgorithm, context);
		// TODO Auto-generated constructor stub
	}

	private int[] getColorVector(Image img) {
		int[] out = new int[24];

		if (img instanceof BufferedImage) {
			BufferedImage image = (BufferedImage) img;
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					int rgb = image.getRGB(x, y);
					int r = (rgb & 0x00ff0000) >> 16;
					int g = (rgb & 0x0000ff00) >> 8;
					int b = (rgb & 0x000000ff);

					out[r / 32]++;
					out[g / 32 + 8]++;
					out[b / 32 + 16]++;
				}
			}

		}
		return out;
	}

	public static double getCosAngle(int[] img1, int[] img2) {
		if (img1.length != img2.length) {
			return -1;
		}

		int sum = 0;
		for (int i = 0; i < img1.length; i++) {
			sum += img1[i] * img2[i];
		}
		return sum / (getVectorLength(img1) * getVectorLength(img2));
	}

	private static double getVectorLength(int[] img) {
		int out = 0;
		for (int i = 0; i < img.length; i++) {
			out += img[i] * img[i];
		}
		return Math.sqrt(out);
	}

	@Override
	public void preProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void proProcess() {
		// TODO Auto-generated method stub
		
	}
}
