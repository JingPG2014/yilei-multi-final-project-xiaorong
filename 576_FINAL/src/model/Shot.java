package model;

public class Shot {
	private int startTime;
	private int endTime;
	private int id;
	private double endAngle;

	public Shot(int id, int startTime, int endTime, double endAngle) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.endAngle = endAngle;
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

	public double getEndAngle() {
		return endAngle;
	}

	public String toString() {
		return "Shot" + id + " " + startTime + " " + endTime;
	}
}
