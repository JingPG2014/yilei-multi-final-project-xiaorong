package model;

import java.awt.Image;
import java.awt.image.BufferedImage;

import util.VideoBuffer;

public class Frame {

	private int timestamp;
	private VideoBuffer vb;

	private boolean readable;
	private boolean lazy;

	public Frame(int timestamp) {
		this.timestamp = timestamp;
		vb = VideoBuffer.getInstance();
	}

	public Image getImage() {
		return vb.getImage(timestamp);
	}
}
