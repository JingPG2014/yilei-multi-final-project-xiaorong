import java.io.File;
import java.io.IOException;

import ctrl.ProjectCenter;
import ctrl.SummarizeControler;

/**
 * 
 * @author YileiQian
 * 
 */
public class videoSummarize {
	public static void main(String args[]) {

		if (args.length != 3) {
			System.out.println("Format: Java videoSummarize videoInput"
					+ ".rgb audioInput.wav percentage");
			System.exit(0);
		}

		String videoPath = args[0];
		String audioPath = args[1];
		String percentageNum = args[2];

		File video = new File(videoPath);
		if (!video.exists()) {
			System.out.println("Video Path is wrong");
			System.exit(0);
		}

		File audio = new File(audioPath);
		if (!video.exists()) {
			System.out.println("Audio Path is wrong");
			System.exit(0);
		}

		double percentage = 0.0;
		try {
			percentage = Double.parseDouble(percentageNum);
		} catch (NumberFormatException e) {
			System.out.println("Audio Path is wrong");
			System.exit(0);
		}

		try {
			ProjectCenter.getInstance().init(video, audio);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SummarizeControler.getInstance().init().summarize(percentage);
	}
}
