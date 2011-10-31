package ctrl;

public class SoundPlayer extends MediaPlayer {

	private static SoundPlayer sp = null;

	public static SoundPlayer getInstance() {
		if (sp == null) {
			sp = new SoundPlayer();
		}
		return sp;
	}

	private SoundPlayer() {

	}
	
	public void play(){
		
	}
}
