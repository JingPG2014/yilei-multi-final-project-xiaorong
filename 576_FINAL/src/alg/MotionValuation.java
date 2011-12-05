package alg;

import model.Scene;
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
	protected void preProcess(int index) {
		Video video = context.getVideo();
		Scene scene = context.getVideo().getScenes().get(index);

		int avgScore = Math
				.min(90,
						(int) avgFrames(video, scene.getStartTime(),
								scene.getEndTime())
								* 90 / (video.getMotionAvg() * 2));
		int maxScore = Math
				.min(10,
						(int) maxFrames(video, scene.getStartTime(),
								scene.getEndTime())
								* 10 / video.getMotionMax());

		// System.out.println(index + " " + (maxAvgScore + avgScore +
		// maxScore));

		scene.setValue(scene.getValue() + avgScore + maxScore);
	}

	@Override
	protected void proProcess(int timestamp) {
		// TODO Auto-generated method stub

	}

}
