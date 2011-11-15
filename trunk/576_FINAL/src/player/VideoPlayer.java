package player;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import util.VideoBuffer;

import gui.RGBPlayer;
import model.Video;

public class VideoPlayer extends Thread {

	private Video video;
	private RGBPlayer playerPane;
	private VideoBuffer videoBuffer;

	public VideoPlayer(Video video, RGBPlayer playerPane) {
		this.video = video;
		this.playerPane = playerPane;
		playerPane.fresh(video.getFrame(0).getImage());
	}

	public void run() {

		for (int i = 0; i < 14400; i++) {

			long starttime = System.currentTimeMillis();
			// System.out.println(starttime);
			playerPane.fresh(video.getFrame(i).getImage());
			long endtime = System.currentTimeMillis();
			// System.out.println(endtime - starttime);

			try {
				if (i % 3 == 0) {
					Thread.sleep(41 - (endtime - starttime) + i % 3);
				} else {
					Thread.sleep(41 - (endtime - starttime) + i % 3);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
