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

	public abstract void processAll();

	public void processAll(int max) {
		for (int i = 0; i < max; i++) {
			process(i);
		}
	}

	private void process(int index) {
		preProcess(index);
		if (nextAlgorithm != null) {
			nextAlgorithm.process(index);
		}
		proProcess(index);
	}

	protected abstract void preProcess(int timestamp);

	protected abstract void proProcess(int timestamp);
}
