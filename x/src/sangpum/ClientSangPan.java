package sangpum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import member.ClientMemberDGUI;
import member.IndexMain;
import member.MemberDAO;
import member.MemberDTO;
import reservation.ResDAO;;

public class ClientSangPan extends JPanel {
	 
	public static void main(String[] args) {
		new ClientSangPan();
	}
	public ClientSangPan() {
		initialize();
	}
	public void initialize() {	 
		ImageIcon group= new ImageIcon("group.jpg");
		ImageIcon person= new ImageIcon("person.jpg");
		setBounds(100, 100, 1000, 650);
//		setLocationRelativeTo(null);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		Image img = group.getImage();
		Image img1 = person.getImage();
		Image changeimg =img.getScaledInstance(475, 354, Image.SCALE_SMOOTH );
		Image changeimg1 =img1.getScaledInstance(475, 354, Image.SCALE_SMOOTH );
		ImageIcon ChangeIcon = new ImageIcon(changeimg);
		ImageIcon ChangeIcon1 = new ImageIcon(changeimg1);
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.GRAY);
		mainPanel.setBounds(0, 0, 1000, 650);
		add(mainPanel);
		mainPanel.setLayout(null);	
		JButton btnNewButton = new JButton(ChangeIcon1);
		btnNewButton.setBounds(12, 80, 464, 354);
		mainPanel.add(btnNewButton);
		JButton btnNewButton_1 = new JButton(ChangeIcon);
		btnNewButton_1.setBounds(494, 80, 477, 354);
		mainPanel.add(btnNewButton_1);	
		JButton btnNewButton_2 = new JButton("개인실");
		btnNewButton_2.setBounds(77, 444, 329, 50);
		btnNewButton_2.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		btnNewButton_2.setBackground(Color.ORANGE);
		mainPanel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  //여기가 프레임 전환 역할
                Main M = new Main();
				
				M.setVisible(true);		 
			}
		});	
		JButton btnNewButton_3 = new JButton("단체실");
		btnNewButton_3.setBounds(559, 444, 361, 50);
		btnNewButton_3.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		btnNewButton_3.setBackground(Color.ORANGE);
		mainPanel.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  //여기가 프레임 전환 역할
                Sub S = new Sub();
				
				S.setVisible(true);
			}
		});			
			
	     
        JLabel lblNewLabel = new JLabel("★가이아 스터디 카페★");
        lblNewLabel.setBackground(Color.BLACK);
        lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        lblNewLabel.setBounds(310, 10, 477, 50);
        mainPanel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("★환영합니다★");
        lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        lblNewLabel_1.setBackground(Color.BLACK);
        lblNewLabel_1.setBounds(376, 526, 227, 50);
        mainPanel.add(lblNewLabel_1);
	}	
}
class Main extends JFrame implements ActionListener, ItemListener {
	 
	   JPanel pan, p0, p1, p2, p3, p4, p5, p6;
		JComboBox cbb1, cbb2;
		JTextField txtPrice, txtDate;
		JRadioButton rb1, rb2;
		ButtonGroup group = new ButtonGroup();
		JButton rev, res, cal, btnNewButton;
		JFrame frame;
		String[] combo1 = { "------------------    ", "1일", "1주", "2주", "한달" };
		String[] combo2 = { "------------------- ", "자유석", "지정석" };
		

	Main() {
		super("개인실");
		pan = new JPanel(new GridLayout(8, 0));
		p0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan.add(p0);
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1.add(new JLabel("이 용 권"));
		p1.add(cbb1 = new JComboBox(combo1));
		pan.add(p1);
		
		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p2.add(new JLabel("가     격 "));
		txtPrice = new JTextField(10);
		txtPrice.setHorizontalAlignment(JTextField.RIGHT);
		p2.add(txtPrice);
		pan.add(p2);
		
		p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p6.add(new JLabel("좌석종류"));
		p6.add(cbb2 = new JComboBox(combo2));
		cbb2.setEnabled(false);
		pan.add(p6);

		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p3.add(new JLabel("칸 막 이     "));
		rb1 = new JRadioButton("Yes", true);
		rb2 = new JRadioButton("No", false);
		group.add(rb1);
		group.add(rb2);
		p3.add(rb1);
		p3.add(rb2);
		pan.add(p3);
        
		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p4.add(new JLabel("이 용 일"));
		p4.add(txtDate = new JTextField(10));			
		pan.add(p4);
		
		btnNewButton = new JButton("확인");
		p4.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"입력하신 날짜가 맞습니까?");
			}
		   });
		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		rev = new JButton("예             약");
		rev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  //여기가 프레임 전환 역할
				Object obj = e.getSource();
				MemberDAO mdao = new MemberDAO();
