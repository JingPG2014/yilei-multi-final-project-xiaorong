package alg;

import java.io.File;
import java.io.IOException;

import ctrl.ProjectCenter;

public class SoundMergeAlgorithm extends Algorithm {

	public SoundMergeAlgorithm(Algorithm nextAlgorithm, Context context) {
		super(nextAlgorithm, context);
	}

	private static long[] transform(byte[] bytes) {
		long[] sound = new long[bytes.length / 2];

		System.out.println("length: " + sound.length);

		for (int i = 0; i < sound.length; i++) {
			sound[i] = bytes[2 * i];
			sound[i] = sound[i] << 8;
			sound[i] += bytes[2 * i + 1];
			System.out.println(sound[i]);
		}

		return sound;
	}

	private static long sumClip(long[] clip) {
		long sum = 0;
		for (int i = 0; i < clip.length; i++) {
			sum += Math.abs(clip[i]);
		}
		System.out.println("sum: " + sum);
		return sum;
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
		File a = new File("data/terminator_06.wav");

		ProjectCenter.getInstance().init(v, a);

		byte[] testAudio = ProjectCenter.getInstance().getVideo().getFrame(83)
				.getAudio();
		long[] clip = transform(testAudio);
		sumClip(clip);
	}
}
