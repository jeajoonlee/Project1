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
		JButton btnNewButton_2 = new JButton("���ν�");
		btnNewButton_2.setBounds(77, 444, 329, 50);
		btnNewButton_2.setFont(new Font("���� ���", Font.BOLD, 23));
		btnNewButton_2.setBackground(Color.ORANGE);
		mainPanel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  //���Ⱑ ������ ��ȯ ����
                Main M = new Main();
				
				M.setVisible(true);		 
			}
		});	
		JButton btnNewButton_3 = new JButton("��ü��");
		btnNewButton_3.setBounds(559, 444, 361, 50);
		btnNewButton_3.setFont(new Font("���� ���", Font.BOLD, 23));
		btnNewButton_3.setBackground(Color.ORANGE);
		mainPanel.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  //���Ⱑ ������ ��ȯ ����
                Sub S = new Sub();
				
				S.setVisible(true);
			}
		});			
			
	     
        JLabel lblNewLabel = new JLabel("�ڰ��̾� ���͵� ī���");
        lblNewLabel.setBackground(Color.BLACK);
        lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 30));
        lblNewLabel.setBounds(310, 10, 477, 50);
        mainPanel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("��ȯ���մϴ١�");
        lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 30));
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
		String[] combo1 = { "------------------    ", "1��", "1��", "2��", "�Ѵ�" };
		String[] combo2 = { "------------------- ", "������", "������" };
		

	Main() {
		super("���ν�");
		pan = new JPanel(new GridLayout(8, 0));
		p0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan.add(p0);
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1.add(new JLabel("�� �� ��"));
		p1.add(cbb1 = new JComboBox(combo1));
		pan.add(p1);
		
		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p2.add(new JLabel("��     �� "));
		txtPrice = new JTextField(10);
		txtPrice.setHorizontalAlignment(JTextField.RIGHT);
		p2.add(txtPrice);
		pan.add(p2);
		
		p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p6.add(new JLabel("�¼�����"));
		p6.add(cbb2 = new JComboBox(combo2));
		cbb2.setEnabled(false);
		pan.add(p6);

		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p3.add(new JLabel("ĭ �� ��     "));
		rb1 = new JRadioButton("Yes", true);
		rb2 = new JRadioButton("No", false);
		group.add(rb1);
		group.add(rb2);
		p3.add(rb1);
		p3.add(rb2);
		pan.add(p3);
        
		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p4.add(new JLabel("�� �� ��"));
		p4.add(txtDate = new JTextField(10));			
		pan.add(p4);
		
		btnNewButton = new JButton("Ȯ��");
		p4.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"�Է��Ͻ� ��¥�� �½��ϱ�?");
			}
		   });
		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		rev = new JButton("��             ��");
		rev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  //���Ⱑ ������ ��ȯ ����
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
				if(mType=="������") {
					code+="Fr";
				}else if(mType=="������") {
					code+="Fi";
				}else { 
				JOptionPane.showMessageDialog(null, "�¼��� �����ϼ���");
				return;}
					    
				if(mVoucher=="1��") {
					code+="1d";
				}else if (mVoucher=="1��") {
					code+="1w";
				}else if (mVoucher=="2��") {
		        	code+="2w";
			    }else if (mVoucher=="�Ѵ�") {
		        	code+="1m";
		        } else {
		        	JOptionPane.showMessageDialog(null, "�̿���� �����ϼ���");
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
					
		res = new JButton("��             ��");
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
					JOptionPane.showMessageDialog(null, "�̿���� �����ϼ���");
					return;
				} 
				if(mSeat == "------------------- " && mVoucher != "1��") {
					JOptionPane.showMessageDialog(null, "�¼������� �����ϼ���");
					return;
				}
				if(mDate.equals("")) {
					JOptionPane.showMessageDialog(null, "�̿����� �����ϼ���");
					return;
				}				
			} catch(Exception e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "�߰� ����");}
	@Override
	public void itemStateChanged(ItemEvent e) {
		String mSeat = (String) cbb1.getSelectedItem();
		if (mSeat == "1��") {
			txtPrice.setText("10,000��");
			cbb2.setEnabled(false);
		} else if (mSeat == "1��") {
			txtPrice.setText("60,000��");
			cbb2.setEnabled(true);
		} else if (mSeat == "2��") {
			txtPrice.setText("100,000��");
			cbb2.setEnabled(true);
		} else {
			txtPrice.setText("200,000��");
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
		String[] combo1 = {  "------------------- ", "4��", "6��", "8��" };
		String[] combo2 = {   "������" };
			
		Sub() {		
		super("��ü��");		
		
		pan = new JPanel(new GridLayout(8, 0));
		p0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan.add(p0);
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1.add(new JLabel("�ο�"));
		p1.add(cbb1 = new JComboBox(combo1));
		pan.add(p1);
		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p2.add(new JLabel("��     �� "));
		txtPrice = new JTextField(10);
		txtPrice.setHorizontalAlignment(JTextField.RIGHT);
		p2.add(txtPrice);
		pan.add(p2);
		p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p6.add(new JLabel("�¼�����"));
		p6.add(cbb2 = new JComboBox(combo2));
		cbb2.setEnabled(false);
		pan.add(p6);
		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p3.add(new JLabel("�ɼ� ��  "));
		rb1 = new JRadioButton("O", false);
		rb2 = new JRadioButton("X", true);
		group.add(rb1);
		group.add(rb2);
		p3.add(rb1);
		p3.add(rb2);
		pan.add(p3);
		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p4.add(new JLabel("�� �� ��"));
		p4.add(txtDate = new JTextField(10));			
		pan.add(p4);
		btnNewButton = new JButton("Ȯ��");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"�Է��Ͻ� ��¥�� �½��ϱ�?");			
			} });
		 
		p4.add(btnNewButton);
		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		rev = new JButton("��             ��");
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
			if(mType=="������") {
				code+="Sem";
			}else { 
				code+="";
			}	
			if(mVoucher=="4��") {
				code+="4";
			}else if (mVoucher=="6��") {
				code+="6";       
			}else if (mVoucher=="8��") {
	        	code+="8";
			}else {
			JOptionPane.showMessageDialog(null, "�ο��� �����ϼ���");
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
								
	    res = new JButton("��             ��");
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
					JOptionPane.showMessageDialog(null, "�̿���� �����ϼ���");
					return;
				} 
				if(mType == "------------------- " && mVoucher != "1��") {
					JOptionPane.showMessageDialog(null, "�¼������� �����ϼ���");
					return;
				}			
				if(mDate.equals("")) {
					JOptionPane.showMessageDialog(null, "�̿����� �����ϼ���");
					return;
				}				
			} catch(Exception e) {
				e.printStackTrace();
			}	
			JOptionPane.showMessageDialog(null, "�߰� ����");}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			String mVoucher = (String) cbb1.getSelectedItem();	
			if (mVoucher == "1��") {
				txtPrice.setText("10,000��");
				cbb2.setEnabled(false);
			} else if (mVoucher == "1��") {
				txtPrice.setText("60,000��");
				cbb2.setEnabled(true);
			} else if (mVoucher == "2��") {
				txtPrice.setText("100,000��");
				cbb2.setEnabled(true);
			} else {
				txtPrice.setText("200,000��");
				cbb2.setEnabled(true);
			}
		}	
	}