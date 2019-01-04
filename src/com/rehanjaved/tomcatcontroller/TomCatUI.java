package com.rehanjaved.tomcatcontroller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Rehan Javed
 * @version 1.0
 *
 */
public class TomCatUI extends JFrame {

	// Attributes
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField pathField;
	private JLabel title, tomcatFolderTitleLabel, statusLabel;
	private JButton selectButton, startButton, stopButton;
	private PathHelper pathHelper;

	/**
	 * Constructor for Tom Cat UI.
	 */
	public TomCatUI() {
		
		// Path Helper..
		pathHelper = PathHelper.getPathHelper();
		
		setTitle("TOMCAT CONTROLLER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(523, 297);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Adding Components..
		addComponents();
		
		// Listeners for buttons..
		selectButton.addActionListener(action -> {
			
			// Opening file chooser.
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.showOpenDialog(null);
			
			File file = fileChooser.getSelectedFile();
			if(file != null) {
			
				pathHelper.updatePath(file.getAbsolutePath());
				pathField.setText(pathHelper.getPath());
				
			}
			
		});
		
		startButton.addActionListener(action -> {
			
			statusLabel.setText("Status: Starting...");
			try {
				ShellExecutor.executeShellFile(ShellExecutor.TOMCAT_START, pathHelper);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error occured while Starting Tomcat: "+e.toString());
			}
			statusLabel.setText("Status: TOMCAT SERVER RUNNING...");
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			statusLabel.setForeground(Color.GREEN);
			
		});
		
		stopButton.addActionListener(action -> {
			
			statusLabel.setText("Status: Stoping...");
			try {
				ShellExecutor.executeShellFile(ShellExecutor.TOMCAT_SHUTDOWN, pathHelper);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error occured while Stopping Tomcat: "+e.toString());
			}
			statusLabel.setText("Status: STOPPED...");
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			statusLabel.setForeground(Color.RED);
			
		});
		
	}
	
	/**
	 * Creating and adding components on the screen.
	 */
	private void addComponents() {
		
		title = new JLabel("TOMCAT CONTROLLER");
		title.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(25, 17, 478, 26);
		contentPane.add(title);
		
		tomcatFolderTitleLabel = new JLabel("TomCat Folder:");
		tomcatFolderTitleLabel.setBounds(25, 93, 130, 16);
		contentPane.add(tomcatFolderTitleLabel);

		pathField = new JTextField(pathHelper.getPath());
		pathField.setBounds(167, 88, 195, 26);
		contentPane.add(pathField);
		pathField.setColumns(10);
		
		selectButton = new JButton("Select");
		selectButton.setBackground(Color.WHITE);
		selectButton.setBounds(374, 88, 117, 29);
		contentPane.add(selectButton);
		
		statusLabel = new JLabel("Status: STOPPED...");
		statusLabel.setForeground(Color.RED);
		statusLabel.setBounds(25, 238, 466, 16);
		contentPane.add(statusLabel);
		
		startButton = new JButton("Start");
		startButton.setBackground(Color.WHITE);
		startButton.setBounds(25, 145, 148, 68);
		contentPane.add(startButton);
		
		stopButton = new JButton("Stop");
		stopButton.setEnabled(false);
		stopButton.setBackground(Color.WHITE);
		stopButton.setBounds(185, 145, 148, 68);
		contentPane.add(stopButton);
		
	}
	
	/**
	 * Main method to run the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
	
			public void run() {
			
				// running the GUI.
				new TomCatUI().setVisible(true);
				
			}
		});
	
	}
	
}
