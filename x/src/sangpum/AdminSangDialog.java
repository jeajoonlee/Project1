package sangpum;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AdminSangDialog extends JDialog implements ActionListener {
	JTextField txtCode, txtType, txtPrice;
	JRadioButton opt1, opt2, seat1, seat2;
	ButtonGroup optgrp = new ButtonGroup();
	ButtonGroup seatgrp = new ButtonGroup();
	JComboBox combo;
	JButton btnAdd, btnCancel;
	
	
	AdminSang main; //���� join_UserJDailog���� ���� ������
	SangDAO dao = new SangDAO();
	
	public AdminSangDialog(AdminSang main, String str) {
		//super();
		setModal(true);
		this.main = main;
		
		setLayout(new GridLayout(8,1));
		add(new JLabel("��ǰ����", SwingConstants.CENTER));		

		JPanel p1 = new JPanel(); //�ڵ��ȣ
		JPanel p2 = new JPanel(); //����/��
		JPanel p3 = new JPanel(); //��
		JPanel p4 = new JPanel(); //�¼�
		JPanel p5 = new JPanel(); //����
		JPanel p6 = new JPanel(); //�̿��
		JPanel p7 = new JPanel(); //��� ��ҹ�ư	
		
		p1.add(new JLabel("�ڵ��ȣ : ", JLabel.RIGHT));
		p2.add(new JLabel("����  /  �� : ", JLabel.RIGHT));
		p3.add(new JLabel("     ��          : ", JLabel.RIGHT));
		p4.add(new JLabel("��        �� : ", JLabel.RIGHT));
		p5.add(new JLabel("��        �� : ", JLabel.RIGHT));
		p6.add(new JLabel("��  ��  �� : ", JLabel.RIGHT));		
		
		p1.add(txtCode = new JTextField(13));			add(p1);
		p2.add(txtType = new JTextField(13));			add(p2);
		
		opt1 = new JRadioButton("X", true);
		opt2 = new JRadioButton("O", false);
		optgrp.add(opt1);		optgrp.add(opt2);
		p3.add(opt1);			p3.add(opt2);			add(p3);
		
		seat1 = new JRadioButton("������", true);
		seat2 = new JRadioButton("������", false);
		seatgrp.add(seat1);		seatgrp.add(seat2);
		p4.add(seat1);			p4.add(seat2);			add(p4);
		
	
		p5.add(txtPrice = new JTextField(13));			add(p5);
		
		String[] vou = {"------------------    ", "1��", "1����", "2����", "1����" };
		combo = new JComboBox(vou);	
		p6.add(combo);									add(p6);		
		
		p7.add(btnAdd = new JButton("����"));
		p7.add(btnCancel = new JButton("���"));			add(p7);
		
		
		
		if (str.equals("����")) {
			System.out.println("������ư Ŭ��");
			
			int row = main.jt.getSelectedRow();
			System.out.println("���� �� : " + row);
			
			
			txtCode.setText(main.jt.getValueAt(row, 0).toString()); //������ �ڵ��ȣ
			txtType.setText(main.jt.getValueAt(row, 1).toString());
			String opt = main.jt.getValueAt(row, 2).toString();
			String se = main.jt.getValueAt(row, 3).toString();
			txtPrice.setText(main.jt.getValueAt(row, 4).toString());
			String vo = main.jt.getValueAt(row, 5).toString();
			
			txtCode.setEditable(false);
			
			
			
			
			if(opt.equals("X")) {
				opt1.setSelected(true);
				opt2.setSelected(false);
			} else {
				opt1.setSelected(false);
				opt2.setSelected(true);
			}
			if(se.equals("������")) {
				seat1.setSelected(true);
				seat2.setSelected(false);
			} else {
				seat1.setSelected(false);
				seat2.setSelected(true);
			}
			if(vo.contentEquals("1��")) {
				combo.setSelectedIndex(1);
			} else if(vo.contentEquals("1����")) {
				combo.setSelectedIndex(2);
			} else if(vo.contentEquals("2����")) {
				combo.setSelectedIndex(3);
			} else {
				combo.setSelectedIndex(4);
			}
		} 
		
		btnAdd.addActionListener(this);
		btnCancel.addActionListener(this);
		
		setTitle("Gaia Study Cafe");
		setBounds(800, 250, 350, 500);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnAdd) {
			String c = txtCode.getText();
			System.out.println(c);
			if (dao.SangpumUpdate(this, c ) > 0) {
				AdminSang.messageBox(this, "��ǰ ������ �Ϸ�Ǿ����ϴ�.");
				dispose();
				dao.sangpumSelectAll(main.dt); 
				if (main.dt.getRowCount() > 0) main.jt.setRowSelectionInterval(0, 0); //������ ����
			} else {
				AdminSang.messageBox(this, "���� Fail!");
//				txtType.setText("");
//				txtPrice.setText("");
				return;
			}
		} else if ( obj == btnCancel ) {
			AdminSang.messageBox(this, "��ǰ ������ ��ҵǾ����ϴ�.");
			dispose();		
		}		
	}	
}