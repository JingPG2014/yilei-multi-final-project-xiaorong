package util;

import java.awt.image.BufferedImage;

public class ImageProcess {
	public static BufferedImage grayscale(BufferedImage img) {
		BufferedImage aImg = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				int rgb = img.getRGB(x, y);
				int r = (rgb & 0x00ff0000) >> 16;
				int g = (rgb & 0x0000ff00) >> 8;
				int b = (rgb & 0x000000ff);
				int gray = (r * 30 + g * 59 + b * 11) / 100;
				int pix = 0xff000000 | ((gray & 0xff) << 16)
						| ((gray & 0xff) << 8) | (gray & 0xff);
				aImg.setRGB(x, y, pix);
			}
		}
		return aImg;
	}

}

