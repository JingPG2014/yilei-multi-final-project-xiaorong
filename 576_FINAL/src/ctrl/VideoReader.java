package ctrl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

	File file = null;
	FileInputStream is = null;

	private VideoReader() {

	}

	public void init(File file) throws IOException {
		if (is != null) {
			is.close();
		}
		if (file != null) {
			this.file = file;
		}

		is = new FileInputStream(file);
	}

	public BufferedImage readFrame(int time) throws IOException {

		int width = Configure.WIDTH;
		int height = Configure.HEIGHT;

		int len = width * height * 3;
		byte[] bytes = new byte[len];

		BufferedImage img = new BufferedImage(Configure.WIDTH,
				Configure.HEIGHT, BufferedImage.TYPE_INT_RGB);

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		int ind = 0;
		for (int y = 0; y < height; y++) {

			for (int x = 0; x < width; x++) {

				// byte a = 0;
				byte r = bytes[ind];
				byte g = bytes[ind + height * width];
				byte b = bytes[ind + height * width * 2];

				int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8)
						| (b & 0xff);
				img.setRGB(x, y, pix);
				ind++;
			}
		}

		return img;
	}

	public static void main(String args[]) throws IOException {
		File file = new File("data/terminator3.rgb");
		int frameSize = Configure.WIDTH * Configure.HEIGHT
				* Configure.FRAME_RATE * 3;
		System.out.println(file.length() / frameSize);
		JFrame frame = new JFrame();
		VideoReader r = VideoReader.getInstance();
		r.init(file);
		BufferedImage img = r.readFrame(0);
		JLabel befLabel = new JLabel(new ImageIcon(img));
		frame.getContentPane().add(befLabel);
		frame.pack();
		frame.setVisible(true);
	}
}
