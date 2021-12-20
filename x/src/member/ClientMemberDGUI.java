package member;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientMemberDGUI extends JDialog implements ActionListener{
	
	JPanel bckground = new JPanel();
	JPanel adminmain = new JPanel(new GridLayout(10, 1));

	JLabel label_Intro = new JLabel("------정   보------", JLabel.CENTER);
	JLabel label_ID = new JLabel("        아  이  디        ", JLabel.CENTER); 
	JLabel label_Name = new JLabel("    이         름        ", JLabel.CENTER);
	JLabel label_Pass = new JLabel("      비 밀 번 호      ", JLabel.CENTER);
	JLabel label_Tel = new JLabel("      전 화 번 호      ", JLabel.CENTER);
	JLabel label_Email = new JLabel("       이  메  일        ", JLabel.CENTER);
	
	public static JTextField ctext_Id = new JTextField(10);
	JTextField ctext_Pass = new JTextField(10);
	JTextField ctext_Name = new JTextField(10);
	JTextField ctext_Tel = new JTextField(10);
	JTextField ctext_Email = new JTextField(10);
	
	JButton idCkBtn = new JButton("ID Check");
	JButton ok = new JButton("확인");
	JButton cancel = new JButton("취소");
	
	public static String userid;
	String mid;
	
	ClientMember me;
	MemberDTO mdata;
	MemberDAO dao = new MemberDAO();
	
		public ClientMemberDGUI(ClientMember me, String index) {
		setTitle("다이어로그");
		this.me=me;
		if(index.equals("가입")) {
			ok = new JButton(index);
		} else {
			ok = new JButton("수정");
			
			// text 박스에 선택된 레코드의 정보 넣기
			int row=me.jt.getSelectedRow();
			ctext_Id.setText(me.jt.getValueAt(row, 0).toString());
			ctext_Pass.setText(me.jt.getValueAt(row, 1).toString());
			ctext_Name.setText(me.jt.getValueAt(row, 2).toString());
			ctext_Tel.setText(me.jt.getValueAt(row, 3).toString());
			ctext_Email.setText(me.jt.getValueAt(row, 4).toString());
			
			ctext_Id.setEditable(false);
			idCkBtn.setEnabled(false);
//			System.out.println(ctext_Id.getText());
//			userid = me.jt.getValueAt(row, 0).toString();
//			System.out.println("userid" + userid);
		}
		
		label_Intro.setFont(new Font("고딕체", Font.BOLD, 20));
		
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(255, 0, 0, 0));
        JPanel p2 = new JPanel();
        p2.setBackground(new Color(255, 0, 0, 0));
        JPanel p3 = new JPanel();
        p3.setBackground(new Color(255, 0, 0, 0));
        JPanel p4 = new JPanel();
        p4.setBackground(new Color(255, 0, 0, 0));
        JPanel p5 = new JPanel();
        p5.setBackground(new Color(255, 0, 0, 0));
        JPanel p6 = new JPanel();
        p6.setBackground(new Color(255, 0, 0, 0));
        JPanel p7 = new JPanel();
        p7.setBackground(new Color(255, 0, 0, 0));
        JPanel p8 = new JPanel();
        p8.setBackground(new Color(255, 0, 0, 0));
        JPanel p9 = new JPanel();
        p9.setBackground(new Color(255, 0, 0, 0));
        JPanel p10 = new JPanel();
        p10.setBackground(new Color(255, 0, 0, 0));
        
		add(bckground);
		p1.add(label_Intro);		
		p2.add(label_ID);			p2.add(ctext_Id);
        p3.add(idCkBtn);			
        p4.add(label_Pass);			p4.add(ctext_Pass);
        p5.add(label_Name);			p5.add(ctext_Name);
        p6.add(label_Tel);			p6.add(ctext_Tel);
        p7.add(label_Email);		p7.add(ctext_Email);
        p8.add(ok);					p8.add(cancel);
        
        bckground.add(adminmain);
        	
        adminmain.add(p9);
        adminmain.add(p1);			
        System.out.println("");
        adminmain.add(p2);			
        adminmain.add(p3);
        adminmain.add(p4);			
        adminmain.add(p5);			
        adminmain.add(p6);
        adminmain.add(p7);			
        adminmain.add(p8);
           
      	setSize(280, 450);
		adminmain.setBackground(new Color(250,240,230));
		bckground.setBackground(new Color(250,240,230));
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		ok.addActionListener(this);
		cancel.addActionListener(this);
		idCkBtn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String btnLabel=e.getActionCommand();
		
		if(btnLabel.equals("가입")) {
			if(dao.ClientuserListInsert(this) > 0) {
				messageBox(this, ctext_Name.getText() + "님 가입됨");
				dispose();
				
				// 모든 레코드를 가져와서 DefaultTableModel 에 넣기
				dao.userSelectAll(me.dt);
				
				if(me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0); // 첫 번재 행 선택
			} else {
				messageBox(this, "가입안됨");
			} 
		} else if(btnLabel.equals("수정")) {
			if(dao.clientuserUpdate(this) > 0) {
				messageBox(this, "수정 완료");
				dispose();
				dao.userSelectOne(me.dt, userid);
				if(me.jt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);
			} else {
				messageBox(this, "수정 Fail!");
			} 
		}else if(btnLabel.equals("취소")) {
			dispose();
		} else if(btnLabel.equals("ID Check")) {
			mid = ctext_Id.getText().trim();
			if(mid.equals("")) {
				JOptionPane.showMessageDialog(this, "ID 입력");
				ctext_Id.requestFocus();
				return;
			}
			if(dao.getIdByCheck(ctext_Id.getText())) {
				messageBox(this, ctext_Id.getText() + "는 사용가능");
				} else {
					messageBox(this, ctext_Id.getText() + "는 중복!");
					ctext_Id.setText("");
					ctext_Id.requestFocus();
				}
			}
		}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component)obj, message);
	}
}