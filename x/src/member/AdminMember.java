package member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AdminMember extends Panel implements ActionListener {
	JPanel south = new JPanel();
	JButton insert;
	JButton update;
	JButton delete;
	
	String[] name = { "���̵�", "��й�ȣ", "�̸�", "��ȭ��ȣ", "�̸���", "���Գ�¥" };

	DefaultTableModel dt = new DefaultTableModel(name, 0) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	JTable jt = new JTable(dt);
	JScrollPane center = new JScrollPane(jt);
	JPanel north = new JPanel();
	
	static String[] comboName = { "ALL", "���̵�", "�̸�", "��ȭ��ȣ", "�̸���" };
	//String[] comboName = { "ALL", "���̵�", "�̸�", "��ȭ��ȣ", "�̸���" };

	static JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(20);
	JButton search = new JButton("�˻�");
	MemberDAO dao = new MemberDAO();
	JLabel empty =  new JLabel("           ");
	JLabel empty1 =  new JLabel("        ");
	//JLabel empty1 =  new JLabel("             ");
	
	public AdminMember() {
		insert = new JButton("����");
		update = new JButton("����");
		delete = new JButton("����");
		

		setLayout(new BorderLayout());
		south.setBackground(Color.white);
		south.add(combo);		south.add(jtf);			south.add(search);
		south.add(empty);		south.add(empty1);
		south.add(insert);		south.add(update);		south.add(delete);
		
		add(center, "Center");
		add(south, "South");
		JTableHeader jtHeader = jt.getTableHeader();
  	    Font headerFont = new Font("���ü", Font.BOLD, 13);
	    jtHeader.setFont(headerFont);
		jt.setRowHeight(24);
		jt.setFont(new Font("���ü", Font.PLAIN, 13));
		
		

		jt.getTableHeader().setReorderingAllowed(false);
		jt.getTableHeader().setResizingAllowed(false);
		setSize(500, 400);
		
		setVisible(true);

		
		combo.addActionListener(this);
		insert.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		search.addActionListener(this);

		// ��� ���ڵ带 �˻��Ͽ� DefaultTableModel �� �ø���
		dao.userSelectAll(dt);

		// ù ��° �� ����
		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {
			new AdminMemberDGUI(this, "����");
		} else if (e.getSource() == combo) { //��ü��ǰ ����
			dao.userSelectAll(dt);
			jtf.setText(""); //�˻�â �����ϰ�
		} else if (e.getSource() == update) {
			new AdminMemberDGUI(this, "����");
		} else if (e.getSource() == delete) {
			// ���� JTable�� ���õ� ��� ���� �� ������
			int row = jt.getSelectedRow();
			System.out.println("���� �� : " + row);
			Object obj = jt.getValueAt(row, 0);
			System.out.println("�� : " + obj);

			if (dao.userDelete(obj.toString()) > 0) {
				AdminMemberDGUI.messageBox(this, "���ڵ� ����");
				// ����Ʈ ����
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				AdminMemberDGUI.messageBox(this, "���ڵ� �������� ����");
			}
		} else if(e.getSource()==search) {
			// JComboBox�� ���õ� value ��������
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("�ʵ��" + fieldName);
			
			if(fieldName.trim().equals("ALL")) {
				dao.userSelectAll(dt);  
				if(dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				if(jtf.getText().trim().equals("")) {
					AdminMemberDGUI.messageBox(this,"�˻��� �Է�!");
					jtf.requestFocus();
				} else {
					dao.admingetUserSearch(dt, jtf.getText());
					if(dt.getRowCount() > 0)
						jt.setRowSelectionInterval(0, 0);
				}
			}
		}
	} 

	public static void main(String[] args) {
		new AdminMember();
	}

}