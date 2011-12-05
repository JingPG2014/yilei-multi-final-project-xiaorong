package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import util.AudioBuffer;
import util.VideoBuffer;
import util.VideoReader;

public class Video {

	private File videoFile;
	private File audioFile;

	private Frame[] frames;
	private ArrayList<Shot> shots;
	private LinkedList<Scene> scenes;

	public Video(File video, File audio) throws IOException {
		videoFile = video;
		audioFile = audio;

		int length = VideoReader.getMaxTime(video);
		VideoBuffer.getInstance().init(video, 0);
		AudioBuffer.getInstance().init(audio, length);

		frames = new Frame[length];
		for (int i = 0; i < frames.length; i++) {
			frames[i] = new Frame(i);
		}

		shots = new ArrayList<Shot>();
		scenes = new LinkedList<Scene>();
	}

	public Frame getFrame(int i) {
		if (i < 0 || i >= frames.length) {
			return null;
		}
		return frames[i];
	}

	public Shot addShot(int startTime, int endTime, double endAngle) {
		Shot shot = new Shot(shots.size(), startTime, endTime, endAngle);
		shots.add(shot);
		return shot;
	}

	public ArrayList<Shot> getShots() {
		return shots;
	}

	public Scene addScene(int startShot, int endShot) {
		int shotLength = endShot - startShot + 1;
		Shot[] aShot = new Shot[shotLength];
		for (int i = 0; i < shotLength; i++) {
			aShot[i] = shots.get(startShot + i);
		}
		Scene scene = new Scene(scenes.size(), aShot);
		scenes.add(scene);
		return scene;
	}

	public LinkedList<Scene> getScenes() {
		return scenes;
	}

	public void reBuildScenes(List<Integer> results) {
		LinkedList<Scene> newScenes = new LinkedList<Scene>();
		for (int i = results.size() - 1; i >= 0; i--) {
			// System.out.println(i);
			newScenes.add(scenes.get(results.get(i)));
		}
		scenes = newScenes;
	}

	public int getLength() {
		return frames.length;
	}

	public File getVideoFile() {
		return videoFile;
	}

	public File getAudioFile() {
		return audioFile;
	}

	public String getVideoPath() {
		return videoFile.getPath();
	}

	public String getAudioPath() {
		return audioFile.getPath();
	}
}
