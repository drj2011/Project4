import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ScholarPubInputWindow {

	
	
	
	
	
	
	
	JFrame mainFrame = new JFrame();
	
	
	
	BoxLayout mainFrameLayout = new BoxLayout(mainFrame.getContentPane(), BoxLayout.PAGE_AXIS);
	
	JTextField fileText = new javax.swing.JTextField();
	JLabel fileLocation = new javax.swing.JLabel();
	
	//Main pane
	JPanel mainPane = new JPanel();
	GridLayout mainLayout = new GridLayout(3,2);
	JLabel mainLabel = new javax.swing.JLabel("Select a button to perform a task.");
	JButton sortButton = new JButton("Sort the Database");
	JButton searchButton = new JButton("Search the Database");
	JButton graphButton = new JButton("Graph Author Stats");
	JButton fileButton = new JButton("Database Input and Output");
	JButton exitButton = new JButton("Exit the Program");



	
	
	//Sort Pane variables
	JPanel sortPane = new JPanel();
	GridLayout sortLayout = new GridLayout(0,3);
	JLabel sortLabel = new javax.swing.JLabel("Click a button below to sort the database accordingly.");
	JButton biButton = new JButton("Bibliographically");
	JButton anButton = new JButton("Author Name");
	JButton ptButton = new JButton("Paper Title");
	JButton stButton = new JButton("Serial Title");
	JButton chButton = new JButton("Chronologically");
	JButton rButton = new JButton("Randomly");
	


	
	
	
	public ScholarPubInputWindow(){
		
		
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("Scholar Publications");
		
		//initialize mainPane
		mainPane.setLayout(mainLayout);
		mainPane.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK,Color.GRAY));
		mainPane.add(sortButton);
		mainPane.add(searchButton);
		mainPane.add(graphButton);
		mainPane.add(fileButton);
		mainPane.add(exitButton);
		mainLabel.setFont(new Font("Times", Font.PLAIN, 20));
		mainLabel.setAlignmentX(Container.CENTER_ALIGNMENT);
		
		
	
		mainFrame.setLayout(mainFrameLayout);
		mainFrame.add(Box.createRigidArea(new Dimension(0,12)));
		mainFrame.add(mainLabel);
		mainFrame.add(Box.createRigidArea(new Dimension(0,12)));
		mainFrame.add(mainPane);
		mainFrame.pack();
		mainFrame.setVisible(true);
		
	}
	
	public boolean mainFrameVis(){
		return mainFrame.isVisible();
	}
	public void setMainFrameNotVis(){
		mainFrame.setVisible(false);
	}
	
	public JButton getSortButton(){
		return sortButton;
	}
	
	public JButton getBIButton(){
		return biButton;
	}
	
	
	
	public void displaySortPane(){

		//initialize sortPane
		sortPane.setLayout(sortLayout);
		sortPane.setBorder(BorderFactory.createBevelBorder(1,Color.BLACK,Color.GRAY));
		sortPane.add(biButton);
		sortPane.add(anButton);
		sortPane.add(ptButton);
		sortPane.add(stButton);
		sortPane.add(chButton);
		sortPane.add(rButton);
		sortLabel.setFont(new Font("Times", Font.PLAIN, 20));
		sortLabel.setAlignmentX(Container.CENTER_ALIGNMENT);

		
		
		mainFrame.remove(Box.createRigidArea(new Dimension(0,12)));
		mainFrame.remove(mainLabel);
		mainFrame.remove(Box.createRigidArea(new Dimension(0,12)));
		mainFrame.remove(mainPane);
		mainFrame.add(sortLabel);
		mainFrame.add(Box.createRigidArea(new Dimension(0,12)));
		mainFrame.add(sortPane);
		mainFrame.pack();
		mainFrame.setVisible(true);
		
	}

	
	
}
