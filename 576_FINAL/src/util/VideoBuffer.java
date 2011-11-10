package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Frame;
import model.Video;
import config.Configure;
import ctrl.VideoReader;

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

	private boolean changed;

	private Video video;

	private BufferedImage[] outputBuffer;
	private BufferedImage[] inputBuffer;

	private BufferThread thread;

	private VideoBuffer() {
		maxSize = Configure.BUFFER_SIZE * Configure.FRAME_RATE;

		init();
	}

	public void init() {
		outputBuffer = new BufferedImage[maxSize];
		inputBuffer = new BufferedImage[maxSize];
		VideoReader.getInstance().readBuffers(outputBuffer, 0);
		bufferPoint += inputBuffer.length;
		loadBuffer();

	}

	public BufferedImage nextImage() {
		if (changed && !thread.isAlive()) {
			bufferPoint += outputBuffer.length;
			changed = false;
		}

		if (point != outputBuffer.length) {
			point++;
			return outputBuffer[point - 1];
		} else {
			point = 1;
			System.out.println(thread.isAlive());
			BufferedImage[] temp = outputBuffer;
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

	public void close() {

	}

	public static void main(String args[]) throws IOException {
		File file = new File("data/terminator.rgb");

		JFrame frame = new JFrame();
		VideoReader r = VideoReader.getInstance();
		VideoReader.getInstance().init(file);
		VideoBuffer vb = VideoBuffer.getInstance();

		System.out.println(r.getMaxTime());
		JLabel befLabel = new JLabel(new ImageIcon(vb.nextImage()));
		frame.getContentPane().add(befLabel);
		frame.pack();
		frame.setVisible(true);

		
		for (int i = 0; i < 14400; i++) {
			befLabel.setIcon(new ImageIcon(vb.nextImage()));
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class BufferThread extends Thread {

	private BufferedImage[] buffer;
	private int time;

	public BufferThread(BufferedImage[] buffer, int time) {
		super();
		this.buffer = buffer;
		this.time = time;
	}

	public void run() {
		VideoReader vr = VideoReader.getInstance();
		vr.readBuffers(buffer, time);
	}
}
