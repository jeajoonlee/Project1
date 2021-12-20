package main;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import member.AdminMember;
import reservation.AdminRes;
import sangpum.AdminSang;

public class GaiaAdminMain extends JFrame {
	//public static JFrame test = new JFrame();
	public GaiaAdminMain() { 
		
		setTitle("study cafe - gaia");
		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab("��", new AdminMember() );
		jtp.addTab("����", new AdminRes());
		jtp.addTab("��ǰ", new AdminSang());
		add(jtp);
		setResizable(false);
		setBounds(350, 150, 1000, 650);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new GaiaAdminMain();
		} 
}
