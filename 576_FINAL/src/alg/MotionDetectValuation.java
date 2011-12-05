package alg;

import java.awt.image.BufferedImage;

import util.ImageProcess;

public class MotionDetectValuation extends Algorithm {

	private BufferedImage lGray = null;

	public MotionDetectValuation(Algorithm nextAlgorithm, Context context) {
		super(nextAlgorithm, context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processAll() {
		processAll(context.getVideo().getLength());
	}

	@Override
	protected void preProcess(int timestamp) {
		BufferedImage cGray = ImageProcess.grayscale(context.getVideo()
				.getFrame(timestamp).getImage());

		if (lGray != null) {

		}

		lGray = cGray;
	}

	@Override
	protected void proProcess(int timestamp) {
		// TODO Auto-generated method stub

	}

}
