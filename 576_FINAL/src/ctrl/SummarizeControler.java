package ctrl;

import java.io.File;

public class SummarizeControler {
	private static SummarizeControler sc = null;

	public static SummarizeControler getInstance() {
		if (sc == null) {
			sc = new SummarizeControler();
		}
		return sc;
	}

	private SummarizeControler() {
		
	}
	
	public void summarize(File video, File audio, double percentage){
		
	}
}
