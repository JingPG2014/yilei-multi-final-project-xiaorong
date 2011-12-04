package alg;

import java.io.File;
import java.io.IOException;

import ctrl.ProjectCenter;

public class SoundMergeAlgorithm extends Algorithm {

	public SoundMergeAlgorithm(Algorithm nextAlgorithm, Context context) {
		super(nextAlgorithm, context);
	}

	@Override
	protected void preProcess(int timestamp) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void proProcess(int timestamp) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String args[]) throws IOException{
		File v = new File("data/terminator3.rgb");
		File a = new File("data/terminator3.wav");
		
		ProjectCenter.getInstance().init(v, a);
		
		byte[] testAudio = ProjectCenter.getInstance().getVideo().getFrame(0).getAudio();
	}
}
