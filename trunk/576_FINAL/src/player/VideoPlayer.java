package player;

import gui.RGBPlayer;
import model.Video;

public class VideoPlayer extends Thread {

	private Video video;
	private RGBPlayer playerPane;
	private long initTime;

	public VideoPlayer(Video video, RGBPlayer playerPane, int timestamp) {
		this.video = video;
		this.playerPane = playerPane;
		playerPane.fresh(video.getFrame(0).getImage());
	}

	public void setInitTime(long initTime) {
		this.initTime = initTime;
	}

	public void run() {

		System.out.println("VideoPlayer: "
				+ (System.currentTimeMillis() - initTime));
		long begintime = System.currentTimeMillis();
		long starttime = begintime;
		long endtime = begintime;

		for (int i = 0; i < video.getLength(); i++) {

			starttime = System.currentTimeMillis();
			// System.out.println(starttime);
			playerPane.fresh(video.nextImage());
			endtime = System.currentTimeMillis();
			// if (i % 24 == 0) {
			// System.out.println(endtime - begintime);
			// }
			// System.out.println(endtime - starttime);

			try {
				int wait = 0;
				if (i % 24 == 0) {
					wait = (int) (41 - (endtime - begintime - i / 24 * 1000));
				} else if (i % 3 == 0) {
					wait = (int) (41 - (endtime - starttime));
				} else {
					wait = (int) (42 - (endtime - starttime));
				}
				if (wait > 0) {
					Thread.sleep(wait);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
