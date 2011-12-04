package model;

public class Shot {
	private int startTime;
	private int endTime;

	public Shot(int startTime, int endTime) {
		super();
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
		return endTime - startTime;
	}
}
