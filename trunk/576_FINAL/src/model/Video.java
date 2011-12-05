package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import config.Configure;

import alg.SoundMergeAlgorithm;

import util.AudioBuffer;
import util.VideoBuffer;
import util.VideoReader;

public class Video {

	private File videoFile;
	private File audioFile;

	private int soundAvg;
	private int soundMax;
	private int soundMaxAvg;

	private int motionAvg;
	private int motionMax;

	private Frame[] frames;
	private ArrayList<Shot> shots;
	private LinkedList<Scene> scenes;

	private List<Integer> results;

	public Video(File video, File audio) throws IOException {

		videoFile = video;
		audioFile = audio;

		int length = VideoReader.getMaxTime(video);

		frames = new Frame[length];
		for (int i = 0; i < frames.length; i++) {
			frames[i] = new Frame(i);
		}

		shots = new ArrayList<Shot>();
		scenes = new LinkedList<Scene>();

		System.out.println("Video File Loaded: " + video.getPath());
		System.out.println("Audio File Loaded: " + audio.getPath());
		System.out.println("Video Length: " + length + " frames");
		System.out.println("Video Time: " + length / Configure.FRAME_RATE / 60
				+ " min " + length / Configure.FRAME_RATE % 60 + " second");

		VideoBuffer.getInstance().init(video, 0);
		AudioBuffer.getInstance().init(audio, length, frames);

		long sum = 0;

		for (int i = 0; i < length; i++) {
			sum += frames[i].getSoundAvg();
			if (frames[i].getSoundMax() > soundMax) {
				soundMax = frames[i].getSoundMax();
			}
			if (frames[i].getSoundAvg() > soundMaxAvg) {
				soundMaxAvg = frames[i].getSoundMax();
			}
		}
		soundAvg = (int) (sum / length);
	}

	public void calMotion(int length) {
		motionMax = 0;
		long sum = 0;
		for (int i = 0; i < length; i++) {
			System.out.println("Motion: " + frames[i].getMotionValue());
			sum += frames[i].getMotionValue();
			if (frames[i].getMotionValue() > motionMax) {
				motionMax = frames[i].getMotionValue();
			}
		}

		motionAvg = (int) (sum / length);

		System.out.println("MotionAvg: " + motionAvg);
		System.out.println("MotionMax: " + motionMax);
	}

	public int getSoundAvg() {
		return soundAvg;
	}

	public int getSoundMax() {
		return soundMax;
	}

	public int getSoundMaxAvg() {
		return soundMaxAvg;
	}

	public int getMotionAvg() {
		return motionAvg;
	}

	public int getMotionMax() {
		return motionMax;
	}

	public Frame getFrame(int i) {
		if (i < 0 || i >= frames.length) {
			return null;
		}
		return frames[i];
	}

	public Shot addShot(int startTime, int endTime, double endAngle) {
		Shot shot = new Shot(shots.size(), startTime, endTime, endAngle);
		for (int i = startTime; i < endTime; i++) {
			frames[i].setShot(shot);
		}
		shots.add(shot);
		return shot;
	}

	public ArrayList<Shot> getShots() {
		return shots;
	}

	public Scene addScene(int startShot, int endShot) {
		int shotLength = endShot - startShot;
		Shot[] aShot = new Shot[shotLength];
		for (int i = 0; i < shotLength; i++) {
			aShot[i] = shots.get(startShot + i);
		}
		Scene scene = new Scene(scenes.size(), aShot);

		for (int i = scene.getStartTime(); i < scene.getEndTime(); i++) {
			frames[i].setScene(scene);
		}

		scenes.add(scene);
		return scene;
	}

	public Scene getSceneAt(int timestamp) {
		return frames[timestamp].getScene();
	}

	public Shot getShotAt(int timestamp) {
		return frames[timestamp].getShot();
	}

	public LinkedList<Scene> getScenes() {
		return scenes;
	}

	public List<Integer> getList() {
		return results;
	}

	public void setList(List<Integer> list) {
		this.results = list;
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

	public Image nextImage() {
		return VideoBuffer.getInstance().nextImage();
	}

}
