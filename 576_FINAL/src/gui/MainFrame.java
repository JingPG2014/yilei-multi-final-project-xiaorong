package gui;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import config.FrameConfig;
import ctrl.VideoReader;

public class MainFrame extends JFrame {
	public MainFrame() {
		init(null, null);
	}

	public MainFrame(File video, File audio) {
		init(video, audio);
	}

	private void init(File video, File audio) {
		initCtrl(video, audio);

		setBounds(0, 0, FrameConfig.FRAME_WIDTH, FrameConfig.FRAME_HEIGHT);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initComponents();

	}

	private void initCtrl(File video, File audio) {
		try {
			VideoReader.getInstance().init(video);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {
		initMenu();
	}

	private void initMenu() {
		JMenuBar bar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		bar.add(fileMenu);

		JMenuItem newItem = new JMenuItem("New");
		fileMenu.add(newItem);

		JMenuItem openItem = new JMenuItem("Open");
		fileMenu.add(openItem);

		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);

		JMenu helpMenu = new JMenu("Help");
		bar.add(helpMenu);

		JMenuItem helpItem = new JMenuItem("Help");
		helpMenu.add(helpItem);

		JMenuItem aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);

		setJMenuBar(bar);
	}
}
