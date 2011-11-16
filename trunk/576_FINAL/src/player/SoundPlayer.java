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

		long beginTime = System.currentTimeMillis();
		int i = 0;

		AudioBuffer buffer = AudioBuffer.getInstance();
		byte[] audioBuffer = buffer.getNextSecond();

		while (audioBuffer != null) {
			//System.out.println((System.currentTimeMillis() - beginTime) - 1000 * i);
			if (((System.currentTimeMillis() - beginTime) - 1000 * i) > -2000) {
				//System.out.println("Audio: Wait");
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			dataLine.write(audioBuffer, 0, audioBuffer.length);
			audioBuffer = buffer.getNextSecond();

			//System.out.println(i++);
			//System.out.println("Auido: "
			//		+ (System.currentTimeMillis() - beginTime));
		}
	}
}