//				IndexMain im = new IndexMain();
				String mVoucher =  cbb1.getSelectedItem().toString();
				String mType =  cbb2.getSelectedItem().toString();
				String Can =  rb1.getText().toString();
				
				String mDate = txtDate.getText();
				SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy/mm/dd");
		        
		        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
		       
		        java.util.Date tempDate = null;
		        
		        try {
		            tempDate = beforeFormat.parse(mDate);
		        } catch (ParseException pe) {
		            pe.printStackTrace();
		        }
		        
		        String transDate = afterFormat.format(tempDate);
		        
		        Date date = Date.valueOf(transDate);
				String code="";
				//String mId = 	AdminMemberDGUI user user.text_Id.getText()
				String mId = ClientMemberDGUI.ctext_Id.getText();
				System.out.println(mId);
				if(rb1.isSelected()) {			
				code+="c";
				}else {
					code+="p";
				}			 		
				if(mType=="자유석") {
					code+="Fr";
				}else if(mType=="지정석") {
					code+="Fi";
				}else { 
				JOptionPane.showMessageDialog(null, "좌석을 선택하세요");
				return;}
					    
				if(mVoucher=="1일") {
					code+="1d";
				}else if (mVoucher=="1주") {
					code+="1w";
				}else if (mVoucher=="2주") {
		        	code+="2w";
			    }else if (mVoucher=="한달") {
		        	code+="1m";
		        } else {
		        	JOptionPane.showMessageDialog(null, "이용권을 선택하세요");
				return;	}		
				System.out.println(code);
				System.out.println(mId);
				System.out.println(date);
				
				ResDAO resdao = new ResDAO();
				resdao.setInsert(code, mId, date);
			
				if (obj == rev) {
					
					add();										
					setVisible(false);			
				}}
		});
					
		res = new JButton("취             소");
		res.addActionListener(this);                   	
		p5.add(rev);
		p5.add(res);
		pan.add(p5);
		add(pan, BorderLayout.CENTER);
		cbb1.addItemListener(this); 
		setResizable(false);
		setSize(350, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		
	}
	public void init() {
		cbb1.setSelectedIndex(0);
		txtPrice.setText("");
		cbb2.setSelectedIndex(0);
		rb1.setSelected(true);
		rb2.setSelected(false);
		txtDate.setText("");
		txtPrice.setText("");	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if (obj == res) {
			init();					
		}
	}
		private void add() {
			try {		
				String mVoucher =  cbb1.getSelectedItem().toString();
				String mSeat =  cbb2.getSelectedItem().toString();
				String mDate = txtDate.getText();
				
				if(mVoucher == "------------------    ") {
					JOptionPane.showMessageDialog(null, "이용권을 선택하세요");
					return;
				} 
				if(mSeat == "------------------- " && mVoucher != "1일") {
					JOptionPane.showMessageDialog(null, "좌석종류를 선택하세요");
					return;
				}
				if(mDate.equals("")) {
					JOptionPane.showMessageDialog(null, "이용일을 선택하세요");
					return;
				}				
			} catch(Exception e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "추가 성공");}
	@Override
	public void itemStateChanged(ItemEvent e) {
		String mSeat = (String) cbb1.getSelectedItem();
		if (mSeat == "1일") {
			txtPrice.setText("10,000원");
			cbb2.setEnabled(false);
		} else if (mSeat == "1주") {
			txtPrice.setText("60,000원");
			cbb2.setEnabled(true);
		} else if (mSeat == "2주") {
			txtPrice.setText("100,000원");
			cbb2.setEnabled(true);
		} else {
			txtPrice.setText("200,000원");
			cbb2.setEnabled(true);		
		}}}
class Sub extends JFrame implements ActionListener, ItemListener {
	 
	   JPanel pan, p0, p1, p2, p3, p4, p5, p6;
	   JComboBox cbb1, cbb2;
	   JTextField txtPrice, txtDate;
	   JRadioButton rb1, rb2;
	   ButtonGroup group = new ButtonGroup();
		JButton rev, res, cal,btnNewButton;
		JFrame frame;
		String[] combo1 = {  "------------------- ", "4인", "6인", "8인" };
		String[] combo2 = {   "지정석" };
			
