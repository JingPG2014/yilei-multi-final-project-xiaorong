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

	public static int avgFrames(Video video, int start, int end) {
		long sum = 0;

		for (int i = start; i < end; i++) {
			sum += video.getFrame(i).getSoundAvg();
		}
		return (int) (sum / (end - start));
	}

	public static int maxFrames(Video video, int start, int end) {
		int max = 0;

		for (int i = start; i < end; i++) {
			int subMax = video.getFrame(i).getSoundMax();
			if (subMax > max) {
				max = subMax;
			}
		}
		return max;
	}

	public static int maxAvgFrames(Video video, int start, int end) {
		int max = 0;

		for (int i = start; i < end; i++) {
			int subMax = video.getFrame(i).getSoundAvg();
			if (subMax > max) {
				max = subMax;
			}
		}
		return max;
	}

	public boolean soundMerge(int index) {
		Video video = context.getVideo();
		List<Shot> shots = video.getShots();

		long lastTwoAVG = avgFrames(video,
				Math.max(0, shots.get(index).getEndTime() - 48),
				shots.get(index).getEndTime());
		long futureTwoAVG = avgFrames(video, shots.get(index).getEndTime(),
				Math.min(shots.get(index).getEndTime() + 48,
						video.getLength() - 1));

		long lastFrameAVG = avgFrames(video, Math.max(0, shots.get(index)
				.getEndTime() - 6), Math.min(shots.get(index).getEndTime() + 6,
				video.getLength() - 1));

		return !((lastTwoAVG / lastFrameAVG > 2)
				|| (futureTwoAVG / lastFrameAVG > 2) || (lastFrameAVG < video
				.getSoundAvg() / 2));
	}

	private boolean sizeMerge(int index) {
		return context.getVideo().getShots().get(index).getEndTime()
				- context.getVideo().getShots().get(startShot).getStartTime() < Configure.MIN_SCENE;
	}

	@Override
	protected void preProcess(int index) {
		if (index < context.getVideo().getShots().size() - 1) {
			if ((soundMerge(index) || sizeMerge(index))
					&& (context.getVideo().getShots().get(index).getEndTime()
							- context.getVideo().getShots().get(startShot)
									.getStartTime() < Configure.MAX_SCENE)) {

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
		File a = new File("data/sports1.wav");

		ProjectCenter.getInstance().init(v, a);
		SoundMergeAlgorithm sma = new SoundMergeAlgorithm(null, new Context(
				ProjectCenter.getInstance().getVideo()));

		for (int i = 0; i < 240; i++) {
			System.out.println(i);
			System.out.println("Avg: "
					+ ProjectCenter.getInstance().getVideo().getFrame(i)
							.getSoundAvg());
			System.out.println("Max: "
					+ ProjectCenter.getInstance().getVideo().getFrame(i)
							.getSoundMax());
		}

		System.out
				.println(ProjectCenter.getInstance().getVideo().getSoundAvg());
		System.out
				.println(ProjectCenter.getInstance().getVideo().getSoundMax());

		// System.out.println(sma.avgFrames(0, 1));
	}

	@Override
	public void processAll() {
		processAll(context.getVideo().getShots().size());
	}

}
