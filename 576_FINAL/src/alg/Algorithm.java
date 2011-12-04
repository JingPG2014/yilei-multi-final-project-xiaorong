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
		processAll(context.getVideo().getLength());
	}

	public void processAll(int max) {
		Video video = context.getVideo();
		for (int i = 0; i < max; i++) {
			process(i);
		}
	}

	private void process(int timestamp) {
		preProcess(timestamp);
		if (nextAlgorithm != null) {
			nextAlgorithm.process(timestamp);
		}
		proProcess(timestamp);
	}

	protected abstract void preProcess(int timestamp);

	protected abstract void proProcess(int timestamp);
}