		Sub() {		
		super("단체실");		
		
		pan = new JPanel(new GridLayout(8, 0));
		p0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan.add(p0);
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1.add(new JLabel("인원"));
		p1.add(cbb1 = new JComboBox(combo1));
		pan.add(p1);
		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p2.add(new JLabel("가     격 "));
		txtPrice = new JTextField(10);
		txtPrice.setHorizontalAlignment(JTextField.RIGHT);
		p2.add(txtPrice);
		pan.add(p2);
		p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p6.add(new JLabel("좌석종류"));
		p6.add(cbb2 = new JComboBox(combo2));
		cbb2.setEnabled(false);
		pan.add(p6);
		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p3.add(new JLabel("옵션 빔  "));
		rb1 = new JRadioButton("O", false);
		rb2 = new JRadioButton("X", true);
		group.add(rb1);
		group.add(rb2);
		p3.add(rb1);
		p3.add(rb2);
		pan.add(p3);
		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p4.add(new JLabel("이 용 일"));
		p4.add(txtDate = new JTextField(10));			
		pan.add(p4);
		btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"입력하신 날짜가 맞습니까?");			
			} });
		 
		p4.add(btnNewButton);
		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		rev = new JButton("예             약");
		rev.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
//			IndexMain im = new IndexMain();
	//		MemberDTO memdto = new MemberDTO();
			String mVoucher =  cbb1.getSelectedItem().toString();
			String mType =  cbb2.getSelectedItem().toString();
			String mOpt =  rb1.getText().toString();
			String mDate = txtDate.getText();
			SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy/mm/dd");
	        
	        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
	       
	        java.util.Date tempDate = null;
	        
	        try {
	            tempDate = beforeFormat.parse(mDate);
	        } catch (ParseException pe) {
	            pe.printStackTrace();
	        }
	        
	        String transDate = afterFormat.format(tempDate);
	        
	        Date date = Date.valueOf(transDate);
			String code="";
	//		memdto.setmID(im.tf_id.getText());
			String mId = ClientMemberDGUI.userid;
			if(mType=="지정석") {
				code+="Sem";
			}else { 
				code+="";
			}	
			if(mVoucher=="4인") {
				code+="4";
			}else if (mVoucher=="6인") {
				code+="6";       
			}else if (mVoucher=="8인") {
	        	code+="8";
			}else {
			JOptionPane.showMessageDialog(null, "인원을 선택하세요");
			return;}
		 
			if(rb1.isSelected()) {			
				code+="V";
				}else {
					code+="N";
				}		 			
			ResDAO dao = new ResDAO();
			dao.setInsert(code, mId, date);
			if (obj == rev) {
				
				add();
																			
		setVisible(false);
			}}});	
								
	    res = new JButton("취             소");
	    res.addActionListener(this);	
		p5.add(rev);
		p5.add(res);
		pan.add(p5);
		add(pan, BorderLayout.CENTER);

		cbb1.addItemListener(this); 
		setResizable(false);
		setSize(350, 400);
		setVisible(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} 	
		public void init() {
			cbb1.setSelectedIndex(0);
			txtPrice.setText("");
			cbb2.setSelectedIndex(0);
			rb1.setSelected(true);
			rb2.setSelected(false);							
							}
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj == res) {
				init();
			}}
		
		private void add() {
			try {		
				String mVoucher =  cbb1.getSelectedItem().toString();
				String mType =  cbb2.getSelectedItem().toString();
				String mDate = txtDate.getText();

				if(mVoucher == "------------------    ") {
					JOptionPane.showMessageDialog(null, "이용권을 선택하세요");
					return;
				} 
				if(mType == "------------------- " && mVoucher != "1일") {
					JOptionPane.showMessageDialog(null, "좌석종류를 선택하세요");
					return;
				}			
				if(mDate.equals("")) {
					JOptionPane.showMessageDialog(null, "이용일을 선택하세요");
					return;
				}				
			} catch(Exception e) {
				e.printStackTrace();
			}	
			JOptionPane.showMessageDialog(null, "추가 성공");}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			String mVoucher = (String) cbb1.getSelectedItem();	
			if (mVoucher == "1일") {
				txtPrice.setText("10,000원");
				cbb2.setEnabled(false);
			} else if (mVoucher == "1주") {
				txtPrice.setText("60,000원");
				cbb2.setEnabled(true);
			} else if (mVoucher == "2주") {
				txtPrice.setText("100,000원");
				cbb2.setEnabled(true);
			} else {
				txtPrice.setText("200,000원");
				cbb2.setEnabled(true);
			}
		}	
	}