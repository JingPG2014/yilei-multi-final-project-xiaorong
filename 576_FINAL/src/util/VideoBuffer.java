package util;

import config.Configure;

public class VideoBuffer {

	private static VideoBuffer vb = null;

	public static VideoBuffer getInstance() {
		if (vb == null) {
			vb = new VideoBuffer();
		}
		return vb;
	}

	private int maxSize;

	private VideoBuffer() {
		maxSize = Configure.BUFFER_SIZE * Configure.FRAME_RATE;
	}
	
	
	
}
