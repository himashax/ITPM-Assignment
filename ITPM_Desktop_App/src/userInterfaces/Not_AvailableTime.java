package userInterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import dao.WorkingDaysDAOImpl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class Not_AvailableTime implements ActionListener{

	private JFrame frame;
	private JTextField timeT;
	private JComboBox session;
	private JComboBox lecturerType;
	private JComboBox groupT;
	private JComboBox subGroupType;
	private JButton btnClear;
	private JButton btnSubmit;
	private JButton btnView;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Not_AvailableTime window = new Not_AvailableTime();
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
	public Not_AvailableTime() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 650, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(21, 10, 589, 330);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 248, 255));
		panel_2.setBounds(27, 26, 310, 272);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		session = new JComboBox();
		session.setBounds(141, 25, 143, 22);
		panel_2.add(session);
		
		lecturerType = new JComboBox();
		lecturerType.setBounds(141, 77, 143, 22);
		panel_2.add(lecturerType);
		
		groupT = new JComboBox();
		groupT.setBounds(141, 129, 143, 23);
		panel_2.add(groupT);
		
		subGroupType = new JComboBox();
		subGroupType.setBounds(141, 181, 143, 23);
		panel_2.add(subGroupType);
		
		JLabel lblNewLabel = new JLabel("Select Session ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 24, 112, 23);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Lecturer");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 76, 101, 23);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Select Group");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 128, 101, 23);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Select Sub Group");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 177, 121, 28);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("TIME");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(56, 240, 42, 23);
		panel_2.add(lblNewLabel_4);
		
		
		timeT = new JTextField();
		timeT.setBounds(112, 243, 112, 19);
		panel_2.add(timeT);
		timeT.setColumns(10);
		timeT.setColumns(10);
		
		btnView = new JButton("VIEW");
		btnView.setBackground(new Color(255, 255, 255));
		btnView.setBounds(410, 61, 130, 38);
		panel_1.add(btnView);
		
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.setBackground(new Color(153, 204, 255));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(410, 141, 130, 38);
		panel_1.add(btnSubmit);
		
		btnClear = new JButton("CLEAR");
		btnClear.setBackground(new Color(255, 255, 255));
		btnClear.setBounds(410, 218, 130, 38);
		panel_1.add(btnClear);
		
		
	}
	
	public void clear() {
		session.setSelectedIndex(0);
		lecturerType.setSelectedIndex(0);
		groupT.setSelectedIndex(0);
		subGroupType.setSelectedIndex(0);
		timeT.setText(null);
	}
	

	@Override
	public void actionPerformed(ActionEvent e1) {
		// TODO Auto-generated method stub
		Object ob1 = e1.getSource();
		if(ob1 == btnSubmit) {
			
			String sessions =session.getSelectedItem().toString();
			String lecturers =lecturerType.getSelectedItem().toString();
			String groups =groupT.getSelectedItem().toString();
			String subGroups =subGroupType.getSelectedItem().toString();
			String tTime = timeT.getText().toString();
			
		}
		
	

		if(ob1 == btnClear ) {
			
			clear();
		}
	}
}
