package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import config.Configure;

public class VideoPane extends JLabel implements RGBPlayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int width;
	private int height;
	private ImageIcon icon;

	public VideoPane() {
		width = Configure.WIDTH;
		height = Configure.HEIGHT;
		icon = new ImageIcon();

		setSize(width, height);

		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		
	}

	public void fresh(Image image) {
		setIcon(new ImageIcon(image));
	}

}