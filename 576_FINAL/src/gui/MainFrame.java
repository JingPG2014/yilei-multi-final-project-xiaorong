package gui;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
	public MainFrame() {
		init(null, null);
	}

	public MainFrame(File videoPath, File audioPath) {
		init(videoPath, audioPath);
	}

	private void init(File videoPath, File audioPath) {
		initComponents();
	}

	private void initComponents() {

	}

	private void initMenu() {
		JMenu menu = new JMenu();

		JMenuItem fileItem = new JMenuItem("File");
	}
}
