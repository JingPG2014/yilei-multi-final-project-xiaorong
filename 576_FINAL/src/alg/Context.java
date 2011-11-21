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

	private void initAlgs() {
		processor = new ColorVectorProcessor(null, this);
	}

	public Video getVideo() {
		return video;
	}

	public void processAll() {
		processor.processAll();
	}

}
