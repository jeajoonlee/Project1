package main;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import member.ClientMember;
import reservation.ClientRes;
import sangpum.ClientSangPan;


public class GaiaClientMain extends JFrame {
	public static JFrame test = new JFrame();
	public GaiaClientMain() {
		
		setTitle("study cafe - gaia");
		JTabbedPane jtp = new JTabbedPane(); // JTabbedPane ��ü ����
		jtp.addTab("��ǰ", new ClientSangPan());
		jtp.addTab("����", new ClientRes());
		jtp.addTab("�� ����", new ClientMember());
		test.add(jtp); // �� �ǳڷκ��� ��ü�� �����Ͽ� �߰�
		test.setResizable(false); // frame����
		test.setBounds(350, 350, 1000, 650);
		test.setVisible(true);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new GaiaClientMain();
		}
}