package model;

import config.Configure;

public class Scene {
	private Shot[] shots = null;

	private int value;
	private int id;

	public Scene(int id, Shot[] shots) {
		this.shots = shots;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getStartTime() {
		if (shots != null && shots.length > 0) {
			return shots[0].getStartTime();
		}
		return -1;
	}

	public int getEndTime() {
		if (shots != null && shots.length > 0) {
			return shots[shots.length - 1].getEndTime();
		}
		return -1;
	}

	public int getLength() {
		return getEndTime() - getStartTime();
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getBalancedValue() {
		if (getLength() < Configure.MIN_SCENE) {
			return value * getLength() / Configure.MIN_SCENE;
		} else if (getLength() > Configure.MAX_SCENE) {
			return value * Configure.MAX_SCENE / Configure.MAX_SCENE;
		} else {
			return value;
		}
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return "Scene: " + id + " start: " + getStartTime() + " end: "
				+ getEndTime() + " length " + getLength() + " Score: " + getBalancedValue();
	}
}
