package ctrl;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import alg.ColorVectorProcessor;
import alg.Context;
import alg.SoundMergeAlgorithm;

import util.VideoWriter;

import model.Scene;
import model.Shot;
import model.Video;

public class SummarizeControler {
	private static SummarizeControler sc = null;

	public static SummarizeControler getInstance() {
		if (sc == null) {
			sc = new SummarizeControler();
		}
		return sc;
	}

	private Video video = null;

	private SummarizeControler() {

	}

	public SummarizeControler init() {
		video = ProjectCenter.getInstance().getVideo();
		return this;
	}

	public void summarize(double percentage) {
		buildShots();
		buildScenes();
		valuation();
		buildNewVideo(percentage, video.getLength());
		output();
	}

	private void buildShots() {
		System.out.println("Start Cut Shots");
		Context context = new Context(video);
		ColorVectorProcessor cvp = new ColorVectorProcessor(null, context);
		cvp.processAll();
		
		for(Shot s : video.getShots()){
			System.out.println(s.getLength());
		}
		
		//for (int i = 0; i < video.getShots().size(); i++) {
		//	System.out.println(video.getShots().get(i));
		//}
		System.out.println("Finish Cut Shots");
	}

	private void buildScenes() {
		System.out.print("Start Cut Scene: ");
		Context context = new Context(video);
		SoundMergeAlgorithm sma = new SoundMergeAlgorithm(null, context);
		sma.processAll();
		
		//for (int i = 0; i < video.getShots().size(); i++) {
		//	video.addScene(i, i);
		//}
		System.out.println("Finish!");
	}

	private void valuation() {
		System.out.print("Start Valuation: ");
		Random r = new Random();
		for (Scene s : video.getScenes()) {
			s.setValue(r.nextInt(100));
		}
		System.out.println("Finish!");
	}

	private void buildNewVideo(double percentage, int length) {
		System.out.print("Start Rebuild: ");
		int maxSize = (int) (percentage * 1.05 * length) / 6 * 6;
		// int maxSize = 9;

		int[][] matrix = new int[video.getScenes().size() + 1][maxSize + 1];
		ArrayList<Integer> result = new ArrayList<Integer>();

		List<Scene> scenes = video.getScenes();

		for (int i = 0; i <= scenes.size(); i++) {
			matrix[i][0] = 0;
		}

		for (int j = 0; j <= maxSize; j++) {
			matrix[0][j] = 0;
		}

		for (int i = 1; i <= scenes.size(); i++) {
			for (int j = 1; j <= maxSize; j++) {
				matrix[i][j] = matrix[i - 1][j];
				if (scenes.get(i - 1).getLength() <= j) {
					matrix[i][j] = Math.max(matrix[i][j], matrix[i - 1][j
							- scenes.get(i - 1).getLength()]
							+ scenes.get(i - 1).getValue());
				}
			}
		}

		// for (int i = 0; i <= scenes.size(); i++) {
		// for (int j = 0; j <= maxSize; j++) {
		// System.out.print(matrix[i][j] + " ");
		// }
		// System.out.println();
		// }

		int max = matrix[scenes.size()][maxSize];
		// System.out.println(max);

		for (int i = scenes.size(); i > 0; i--) {
			if (maxSize >= scenes.get(i - 1).getLength()
					&& matrix[i][maxSize] == matrix[i - 1][maxSize
							- scenes.get(i - 1).getLength()]
							+ scenes.get(i - 1).getValue()) {
				result.add(i - 1);
				// System.out.print((i - 1) + " ");
				maxSize -= scenes.get(i - 1).getLength();
			}
		}

		// System.out.print(" Length " + result.size());

		video.reBuildScenes(result);
		System.out.println(" Finish");
	}

	private void output() {
		VideoWriter wr = new VideoWriter(video);
		wr.writeFile();
	}
}
