package gui;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import config.Configure;

public class VideoPane extends JPanel implements RGBPlayer{
	
	private int width;
	private int height;
	
	public VideoPane() {
		width = Configure.WIDTH;
		height = Configure.HEIGHT;
	}

	@Override
	public void fresh(BufferedImage image) {
		
	}
}
