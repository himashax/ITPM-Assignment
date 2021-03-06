
package userInterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dao.SubjectDAOImpl;
import models.Subject;

import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Add_Subject implements ActionListener {

	private JFrame frmAddSubject;
	private JTextField textFieldSubCode;
	private JTextField textFieldSubName;
	private JComboBox comboBoxOffYear;
	private JLabel lblOffYear;
	private JLabel lblSubCode;
	private JLabel lblSubName;
	private JLabel lblOffSem;
	private JRadioButton rdbtnFirstSem;
	private JRadioButton rdbtnSecSem;
	private JLabel lblNoOfHrs;
	private JLabel lblNoOfTutorial;
	private JLabel lblNoOfLab;
	private JLabel lblNoOfEvaluation;
	private JSpinner spinner_LecHrs;
	private JSpinner spinner_Tute;
	private JSpinner spinner_Lab;
	private JSpinner spinner_EvHrs;
	private JButton btnClear;
	private JButton btnSave;
	ButtonGroup G1; 
	public JPanel panel_AddSubject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Subject window = new Add_Subject();
					window.frmAddSubject.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Add_Subject() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddSubject = new JFrame();
		frmAddSubject.setTitle("ADD SUBJECT");
		frmAddSubject.setForeground(Color.WHITE);
		frmAddSubject.setBackground(Color.WHITE);
		frmAddSubject.setBounds(100, 100, 902, 467);
		frmAddSubject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddSubject.getContentPane().setLayout(null);
		frmAddSubject.setLocationRelativeTo(null);
		
		SpinnerModel model_1 = new SpinnerNumberModel(0, 0, 12, 1);
		SpinnerModel model_2 = new SpinnerNumberModel(0, 0, 12, 1);
		SpinnerModel model_3 = new SpinnerNumberModel(0, 0, 12, 1);
		SpinnerModel model_4 = new SpinnerNumberModel(0, 0, 12, 1);
		
		panel_AddSubject = new JPanel();
		panel_AddSubject.setBackground(Color.WHITE);
		panel_AddSubject.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		panel_AddSubject.setBounds(0, 0, 886, 428);

		panel_AddSubject.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(UIManager.getColor("Button.background")));
		panel_1.setBackground(UIManager.getColor("Button.background"));
		panel_1.setBounds(38, 43, 378, 293);
		panel_AddSubject.add(panel_1);
		panel_1.setLayout(null);
		
		textFieldSubCode = new JTextField();
		textFieldSubCode.setBounds(157, 180, 183, 21);
		panel_1.add(textFieldSubCode);
		textFieldSubCode.setColumns(10);
		
		textFieldSubName = new JTextField();
		textFieldSubName.setColumns(10);
		textFieldSubName.setBounds(157, 241, 183, 21);
		panel_1.add(textFieldSubName);
		
		String[] year = {"1","2","3","4"};
		
		comboBoxOffYear = new JComboBox(year);
		comboBoxOffYear.setEditable(true);
		comboBoxOffYear.setBackground(Color.WHITE);
		comboBoxOffYear.setBounds(150, 28, 190, 20);
		panel_1.add(comboBoxOffYear);
		
		lblOffYear = new JLabel("OFFERED YEAR");
		lblOffYear.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblOffYear.setBounds(31, 31, 96, 14);
		panel_1.add(lblOffYear);
		
		lblSubCode = new JLabel("SUBJECT CODE");
		lblSubCode.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblSubCode.setBounds(31, 180, 96, 14);
		panel_1.add(lblSubCode);
		
		lblSubName = new JLabel("SUBJECT NAME");
		lblSubName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblSubName.setBounds(31, 241, 96, 14);
		panel_1.add(lblSubName);
		
		lblOffSem = new JLabel("OFFERED SEMESTER");
		lblOffSem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblOffSem.setBounds(31, 100, 142, 14);
		panel_1.add(lblOffSem);
		
		 G1 = new ButtonGroup(); 
		  
		
		rdbtnFirstSem = new JRadioButton("1st Semester");
		rdbtnFirstSem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdbtnFirstSem.setBackground(SystemColor.text);
		rdbtnFirstSem.setBounds(97, 121, 96, 23);
		panel_1.add(rdbtnFirstSem);
		
		
		rdbtnSecSem = new JRadioButton("2nd Semester");
		rdbtnSecSem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdbtnSecSem.setBackground(SystemColor.text);
		rdbtnSecSem.setBounds(244, 121, 96, 23);
		panel_1.add(rdbtnSecSem);
		
		G1.add(rdbtnFirstSem); 
		G1.add(rdbtnSecSem);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(UIManager.getColor("Button.background")));
		panel_1_1.setBackground(UIManager.getColor("Button.background"));
		panel_1_1.setBounds(470, 43, 378, 293);
		panel_AddSubject.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		lblNoOfHrs = new JLabel("NO OF LECTURE HOURS");
		lblNoOfHrs.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNoOfHrs.setBounds(27, 33, 177, 14);
		panel_1_1.add(lblNoOfHrs);
		
		lblNoOfTutorial = new JLabel("NO OF TUTORIAL HOURS");
		lblNoOfTutorial.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNoOfTutorial.setBounds(27, 107, 165, 14);
		panel_1_1.add(lblNoOfTutorial);
		
		lblNoOfLab = new JLabel("NO OF LAB HOURS");
		lblNoOfLab.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNoOfLab.setBounds(27, 177, 130, 14);
		panel_1_1.add(lblNoOfLab);
		
		lblNoOfEvaluation = new JLabel("NO OF EVALUATION HOURS");
		lblNoOfEvaluation.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNoOfEvaluation.setBounds(27, 235, 177, 14);
		panel_1_1.add(lblNoOfEvaluation);
		
		spinner_LecHrs = new JSpinner(model_1);
		spinner_LecHrs.setBounds(214, 30, 120, 20);
		panel_1_1.add(spinner_LecHrs);
		
		spinner_Tute = new JSpinner(model_2);
		spinner_Tute.setBounds(214, 104, 120, 20);
		panel_1_1.add(spinner_Tute);
		
		spinner_Lab = new JSpinner(model_3);
		spinner_Lab.setBounds(214, 174, 120, 20);
		panel_1_1.add(spinner_Lab);
		
		spinner_EvHrs = new JSpinner(model_4);
		spinner_EvHrs.setBounds(214, 232, 120, 20);
		panel_1_1.add(spinner_EvHrs);
		
		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "Cleared!");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClear.setBackground(SystemColor.activeCaption);
		btnClear.setBounds(494, 378, 146, 39);
		btnClear.addActionListener(this);
		panel_AddSubject.add(btnClear);
		
		btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSave.setBackground(SystemColor.activeCaption);
		btnSave.setBounds(687, 378, 138, 39);
		btnSave.addActionListener(this);
		panel_AddSubject.add(btnSave);
		
		frmAddSubject.getContentPane().add(panel_AddSubject);
	}
	
	
	public void resetSubFields() {
		comboBoxOffYear.setSelectedIndex(0);
		
		if(rdbtnFirstSem.isSelected()) {
			rdbtnFirstSem.setSelected(false);
		}else {
			rdbtnSecSem.setSelected(false);
		}
		textFieldSubCode.setText(null);
		textFieldSubName.setText(null);
		spinner_LecHrs.setValue(0);
		spinner_Tute.setValue(0);
		spinner_Lab.setValue(0);
		spinner_EvHrs.setValue(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj =e.getSource();
		
		if(obj == btnSave) {
			if(rdbtnFirstSem.isSelected()==false && rdbtnSecSem.isSelected()==false) {
				JOptionPane.showMessageDialog(null, "Please select a semester!", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}else if(textFieldSubCode.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill the subject code!", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}else if(textFieldSubName.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill the subject name!", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}else {
				Subject subject = new Subject();
				subject.setYear(Integer.parseInt((String) comboBoxOffYear.getSelectedItem())); 
				String sem = "";
				
				if(rdbtnFirstSem.isSelected()) {
					subject.setSemester("First Semester"); 
				}else {
					subject.setSemester("Second Semester"); 
				}
				
				subject.setSubjectCode(textFieldSubCode.getText());
				subject.setSubjectName(textFieldSubName.getText());
				
				subject.setNoOfLecHrs((int) spinner_LecHrs.getValue());
				subject.setNoOfTuteHrs((int) spinner_Tute.getValue());
				subject.setNoOfLabHrs((int) spinner_Lab.getValue());
				subject.setNoOfEvalHRs((int) spinner_EvHrs.getValue());
				
				int confirm =JOptionPane.showConfirmDialog(null,"Are you sure you want to save "+ subject.getSubjectName() + " ?","Save Subject",JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION) {
					SubjectDAOImpl subjectDAOImpl = new SubjectDAOImpl();
					try {
						subjectDAOImpl.insertSubQuery(subject);
					} catch (Exception e2) {
						System.out.println("Hiii");
						JOptionPane.showMessageDialog(null,e2.getMessage());
					}
					
				}
				resetSubFields();
			}
			
 			
		}
		if(obj == btnClear) {
			resetSubFields();
		}
	}
}
