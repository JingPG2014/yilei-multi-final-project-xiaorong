package ctrl;

public class VideoPlayer {

	private static VideoPlayer vp = null;

	public static VideoPlayer getInstance() {
		if (vp == null) {
			vp = new VideoPlayer();
		}
		return vp;
	}

	private VideoPlayer() {

	}

	public void play() {

	}
}
