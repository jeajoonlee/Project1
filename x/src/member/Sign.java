package member;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Sign extends JFrame {
	JLabel sl_intro, sl_id, sl_pass1, sl_pass2, sl_name, sl_tel, sl_email, sl_empty1, sl_empty2, sl_empty3, sl_empty4, sl_empty5, sl_empty6, sl_empty7, sl_empty8, sl_empty9, sl_empty10,
		sl_empty11, sl_empty12, sl_empty13, sl_empty14, sl_empty15, sl_empty16;
	TextField stf_id, stf_pass1, stf_pass2, stf_name, stf_tel, stf_email;
	JButton idCheck, submit, cancel;

    JScrollPane scrollPane;
    
    public Sign() {
	//	addWindowListener(new WindowAdapter() {
	//	public void windowClosing(WindowEvent e) {
	//			System.exit(0);
	//		}
	//	});
    	
    	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    	
		setTitle("회원가입");
		setBounds(350, 350, 285, 390); 
		setResizable(false);
		
        JPanel bckground = new JPanel();
		JPanel signpan = new JPanel();
        signpan.setLayout(new GridLayout(9, 3));
        signpan.setBackground(new Color(250,240,230));
        bckground.setBackground(Color.black);
        sl_intro = new JLabel("회원가입", JLabel.CENTER);
        sl_intro.setFont(new Font("고딕체", Font.BOLD, 15));
        sl_id = new JLabel("        아  이  디        ", JLabel.CENTER);
        sl_pass1 = new JLabel("      비 밀 번 호      ", JLabel.CENTER);
        sl_pass2 = new JLabel("      비 번 확 인      ", JLabel.CENTER);
        sl_name = new JLabel("       이         름       ", JLabel.CENTER);
        sl_tel = new JLabel("      전 화 번 호      ", JLabel.CENTER);
        sl_email = new JLabel("       이  메  일        ", JLabel.CENTER);
        
        sl_empty1 = new JLabel(" ");		sl_empty2 = new JLabel(" ");
        sl_empty3 = new JLabel(" ");		sl_empty4 = new JLabel(" ");
        sl_empty5 = new JLabel(" ");		sl_empty6 = new JLabel(" ");
        sl_empty7 = new JLabel(" ");		sl_empty8 = new JLabel(" ");
        sl_empty9 = new JLabel(" ");		sl_empty10 = new JLabel("        ");
        sl_empty11 = new JLabel(" ");		sl_empty12 = new JLabel(" ");
      	sl_empty13 = new JLabel(" ");		sl_empty14 = new JLabel(" ");
      	sl_empty15 = new JLabel(" ");		sl_empty16 = new JLabel(" ");
        
        stf_id = new TextField(15);
        stf_pass1 = new TextField(15);
        stf_pass2 = new TextField(15);
        stf_name = new TextField(15);
        stf_tel = new TextField(15);{
        	stf_tel.addKeyListener(new KeyAdapter() {
    			public void keyTyped(KeyEvent ke) {
    				TextField src = (TextField) ke.getSource();
    				if(src.getText().length()>=11)ke.consume();
    			}
    		});
    	}
        stf_email = new TextField(15);
        idCheck = new JButton("ID 중복 확인");
        idCheck.setPreferredSize(new Dimension(110, 20));
        submit = new JButton("등   록");
        cancel = new JButton("취   소");
        
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
        stf_pass1.setEchoChar('*');
		stf_pass2.setEchoChar('*');
        add(bckground);
        p1.add(sl_intro);       p2.add(sl_id);     		p2.add(stf_id);			p2.add(sl_empty11);
        p3.add(idCheck);        p4.add(sl_pass1);		p4.add(stf_pass1);		p4.add(sl_empty12);		
        p5.add(sl_pass2);		p5.add(stf_pass2);		p5.add(sl_empty13);		p6.add(sl_name);
        p6.add(stf_name);		p6.add(sl_empty14);		p7.add(sl_tel);         p7.add(stf_tel);
        p7.add(sl_empty15);		p8.add(sl_email);		p8.add(stf_email);		p2.add(sl_empty16);    	
        p9.add(submit);	        p9.add(sl_empty10);		p9.add(cancel);
        
        signpan.add(p1);		signpan.add(p2);		signpan.add(p3);
        signpan.add(p4);		signpan.add(p5);		signpan.add(p6);
        signpan.add(p7);		signpan.add(p8);		signpan.add(p9);
        
        bckground.add(signpan);
    }

    public static void main(String[] args) {
		new Sign();
			}
}
