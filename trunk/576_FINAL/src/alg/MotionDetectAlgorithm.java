package alg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import model.Video;

import config.Configure;

import ctrl.ProjectCenter;

import util.ImageProcess;

public class MotionDetectAlgorithm extends Algorithm {

	private int[][] lGray = null;

	public MotionDetectAlgorithm(Algorithm nextAlgorithm, Context context) {
		super(nextAlgorithm, context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processAll() {
		processAll(context.getVideo().getLength());
	}

	@Override
	protected void preProcess(int timestamp) {
		int[][] cGray = ImageProcess.grayscale(context.getVideo()
				.getFrame(timestamp).getImage());

		if (lGray != null) {
			int motion = 0;
			// int[][] opticalFlow = new int[Configure.WIDTH][Configure.HEIGHT];

			for (int y = 2; y < Configure.HEIGHT - 2; y++) {
				for (int x = 2; x < Configure.WIDTH - 2; x++) {
					int Et = cGray[x][y] - lGray[x][y];
					int Ex = cGray[x + 1][y] - cGray[x][y];
					int Ey = cGray[x][y + 1] - cGray[x][y];

					int u = (int) ((Et * 10.0) / ((Math
							.sqrt((Ex * Ex + Ey * Ey) * 1.0)) + 1));
					// opticalFlow[x][y] = u;

					motion += Math.abs(u);

					// if (u != 0) {
					// System.out.println(u + " " + Ex + " " + Ey + " " + Et);
					// }

					// System.out.print(u + " ");
				}
				// System.out.println();
			}

			context.getVideo()
					.getFrame(timestamp)
					.setMotionValue(
							motion * 100 / Configure.WIDTH / Configure.HEIGHT);
			System.out.println(motion * 100 / Configure.WIDTH
					/ Configure.HEIGHT);
		}

		lGray = cGray;
	}

	@Override
	protected void proProcess(int timestamp) {
		// TODO Auto-generated method stub

	}

	public static void main(String args[]) throws IOException {
		File v = new File("data/terminator3.rgb");
		File a = new File("data/sports1.wav");

		ProjectCenter.getInstance().init(v, a);
		MotionDetectAlgorithm mdv = new MotionDetectAlgorithm(null,
				new Context(ProjectCenter.getInstance().getVideo()));

		// for (int i = 24; i < 26; i++) {

		mdv.processAll();

		Video video = ProjectCenter.getInstance().getVideo();

		
		// }
	}
}
