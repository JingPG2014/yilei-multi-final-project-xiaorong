package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Frame;
import model.Video;
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
		init(0);
	}

	public void init(int timestamp) {

		clearBuffers();

		reader.readBuffers(outputBuffer, timestamp);

		bufferPoint += inputBuffer.length;

		loadBuffer();
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
			while (thread.isAlive()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
		thread = new BufferThread(inputBuffer, bufferPoint);
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

	public BufferThread(Image[] buffer, int time) {
		super();
		this.buffer = buffer;
		this.time = time;
	}

	public void run() {
		VideoReader vr = VideoReader.getInstance();
		vr.readBuffers(buffer, time);
	}
}
