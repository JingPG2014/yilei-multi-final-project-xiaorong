package model;

public class Shot {
	private int startTime;
	private int endTime;
	private int id;

	public Shot(int id, int startTime, int endTime) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public int getLength() {
		return endTime - startTime + 1;
	}

	public String toString() {
		return "Shot" + id + " " + startTime + " " + endTime;
	}
}
