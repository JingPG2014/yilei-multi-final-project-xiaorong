package alg;

import java.io.File;
import java.io.IOException;

import util.AudioBuffer;

import ctrl.ProjectCenter;

public class SoundMergeAlgorithm extends Algorithm {

	public SoundMergeAlgorithm(Algorithm nextAlgorithm, Context context) {
		super(nextAlgorithm, context);
	}

	

	private static long sumClip(int[] clip) {
		long sum = 0;
		for (int i = 0; i < clip.length; i++) {
			sum += Math.abs(clip[i]);
		}

		return sum / clip.length;
	}

	private static long getMax(int[] clip) {
		long sum = 0;
		for (int i = 0; i < clip.length; i++) {
			if (Math.abs(clip[i]) > sum) {
				sum = Math.abs(clip[i]);
			}
		}
		System.out.println("Max: " + sum);
		return sum;
	}

	@Override
	protected void preProcess(int timestamp) {
		
	}

	@Override
	protected void proProcess(int timestamp) {
		// TODO Auto-generated method stub
	}

	public static void main(String args[]) throws IOException {
		File v = new File("data/terminator3.rgb");
		File a = new File("data/terminator_06.wav");

		ProjectCenter.getInstance().init(v, a);

		for (int i = 0; i < 240; i ++) {
			System.out.println(i); 
			int[] clip = AudioBuffer.getInstance().getSound(i);
			System.out.println("Avg: " + sumClip(clip));
			getMax(clip);
		}
	}

	@Override
	public void processAll() {
		processAll();
	}

}
