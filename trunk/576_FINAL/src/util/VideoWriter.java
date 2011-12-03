package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

import model.Scene;
import model.Video;

public class VideoWriter {

	private Video video;
	private VideoReader reader;
	private AudioBuffer audio;

	public VideoWriter(Video video) {
		this.video = video;
		reader = VideoBuffer.getInstance().getReader();
		audio = AudioBuffer.getInstance();
	}

	public void writeFile() {
		File videoFile = video.getVideoFile();
		File ovFile = new File("Shit" + ".rgb");
		File oaFile = new File("Shit.wav");

		try {
			System.out.print("Start WriteFile: ");
			FileOutputStream fos = new FileOutputStream(ovFile);

			List<Scene> scenes = video.getScenes();
			int length = 0;

			// System.out.print(scenes.size());
			for (Scene scene : scenes) {
				for (int i = scene.getStartTime(); i < scene.getEndTime(); i++) {
					length++;
					fos.write(reader.readByte(i));
				}
			}

			fos.close();

			System.out.println(length);
			byte[] audioOutputBuffer = new byte[length / 6
					* audio.getFrameSize()];

			for (Scene scene : scenes) {
				for (int i = scene.getStartTime(); i < scene.getEndTime(); i++) {
					
				}
			}

			AudioSystem.write(audio.initStream(audioOutputBuffer),
					AudioFileFormat.Type.WAVE, oaFile);

			System.out.println("Finish");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
