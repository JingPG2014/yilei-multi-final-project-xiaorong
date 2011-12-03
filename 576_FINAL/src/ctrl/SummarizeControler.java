package ctrl;

import java.io.File;

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
	private Video summarizedVideo = null;

	private SummarizeControler() {

	}

	public void init() {

	}

	public void summarize(File video, File audio, double percentage) {
		
	}

	private void shot(){
		
	}
	
	private void scene(){
		
	}
	
	private void valuation(){
		
	}
	
	private void buildNewVideo(){
		
	}
	
	private void output() {

	}
}
