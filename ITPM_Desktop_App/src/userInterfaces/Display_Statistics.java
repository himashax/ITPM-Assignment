package userInterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.Statistics;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class Display_Statistics {

	private JFrame frame;
	private JLabel lastlec;
	private JLabel lastgroup;
	private JLabel lastsub;
	private JLabel countroom;
	private JLabel countstd;
	private JLabel countsub ;
	private JLabel countlec;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display_Statistics window = new Display_Statistics();
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
	public Display_Statistics() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 803, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(39, 70, 506, 422);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(23, 13, 465, 224);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(23, 250, 465, 159);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Latest Lecture");
		lblNewLabel_1.setBounds(27, 32, 108, 16);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Latest Group");
		lblNewLabel_1_1.setBounds(27, 71, 108, 16);
		panel_3.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Latest Subject");
		lblNewLabel_1_2.setBounds(27, 116, 108, 16);
		panel_3.add(lblNewLabel_1_2);
		
		Statistics st = new Statistics();
		
		lastlec = new JLabel(st.latestRecord("lecturer"));
		lastlec.setBounds(176, 32, 206, 16);
		panel_3.add(lastlec);
		
		lastgroup = new JLabel(st.latestRecord("student_group"));
		lastgroup.setBounds(176, 71, 206, 16);
		panel_3.add(lastgroup);
		
		lastsub = new JLabel(st.latestRecord("subject"));
		lastsub.setBounds(147, 116, 206, 16);
		panel_3.add(lastsub);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 153, 255));
		panel_1.setBounds(0, 0, 785, 44);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Statistics");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(12, 13, 91, 16);
		panel_1.add(lblNewLabel);
		
		JPanel panel_4_3 = new JPanel();
		panel_4_3.setBackground(Color.WHITE);
		panel_4_3.setLayout(null);
		panel_4_3.setBounds(596, 400, 157, 92);
		frame.getContentPane().add(panel_4_3);
		
		JLabel lblNewLabel_1_3 = new JLabel("Registered Rooms");
		lblNewLabel_1_3.setBounds(12, 13, 125, 16);
		panel_4_3.add(lblNewLabel_1_3);
		
		countroom = new JLabel(String.valueOf(st.registeredCount("location")));
		countroom.setBounds(43, 50, 56, 16);
		panel_4_3.add(countroom);
		
		JPanel panel_4_3_1 = new JPanel();
		panel_4_3_1.setLayout(null);
		panel_4_3_1.setBackground(Color.WHITE);
		panel_4_3_1.setBounds(596, 295, 157, 92);
		frame.getContentPane().add(panel_4_3_1);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Registered Students");
		lblNewLabel_1_3_1.setBounds(12, 13, 125, 16);
		panel_4_3_1.add(lblNewLabel_1_3_1);
		
		countstd = new JLabel(String.valueOf(st.registeredCount("student_group")));
		countstd.setBounds(45, 51, 56, 16);
		panel_4_3_1.add(countstd);
		
		JPanel panel_4_3_2 = new JPanel();
		panel_4_3_2.setLayout(null);
		panel_4_3_2.setBackground(Color.WHITE);
		panel_4_3_2.setBounds(596, 178, 157, 92);
		frame.getContentPane().add(panel_4_3_2);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Registered Subjects");
		lblNewLabel_1_3_2.setBounds(12, 13, 125, 16);
		panel_4_3_2.add(lblNewLabel_1_3_2);
		
		countsub = new JLabel(String.valueOf(st.registeredCount("subject")));
		countsub.setBounds(43, 48, 56, 16);
		panel_4_3_2.add(countsub);
		
		JPanel panel_4_3_3 = new JPanel();
		panel_4_3_3.setLayout(null);
		panel_4_3_3.setBackground(Color.WHITE);
		panel_4_3_3.setBounds(596, 70, 157, 92);
		frame.getContentPane().add(panel_4_3_3);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Registered Lectures");
		lblNewLabel_1_3_3.setBounds(12, 13, 125, 16);
		panel_4_3_3.add(lblNewLabel_1_3_3);
		
	
		countlec = new JLabel(String.valueOf(st.registeredCount("lecturer")));
		countlec.setBackground(Color.LIGHT_GRAY);
		countlec.setBounds(42, 50, 56, 16);
		panel_4_3_3.add(countlec);
	}
}
