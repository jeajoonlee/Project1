package member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import main.GaiaClientMain;
import main.GaiaClientMain;

public class ClientMember extends Panel implements ActionListener {
	JPanel south = new JPanel();
	JButton insert, update, delete, logout;
	GaiaClientMain clmain;
	IndexDAO indexdao;
	
	String[] name = { "아이디", "비밀번호", "이름", "전화번호", "이메일", "가입날짜" };

	DefaultTableModel dt = new DefaultTableModel(name, 0) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	JTable jt = new JTable(dt);
	JScrollPane north = new JScrollPane(jt);
	JPanel center = new JPanel();
	
	String userid = IndexMain.tf_id.getText();
	
	MemberDAO dao = new MemberDAO();
	JLabel empty =  new JLabel("           ");
	JLabel empty1 =  new JLabel("        ");
	//JLabel empty1 =  new JLabel("             ");
	
	public ClientMember() {
		insert = new JButton("가입");
		insert.setEnabled(false);
		update = new JButton("수정");
		delete = new JButton("삭제");
		logout = new JButton("로그아웃");
		
		setLayout(new BorderLayout());
		
		JPanel center = new JPanel(new GridLayout(6, 1));
		
		JLabel clid = new JLabel("아이디", JLabel.CENTER);
		JLabel clpw = new JLabel("비밀번호", JLabel.CENTER);
		JLabel clname = new JLabel("이름", JLabel.CENTER);
		JLabel cltel = new JLabel("전화번호", JLabel.CENTER);
		JLabel clemail = new JLabel("이메일", JLabel.CENTER);
				
		TextField cltext_id = new TextField(10);
		TextField cltext_pw = new TextField(10);
		TextField cltext_name = new TextField(10);
		TextField cltext_tel = new TextField(10);
		TextField cltext_email = new TextField(10);
		
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
		JPanel plus1 = new JPanel();
		plus1.setBackground(new Color(255, 0, 0, 0));
		
		p2.add(clid);			p2.add(cltext_id);
		p3.add(clpw);			p3.add(cltext_pw);
		p4.add(clname);			p4.add(cltext_name);
		p5.add(cltel);			p5.add(cltext_tel);
		p6.add(clemail);		p6.add(cltext_email);
				
		center.add(plus1);		center.add(p2);			center.add(p3);
		center.add(p4);			center.add(p5);			center.add(p6);
		
		north.setPreferredSize(new Dimension(800,100));
		cltext_id.setEditable(false);
		cltext_pw.setEditable(false);
		cltext_name.setEditable(false);
		cltext_tel.setEditable(false);
		cltext_email.setEditable(false);
		
		south.setBackground(Color.white);
		south.add(insert);		south.add(update);		south.add(delete);		south.add(logout);
		
		add(north, "North");
		add(center, "Center");
		add(south, "South");
		JTableHeader jtHeader = jt.getTableHeader();
  	    Font headerFont = new Font("고딕체", Font.BOLD, 13);
	    jtHeader.setFont(headerFont);
		jt.setRowHeight(24);
		jt.setFont(new Font("고딕체", Font.PLAIN, 13));
		
		jt.getTableHeader().setReorderingAllowed(false);
		jt.getTableHeader().setResizingAllowed(false);
		setSize(500, 400);
		setVisible(true);

		insert.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		logout.addActionListener(this);

		// 모든 레코드를 검색하여 DefaultTableModel 에 올리기
		dao.userSelectOne(dt, userid);

		// 첫 번째 행 선택
		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);
		
		int row = jt.getSelectedRow();
		cltext_id.setText(jt.getValueAt(row, 0).toString());
		cltext_pw.setText(jt.getValueAt(row, 1).toString());
		cltext_name.setText(jt.getValueAt(row, 2).toString());
		cltext_tel.setText(jt.getValueAt(row, 3).toString());
		cltext_email.setText(jt.getValueAt(row, 4).toString());
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {
			new ClientMemberDGUI(this, "가입");
		} else if (e.getSource() == update) {
			new ClientMemberDGUI(this, "수정");
		} else if (e.getSource() == delete) {
			// 현재 JTable의 선택된 행과 열의 값 얻어오기
			int row = jt.getSelectedRow();
			System.out.println("선택 행 : " + row);
			Object obj = jt.getValueAt(row, 0);
			System.out.println("값 : " + obj);

			if (dao.userDelete(obj.toString()) > 0) {
				AdminMemberDGUI.messageBox(this, "레코드 삭제");
				
				// 삭제된 정보 날리기
				dao.userSelectOne(dt, userid);
				
				 //GaiaClientMain clmain = new GaiaClientMain();
				// clmain.setVisible(false);
				 //ixmain.setVisible(true);
			} else {
				AdminMemberDGUI.messageBox(this, "레코드 삭제되지 않음");
			}
		} else if (e.getSource() == logout) {
			 //GaiaClientMain clmain = new GaiaClientMain();
			 indexdao = new IndexDAO();
			 GaiaClientMain.test.setVisible(false);
			 indexdao.setVisible(true);
		}
	} 

	public static void main(String[] args) {
		new ClientMember();
	}

}