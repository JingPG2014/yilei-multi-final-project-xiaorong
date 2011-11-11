package gui;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import config.FrameConfig;
import ctrl.ProjectCenter;
import ctrl.VideoReader;

public class MainFrame extends JFrame {

	private static MainFrame mf = null;

	public static MainFrame getInstance() {
		if (mf == null) {
			mf = new MainFrame();
		}
		return mf;
	}

	private VideoPane videoPane;

	private MainFrame() {
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
			ProjectCenter.getInstance().init(video, audio);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {
		initMenu();
		videoPane = new VideoPane();
		getContentPane().add(videoPane);
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

	public VideoPane getVideoPane() {
		return videoPane;
	}
}
