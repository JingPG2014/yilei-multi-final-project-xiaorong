package model;

import java.awt.Image;
import java.awt.image.BufferedImage;

import util.AudioBuffer;
import util.VideoBuffer;

public class Frame {

	private int timestamp;
	private VideoBuffer vb;

	private Shot shot;
	private Scene scene;

	public Frame(int timestamp) {
		this.timestamp = timestamp;
		vb = VideoBuffer.getInstance();
	}

	public BufferedImage getImage() {
		return vb.getImage(timestamp);
	}

	public int[] getAudio() {
		return AudioBuffer.getInstance().getSound(timestamp);
	}

	public Shot getShot() {
		return shot;
	}

	public void setShot(Shot shot) {
		this.shot = shot;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
