package ctrl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.Video;

public class ProjectCenter {
	private static ProjectCenter pc = null;

	public static ProjectCenter getInstance() {
		if (pc == null) {
			pc = new ProjectCenter();
		}
		return pc;
	}

	private File videoFile;
	private File audioFile;

	private List<ActionListener> actionListeners;

	private Video currentVideo;

	private ProjectCenter() {
		actionListeners = new ArrayList<ActionListener>();
	}

	public void init(File videoFile, File audioFile) {
		this.videoFile = videoFile;
		this.audioFile = audioFile;
	}

	private void updateListeners(String inf) {
		ActionEvent action = new ActionEvent(this, 0, inf);
		for (ActionListener actionListener : actionListeners) {
			actionListener.actionPerformed(action);
		}
	}
}
