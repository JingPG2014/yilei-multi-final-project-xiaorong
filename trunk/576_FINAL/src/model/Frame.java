package model;

import java.awt.Image;

import util.VideoBuffer;

public class Frame {

	private int timestamp;
	private VideoBuffer vb;

	public Frame(int timestamp) {
		this.timestamp = timestamp;
		vb = VideoBuffer.getInstance();
	}

	public Image getImage() {
		return vb.getImage(timestamp);
	}
	
	public byte[] getAudio(){
		return null;
	}
}
