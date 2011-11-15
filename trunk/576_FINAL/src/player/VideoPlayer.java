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
			playerPane.fresh(video.getFrame(i).getImage());
			endtime = System.currentTimeMillis();
			// if (i % 24 == 0) {
			// System.out.println(endtime - begintime);
			// }
			// System.out.println(endtime - starttime);

			try {
				if (i % 24 == 0) {
					int wait = (int) (41 - (endtime - begintime - i / 24 * 1000));
					if (wait > 0) {
						Thread.sleep(41 - (endtime - begintime - i / 24 * 1000));
					}
				} else if (i % 3 == 0) {
					Thread.sleep(41 - (endtime - starttime));
				} else {
					Thread.sleep(42 - (endtime - starttime));
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
