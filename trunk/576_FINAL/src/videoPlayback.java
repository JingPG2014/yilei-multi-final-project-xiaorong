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

		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}

}
