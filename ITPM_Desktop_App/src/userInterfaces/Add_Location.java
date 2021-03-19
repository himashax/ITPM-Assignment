package userInterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JTextField;

import models.Location;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

public class Add_Location implements ActionListener {

	private JFrame frame;
	private JTextField name;
	private JTextField room;
	private JButton btnSave1;
	private JRadioButton lechallRadio,rdbtnLab;
	private JSpinner capacity1;
	private JButton btnClear;
	private ButtonGroup bg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Location window = new Add_Location();
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
	public Add_Location() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 861, 616);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(51, 153, 255));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(113, 130, 584, 373);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		name = new JTextField();
		name.setBounds(206, 58, 277, 22);
		panel_1.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Building Name");
		lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		lblNewLabel.setBounds(47, 61, 113, 22);
		panel_1.add(lblNewLabel);
		
		JLabel lblRoomName = new JLabel("Room Name");
		lblRoomName.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		lblRoomName.setBounds(47, 112, 113, 22);
		panel_1.add(lblRoomName);
		
		JLabel lblRoomType = new JLabel("Room Type");
		lblRoomType.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		lblRoomType.setBounds(47, 167, 113, 22);
		panel_1.add(lblRoomType);
		
		JLabel lblCapacity = new JLabel("Capacity");
		lblCapacity.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		lblCapacity.setBounds(47, 233, 113, 22);
		panel_1.add(lblCapacity);
		
		room = new JTextField();
		room.setColumns(10);
		room.setBounds(206, 109, 277, 22);
		panel_1.add(room);
		
		bg = new ButtonGroup();
		
		lechallRadio = new JRadioButton("Lecture Hall");
		lechallRadio.setBounds(206, 164, 127, 25);
		panel_1.add(lechallRadio);
		
		
		rdbtnLab = new JRadioButton("Lab");
		rdbtnLab.setBounds(356, 164, 127, 25);
		panel_1.add(rdbtnLab);
		
		bg.add(lechallRadio);
		bg.add(rdbtnLab);
		
		btnSave1 = new JButton("Save");
		btnSave1.setBounds(360, 315, 97, 25);
		btnSave1.addActionListener(this);
		panel_1.add(btnSave1);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(236, 315, 97, 25);
		btnClear.addActionListener(this);
		panel_1.add(btnClear);
		
		capacity1 = new JSpinner();
		capacity1.setBounds(206, 230, 86, 22);
		panel_1.add(capacity1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(102, 102, 204));
		panel_2.setForeground(new Color(102, 153, 255));
		panel_2.setBounds(0, 0, 843, 56);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Add Location");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(12, 27, 129, 16);
		panel_2.add(lblNewLabel_1);
	}
	
	public void reset() {
		
		capacity1.setValue(0);
		name.setText("");
		room.setText(null);
		
		lechallRadio.setSelected(false);
		rdbtnLab.setSelected(false);
		
		}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == btnSave1) {
			
			if(name.getText().isEmpty()|| room.getText().isEmpty() || (Integer) capacity1.getValue() == 0) {
				
				JOptionPane.showMessageDialog(frame,"Please Enter the all details","Alert",JOptionPane.WARNING_MESSAGE);
	
			}
			else {
				String bname = (String)name.getText();
				
				String roomname = room.getText();
				
				int capacity = (Integer)capacity1.getValue();
				
				String value = "";
				
				if(lechallRadio.isSelected()) {
					value = "lecturehall" ;
				}
				else if (rdbtnLab.isSelected()){
					value = "lab";
				}
				System.out.println(value);
				Location loca = new Location();
				loca.insertLocation(bname, roomname, value, capacity);
				
				reset();

			}
			if(ob == btnClear) {
				reset();
			}
				
			}
			
//			String bname = (String)name.getText();
//			
//			String roomname = room.getText();
//			
//			int capacity = (Integer)capacity1.getValue();
//			
//			String value = "";
//			
//			if(lechallRadio.isSelected()) {
//				value = "lecturehall" ;
//			}
//			else if (rdbtnLab.isSelected()){
//				value = "lab";
//			}
//			System.out.println(value);
//			Location loca = new Location();
//			loca.insertLocation(bname, roomname, value, capacity);
//			
//			reset();
//
//		}
		if(ob == btnClear) {
			reset();
		}
		
		
	}
	
	
}








