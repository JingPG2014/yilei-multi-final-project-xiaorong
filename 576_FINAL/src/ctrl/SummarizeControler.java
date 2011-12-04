package ctrl;

import java.io.File;
import java.util.List;

import alg.ColorVectorProcessor;
import alg.Context;

import util.VideoWriter;

import model.Scene;
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
		// valuation();
		// buildNewVideo();
		output();
	}

	private void buildShots() {
		Context context = new Context(video);
		ColorVectorProcessor cvp = new ColorVectorProcessor(null, context);
		cvp.processAll(2400);
	}

	private void buildScenes() {
		video.addScene(0, 0);
		video.addScene(2, 2);
		video.addScene(4, 4);
	}

	private void valuation() {

	}

	private void buildNewVideo(double percentage) {
		int maxSize = (int) (percentage * 1.05 * video.getLength());

		int[][] matrix = new int[video.getScenes().size()][maxSize];
		List<Scene> scenes = video.getScenes();

		for (int i = 0; i < scenes.size(); i++) {
			matrix[i][0] = 0;
		}

		for (int j = 0; j < maxSize; j++) {
			matrix[0][j] = 0;
		}

		for (int i = 1; i < scenes.size(); i++) {
			for (int j = 1; j < maxSize; j++) {
				matrix[i][j] = matrix[i - 1][j];
				if (scenes.get(i).getLength() < j) {
					matrix[i][j] = Math.max(matrix[i][j], matrix[i - 1][j
							- scenes.get(i).getLength()]
							+ scenes.get(i).getValue());
				}
			}
		}
	}

	private void output() {
		VideoWriter wr = new VideoWriter(video);
		wr.writeFile();
	}
}
