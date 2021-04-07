package userInterfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.TimeSlotDAOImpl;
import models.TimeSlot;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class Add_Manage_TimeSlot implements ActionListener {


	private JFrame frame;
	private JButton createBtn, deleteBtn;
	private JSpinner startTime,endTime; 
	private DefaultTableModel mngModel;
	private JTable timeslotTable;
	private JScrollPane scPane;
	private JPanel panel_1;
	private int id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Manage_TimeSlot window = new Add_Manage_TimeSlot();
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
	public Add_Manage_TimeSlot() {
		initialize();
		
	}
	

	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 845, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(110, 77, 590, 220);
		panel.setBackground(new Color(220, 220, 220));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(146, 321, 530, 194);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Starting Time");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel.setBounds(86, 29, 128, 24);
		panel.add(lblNewLabel);
		
		JLabel lblEndingTime = new JLabel("Ending Time");
		lblEndingTime.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblEndingTime.setBounds(86, 80, 107, 24);
		panel.add(lblEndingTime);
		
		startTime = new JSpinner(new SpinnerDateModel());
		startTime.setBackground(new Color(0, 0, 255));
		JSpinner.DateEditor de_startTime = new JSpinner.DateEditor(startTime, "HH:mm:ss");
		startTime.setBounds(304, 29, 173, 32);
		startTime.setEditor(de_startTime);
		startTime.setValue(new Date());
		panel.add(startTime);
		
		endTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor de_endTime = new JSpinner.DateEditor(endTime, "HH:mm:ss");
		endTime.setEditor(de_endTime);
		endTime.setValue(new Date());
		endTime.setBounds(304, 80, 173, 32);
		panel.add(endTime);

		createBtn = new JButton("CREATE");
		createBtn.setBackground(SystemColor.textHighlight);
		createBtn.setForeground(SystemColor.window);
		createBtn.setBounds(201, 146, 164, 32);
		createBtn.addActionListener(this);
		panel.add(createBtn);
		
		
		mngModel = new DefaultTableModel(new String[]{"id","SHour", "SMin"}, 0);
		timeslotTable = new JTable(mngModel);
		timeslotTable.setBounds(622, 154, -500, -93);
		
		timeslotTable = new JTable();
		timeslotTable.setBounds(46, 171, 546, -135);
		
		
		timeslotTable = new JTable(mngModel);
		timeslotTable.setBounds(622, 154, -500, -93);
		
		timeSlots();
		scPane = new JScrollPane(timeslotTable);
		scPane.setBounds(21, 21, 396, 152);
		panel_1.add(scPane);
		
		deleteBtn = new JButton("DELETE");
		deleteBtn.setBounds(427, 75, 89, 39);
		panel_1.add(deleteBtn);
		deleteBtn.addActionListener(this);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(100, 149, 237));
		panel_2.setBounds(10, 5, 804, 34);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CREATE TIME SLOTS");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(26, 10, 165, 18);
		panel_2.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ADDING TIMESLOT", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(102, 153, 255)));
		panel_3.setBackground(new Color(240, 248, 255));
		panel_3.setBounds(10, 49, 804, 491);
		frame.getContentPane().add(panel_3);
		
		JTableHeader head = timeslotTable.getTableHeader();
		head.setBackground(new Color(102, 153, 255));
		head.setFont(new Font("Tahoma", Font.BOLD, 11));
		head.setForeground(Color.white);
		
		timeslotTable.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row= timeslotTable.getSelectedRow();
				id = Integer.parseInt(mngModel.getValueAt(row, 0).toString());
			}
			});
		
		
	}
		
	
	public void timeSlots() {
		TimeSlotDAOImpl ts = new TimeSlotDAOImpl();
		ArrayList<TimeSlot> list = ts.getTimeSlots();
		DefaultTableModel tableModel = (DefaultTableModel)timeslotTable.getModel();
		Object[] row = new Object[3];
		for(int i=0;i<list.size();i++) {
			row[0]=list.get(i).getId();
			row[1]=list.get(i).getStartTime();
			row[2]=list.get(i).getEndTime();
			tableModel.addRow(row);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		Object ob = e.getSource();
		if(ob == createBtn) {
			
			int results = JOptionPane.showConfirmDialog(frame,"Are you sure you want to submit your data?","Submit Data",JOptionPane.YES_NO_OPTION);
			
			if(results == JOptionPane.YES_OPTION) {
			
			Date start_time =  (Date) startTime.getValue();
			Timestamp ts=new Timestamp(start_time.getTime());
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
			
            Date end_time =  (Date) endTime.getValue();
			Timestamp ts2=new Timestamp(end_time.getTime());
			SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");  
			
			TimeSlotDAOImpl tslot = new TimeSlotDAOImpl();
            tslot.createTimeSlot(formatter.format(ts), formatter2.format(ts2));
            DefaultTableModel tModel = (DefaultTableModel)timeslotTable.getModel();
            tModel.setRowCount(0);
			timeSlots();
		}
		}
		if(ob == deleteBtn) {
			
			int results = JOptionPane.showConfirmDialog(frame,"Are you sure you want to delete your data?","Delete Data",JOptionPane.YES_NO_OPTION);
			if(results == JOptionPane.YES_OPTION) {
			TimeSlotDAOImpl ts = new TimeSlotDAOImpl();
			ts.deleteTimeSlot(id);
			DefaultTableModel tModel = (DefaultTableModel)timeslotTable.getModel();
            tModel.setRowCount(0);
			timeSlots();
			
			}
		}
	}
}
