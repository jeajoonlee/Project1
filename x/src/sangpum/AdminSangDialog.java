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
	
	
	AdminSang main; //여기 join_UserJDailog에서 보고 따라함
	SangDAO dao = new SangDAO();
	
	public AdminSangDialog(AdminSang main, String str) {
		//super();
		setModal(true);
		this.main = main;
		
		setLayout(new GridLayout(8,1));
		add(new JLabel("상품수정", SwingConstants.CENTER));		

		JPanel p1 = new JPanel(); //코드번호
		JPanel p2 = new JPanel(); //개인/룸
		JPanel p3 = new JPanel(); //빔
		JPanel p4 = new JPanel(); //좌석
		JPanel p5 = new JPanel(); //가격
		JPanel p6 = new JPanel(); //이용권
		JPanel p7 = new JPanel(); //등록 취소버튼	
		
		p1.add(new JLabel("코드번호 : ", JLabel.RIGHT));
		p2.add(new JLabel("개인  /  룸 : ", JLabel.RIGHT));
		p3.add(new JLabel("     빔          : ", JLabel.RIGHT));
		p4.add(new JLabel("좌        석 : ", JLabel.RIGHT));
		p5.add(new JLabel("가        격 : ", JLabel.RIGHT));
		p6.add(new JLabel("이  용  권 : ", JLabel.RIGHT));		
		
		p1.add(txtCode = new JTextField(13));			add(p1);
		p2.add(txtType = new JTextField(13));			add(p2);
		
		opt1 = new JRadioButton("X", true);
		opt2 = new JRadioButton("O", false);
		optgrp.add(opt1);		optgrp.add(opt2);
		p3.add(opt1);			p3.add(opt2);			add(p3);
		
		seat1 = new JRadioButton("자유석", true);
		seat2 = new JRadioButton("지정석", false);
		seatgrp.add(seat1);		seatgrp.add(seat2);
		p4.add(seat1);			p4.add(seat2);			add(p4);
		
	
		p5.add(txtPrice = new JTextField(13));			add(p5);
		
		String[] vou = {"------------------    ", "1일", "1주일", "2주일", "1개월" };
		combo = new JComboBox(vou);	
		p6.add(combo);									add(p6);		
		
		p7.add(btnAdd = new JButton("수정"));
		p7.add(btnCancel = new JButton("취소"));			add(p7);
		
		
		
		if (str.equals("수정")) {
			System.out.println("수정버튼 클릭");
			
			int row = main.jt.getSelectedRow();
			System.out.println("선택 행 : " + row);
			
			
			txtCode.setText(main.jt.getValueAt(row, 0).toString()); //기존의 코드번호
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
			if(se.equals("자유석")) {
				seat1.setSelected(true);
				seat2.setSelected(false);
			} else {
				seat1.setSelected(false);
				seat2.setSelected(true);
			}
			if(vo.contentEquals("1일")) {
				combo.setSelectedIndex(1);
			} else if(vo.contentEquals("1주일")) {
				combo.setSelectedIndex(2);
			} else if(vo.contentEquals("2주일")) {
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
				AdminSang.messageBox(this, "상품 수정이 완료되었습니다.");
				dispose();
				dao.sangpumSelectAll(main.dt); 
				if (main.dt.getRowCount() > 0) main.jt.setRowSelectionInterval(0, 0); //무조건 선택
			} else {
				AdminSang.messageBox(this, "수정 Fail!");
//				txtType.setText("");
//				txtPrice.setText("");
				return;
			}
		} else if ( obj == btnCancel ) {
			AdminSang.messageBox(this, "상품 수정이 취소되었습니다.");
			dispose();		
		}		
	}	
}