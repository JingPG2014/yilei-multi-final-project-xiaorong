package model;

public class Scene {
	private Shot[] shots = null;

	private int value;

	public Scene(Shot[] shots) {
		this.shots = shots;
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

	public int getValue() {
		return value;
	}
}
