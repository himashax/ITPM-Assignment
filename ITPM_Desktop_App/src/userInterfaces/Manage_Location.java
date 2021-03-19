package userInterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.Location;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JTable;

public class Manage_Location implements ActionListener {
	
	
	private ButtonGroup bg;
	private JFrame frame;
	private JTextField MRname;
	private JTextField MBname;
	private JTable table;
	private JButton btnSave;
	private JButton btnclear;
	private JRadioButton lechall,lab;
	private JSpinner Mcapa;
	private int locid;
	private JButton btndelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_Location window = new Manage_Location();
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
	public Manage_Location() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 886, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(36, 282, 783, 152);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		MRname = new JTextField();
		MRname.setColumns(10);
		MRname.setBounds(143, 87, 116, 22);
		panel.add(MRname);
		
		MBname = new JTextField();
		MBname.setColumns(10);
		MBname.setBounds(143, 35, 116, 22);
		panel.add(MBname);
		
		JLabel lblNewLabel = new JLabel("Building name");
		lblNewLabel.setBounds(33, 38, 89, 16);
		panel.add(lblNewLabel);
		
		JLabel lblRoomName = new JLabel("Room Name");
		lblRoomName.setBounds(33, 90, 89, 16);
		panel.add(lblRoomName);
		
		JLabel lblCapacity = new JLabel("Capacity");
		lblCapacity.setBounds(336, 90, 56, 16);
		panel.add(lblCapacity);
		
		bg = new ButtonGroup();
		lechall = new JRadioButton("Lecture Hall");
		lechall.setBounds(462, 34, 127, 25);
		panel.add(lechall);
		
		lab = new JRadioButton("Lab");
		lab.setBounds(593, 34, 127, 25);
		panel.add(lab);
		
		bg.add(lechall);
		bg.add(lab);
		
		JLabel lblNewLabel_1 = new JLabel("Room Type");
		lblNewLabel_1.setBounds(336, 38, 94, 16);
		panel.add(lblNewLabel_1);
		
		Mcapa = new JSpinner();
		Mcapa.setBounds(462, 87, 127, 22);
		panel.add(Mcapa);
		
		 btnclear = new JButton("Clear");
		btnclear.setBounds(310, 455, 97, 25);
		btnclear.addActionListener(this);
		frame.getContentPane().add(btnclear);
		
		 btnSave = new JButton("Update");
		btnSave.setBounds(488, 455, 97, 25);
		btnSave.addActionListener(this);
		frame.getContentPane().add(btnSave);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 102, 204));
		panel_1.setBounds(0, 0, 868, 46);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Manage Location");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(12, 13, 118, 20);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(36, 74, 778, 185);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel(new String[] {"ID","Building" , "Room", "Room Type" , "Capacity"},0);
		table = new JTable(model);
		table.setBounds(23, 31, 731, 134);
		JTableHeader header = table.getTableHeader();
		header.setBackground(SystemColor.activeCaption);
		table.setModel(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(20, 20, 750, 150);
		locationtable();
		panel_2.add(scroll);
		
		btndelete = new JButton("Delete");
		btndelete.setBounds(659, 455, 97, 25);
		btndelete.addActionListener(this);
		
		frame.getContentPane().add(btndelete);
		
		table.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				int selectedrow = table.getSelectedRow();
				MBname.setText(model.getValueAt(selectedrow, 1).toString());
				MRname.setText(model.getValueAt(selectedrow, 2).toString());
				String value = model.getValueAt(selectedrow, 3).toString();
				Mcapa.setValue(model.getValueAt(selectedrow, 4));
				locid = (int) model.getValueAt(selectedrow, 0);
				
				
				
				if (value.equals("lecturehall")) 
				{
					lechall.setSelected(true);
					lab.setSelected(false);
					System.out.println("eeeee");
				}
				else {
					lab.setSelected(true);
					lechall.setSelected(false);
				}			
			
			}
			});
	}
	
	public void locationtable () {
		Location loc = new Location() ;
		ArrayList <Location> list =  loc.locations();
		
		DefaultTableModel tModel = (DefaultTableModel)table.getModel();
		Object[] row = new Object[5];
		
		for(int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getId();
			row[1] = list.get(i).getBuildingname();
			row[2] = list.get(i).getRoomname();
			row[3] = list.get(i).getType();
			row[4] = list.get(i).getCapacity();
			
			tModel.addRow(row);
		}
	}
		public void reset() {
		
		Mcapa.setValue(0);
		MBname.setText("");
		MRname.setText("");
		locid = 0;
		
		lechall.setSelected(false);
		lab.setSelected(false);
		
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Object ob = e.getSource();
			
			if (ob == btnclear) {
				reset();
			}
			else if (ob == btnSave ){
				
				if(locid == 0) {
					JOptionPane.showMessageDialog(frame,"Please select the row first","Alert",JOptionPane.WARNING_MESSAGE);
				}
				
				else if (MRname.getText().isEmpty() || MBname.getText().isEmpty() || (Integer) Mcapa.getValue() == 0) {
					
					JOptionPane.showMessageDialog(frame,"Please Enter the all details","Alert",JOptionPane.WARNING_MESSAGE);
				}
				
				else {
					String name = MBname.getText();
					String room = MRname.getText();
					int cap = (Integer)(Mcapa.getValue());	
					
					String value = "";
					
					if(lechall.isSelected()) {
						value = "lecturehall";
					}
					else {
						value = "lab";
					}
					
					Location lo = new Location();
					lo.updatelocation(locid, name, room, value, cap);
					
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					
					locationtable();
					
					reset();
				}
									
			}else if (ob == btndelete) {
				
				int confirm =JOptionPane.showConfirmDialog(null,"Are you sure?","An Inane Question",JOptionPane.YES_NO_OPTION);

				if(confirm == JOptionPane.YES_OPTION) {
				Location loc = new Location();
				loc.deleteLocation(locid);   

				//to display remaining table with data
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setRowCount(0);
				locationtable();
				
				reset();
				}
			}
			
				
		}
}






