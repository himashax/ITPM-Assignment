package userInterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import models.Tags;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add_Tags implements ActionListener {

	private JFrame frame;
	private JTextField tagCode;
	private JComboBox relatedTag, tagName;
	private JButton clearBtn, saveBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Tags window = new Add_Tags();
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
	public Add_Tags() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 870, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(95, 77, 657, 343);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tag Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(116, 73, 63, 15);
		panel.add(lblNewLabel);
		
		String tag[] = {"Lecture", "Tutorial", "Lab|Practical", "Evaluation"};
		tagName = new JComboBox(tag);
		tagName.setBackground(Color.WHITE);
		tagName.setBounds(245, 66, 281, 30);
		panel.add(tagName);
		
		JLabel lblTagCode = new JLabel("Tag Code");
		lblTagCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTagCode.setBounds(116, 149, 63, 15);
		panel.add(lblTagCode);
		
		tagCode = new JTextField();
		tagCode.setEditable(false);
		tagCode.setBackground(new Color(211, 211, 211));
		tagCode.setColumns(10);
		tagCode.setBounds(245, 142, 281, 30);
		panel.add(tagCode);
		
//		JLabel lblRelativeTag = new JLabel("Related Tag");
//		lblRelativeTag.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		lblRelativeTag.setBounds(116, 169, 78, 15);
//		panel.add(lblRelativeTag);
//		
//		String tags[] = {"Lecture", "Tutorial", "Lab|Practical", "Evaluation"};
//		
//		relatedTag = new JComboBox(tags);
//		relatedTag.setBackground(Color.WHITE);
//		relatedTag.setBounds(245, 162, 281, 30);
//		panel.add(relatedTag);
		
		saveBtn = new JButton("Save");
		saveBtn.setBackground(new Color(102, 153, 255));
		saveBtn.setForeground(Color.WHITE);
		saveBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		saveBtn.setBounds(400, 256, 178, 30);
		saveBtn.addActionListener(this);
		panel.add(saveBtn);
		
		clearBtn = new JButton("Clear");
		clearBtn.setBackground(Color.LIGHT_GRAY);
		clearBtn.setForeground(Color.DARK_GRAY);
		clearBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		clearBtn.setBounds(81, 256, 178, 30);
		clearBtn.addActionListener(this);
		panel.add(clearBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(0, 0, 854, 30);
		frame.getContentPane().add(panel_1);
	}

	public void resetFields() {
		tagName.setSelectedIndex(0);
		tagCode.setText("");
		//relatedTag.setSelectedIndex(0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == saveBtn) {
			if(tagName.getSelectedItem().toString().isEmpty()) {
				
				JOptionPane.showMessageDialog(frame,"Please enter Tag Name and Code","Alert",JOptionPane.WARNING_MESSAGE);
				
			}else {

				String tag_name = tagName.getSelectedItem().toString();
				//String tag_code = tagCode.getText();
				String tag_code = "";
				//String related_tag = relatedTag.getSelectedItem().toString();
				
				if(tag_name.equals("Lecture")) {
					tag_code = "T1001";
				}else if(tag_name.equals("Tutorial")) {
					tag_code = "T1002";
				}else if(tag_name.equals("Lab|Practical")) {
					tag_code = "T1003";
				}else {
					tag_code = "T1004";
				}
				
				System.out.println(tag_code);
				
				int confirm = JOptionPane.showConfirmDialog(frame,"Are you sure you want to submit your data?","Submit Data",JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION) {
					tagCode.setText(tag_code);
					
					Tags tag = new Tags();
					tag.insertTags(tag_name, tag_code);
					resetFields();
				}
				
			}
		}else if(obj == clearBtn) {
			int confirm = JOptionPane.showConfirmDialog(frame,"Are you sure you want to clear data?","Submit Data",JOptionPane.YES_NO_OPTION);
			
			if(confirm == JOptionPane.YES_OPTION) {
				resetFields();
			}
			
		}
	}
}
