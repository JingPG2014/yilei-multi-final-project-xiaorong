package alg;

import java.util.List;

import model.Scene;
import model.Video;

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
	protected void preProcess(int index) {
		Video video = context.getVideo();

		Scene scene = context.getVideo().getScenes().get(index);
		System.out.println(SoundMergeAlgorithm.avgFrames(video, 0, 1));
		int avgScore = Math.min(90, (int) SoundMergeAlgorithm.avgFrames(video,
				scene.getStartTime(), scene.getEndTime()) / (video.getSoundAvg() * 2));
		int maxScore = Math.min(10, (int) SoundMergeAlgorithm.maxFrames(video,
				scene.getStartTime(), scene.getEndTime()) / (video.getSoundAvg() ));

		System.out.println(index + " " + avgScore + " " + maxScore);

		scene.setValue(scene.getValue() + avgScore + maxScore);

	}

	@Override
	protected void proProcess(int timestamp) {
		// TODO Auto-generated method stub

	}

}
