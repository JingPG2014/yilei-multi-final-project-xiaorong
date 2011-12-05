package util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import config.Configure;

public class AudioBuffer {
	private static AudioBuffer ab = null;

	public static AudioBuffer getInstance() {
		if (ab == null) {
			ab = new AudioBuffer();
		}
		return ab;
	}

	private static int[] transform(byte[] bytes) {
		int[] sound = new int[bytes.length / 2];

		for (int i = 0; i < sound.length; i++) {
			sound[i] = bytes[2 * i + 1];
			sound[i] = sound[i] << 8;
			sound[i] = Math.abs(sound[i] | (0x00ff & bytes[2 * i]));
		}

		return sound;
	}

	private List<byte[]> outputBuffer;
	private int[] intBuffer;
	// private List<byte[]> inputBuffer;

	private boolean changed;
	// private AudioBufferThread thread = null;
	private AudioInputStream audioInputStream = null;

	// private int bufferPoint;
	private int frameSize;
	private int length;
	private AudioFormat audioFormat;

	private int point;

	private AudioBuffer() {

	}

	public void init(File file, int length) {
		System.out.print("Initialize audioBuffer: ");

		this.length = length;
		// System.out.println(length);
		outputBuffer = new ArrayList<byte[]>();

		point = 0;

		outputBuffer.clear();
		// inputBuffer.clear();

		FileInputStream waveStream = null;
		try {
			waveStream = new FileInputStream(file);
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

		audioFormat = audioInputStream.getFormat();
		// System.out.println(audioFormat);
		frameSize = (int) audioFormat.getFrameRate()
				* audioFormat.getFrameSize() * 4 / Configure.FRAME_RATE;

		System.out.println(frameSize);
		// int readBytes;

		// System.out.print(length);

		for (int i = 0; i < length / 6; i++) {
			try {
				byte[] audioBuffer = new byte[frameSize];
				audioInputStream.read(audioBuffer, 0, audioBuffer.length);
				outputBuffer.add(audioBuffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		byte[] totalBuffer = new byte[(int) (audioFormat.getFrameRate()
				* length * audioFormat.getFrameSize() / Configure.FRAME_RATE)];

		// System.out.println(length);

		for (int i = 0; i < outputBuffer.size(); i++) {
			byte[] aBuffer = outputBuffer.get(i);
			// System.out.println(outputBuffer.size() + " " + aBuffer.length +
			// " "
			// + i);
			for (int j = 0; j < aBuffer.length; j++) {

				totalBuffer[i * aBuffer.length + j] = aBuffer[j];
			}
		}

		intBuffer = transform(totalBuffer);

		System.out.println("Finish!");
	}

	public int getFrameSize() {
		return frameSize;
	}

	public AudioFormat getAudioFormat() {
		return audioFormat;
	}

	public int[] getSound(int timestamp) {

		int len = frameSize / 12;
		int[] array = new int[len];
		int offset = (int) (((long) frameSize) * timestamp / 12);

		for (int i = 0; i < len; i++) {
			array[i] = intBuffer[i + offset];
		}

		return array;
	}

	public byte[] getSoundByQSecond(int timestamp) {
		return outputBuffer.get(timestamp / 6);
	}

	// public byte[] getSound(int timestamp) {
	// // byte[] bytes = new byte[frameSize / 6];
	// byte[] buffer = outputBuffer.get(timestamp / 6);
	// int offset = timestamp % 6;
	// for (int i = 0; i < bytes.length; i++) {
	// bytes[i] = buffer[i + offset * frameSize / 6];
	// }
	// return bytes;
	// }

	public int getLength() {
		return outputBuffer.size();
	}

	public AudioInputStream initStream(byte[] buffer) {
		ByteArrayInputStream baStream = new ByteArrayInputStream(buffer);
		AudioInputStream ais = new AudioInputStream(baStream, getAudioFormat(),
				buffer.length / getAudioFormat().getFrameSize());
		return ais;
	}

	/*
	 * public byte[] getNextSecond() {
	 * 
	 * if (changed && !thread.isAlive()) { // bufferPoint += outputBuffer.size()
	 * * frameSize; changed = false; }
	 * 
	 * if (point != outputBuffer.size()) { point++; return
	 * outputBuffer.get(point - 1); } else {
	 * 
	 * if (thread.isAlive()) { while (thread.isAlive()) { try {
	 * Thread.sleep(100); } catch (InterruptedException e) {
	 * e.printStackTrace(); } } changed = false; }
	 * 
	 * point = 1;
	 * 
	 * List<byte[]> temp = outputBuffer; outputBuffer = inputBuffer; inputBuffer
	 * = temp;
	 * 
	 * loadBuffer();
	 * 
	 * return outputBuffer.get(0); } }
	 */
	// private void loadBuffer() {
	// System.out.println(inputBuffer.hashCode());
	// thread = new AudioBufferThread(inputBuffer, length, frameSize,
	// audioInputStream);
	// thread.start();
	// changed = true;
	// }
}
/*
 * class AudioBufferThread extends Thread {
 * 
 * private List<byte[]> buffer; private int frameSize; private int length;
 * 
 * private AudioInputStream audioInputStream;
 * 
 * public AudioBufferThread(List<byte[]> buffer, int length, int frameSize,
 * AudioInputStream audioInputStream) { super(); this.buffer = buffer;
 * this.frameSize = frameSize; this.length = length; this.audioInputStream =
 * audioInputStream; }
 * 
 * public void run() { //System.out.print("Load Audio: "); buffer.clear();
 * 
 * int readBytes; try { for (int i = 0; i < length; i++) { byte[] audioBuffer =
 * new byte[frameSize]; readBytes = audioInputStream.read(audioBuffer, 0,
 * frameSize); // System.out.println(audioBuffer.length);
 * buffer.add(audioBuffer);
 * 
 * } } catch (IOException e) { e.printStackTrace(); }
 * //System.out.println("Finish"); } }
 */