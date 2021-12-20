package member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClientMember1 extends JPanel {
	IndexMain index = new IndexMain();
	
	JPanel north = new JPanel();
	JPanel center = new JPanel(new GridLayout(6, 1));
	JPanel south = new JPanel(new GridLayout(1, 3));
	
	JLabel clintro1 = new JLabel();
	JLabel clintro2 = new JLabel("님 회원정보", JLabel.CENTER);
	JLabel clid = new JLabel("아이디", JLabel.CENTER);
	JLabel clpw = new JLabel("비밀번호", JLabel.CENTER);
	JLabel clname = new JLabel("이름", JLabel.CENTER);
	JLabel cltel = new JLabel("전화번호", JLabel.CENTER);
	JLabel clemail = new JLabel("이메일", JLabel.CENTER);
	
	JButton insert = new JButton("가입");
	JButton update = new JButton("수정");
	JButton delete = new JButton("삭제");
	
	TextField cltext_id = new TextField(10);
	TextField cltext_pw = new TextField(10);
	TextField cltext_name = new TextField(10);
	TextField cltext_tel = new TextField(10);
	TextField cltext_email = new TextField(10);
	
	public ClientMember1() {

		setLayout(new BorderLayout());
		
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
		JPanel plus1 = new JPanel();
		plus1.setBackground(new Color(255, 0, 0, 0));
		
		p1.add(clintro1);		p1.add(clintro2);
		p2.add(clid);			p2.add(cltext_id);
		p3.add(clpw);			p3.add(cltext_pw);
		p4.add(clname);			p4.add(cltext_name);
		p5.add(cltel);			p5.add(cltext_tel);
		p6.add(clemail);		p6.add(cltext_email);
		
		north.add(p1);			
		center.add(plus1);		center.add(p2);			center.add(p3);
		center.add(p4);			center.add(p5);			center.add(p6);
		
		south.add(insert);		south.add(update);		south.add(delete);
		
		add(north);				add(center);			add(south);
		
		add(north, "North");	add(center, "Center");	add(south, "South");
		
		clintro1.setForeground(Color.white);
		clintro2.setForeground(Color.white);
		north.setBackground(Color.DARK_GRAY);
		center.setBackground(new Color(250,240,230));
		south.setBackground(new Color(250,240,230));
				
		insert.setEnabled(false);
		delete.setEnabled(false);
		

		
		cltext_id.setEditable(false);
		cltext_pw.setEditable(false);
		cltext_name.setEditable(false);
		cltext_tel.setEditable(false);
		cltext_email.setEditable(false);
		
		setSize(500, 400);
		setBackground(new Color(250,240,230));
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new ClientMember1();
	}
}
