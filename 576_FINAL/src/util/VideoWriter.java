package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.spi.AudioFileWriter;

import model.Scene;
import model.Video;

public class VideoWriter {

	private Video video;
	private VideoReader reader;

	public VideoWriter(Video video) {
		this.video = video;
		try {
			reader = new VideoReader(video.getVideoFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFile() {
		File videoFile = video.getVideoFile();
		File ovFile = new File(videoFile.getPath() + "Shit" + ".rgb");
		if (!ovFile.exists()) {

		}

		try {
			FileOutputStream fos = new FileOutputStream(ovFile);

			List<Scene> scenes = video.getScenes();
			for (Scene scene : scenes) {
				for (int i = scene.getStartTime(); i < scene.getEndTime(); i++) {
					fos.write(reader.readByte(i));
				}
			}

			fos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
