package model;

import java.awt.image.BufferedImage;

import util.AudioBuffer;
import util.VideoBuffer;

public class Frame {

	private int timestamp;
	private VideoBuffer vb;

	private int soundAvg;
	private int soundMax;

	private int motionValue;

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

	public int getSoundAvg() {
		return soundAvg;
	}

	public void setSoundAvg(int soundAvg) {
		this.soundAvg = soundAvg;
	}

	public int getSoundMax() {
		return soundMax;
	}

	public void setSoundMax(int soundMax) {
		this.soundMax = soundMax;
	}

	public int getMotionValue() {
		return motionValue;
	}

	public void setMotionValue(int motionValue) {
		this.motionValue = motionValue;
	}
}
