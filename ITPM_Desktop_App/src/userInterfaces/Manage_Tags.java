package userInterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.Tags;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Manage_Tags implements ActionListener{

	private JFrame frame;
	private JTextField text_tagCode;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JComboBox combo_relatedTag;
	private JButton updateBtn, deleteBtn, clearBtn;
	private String selectedTagID;
	private JComboBox tagName;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_Tags window = new Manage_Tags();
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
	public Manage_Tags() {
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
		panel.setBounds(22, 67, 822, 383);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(28, 245, 766, 116);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tag Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(73, 37, 65, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tag Code");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(427, 37, 65, 14);
		panel_1.add(lblNewLabel_1);
		
		text_tagCode = new JTextField();
		text_tagCode.setBackground(new Color(211, 211, 211));
		text_tagCode.setEditable(false);
		text_tagCode.setColumns(10);
		text_tagCode.setBounds(427, 57, 255, 27);
		panel_1.add(text_tagCode);
		
//		JLabel lblNewLabel_2 = new JLabel("Related Tag");
//		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		lblNewLabel_2.setBounds(531, 38, 83, 14);
//		panel_1.add(lblNewLabel_2);
//		combo_relatedTag = new JComboBox(tags);
//		combo_relatedTag.setBackground(Color.WHITE);
//		combo_relatedTag.setBounds(531, 57, 170, 27);
//		panel_1.add(combo_relatedTag);
	
		String tags[] = {"","Lecture", "Tutorial", "Lab|Practical", "Evaluation"};
		tagName = new JComboBox(tags);
		tagName.setBackground(Color.WHITE);
		tagName.setBounds(73, 57, 255, 27);
		panel_1.add(tagName);
		
		JLabel lblNewLabel_3 = new JLabel("Update And Delete Tags");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_3.setBounds(28, 220, 157, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tag Details");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setForeground(Color.DARK_GRAY);
		lblNewLabel_4.setBounds(28, 14, 78, 14);
		panel.add(lblNewLabel_4);
		
		tableModel = new DefaultTableModel(new String[]{"Tag ID", "Tag Name", "Tag Name"},0);
		table = new JTable(tableModel);
		table.setBackground(Color.LIGHT_GRAY);
		table.setBounds(545, 158, -518, -110);
		
		JTableHeader header = table.getTableHeader();
		header.setBackground(new Color(102, 153, 255));
		header.setForeground(Color.white);
		header.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(28,46,631,152);
		tagsTable();
		panel.add(scrollPane);
		
		table.addMouseListener((MouseListener) new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
	    		
				int selectedRecord = table.getSelectedRow();	
				selectedTagID = tableModel.getValueAt(selectedRecord, 0).toString();
				tagName.setSelectedItem(tableModel.getValueAt(selectedRecord, 1).toString());
				text_tagCode.setText(tableModel.getValueAt(selectedRecord, 2).toString());
			}
	    });
		
		updateBtn = new JButton("Update");
		updateBtn.setBackground(SystemColor.activeCaption);
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setBounds(669, 47, 125, 26);
		updateBtn.addActionListener(this);
		panel.add(updateBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBackground(new Color(102, 153, 255));
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.setBounds(669, 99, 125, 26);
		deleteBtn.addActionListener(this);
		panel.add(deleteBtn);
		
		clearBtn = new JButton("Clear");
		clearBtn.setBackground(Color.LIGHT_GRAY);
		clearBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		clearBtn.setForeground(Color.DARK_GRAY);
		clearBtn.setBounds(669, 150, 125, 26);
		clearBtn.addActionListener(this);
		panel.add(clearBtn);
	}
	
	
	public void tagsTable() {
		Tags tag = new Tags();
		ArrayList<Tags> tag_list = tag.listTags();
		DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
		Object[] row = new Object[4];
		
		for(int i=0;i<tag_list.size();i++) {
			row[0] = tag_list.get(i).getId();
			row[1] = tag_list.get(i).getTagName();
			row[2] = tag_list.get(i).getTagCode();
			tableModel.addRow(row);
		}
	}
	
	public void resetFields() {
		selectedTagID = null;
		tagName.setSelectedIndex(0);
		text_tagCode.setText("");
//		combo_relatedTag.setSelectedIndex(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj == updateBtn) {
			String tagname = tagName.getSelectedItem().toString();
			String tagCode = "";
			
			if(tagname.equals("Lecture")) {
				tagCode = "T1001";
			}else if(tagname.equals("Tutorial")) {
				tagCode = "T1002";
			}else if(tagname.equals("Lab|Practical")) {
				tagCode = "T1003";
			}else if(tagname.equals("Evaluation")) {
				tagCode = "T1004";
			}
			
			System.out.println(tagCode);
			
			
			if(selectedTagID == null) {
				JOptionPane.showMessageDialog(frame,"Please select the tag you want to edit","Alert",JOptionPane.WARNING_MESSAGE);
				
			}else if(tagName.getSelectedItem().toString().isEmpty()) {
				JOptionPane.showMessageDialog(frame,"Tag Name cannot be empty","Alert",JOptionPane.WARNING_MESSAGE);
				
			}else {
				int confirm = JOptionPane.showConfirmDialog(frame,"Are you sure you want to edit your data?","Update Record",JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					Tags tags = new Tags();
					tags.updateTags(Integer.parseInt(selectedTagID), tagname, tagCode);
					DefaultTableModel model = (DefaultTableModel)table.getModel();
		            model.setRowCount(0);
					tagsTable();
					resetFields();
					
				}
			}
			
		}else if(obj == deleteBtn) {
			
			if(selectedTagID == null) {
				JOptionPane.showMessageDialog(frame,"Please select the tag you want to delete","Alert",JOptionPane.WARNING_MESSAGE);
			}else {
				int confirm = JOptionPane.showConfirmDialog(frame,"Are you sure you want to permenantly delete your record?","Delete Record",JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					Tags tags = new Tags();
					tags.deleteTags(Integer.parseInt(selectedTagID));
					DefaultTableModel model = (DefaultTableModel)table.getModel();
		            model.setRowCount(0);
					tagsTable();
					resetFields();
				}
			}
			
		}
		else if(obj == clearBtn) {
			resetFields();
		}
	}
	
}
