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
		JTabbedPane jtp = new JTabbedPane(); // JTabbedPane 객체 생성
		jtp.addTab("상품", new ClientSangPan());
		jtp.addTab("예약", new ClientRes());
		jtp.addTab("내 정보", new ClientMember());
		test.add(jtp); // 각 판넬로부터 객체를 생성하여 추가
		test.setResizable(false); // frame고정
		test.setBounds(350, 350, 1000, 650);
		test.setVisible(true);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new GaiaClientMain();
		}
}