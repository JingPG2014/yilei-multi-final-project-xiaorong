package util;

import java.awt.image.BufferedImage;

public class ImageProcess {
	public static int[][] grayscale(BufferedImage img) {
		int[][] aImg = new int[img.getWidth()][img.getHeight()];
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				int rgb = img.getRGB(x, y);
				int r = (rgb & 0x00ff0000) >> 16;
				int g = (rgb & 0x0000ff00) >> 8;
				int b = (rgb & 0x000000ff);
				int gray = (r * 30 + g * 59 + b * 11) / 100;
				aImg[x][y] = gray;
			}
		}
		return aImg;
	}

}
