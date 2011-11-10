package ctrl;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import util.VideoBuffer;

import gui.RGBPlayer;
import model.Video;

public class VideoPlayer extends Thread {

	private Video video;
	private RGBPlayer playerPane;

	public VideoPlayer(Video video, RGBPlayer playerPane) {
		this.video = video;
		this.playerPane = playerPane;
	}

	public void init() {

	}

	public void run() {

		for (int i = 0; i < 14400; i++) {
			playerPane.fresh(VideoBuffer.getInstance().nextImage());
			try {
				Thread.sleep(41);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
