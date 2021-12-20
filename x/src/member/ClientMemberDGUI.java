package member;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientMemberDGUI extends JDialog implements ActionListener{
	
	JPanel bckground = new JPanel();
	JPanel adminmain = new JPanel(new GridLayout(10, 1));

	JLabel label_Intro = new JLabel("------��   ��------", JLabel.CENTER);
	JLabel label_ID = new JLabel("        ��  ��  ��        ", JLabel.CENTER); 
	JLabel label_Name = new JLabel("    ��         ��        ", JLabel.CENTER);
	JLabel label_Pass = new JLabel("      �� �� �� ȣ      ", JLabel.CENTER);
	JLabel label_Tel = new JLabel("      �� ȭ �� ȣ      ", JLabel.CENTER);
	JLabel label_Email = new JLabel("       ��  ��  ��        ", JLabel.CENTER);
	
	public static JTextField ctext_Id = new JTextField(10);
	JTextField ctext_Pass = new JTextField(10);
	JTextField ctext_Name = new JTextField(10);
	JTextField ctext_Tel = new JTextField(10);
	JTextField ctext_Email = new JTextField(10);
	
	JButton idCkBtn = new JButton("ID Check");
	JButton ok = new JButton("Ȯ��");
	JButton cancel = new JButton("���");
	
	public static String userid;
	String mid;
	
	ClientMember me;
	MemberDTO mdata;
	MemberDAO dao = new MemberDAO();
	
		public ClientMemberDGUI(ClientMember me, String index) {
		setTitle("���̾�α�");
		this.me=me;
		if(index.equals("����")) {
			ok = new JButton(index);
		} else {
			ok = new JButton("����");
			
			// text �ڽ��� ���õ� ���ڵ��� ���� �ֱ�
			int row=me.jt.getSelectedRow();
			ctext_Id.setText(me.jt.getValueAt(row, 0).toString());
			ctext_Pass.setText(me.jt.getValueAt(row, 1).toString());
			ctext_Name.setText(me.jt.getValueAt(row, 2).toString());
			ctext_Tel.setText(me.jt.getValueAt(row, 3).toString());
			ctext_Email.setText(me.jt.getValueAt(row, 4).toString());
			
			ctext_Id.setEditable(false);
			idCkBtn.setEnabled(false);
//			System.out.println(ctext_Id.getText());
//			userid = me.jt.getValueAt(row, 0).toString();
//			System.out.println("userid" + userid);
		}
		
		label_Intro.setFont(new Font("���ü", Font.BOLD, 20));
		
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
        
		add(bckground);
		p1.add(label_Intro);		
		p2.add(label_ID);			p2.add(ctext_Id);
        p3.add(idCkBtn);			
        p4.add(label_Pass);			p4.add(ctext_Pass);
        p5.add(label_Name);			p5.add(ctext_Name);
        p6.add(label_Tel);			p6.add(ctext_Tel);
        p7.add(label_Email);		p7.add(ctext_Email);
        p8.add(ok);					p8.add(cancel);
        
        bckground.add(adminmain);
        	
        adminmain.add(p9);
        adminmain.add(p1);			
        System.out.println("");
        adminmain.add(p2);			
        adminmain.add(p3);
        adminmain.add(p4);			
        adminmain.add(p5);			
        adminmain.add(p6);
        adminmain.add(p7);			
        adminmain.add(p8);
           
      	setSize(280, 450);
		adminmain.setBackground(new Color(250,240,230));
		bckground.setBackground(new Color(250,240,230));
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		ok.addActionListener(this);
		cancel.addActionListener(this);
		idCkBtn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String btnLabel=e.getActionCommand();
		
		if(btnLabel.equals("����")) {
			if(dao.ClientuserListInsert(this) > 0) {
				messageBox(this, ctext_Name.getText() + "�� ���Ե�");
				dispose();
				
				// ��� ���ڵ带 �����ͼ� DefaultTableModel �� �ֱ�
				dao.userSelectAll(me.dt);
				
				if(me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0); // ù ���� �� ����
			} else {
				messageBox(this, "���Ծȵ�");
			} 
		} else if(btnLabel.equals("����")) {
			if(dao.clientuserUpdate(this) > 0) {
				messageBox(this, "���� �Ϸ�");
				dispose();
				dao.userSelectOne(me.dt, userid);
				if(me.jt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);
			} else {
				messageBox(this, "���� Fail!");
			} 
		}else if(btnLabel.equals("���")) {
			dispose();
		} else if(btnLabel.equals("ID Check")) {
			mid = ctext_Id.getText().trim();
			if(mid.equals("")) {
				JOptionPane.showMessageDialog(this, "ID �Է�");
				ctext_Id.requestFocus();
				return;
			}
			if(dao.getIdByCheck(ctext_Id.getText())) {
				messageBox(this, ctext_Id.getText() + "�� ��밡��");
				} else {
					messageBox(this, ctext_Id.getText() + "�� �ߺ�!");
					ctext_Id.setText("");
					ctext_Id.requestFocus();
				}
			}
		}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component)obj, message);
	}
}