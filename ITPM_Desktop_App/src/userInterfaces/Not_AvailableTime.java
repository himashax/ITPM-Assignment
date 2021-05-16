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
	private JComboBox session;
	private JComboBox lecturerType;
	private JComboBox time;
	private JButton btnClear;
	private JButton btnSubmit;
	private JButton btnView;
	private JTextField day;
	private JTextField duration;
	private JTextField group;
	

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
		panel_2.setBounds(27, 10, 352, 310);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		session = new JComboBox();
		session.setBounds(199, 27, 143, 22);
		panel_2.add(session);
		
		lecturerType = new JComboBox();
		lecturerType.setBounds(199, 78, 143, 22);
		panel_2.add(lecturerType);
		
		JLabel lblNewLabel = new JLabel("Select Session ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 26, 112, 23);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Lecturer");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 77, 101, 23);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Selected Group / Sub Group");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 119, 182, 40);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Selected Day");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 181, 121, 28);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("TIME");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(81, 278, 42, 23);
		panel_2.add(lblNewLabel_4);
		
		day = new JTextField();
		day.setBounds(199, 186, 143, 22);
		panel_2.add(day);
		day.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Allocated Duration");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(10, 231, 136, 19);
		panel_2.add(lblNewLabel_5);
		
		duration = new JTextField();
		duration.setColumns(10);
		duration.setBounds(199, 231, 143, 22);
		panel_2.add(duration);
		
		time = new JComboBox();
		time.setBounds(133, 280, 143, 23);
		panel_2.add(time);
		
		group = new JTextField();
		group.setColumns(10);
		group.setBounds(199, 131, 143, 22);
		panel_2.add(group);
		
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
		time.setSelectedIndex(0);
		group.setText(null);
		duration.setText(null);
		day.setText(null);
	}
	

	@Override
	public void actionPerformed(ActionEvent e1) {
		// TODO Auto-generated method stub
		Object ob1 = e1.getSource();
		if(ob1 == btnSubmit) {
			
			String sessions =session.getSelectedItem().toString();
			String lecturers =lecturerType.getSelectedItem().toString();
			String tTime = time.getSelectedItem().toString();
			String groups =group.getText().toString();
			String days =day.getText().toString();
			String dur = duration.getText().toString();
			
			
		}
		
	

		if(ob1 == btnClear ) {
			
			clear();
		}
	}
}
