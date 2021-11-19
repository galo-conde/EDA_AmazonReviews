package project2;

import java.util.Scanner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JFrame;
//ItemListener
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * DO NOT MODIFY: Exploratory Data Analysis of Amazon Product Reviews
 * @author tesic
 * @author toufik
 * @version 2.0
 */
public class RatingStatsApp implements ActionListener   {

	
	JFrame frame;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem1, menuItem2;
	JTextField t1;
	JLabel jl;
	
	JTable jt_db;
	JTable jt_rat;
	JTable jt_ratSum;
	
	JTextArea ta;
	JScrollPane sp;
	JButton exitBtn;
	JButton resetBtn;
	int datasetID;
	String sourceFilePath;
	boolean found;
	DatasetHandler dh;
	
	/**
	 * implement calculateStats
	 * @author conde
	 * @author padilla
	 */
	public RatingStatsApp() {
		// get the screen size as a java dimension
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// get 2/3 of the height, and 2/3 of the width
		int height = screenSize.height * 2 / 3;
		int width = screenSize.width * 2 / 3;
		// set the jframe height and width		
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
	   // frame.setSize(1000,1000);
		frame.setPreferredSize(new Dimension(width, height));		
	    frame.setLocation(300,400);
		
	    menuBar = new JMenuBar();
		menu = new JMenu("Choose one of the following functions:");
		menuBar.add(menu);
		menuItem1 = new JMenuItem("Display computed statistics for specific dataID.");
		menuItem1.addActionListener(this);
		menuItem2 = new JMenuItem("Add new collection and compute statistics.");
		menuItem2.addActionListener(this);
		menu.add(menuItem1);
		menu.add(menuItem2);
		frame.setJMenuBar(menuBar);

	    
	    
	    jl = new JLabel("Enter datasetID: ");
	    t1 = new JTextField(" ");
	    t1.setBounds(82,10,100,20);
	    frame.add(jl);
	    frame.add(t1);
	     
	    exitBtn=new JButton("EXIT");
		exitBtn.setBounds(200,10,100,20);
		exitBtn.addActionListener(this);
		frame.add(exitBtn);
		
		resetBtn = new JButton("Reset - Start Over");
		resetBtn.setBounds(200,10,100,20);
		resetBtn.addActionListener(this);
		frame.add(resetBtn);
		
		jt_db = new JTable();		
		jt_db.setBounds(500, 100, 150, 100);
		frame.add(jt_db);
				
		jt_rat = new JTable();
		jt_rat.setBounds(500, 300, 150, 500);
		frame.add(jt_rat);

		jt_ratSum = new JTable();
		jt_ratSum.setBounds(500, 500, 150, 500);
		frame.add(jt_ratSum);
		
		ta = new JTextArea("Output \n");
		sp = new JScrollPane(ta); 
		ta.setBounds(200, 100, 150, 100);
		//frame.add(ta);
		frame.getContentPane().add(sp);

		
		
			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
	
	
	// Used to read from System's standard input
	static final Scanner CONSOLE_INPUT = new Scanner(System.in);
	/**
	 * implement RatingStatsApp
	 * @author conde
	 * @author padilla
	 */
	public static void main(final String[] args) {

		 RatingStatsApp obj=new RatingStatsApp();
		
		try {
			obj.dh = new DatasetHandler();
			int dbSize = obj.dh.getDataSets();
			obj.ta.append("Loading the datasets from: \n" + obj.dh.getFolderPath() + System.lineSeparator() + System.lineSeparator());
			
			obj.showTableDB(obj.jt_db);
			obj.resizeColumnWidth(obj.jt_db);
			
			obj.ta.append("     " + dbSize + " datasets available");
			
	
			String newDataID = "";
				//dataset is processed 
				obj.found = false;
				dbSize = obj.dh.getDataSets();
				
				if (dbSize < 1) {
					obj.ta.append("There is no data to select from, select another option\n");
				}
				
		} catch (IOException e) {
			obj.ta.append("Dataset path not found: " + e.getMessage());
		}
		
	}// end mail

	/**
	 * implement RatingStatsApp based on event listeners
	 * @author conde
	 * @author padilla
	 * @param ActionEvent e; e.getSource() determines what action was taken
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		showTableDB(jt_db);
		 if(e.getSource() == exitBtn)
	        {
			 t1.setText("0");
			 ta.append("Goodbye!");
			 System.exit(0);
	        }
		 
		 else if(e.getSource() == resetBtn)
	        {
			 	ta.selectAll();
			    ta.replaceSelection("");
			    jt_ratSum.setModel(new DefaultTableModel());
			    t1.setText(" ");
	        }
		 
		 else if (e.getSource() == menuItem1)
		 {
			 ta.append("\nDisplay Stats \n"); 
			 datasetID = Integer.valueOf(t1.getText().trim());
			 Object[] options = {"Use existing stat data.",
             "Process statistics again, I have new data."};
			 int n = (Integer)JOptionPane.showOptionDialog(null,
					 "Choose one of the following functions:",
					 "Process Data",
					 JOptionPane.YES_NO_OPTION,
					 JOptionPane.QUESTION_MESSAGE,
					 null,     //do not use a custom Icon
					 options,  //the titles of buttons
					 options[0]); //default button title
			 
			 Dataset d = null;
			try {
				d = dh.populateCollection(t1.getText().trim());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			 if (n == JOptionPane.OK_OPTION) { 
				 ta.append("ProccessingStats");
				 d.computeStats();
				 dh.saveStatsToFile(t1.getText().trim());}
			 else {
				 d.computeStats();
					// if stats were computed again, save them.
					dh.saveStatsToFile(t1.getText().trim());
			 }

			 ta.append("\n");
			 int k = 20;
				// prints report to file and console 
	
			 String [] datasetDisplay = dh.saveReportToFile(t1.getText().trim(), k).split("\n");
				String []  cols = datasetDisplay[0].split(",");
				DefaultTableModel model = new DefaultTableModel(cols, datasetDisplay.length);
				model.setRowCount(0);
				for (int i = 1; i < datasetDisplay.length; i++) {
					String [] rdata = datasetDisplay[i].split(",");
					model.addRow(rdata);
				}
				jt_ratSum.setModel(model);
				jt_ratSum.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				resizeColumnWidth(jt_ratSum);
		 }
		 else if  (e.getSource() == menuItem2)
		 {
			 ta.append("\nAdd New Stats ");
			JTextField datasetID = new JTextField();
			JTextField sourceFilePath = new JTextField();
			Object[] message = {
			    "Please enter new unique dataID:", datasetID,
			    "Source file name?:", sourceFilePath
			};			
			int option = JOptionPane.showConfirmDialog(null, message, "New Stats", JOptionPane.OK_CANCEL_OPTION);
			boolean check = false;
			if (option == JOptionPane.OK_OPTION) {
			    if (!(dh.checkID(datasetID.getText().trim()))) {
			    	check =	dh.addCollection(datasetID.getText().trim(), sourceFilePath.getText().trim());
			    	if (check) {
			    		ta.append("Collection " + datasetID.getText().trim() + " added\n");
			    		found = true;
			    		
			    		
			    		Dataset d = null;
			 			try {
			 				d = dh.populateCollection(datasetID.getText().trim());
			 			} catch (IOException e1) {
			 				// TODO Auto-generated catch block
			 				e1.printStackTrace();
			 			}
			 			 
			 			d.computeStats();
			 			// if stats were computed again, save them.
			 			dh.saveStatsToFile(t1.getText().trim());
			 			
			 			 
			    		
			    		// this blocks processes computed statistics in dh
						int k = 20;
						// prints report to file and console
						 String [] datasetDisplay = dh.saveReportToFile(datasetID.getText().trim(), k).split("\n");
							String []  cols = datasetDisplay[0].split(",");
							DefaultTableModel model = new DefaultTableModel(cols, datasetDisplay.length);
							model.setRowCount(0);
							for (int i = 1; i < datasetDisplay.length; i++) {
								String [] rdata = datasetDisplay[i].split(",");
								model.addRow(rdata);
							}
							jt_ratSum.setModel(model);
							jt_ratSum.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
							resizeColumnWidth(jt_ratSum);

						// new data saved
						try {
							dh.writeDBToFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    	System.out.println("successful");
			    	} else {
			    		ta.append("File " + sourceFilePath.getText().trim() + " not found.");
			    		ta.append("Try again.");
			    	}
			    } else {
			    	ta.append("failed");
			    }
			} else {
				ta.append("Canceled");
			}
			 
		 }
	}

	

	/**
	 * diplay the available ratings 
	 * @author conde
	 * @author padilla
	 * @param JTable to display the data in
	 */
	public void showTableDB(JTable table) {

		String [] datasetDisplay = dh.printDB().split("\n");
		String []  cols = datasetDisplay[0].split(",");
		DefaultTableModel model = new DefaultTableModel(cols, datasetDisplay.length);
		model.setRowCount(0);
		for (int i = 1; i < datasetDisplay.length; i++) {
			String [] rdata = datasetDisplay[i].split(",");
			model.addRow(rdata);
		}
		//model.addRow(rowData);
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//resizeColumnWidth(obj.jt);
	}
	
	/**
	 * resize the jtable column widths
	 * @author conde
	 * @author padilla
	 * @param JTable to adjust width
	 */
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
}// end class
