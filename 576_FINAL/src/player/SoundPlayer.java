package player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import util.AudioBuffer;

import model.Video;

public class SoundPlayer extends Thread {

	private SourceDataLine dataLine = null;

	private long initTime;

	private int bufferSize = 0;

	public SoundPlayer(Video video, int timestamp) {

		// Obtain the information about the AudioInputStream

		AudioFormat audioFormat = AudioBuffer.getInstance().getAudioFormat();

		Info info = new Info(SourceDataLine.class, audioFormat);

		try {
			dataLine = (SourceDataLine) AudioSystem.getLine(info);
			dataLine.open(audioFormat);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		// Starts the music :P

		// opens the audio channel

	}

	public void setInitTime(long initTime) {
		this.initTime = initTime;
	}

	public void run() {
		System.out.println("SoundPlayer: "
				+ (System.currentTimeMillis() - initTime));
		dataLine.start();

		long begintime = System.currentTimeMillis();
		long starttime = begintime;
		long endtime = begintime;

		AudioBuffer buffer = AudioBuffer.getInstance();
		byte[] audioBuffer = null;

		for (int i = 0; i < buffer.getLength(); i += 4) {
			starttime = System.currentTimeMillis();
			// System.out.println(starttime);
			audioBuffer = buffer.getSoundByQSecond(i);
			dataLine.write(audioBuffer, 0, audioBuffer.length);

			endtime = System.currentTimeMillis();

			try {
				int wait = 0;
				if (i % 24 == 0) {
					wait = (int) (41 - (endtime - begintime - i / 24 * 1000));
				} else if (i % 3 == 0) {
					wait = (int) (41 - (endtime - starttime));
				} else {
					wait = (int) (42 - (endtime - starttime));
				}
				if (wait > 0) {
					// System.out.println(wait);
					// wait = 0;
					Thread.sleep(wait);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
