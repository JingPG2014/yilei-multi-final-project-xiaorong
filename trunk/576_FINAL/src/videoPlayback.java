import java.io.File;

import player.MediaPlayer;

import ctrl.ProjectCenter;

import gui.MainFrame;

/**
 * 
 * @author YileiQian
 * 
 */
public class videoPlayback {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length != 0 && args.length != 2) {
			System.out.println("Format: videoPlayback video.rgb audio.wav");
			System.exit(0);
		}

		String videoPath = null;
		String audioPath = null;

		File video = null;
		File audio = null;

		if (args.length == 2) {
			videoPath = args[0];
			audioPath = args[1];

			video = new File(videoPath);
			if (!video.exists()) {
				System.out.println("Video Path is wrong");
				System.exit(0);
			}

			audio = new File(audioPath);
			if (!video.exists()) {
				System.out.println("Audio Path is wrong");
				System.exit(0);
			}
		}

		MainFrame frame = new MainFrame(video, audio);
		frame.setVisible(true);
		new MediaPlayer(ProjectCenter.getInstance().getVideo(),
				frame.getVideoPane(), 0).startPlay();
	}

}
