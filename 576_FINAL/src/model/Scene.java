package model;

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
		return getEndTime() - getStartTime() + 1;
	}

	public void setValue(int value) {
		this.value = value;
		if (getLength() < 24) {
			value = 0;
		}
	}

	public int getValue() {
		return value;
	}
}
