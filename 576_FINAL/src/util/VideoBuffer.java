package util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import config.Configure;

public class VideoBuffer {

	private static VideoBuffer vb = null;

	public static VideoBuffer getInstance() {
		if (vb == null) {
			vb = new VideoBuffer();
		}
		return vb;
	}

	private int maxSize;
	private int point;
	private int bufferPoint;

	private VideoReader reader;

	private boolean changed;

	private Image[] outputBuffer;
	private Image[] inputBuffer;

	private BufferThread thread;

	private VideoBuffer() {
		maxSize = Configure.BUFFER_SIZE * Configure.FRAME_RATE;
		outputBuffer = new Image[maxSize];
		inputBuffer = new Image[maxSize];
	}

	public void init(File file, int timestamp) throws IOException {
		System.out.print("Initialize VideoBuffer: ");

		clearBuffers();

		reader = new VideoReader(file);
		reader.readBuffers(outputBuffer, timestamp);

		bufferPoint += inputBuffer.length;

		loadBuffer();

		System.out.println("Finish!");
	}

	public Image getImage(int timestamp) {
		return nextImage();
	}

	public Image nextImage() {
		if (changed && !thread.isAlive()) {
			bufferPoint += outputBuffer.length;
			changed = false;
		}

		if (point != outputBuffer.length) {
			point++;
			return outputBuffer[point - 1];
		} else {
			if (thread.isAlive()) {
				while (thread.isAlive()) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				bufferPoint += outputBuffer.length;
				changed = false;
			}

			point = 1;
			Image[] temp = outputBuffer;
			outputBuffer = inputBuffer;
			inputBuffer = temp;
			loadBuffer();

			return outputBuffer[0];
		}
	}

	private void loadBuffer() {
		thread = new BufferThread(inputBuffer, bufferPoint, reader);
		thread.start();
		changed = true;
	}

	private void clearBuffers() {
		for (int i = 0; i < outputBuffer.length; i++) {
			outputBuffer[i] = null;
		}
		for (int i = 0; i < inputBuffer.length; i++) {
			inputBuffer[i] = null;
		}
	}

	public void close() {

	}
}

class BufferThread extends Thread {

	private Image[] buffer;
	private int time;
	private VideoReader vr;

	public BufferThread(Image[] buffer, int time, VideoReader vr) {
		super();
		this.buffer = buffer;
		this.time = time;
		this.vr = vr;
	}

	public void run() {
		vr.readBuffers(buffer, time);
	}
}
