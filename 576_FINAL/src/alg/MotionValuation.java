package alg;

import model.Video;

public class MotionValuation extends Algorithm {

	public static int avgFrames(Video video, int start, int end) {
		long sum = 0;

		for (int i = start; i < end; i++) {
			sum += video.getFrame(i).getMotionValue();
		}
		return (int) (sum / (end - start));
	}

	public static int maxFrames(Video video, int start, int end) {
		int max = 0;

		for (int i = start; i < end; i++) {
			int subMax = video.getFrame(i).getMotionValue();
			if (subMax > max) {
				max = subMax;
			}
		}
		return max;
	}
	
	public MotionValuation(Algorithm nextAlgorithm, Context context) {
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
