package alg;

import model.Video;

public abstract class Algorithm {

	private Algorithm nextAlgorithm;
	protected Context context;

	public Algorithm(Algorithm nextAlgorithm, Context context) {
		super();
		this.nextAlgorithm = nextAlgorithm;
		this.context = context;
	}

	public void processAll() {
		Video video = context.getVideo();
		for (int i = 0; i < video.getLength(); i++) {
			process(i);
		}
	}

	private void process(int timestamp) {
		preProcess();
		if (nextAlgorithm != null) {
			nextAlgorithm.process(timestamp);
		}
		proProcess();
	}

	public abstract void preProcess();

	public abstract void proProcess();
}
