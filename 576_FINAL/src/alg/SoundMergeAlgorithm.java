package alg;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Scene;
import model.Shot;
import model.Video;

import config.Configure;

import util.AudioBuffer;

import ctrl.ProjectCenter;

public class SoundMergeAlgorithm extends Algorithm {

	private int startShot;

	public SoundMergeAlgorithm(Algorithm nextAlgorithm, Context context) {
		super(nextAlgorithm, context);
	}

	private long avgFrames(int start, int end) {
		long sum = 0;
		Video video = context.getVideo();

		for (int i = start; i < end; i++) {
			sum += video.getFrame(i).getSoundAvg();
		}
		return sum / (end - start);
	}

	private long maxFrames(int start, int end) {
		long max = 0;
		Video video = context.getVideo();

		for (int i = start; i < end; i++) {
			long subMax = video.getFrame(i).getSoundMax();
			if (subMax > max) {
				max = subMax;
			}
		}
		return max;
	}

	private boolean soundMerge(int index) {
		Video video = context.getVideo();
		List<Shot> shots = video.getShots();

		long lastTwoAVG = avgFrames(
				Math.max(0, shots.get(index).getEndTime() - 48),
				shots.get(index).getEndTime());
		long futureTwoAVG = avgFrames(shots.get(index).getEndTime(), Math.min(
				shots.get(index).getEndTime() + 48, video.getLength() - 1));

		long lastFrameAVG = avgFrames(Math.max(0,
				shots.get(index).getEndTime() - 6), Math.min(shots.get(index)
				.getEndTime() + 6, video.getLength() - 1));

		return !((lastTwoAVG / lastFrameAVG > 2)
				|| (futureTwoAVG / lastFrameAVG > 2) || (lastFrameAVG < 2000));
	}

	private boolean sizeMerge(int index) {
		return context.getVideo().getShots().get(index).getEndTime()
				- context.getVideo().getShots().get(startShot).getStartTime() < Configure.MIN_SCENE;
	}

	@Override
	protected void preProcess(int index) {
		if (index < context.getVideo().getShots().size() - 1) {
			if (soundMerge(index) || sizeMerge(index)) {

			} else {
				context.getVideo().addScene(startShot, index + 1);
				startShot = index + 1;
			}
		}
	}

	@Override
	protected void proProcess(int timestamp) {
		// TODO Auto-generated method stub
	}

	public static void main(String args[]) throws IOException {
		File v = new File("data/terminator3.rgb");
		File a = new File("data/terminator_06.wav");

		ProjectCenter.getInstance().init(v, a);

		for (int i = 0; i < 240; i++) {
			System.out.println(i);
			int[] clip = AudioBuffer.getInstance().getSound(i);
			///System.out.println("Avg: " + AvgClip(clip));
			///System.out.println("Max: " + getMax(clip));
		}
	}

	@Override
	public void processAll() {
		processAll(context.getVideo().getShots().size());
	}

}
