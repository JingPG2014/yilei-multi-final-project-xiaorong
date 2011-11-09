package gui;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import config.FrameConfig;

public class MainFrame extends JFrame {
	public MainFrame() {
		init(null, null);
	}

	public MainFrame(File videoPath, File audioPath) {
		init(videoPath, audioPath);
	}

	private void init(File videoPath, File audioPath) {
		setBounds(0, 0, FrameConfig.FRAME_WIDTH, FrameConfig.FRAME_HEIGHT);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initComponents();

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
