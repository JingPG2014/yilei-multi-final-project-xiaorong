package alg;

import java.util.HashMap;

import model.Video;

public class Context {

	private Video video;
	private Algorithm processor;

	private HashMap<String, Object> resourceMap;

	public Context(Video video) {
		this.video = video;

		resourceMap = new HashMap<String, Object>();
	}

	public Video getVideo() {
		return video;
	}
}
