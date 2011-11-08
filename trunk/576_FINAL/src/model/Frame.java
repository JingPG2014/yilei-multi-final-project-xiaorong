package model;

import java.awt.image.BufferedImage;

public class Frame {
	private BufferedImage image;
	
	private int timestamp;
	
	private boolean readable;
	private boolean lazy;
	
	public Frame(int timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
