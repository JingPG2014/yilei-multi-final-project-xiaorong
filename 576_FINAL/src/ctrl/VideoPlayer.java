package ctrl;

public class VideoPlayer extends Thread {

	private static VideoPlayer vp = null;

	public static VideoPlayer getInstance() {
		if (vp == null) {
			vp = new VideoPlayer();
		}
		return vp;
	}

	private VideoPlayer() {

	}

	public void init() {

	}

	public void play() {

	}
}
