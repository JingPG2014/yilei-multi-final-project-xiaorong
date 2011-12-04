package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import config.Configure;
import ctrl.ProjectCenter;

public class VideoReader {

	public static int getMaxTime(File videoFile) {
		return (int) (videoFile.length() / Configure.IMAGE_LENGTH);
	}

	private RandomAccessFile is = null;

	private long currentTime;
	private int maxTime;

	public VideoReader(File file) throws IOException {
		if (is != null) {
			is.close();
		}

		is = new RandomAccessFile(file, "r");

		currentTime = 0;
		maxTime = getMaxTime(file);
	}

	public int getMaxTime() {
		return maxTime;
	}

	public void readBuffers(Image[] buffer, int time) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = readFrame(time + i);
		}
	}

	public BufferedImage readFrame(int timestamp) {

		if (timestamp >= maxTime) {
			return null;
		}

		byte[] bytes = readByte(timestamp);

		int width = Configure.WIDTH;
		int height = Configure.HEIGHT;

		BufferedImage img = new BufferedImage(Configure.WIDTH,
				Configure.HEIGHT, BufferedImage.TYPE_INT_RGB);

		int ind = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				byte r = bytes[ind];
				byte g = bytes[ind + height * width];
				byte b = bytes[ind + height * width * 2];

				int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8)
						| (b & 0xff);
				img.setRGB(x, y, pix);
				ind++;
			}
		}

		currentTime++;

		return img;
	}

	public byte[] readByte(int timestamp) {
		if (timestamp >= maxTime) {
			return null;
		}

		int len = Configure.IMAGE_LENGTH;
		byte[] bytes = new byte[len];
		try {
			int offset = 0;
			//int max = Integer.MAX_VALUE / len;

			if (currentTime != timestamp) {
				currentTime = timestamp;
				long offSeek = currentTime * len;
				is.seek(offSeek);

			}
			is.read(bytes, offset, len);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	public static void main(String args[]) throws IOException {
		File v = new File("data/terminator3.rgb");
		File a = new File("data/terminator3.wav");
		ProjectCenter.getInstance().init(v, a);
		JFrame frame = new JFrame();
		frame.add(new JLabel(new ImageIcon(ProjectCenter.getInstance()
				.getVideo().getFrame(6224).getImage())));
		frame.pack();
		frame.setVisible(true);
	}
}
