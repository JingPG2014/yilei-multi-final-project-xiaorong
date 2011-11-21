package alg;

public abstract class Algorithm {

	private Algorithm nextAlgorithm;
	protected Context context;

	public Algorithm(Algorithm nextAlgorithm, Context context) {
		super();
		this.nextAlgorithm = nextAlgorithm;
		this.context = context;
	}

	public void process() {
		preProcess();
		if (nextAlgorithm != null) {
			nextAlgorithm.process();
		}
		proProcess();
	}

	public abstract void preProcess();

	public abstract void proProcess();
}
