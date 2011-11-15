package util;

import java.awt.Image;
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

	public VideoReader init(File file) throws IOException {
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
		return this;
	}

	public int getMaxTime() {
		return maxTime;
	}

	public void readBuffers(Image[] buffer, int time) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = readFrame(time + i);
		}
	}

	private Image readFrame(int timestamp) {
		try {
			if (timestamp >= maxTime) {
				return null;
			}

			int width = Configure.WIDTH;
			int height = Configure.HEIGHT;

			int len = imageLen;
			byte[] bytes = new byte[len];

			BufferedImage img = new BufferedImage(Configure.WIDTH,
					Configure.HEIGHT, BufferedImage.TYPE_INT_RGB);

			int offset = 0;

			if (currentTime != timestamp) {
				currentTime = timestamp;

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
}
