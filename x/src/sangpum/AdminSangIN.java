package sangpum;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class AdminSangIN extends JFrame implements ActionListener{
	
	  
	JPanel pan, p0, p1, p2, p3, p4, p5, p6 ,p7,p33,p44;
	JComboBox cbb1, cbb2;
	JTextField txtPrice, txtDate,txtPrice1,txtPrice2,codeN,type;
	JRadioButton rb1, rb2 ,rb11,rb22,rb3,rb4,seat;
	ButtonGroup group = new ButtonGroup();
	ButtonGroup group1 = new ButtonGroup();
	JButton rev, res, cal,btn1;
    
	String[] combo1 = { "1일", "1주", "2주", "한달" };
	String[] combo2 = { "------------------- ", "자유석", "지정석" };
	Connection con;
	Statement stmt;
	PreparedStatement pstmtInsert;		  
	public String driver = "oracle.jdbc.driver.OracleDriver";
	public String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public String user = "gaia"; private String pwd = "1234"; 
	public String sqlInsert = "insert into sangpum values(?,?,?,?,?,?)";
		
	AdminSangIN() {			
		super();
		pan = new JPanel(new GridLayout(8, 0));
		p0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
				
		pan.add(p0);
		pan.add(p1);
		pan.add(p2);
		pan.add(p3);
		pan.add(p4);
		pan.add(p5);
		p0.add(new JLabel("상품추가"));
		p1.add(new JLabel("코드번호"));
		p2.add(new JLabel("   개인/룸 "));
		p3.add(new JLabel("좌   석"));
		p4.add(new JLabel("  빔     "));
		p5.add(new JLabel("         가  격"));
		p7.add(new JLabel("이 용 권2"));
		
		codeN = new JTextField(10);//코드
		codeN.setHorizontalAlignment(JTextField.RIGHT);	
		type = new JTextField(10);
		type.setHorizontalAlignment(JTextField.RIGHT);
				
		rb1 = new JRadioButton("O",true);
		rb2 = new JRadioButton("X",false);
		rb3 = new JRadioButton("지정석",true);
		rb4 = new JRadioButton("자유석",false);
		
		group.add(rb1);
		group.add(rb2);
		group1.add(rb3);
		group1.add(rb4);
		p1.add(codeN);
		p2.add(type);
		p3.add(rb3);
		p3.add(rb4);
		p4.add(rb1);
		p4.add(rb2);		
		p5.add(txtPrice = new JTextField(10));		
		p7.add(cbb1 = new JComboBox(combo1));
		pan.add(p7);		
		btn1 = new JButton("확인");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "이 가격이 맞습니까?");
			}			
	   });				
		p5.add(btn1);
		rev = new JButton("등록2");
		rev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				setInsert();
				if (obj == res) {
					init();
				} else if (obj == rev) {					
					JOptionPane.showMessageDialog(null, "예약 완료");
				}
			}
		   });										
		res = new JButton("취소");
		res.addActionListener(this);
		p6.add(rev);
		p6.add(res);
		pan.add(p6);
		getContentPane().add(pan, BorderLayout.CENTER);	
		setResizable(false);
		setSize(350, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dbConnect();//이게 요점
	}
	public void init() {			
		rb1.setSelected(true);
		rb2.setSelected(false);
		txtPrice.setText("");
		type.setText("");	
		codeN.setText(" ");
	}
	@Override 
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();	
		if (obj == res) {
			init();
		} else if (obj == rev) {			
		}	
	}
	public void setInsert() {
		try {  
			String code=codeN.getText().toString();	
			String mType =  type.getText().toString();				
			String mOpt = rb1.getText().toString();
			String mSeat =  rb3.getText().toString();
			String mPrice =  txtPrice.getText();
			String mVoucher =  cbb1.getSelectedItem().toString();			
			if(mSeat=="지정석") {
				mSeat="지정석";
			}else {
				mSeat="자유석";
			}					
			if(mOpt=="O") {
				mOpt="O";
			}else {
				mOpt="X";
			}		    	
			pstmtInsert.setString(1,code);
			pstmtInsert.setString(2,mType);
			pstmtInsert.setString(3,mOpt);
			pstmtInsert.setString(4,mSeat);
			pstmtInsert.setString(5,mPrice);
			pstmtInsert.setString(6,mVoucher);
			pstmtInsert.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}		
	public void dbConnect(){
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pwd);
			pstmtInsert = con.prepareStatement(sqlInsert);
		}catch(Exception e ) {
			e.printStackTrace();
		}         	
	}				
	public static void main(String[] args) {
		new AdminSangIN();
	}		 
}