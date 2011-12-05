package alg;

public class SoundValuation extends Algorithm {

	public SoundValuation(Algorithm nextAlgorithm, Context context) {
		super(nextAlgorithm, context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processAll() {
		processAll(context.getVideo().getScenes().size());
	}
	
	@Override
	protected void preProcess(int timestamp) {
		
	}

	@Override
	protected void proProcess(int timestamp) {
		// TODO Auto-generated method stub

	}

}
