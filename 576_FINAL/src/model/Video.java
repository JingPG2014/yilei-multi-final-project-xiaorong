package model;

import java.io.File;

public class Video {

	private File videoFile;
	private File audioFile;

	private Frame[] frames;

	public Video(File video, File audio, int size) {
		videoFile = video;
		audioFile = audio;
		frames = new Frame[size];
	}

	public Frame getFrame(int i) {
		if (i < 0 || i >= frames.length) {
			return null;
		}
		return frames[i];
	}

	public String getVideoPath() {
		return videoFile.getPath();
	}

	public String getAudioPath() {
		return audioFile.getPath();
	}
}
