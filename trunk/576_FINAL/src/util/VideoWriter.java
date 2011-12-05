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
	private AudioBuffer audioBuffer;

	public VideoWriter(Video video) {
		this.video = video;
		reader = VideoBuffer.getInstance().getReader();
		audioBuffer = AudioBuffer.getInstance();
	}

	public void writeFile() {
		// File videoFile = video.getVideoFile();
		File ovFile = new File("Shit.rgb");
		File oaFile = new File("Shit.wav");

		try {
			System.out.print("Start WriteFile: ");
			FileOutputStream fos = new FileOutputStream(ovFile);

			List<Scene> scenes = video.getScenes();
			int length = 0;

			// System.out.print(scenes.size());
			for (int x = 0; x < video.getList().size(); x++) {
				Scene scene = video.getScenes().get(video.getList().get(x));
				System.out.println(scene);
				for (int i = scene.getStartTime(); i < scene.getEndTime(); i++) {
					length++;
					fos.write(reader.readByte(i));
				}
			}

			fos.close();

			byte[] audioOutputBuffer = new byte[length
					* audioBuffer.getFrameSize() / 4];
			// System.out.println(audioOutputBuffer.length);
			int point = 0;

			for (int x = 0; x < video.getList().size(); x++) {
				Scene scene = video.getScenes().get(video.getList().get(x));
				for (int i = scene.getStartTime(); i < scene.getEndTime(); i += 4) {
					byte[] buffer = audioBuffer.getSoundByQSecond(i);
					for (int j = 0; j < buffer.length; j++) {
						audioOutputBuffer[point + j] = buffer[j];
					}
					point += buffer.length;
					// System.out.println(point);
				}
			}

			AudioSystem.write(audioBuffer.initStream(audioOutputBuffer),
					AudioFileFormat.Type.WAVE, oaFile);

			System.out.println("Finish");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
