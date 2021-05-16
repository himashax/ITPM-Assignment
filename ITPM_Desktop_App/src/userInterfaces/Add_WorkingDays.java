
package userInterfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import dao.WorkingDaysDAOImpl;



public class Add_WorkingDays implements ActionListener{

	private JFrame frame;
	private JTextField minutes;
	private JTextField workingTime;
	private JComboBox workingdays;
	private JButton add,reset;
	private JCheckBox monday,tuesday,wednesday,thursday,friday,sat,sun; 
	public JPanel Add_WorkingDays_Panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_WorkingDays window = new Add_WorkingDays();
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
	public Add_WorkingDays() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 248, 255));
		frame.setBounds(10,52,840, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 255), new Color(160, 160, 160)), "ADD WORKING DAYS HOURS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(20, 24, 796, 411);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("No of Working Days");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel.setBounds(24, 41, 164, 23);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Working Days");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(24, 99, 146, 23);
		panel.add(lblNewLabel_1);
		
		monday = new JCheckBox("Monday");
		monday.setBackground(new Color(240, 255, 255));
		monday.setFont(new Font("Arial", Font.PLAIN, 12));
		monday.setBounds(286, 99, 111, 25);
		panel.add(monday);
		
		tuesday = new JCheckBox("Tuesday");
		tuesday.setBackground(new Color(240, 255, 255));
		tuesday.setFont(new Font("Arial", Font.PLAIN, 12));
		tuesday.setBounds(433, 101, 111, 25);
		panel.add(tuesday);
		
		wednesday = new JCheckBox("Wednesday");
		wednesday.setBackground(new Color(240, 255, 255));
		wednesday.setFont(new Font("Arial", Font.PLAIN, 12));
		wednesday.setBounds(573, 102, 111, 23);
		panel.add(wednesday);
		
		thursday = new JCheckBox("Thursday");
		thursday.setBackground(new Color(240, 255, 255));
		thursday.setFont(new Font("Arial", Font.PLAIN, 12));
		thursday.setBounds(286, 138, 111, 23);
		panel.add(thursday);
		
		friday = new JCheckBox("Friday");
		friday.setBackground(new Color(240, 255, 255));
		friday.setFont(new Font("Arial", Font.PLAIN, 12));
		friday.setBounds(433, 137, 111, 25);
		panel.add(friday);
		
		sat = new JCheckBox("Saturday");
		sat.setBackground(new Color(240, 255, 255));
		sat.setFont(new Font("Arial", Font.PLAIN, 12));
		sat.setBounds(573, 137, 111, 25);
		panel.add(sat);
		
		sun = new JCheckBox("Sunday");
		sun.setBackground(new Color(240, 255, 255));
		sun.setFont(new Font("Arial", Font.PLAIN, 12));
		sun.setBounds(286, 173, 111, 25);
		panel.add(sun);
		
		JLabel lblNewLabel_1_1 = new JLabel("Working Time Per Day");
		lblNewLabel_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(25, 235, 185, 23);
		panel.add(lblNewLabel_1_1);
		
		minutes = new JTextField();
		minutes.setBackground(new Color(240, 255, 255));
		minutes.setColumns(10);
		minutes.setBounds(493, 232, 123, 35);
		panel.add(minutes);
		
		add = new JButton("ADD");
		add.setForeground(SystemColor.window);
		add.setBackground(new Color(153, 204, 255));
		add.setBounds(168, 302, 120, 35);
		add.addActionListener(this);
		panel.add(add);
		
		reset = new JButton("RESET");
		reset.setBackground(new Color(255, 255, 255));
		reset.setBounds(470, 302, 120, 35);
		reset.addActionListener(this);
		panel.add(reset);
		
		workingTime = new JTextField();
		workingTime.setBackground(new Color(240, 255, 255));
		workingTime.setBounds(286, 232, 123, 35);
		panel.add(workingTime);
		workingTime.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Hours");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(419, 245, 45, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Minutes");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(626, 243, 54, 13);
		panel.add(lblNewLabel_3);
		
		String[] data= {"5","2"};
		workingdays = new JComboBox(data);
		workingdays.setBackground(new Color(240, 255, 255));
		workingdays.setBounds(286, 44, 164, 23);
		panel.add(workingdays);
		
		Add_WorkingDays_Panel = new JPanel();
		Add_WorkingDays_Panel.setLayout(null);
		Add_WorkingDays_Panel.setBounds(0, 0, 826, 487);
		Add_WorkingDays_Panel.add(panel);
		frame.getContentPane().add(Add_WorkingDays_Panel);

	}
	
	
	public void clear() {
		workingdays.setSelectedIndex(0);
		workingTime.setText(null);
		minutes.setText(null);
		monday.setSelected(false);
		tuesday.setSelected(false);
		wednesday.setSelected(false);
		thursday.setSelected(false);
		friday.setSelected(false);
		sat.setSelected(false);
		sun.setSelected(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		if(ob == add) {
			
			int noOf =Integer.parseInt(workingdays.getSelectedItem().toString()) ;
			String hours = workingTime.getText().toString();
			String min =minutes.getText().toString();
			
			String days = "";
			if(monday.isSelected()) {
				days = "Monday";
			}if(tuesday.isSelected()) {
				days = days + " Tuesday";
			}if(wednesday.isSelected()) {
				days = days + " Wednesday";
			}if(thursday.isSelected()) {
				days = days + " Thursday";
			}if(friday.isSelected()) {
				days = days + " Friday";
			}if(sat.isSelected()) {
				days = days + " Saturday";
			}if(sun.isSelected()) {
				days = days + " Sunday";
			}
			
			if(hours.isEmpty()) {
				System.out.println("empty");
				JOptionPane.showMessageDialog(frame,"Hours field cannot be empty","Alert",JOptionPane.WARNING_MESSAGE);
			}
			else if(min.isEmpty()) {
				System.out.println("min empty");
				JOptionPane.showMessageDialog(frame,"Please fill the minutes field","Alert",JOptionPane.WARNING_MESSAGE);
			}else {
				WorkingDaysDAOImpl dh = new WorkingDaysDAOImpl();
				dh.insertWorkingDays(days,Integer.parseInt(hours),Integer.parseInt(min),noOf);
			}
			
			System.out.println("Selected days : " + days);
			
			
	}
		
		if(ob == reset ) {
			
			clear();
		}
	}
}
