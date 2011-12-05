package alg;

public class MotionDetectProcessor extends Algorithm {

	public MotionDetectProcessor(Algorithm nextAlgorithm, Context context) {
		super(nextAlgorithm, context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void preProcess(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void proProcess(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void processAll() {
		processAll(context.getVideo().getScenes().size());
	}

}
