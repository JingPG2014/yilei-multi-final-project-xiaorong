package alg;

import java.io.File;
import java.io.IOException;

import ctrl.ProjectCenter;

public class SoundMergeAlgorithm extends Algorithm {

	public SoundMergeAlgorithm(Algorithm nextAlgorithm, Context context) {
		super(nextAlgorithm, context);
	}

	private static int[] transform(byte[] bytes) {
		int[] sound = new int[bytes.length / 2];

		//System.out.println("length: " + sound.length);

		for (int i = 0; i < sound.length; i++) {
			sound[i] = bytes[2 * i + 1];
			//System.out.println(sound[i]);
			//sound[i] = sound[i] << 8;
			//System.out.println(sound[i]);
			//sound[i] += bytes[2 * i];
			//System.out.println("@" + sound[i]);
		}

		return sound;
	}

	private static long sumClip(int[] clip) {
		long sum = 0;
		for (int i = 0; i < clip.length; i++) {
			sum += Math.abs(clip[i]);
		}
		System.out.println("sum: " + sum);
		return sum/clip.length;
	}

	@Override
	protected void preProcess(int timestamp) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void proProcess(int timestamp) {
		// TODO Auto-generated method stub
	}

	public static void main(String args[]) throws IOException {
		File v = new File("data/terminator3.rgb");
		File a = new File("data/terminator3.wav");

		ProjectCenter.getInstance().init(v, a);

		for (int i = 0; i < 24; i++) {
			System.out.println(i);
			byte[] testAudio = ProjectCenter.getInstance().getVideo()
					.getFrame(i).getAudio();
			int[] clip = transform(testAudio);
			sumClip(clip);
		}
	}
}
