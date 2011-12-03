package ctrl;

import java.io.File;

import util.VideoWriter;

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
		buildNewVideo();
		output();
	}

	private void buildShots() {
		video.addShot(0, 120);
		video.addShot(1200, 1320);
	}

	private void buildScenes() {
		video.addScene(0, 1);
	}

	private void valuation() {

	}

	private void buildNewVideo() {

	}

	private void output() {
		VideoWriter wr = new VideoWriter(video);
		wr.writeFile();
	}
}
