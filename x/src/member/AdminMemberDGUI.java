package member;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminMemberDGUI extends JDialog implements ActionListener{
	
	JPanel bckground = new JPanel();
	JPanel adminmain = new JPanel(new GridLayout(9, 1));

	JLabel label_Intro = new JLabel("--------��         ��--------", JLabel.CENTER);
	JLabel label_ID = new JLabel("        ��  ��  ��        ", JLabel.CENTER); 
	JLabel label_Name = new JLabel("    ��         ��        ", JLabel.CENTER);
	JLabel label_Pass = new JLabel("      �� �� �� ȣ      ", JLabel.CENTER);
	JLabel label_Tel = new JLabel("      �� ȭ �� ȣ      ", JLabel.CENTER);
	JLabel label_Email = new JLabel("       ��  ��  ��        ", JLabel.CENTER);
	
	JTextField text_Id = new JTextField(10);
	JTextField text_Pass = new JTextField(10);
	JTextField text_Name = new JTextField(10);
	JTextField text_Tel = new JTextField(10);{
		text_Tel.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if(src.getText().length()>=11)ke.consume();
			}
		});
	}
	JTextField text_Email = new JTextField(10);
	
	JButton idCkBtn = new JButton("ID Check");
	JButton ok = new JButton("Ȯ��");
	JButton cancel = new JButton("���");
	
	String mid; 
	
	AdminMember me = new AdminMember();
	MemberDTO mdata;
	MemberDAO dao = new MemberDAO();
	
		public AdminMemberDGUI(AdminMember me, String index) {
		setTitle("���̾�α�");
		this.me=me;
		if(index.equals("����")) {
			ok = new JButton(index);
		} else {
			ok = new JButton("����");
			
			// text �ڽ��� ���õ� ���ڵ��� ���� �ֱ�
			int row=me.jt.getSelectedRow();
			text_Id.setText(me.jt.getValueAt(row, 0).toString());
			text_Pass.setText(me.jt.getValueAt(row, 1).toString());
			text_Name.setText(me.jt.getValueAt(row, 2).toString());
			text_Tel.setText(me.jt.getValueAt(row, 3).toString());
			text_Email.setText(me.jt.getValueAt(row, 4).toString());
			
			text_Id.setEditable(false);
			idCkBtn.setEnabled(false);
			
		}
		
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
        
		add(bckground);
		p1.add(label_Intro);		
		p2.add(label_ID);			p2.add(text_Id);
        p3.add(idCkBtn);			
        p4.add(label_Pass);			p4.add(text_Pass);
        p5.add(label_Name);			p5.add(text_Name);
        p6.add(label_Tel);			p6.add(text_Tel);
        p7.add(label_Email);		p7.add(text_Email);
        p8.add(ok);					p8.add(cancel);
        
        bckground.add(adminmain);
        
        adminmain.add(p9);			
        adminmain.add(p1);			
        adminmain.add(p2);			
        adminmain.add(p3);
        adminmain.add(p4);			
        adminmain.add(p5);			
        adminmain.add(p6);
        adminmain.add(p7);			
        adminmain.add(p8);
           
      	setSize(385, 490);
		adminmain.setBackground(new Color(250,240,230));
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
			if(dao.adminuserListInsert(this) > 0) {
				messageBox(this, text_Name.getText() + "�� ���Ե�");
				dispose();
				
				// ��� ���ڵ带 �����ͼ� DefaultTableModel �� �ֱ�
				dao.userSelectAll(me.dt);
				
				if(me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0); // ù ���� �� ����
			} else {
				messageBox(this, "���Ծȵ�");
			} 
		} else if(btnLabel.equals("����")) {
			if(dao.adminuserUpdate(this) > 0) {
				messageBox(this, "���� �Ϸ�");
				dispose();
				dao.userSelectAll(me.dt);
				if(me.jt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);
			} else {
				messageBox(this, "���� Fail!");
			} 
		}else if(btnLabel.equals("���")) {
			dispose();
		} else if(btnLabel.equals("ID Check")) {
			mid = text_Id.getText().trim();
			if(mid.equals("")) {
				JOptionPane.showMessageDialog(this, "ID �Է�");
				text_Id.requestFocus();
				return;
			}
			if(dao.getIdByCheck(text_Id.getText())) {
				messageBox(this, text_Id.getText() + "�� ��밡��");
				} else {
					messageBox(this, text_Id.getText() + "�� �ߺ�!");
					text_Id.setText("");
					text_Id.requestFocus();
				}
			}
		}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component)obj, message);
	}
}