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

import model.Video;

public class SoundPlayer extends Thread {

	private File audioFile;

	private AudioInputStream audioInputStream = null;
	private SourceDataLine dataLine = null;

	private long initTime;

	private int bufferSize = 0;

	public SoundPlayer(Video video, int timestamp) {
		this.audioFile = video.getAudioFile();
		FileInputStream waveStream = null;
		try {
			waveStream = new FileInputStream(audioFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			audioInputStream = AudioSystem.getAudioInputStream(waveStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Obtain the information about the AudioInputStream
		AudioFormat audioFormat = audioInputStream.getFormat();
		System.out.println(audioFormat);
		bufferSize = (int) audioFormat.getFrameRate()
				* audioFormat.getFrameSize();
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
		int readBytes = 0;
		byte[] audioBuffer = new byte[bufferSize];

		try {

			while (readBytes != -1) {
				long start = System.nanoTime();
				readBytes = audioInputStream.read(audioBuffer, 0,
						audioBuffer.length);
				long mid = System.nanoTime();
				if (readBytes >= 0) {
					dataLine.write(audioBuffer, 0, readBytes);
				}
				long end = System.currentTimeMillis();
				System.out.println((mid - start)/1000);
				//System.out.println((end - mid));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// plays what's left and and closes the audioChannel
			dataLine.drain();
			dataLine.close();
		}
	}
}
