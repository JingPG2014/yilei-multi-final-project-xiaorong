package model;

import java.awt.Image;
import java.awt.image.BufferedImage;

import util.AudioBuffer;
import util.VideoBuffer;

public class Frame {

	private int timestamp;
	private VideoBuffer vb;

	public Frame(int timestamp) {
		this.timestamp = timestamp;
		vb = VideoBuffer.getInstance();
	}

	public BufferedImage getImage() {
		return vb.getImage(timestamp);
	}

	public byte[] getAudio() {
		return AudioBuffer.getInstance().getSound(timestamp);
	}
}
