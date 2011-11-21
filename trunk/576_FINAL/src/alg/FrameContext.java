package alg;

import model.Video;

public class FrameContext {

	private Video video;

	public FrameContext(Video video) {
		this.video = video;
	}

	public void processAll() {
		for (int i = 0; i < video.getLength(); i++) {
			process(i);
		}
	}

	public void process(int timestamp) {
		
	}
}
