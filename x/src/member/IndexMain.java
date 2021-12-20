package member;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class IndexMain extends JFrame {
	JLabel l_id, l_pass, l_intro;
	public static TextField tf_id;
	TextField tf_pass;
	JButton enter, newid;
    JScrollPane scrollPane; 
    ImageIcon icon;
    //getselectedRow // valueAt
    
    public IndexMain() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setTitle("Index");
		setBounds(350, 350, 800, 490);
		setResizable(false);
        icon = new ImageIcon("C:\\Jdwork\\gaia\\sam.jpg");
        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        scrollPane = new JScrollPane(background);
        setContentPane(scrollPane);
        scrollPane.setSize(800,490);
        
        
        JPanel loginpan = new JPanel();
        loginpan.setLayout(new GridLayout(6, 1, 32, 32));
        loginpan.setBackground(new Color(0,0,0,80));
        
        
        l_id = new JLabel("   I         D   ", JLabel.CENTER);
        l_id.setForeground(Color.white);
        l_pass = new JLabel("Password", JLabel.CENTER);
        l_pass.setForeground(Color.white);
        l_intro = new JLabel("  Gaia Study Cafe  ",JLabel.RIGHT);
        l_intro.setFont(new Font("고딕체", Font.BOLD, 25));
        l_intro.setForeground(Color.white);
      
        tf_id = new TextField(10);
        tf_pass = new TextField(10);
        enter = new JButton("로 그 인");
        newid = new JButton("회원 가입");
        
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
        
        tf_pass.setEchoChar('*');
      
        p2.add(l_intro); 	
        p3.add(l_id);		p3.add(tf_id);
        p4.add(l_pass);		p4.add(tf_pass);
        p5.add(enter);		p5.add(newid); 
        
        loginpan.add(p1);			
        loginpan.add(p2);			
        loginpan.add(p3);			
        loginpan.add(p4);
        loginpan.add(p5);
        background.add(loginpan);
        setVisible(true);
        
        
    }
    
    public static void main(String[] args) {
		new IndexDAO();		
	}
}
