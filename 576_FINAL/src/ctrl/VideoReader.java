package ctrl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import config.Configure;

public class VideoReader {

	private static VideoReader vr = null;

	public static VideoReader getInstance() {
		if (vr == null) {
			vr = new VideoReader();
		}
		return vr;
	}

	private File file = null;
	private RandomAccessFile is = null;

	private int imageLen;
	private int currentTime;
	private int maxTime;

	private VideoReader() {

	}

	public void init(File file) throws IOException {
		if (is != null) {
			is.close();
		}
		if (file != null) {
			this.file = file;
		}

		is = new RandomAccessFile(file, "r");

		imageLen = Configure.WIDTH * Configure.HEIGHT * 3;
		currentTime = 0;
		maxTime = (int) (file.length() / imageLen);
	}

	public int getMaxTime() {
		return maxTime;
	}

	public void readBuffers(BufferedImage[] buffer, int time) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = readFrame(time + i);
		}
	}

	private BufferedImage readFrame(int time) {
		try {
			if (time >= maxTime) {
				return null;
			}

			int width = Configure.WIDTH;
			int height = Configure.HEIGHT;

			int len = imageLen;
			byte[] bytes = new byte[len];

			BufferedImage img = new BufferedImage(Configure.WIDTH,
					Configure.HEIGHT, BufferedImage.TYPE_INT_RGB);

			int offset = 0;

			if (currentTime != time) {
				currentTime = time;

				is.seek(currentTime * len);

			}
			is.read(bytes, offset, len);

			int ind = 0;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					byte r = bytes[ind];
					byte g = bytes[ind + height * width];
					byte b = bytes[ind + height * width * 2];

					int pix = 0xff000000 | ((r & 0xff) << 16)
							| ((g & 0xff) << 8) | (b & 0xff);
					img.setRGB(x, y, pix);
					ind++;
				}
			}

			currentTime++;

			return img;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}

	public static void main(String args[]) throws IOException {
		File file = new File("data/terminator.rgb");
		int frameSize = Configure.WIDTH * Configure.HEIGHT
				* Configure.FRAME_RATE * 3;

		JFrame frame = new JFrame();
		VideoReader r = VideoReader.getInstance();
		r.init(file);
		System.out.println(r.getMaxTime());
		BufferedImage img = r.readFrame(5000);
		JLabel befLabel = new JLabel(new ImageIcon(img));
		frame.getContentPane().add(befLabel);
		frame.pack();
		frame.setVisible(true);
	}
}
