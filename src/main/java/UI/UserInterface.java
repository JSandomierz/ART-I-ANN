/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Neural_ART_I.Network;
import Neural_ART_I.Neuron;
import Neural_Tools.NetworkSerializator;
import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSlider;

public class UserInterface {

	private JFrame frame;
	private JTextField networkFilePathField;
	private JButton networkFileBrowserBtn;
	private JTextField imagesPathField;
	private JButton imageFilesBrowserBtn;
	private JLabel lblReconaizedClasses;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblSize;
	private JLabel lblVigilance;
	private Box horizontalBox;

	private JButton btnSaveNetwork;
	private JButton btnClear;
	private JSlider jSlider1;
	
	static private Network network;
	private int size;
	private List<BinaryImage> imagesList = new ArrayList<>();
	private ClassTableModel classTableModel = new ClassTableModel(imagesList);
	
	final private String NETWORKFILE_INFO = "Select network file for using existing or not for creating new.";
	final private String IMAGEFILES_INFO = "Select image files.";
	final private String NETWORK_FILEFORMAT_FULLNAME = "ART1 Network file";
	final private String NETWORK_FILEFORMAT_EXTENSION = "art";

	/**
	 * Launch the application.
	 */
	public static void show(Network network) {
		UserInterface.network = network;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 690, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {10, 5, 0, 0, 0, 10, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNetworkFile = new JLabel("Network file:");
		GridBagConstraints gbc_lblNetworkFile = new GridBagConstraints();
		gbc_lblNetworkFile.gridwidth = 2;
		gbc_lblNetworkFile.anchor = GridBagConstraints.WEST;
		gbc_lblNetworkFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblNetworkFile.gridx = 1;
		gbc_lblNetworkFile.gridy = 0;
		frame.getContentPane().add(lblNetworkFile, gbc_lblNetworkFile);
		
		networkFilePathField = new JTextField();
		networkFilePathField.setEditable(false);
		networkFilePathField.setText(NETWORKFILE_INFO);
		GridBagConstraints gbc_networkFilePathField = new GridBagConstraints();
		gbc_networkFilePathField.fill = GridBagConstraints.HORIZONTAL;
		gbc_networkFilePathField.insets = new Insets(0, 0, 5, 5);
		gbc_networkFilePathField.gridx = 3;
		gbc_networkFilePathField.gridy = 0;
		frame.getContentPane().add(networkFilePathField, gbc_networkFilePathField);
		networkFilePathField.setColumns(10);
		
		networkFileBrowserBtn = new JButton("Browse...");
		networkFileBrowserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser();
				c.addChoosableFileFilter(new FileNameExtensionFilter(NETWORK_FILEFORMAT_FULLNAME, NETWORK_FILEFORMAT_EXTENSION));
				c.setAcceptAllFileFilterUsed(false);
				c.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if(c.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
					String filename = c.getSelectedFile().toString();
					networkFilePathField.setText(filename);
					NetworkSerializator serializator = new NetworkSerializator(filename);
					try {
						network = serializator.load();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(frame, "Serialization error, file: "+filename, "Error", JOptionPane.ERROR_MESSAGE);
						Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
					}
				}else networkFilePathField.setText(NETWORKFILE_INFO);
			}
		});
		GridBagConstraints gbc_networkFileBrowserBtn = new GridBagConstraints();
		gbc_networkFileBrowserBtn.anchor = GridBagConstraints.EAST;
		gbc_networkFileBrowserBtn.insets = new Insets(0, 0, 5, 5);
		gbc_networkFileBrowserBtn.gridx = 4;
		gbc_networkFileBrowserBtn.gridy = 0;
		frame.getContentPane().add(networkFileBrowserBtn, gbc_networkFileBrowserBtn);
		
		JLabel lblImages = new JLabel("Images:");
		GridBagConstraints gbc_lblImages = new GridBagConstraints();
		gbc_lblImages.gridwidth = 2;
		gbc_lblImages.anchor = GridBagConstraints.WEST;
		gbc_lblImages.insets = new Insets(0, 0, 5, 5);
		gbc_lblImages.gridx = 1;
		gbc_lblImages.gridy = 1;
		frame.getContentPane().add(lblImages, gbc_lblImages);
		
		imagesPathField = new JTextField();
		imagesPathField.setEditable(false);
		imagesPathField.setText(IMAGEFILES_INFO);
		GridBagConstraints gbc_imagesPathField = new GridBagConstraints();
		gbc_imagesPathField.insets = new Insets(0, 0, 5, 5);
		gbc_imagesPathField.fill = GridBagConstraints.HORIZONTAL;
		gbc_imagesPathField.gridx = 3;
		gbc_imagesPathField.gridy = 1;
		frame.getContentPane().add(imagesPathField, gbc_imagesPathField);
		imagesPathField.setColumns(10);
		
		imageFilesBrowserBtn = new JButton("Browse...");
		imageFilesBrowserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser();
				c.addChoosableFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
				c.setAcceptAllFileFilterUsed(false);
				c.setFileSelectionMode(JFileChooser.FILES_ONLY);
				c.setMultiSelectionEnabled(true);
				if(c.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
					if(!network.isInitialized()){
						try {
							BinaryImage test = new BinaryImage(c.getSelectedFiles()[0]);
							size = test.getHeight()*test.getWidth();
							network.init(size, jSlider1.getValue()/100.0f);
						} catch (IOException ex) {
							Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
						}

					}
					addImages(c.getSelectedFiles());
				}else imagesPathField.setText(IMAGEFILES_INFO);
			}
		});
		GridBagConstraints gbc_imageFilesBrowserBtn = new GridBagConstraints();
		gbc_imageFilesBrowserBtn.insets = new Insets(0, 0, 5, 5);
		gbc_imageFilesBrowserBtn.anchor = GridBagConstraints.EAST;
		gbc_imageFilesBrowserBtn.gridx = 4;
		gbc_imageFilesBrowserBtn.gridy = 1;
		frame.getContentPane().add(imageFilesBrowserBtn, gbc_imageFilesBrowserBtn);
		
		lblSize = new JLabel("Vigilance threshold:");
		GridBagConstraints gbc_lblSize = new GridBagConstraints();
		gbc_lblSize.anchor = GridBagConstraints.WEST;
		gbc_lblSize.gridwidth = 2;
		gbc_lblSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblSize.gridx = 1;
		gbc_lblSize.gridy = 2;
		frame.getContentPane().add(lblSize, gbc_lblSize);
		
		horizontalBox = Box.createHorizontalBox();
		GridBagConstraints gbc_horizontalBox = new GridBagConstraints();
		gbc_horizontalBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_horizontalBox.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalBox.gridx = 3;
		gbc_horizontalBox.gridy = 2;
		frame.getContentPane().add(horizontalBox, gbc_horizontalBox);
		
		
		jSlider1 = new javax.swing.JSlider();
		jSlider1.setValue(87);
		lblVigilance = new JLabel(String.format("%.2f",jSlider1.getValue()/100.0f));
		jSlider1.addChangeListener((ce) -> {
			System.out.println(jSlider1.getValue());
			lblVigilance.setText(String.format("%.2f",jSlider1.getValue()/100.0f));
			network.setSensitivity(jSlider1.getValue()/100.0f);
		});
		
		//textField_2 = new JSpinner();
		horizontalBox.add(jSlider1);
		horizontalBox.add(lblVigilance);

		
		
		lblReconaizedClasses = new JLabel("Recognized classes:");
		GridBagConstraints gbc_lblReconaizedClasses = new GridBagConstraints();
		gbc_lblReconaizedClasses.insets = new Insets(0, 0, 5, 5);
		gbc_lblReconaizedClasses.gridx = 3;
		gbc_lblReconaizedClasses.gridy = 3;
		frame.getContentPane().add(lblReconaizedClasses, gbc_lblReconaizedClasses);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(classTableModel);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(157);
		table.getColumnModel().getColumn(2).setPreferredWidth(98);
		table.setRowHeight(50);
		scrollPane.setViewportView(table);
		
		btnSaveNetwork = new JButton("Save network");
		GridBagConstraints gbc_btnSaveNetwork = new GridBagConstraints();
		gbc_btnSaveNetwork.insets = new Insets(0, 0, 0, 5);
		gbc_btnSaveNetwork.gridx = 3;
		gbc_btnSaveNetwork.gridy = 5;
		btnSaveNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(NETWORK_FILEFORMAT_FULLNAME, NETWORK_FILEFORMAT_EXTENSION));
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
					String filename = fileChooser.getSelectedFile().toString();
					if(!filename.endsWith("."+NETWORK_FILEFORMAT_EXTENSION)){
						filename+="."+NETWORK_FILEFORMAT_EXTENSION;
					}
					NetworkSerializator serializator = new NetworkSerializator(filename);
					try {
						serializator.save(network);
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(frame, "Serialization error, file: "+filename, "Error", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				}
			}
		});
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener((ae) -> {
			imagesList.clear();
			classTableModel.clear();
			table.setModel(classTableModel);
			table.updateUI();
			network.clear();
			networkFilePathField.setText(NETWORKFILE_INFO);
			imagesPathField.setText(IMAGEFILES_INFO);
		});
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 0, 5, 5);
		gbc_btnClear.gridx = 3;
		gbc_btnClear.gridy = 6;
		frame.getContentPane().add(btnSaveNetwork, gbc_btnSaveNetwork);
		frame.getContentPane().add(btnClear, gbc_btnClear);
	}
	
	private void addImages(File [] files){
		String fileNames = "";
		for(File file : files){
			try {
				BinaryImage img = new BinaryImage(file);
				network.setInputs(img.getBinaryArray());
				network.sendInputs();
                                
				Neuron bestMatch = network.computeResult();
				img.reconizedNeuron = bestMatch;
                                System.out.println(bestMatch.id+", "+bestMatch.similarityRatio);
                                network.adaptWeights(bestMatch);
                                
				imagesList.add(img);
				fileNames+=file.toString()+", ";
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(frame, "IO error, file: "+file.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Bad file size: " + file.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
				
		}	
		//System.out.println("ils: "+imagesList.size());
		table.setModel(classTableModel);
		table.updateUI();
		imagesPathField.setText(fileNames);

	}
	
	
	
}
