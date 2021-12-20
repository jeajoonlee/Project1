package sangpum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class AdminSang extends JPanel implements ActionListener {
	String[][] data = new String[0][6];
	String[] name = {"�ڵ��ȣ", "����/��", "��", "�¼�", "����", "�̿��"};
	
	DefaultTableModel dt = new DefaultTableModel(data, name){
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	
	JPanel p = new JPanel();
	String[] comboName = {"��ü �˻�", "�ڵ��ȣ", "����/��", "��", "�¼�", "����", "�̿��"};
	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(40);
	JButton search = new JButton("�˻�");

	
	JPanel south = new JPanel();
	JButton in = new JButton("��ǰ �߰�");
	JButton update = new JButton("��ǰ ����");
	JButton del = new JButton("��ǰ ����");
	
	SangDAO dao = new SangDAO();	
	
	public AdminSang() {
		setLayout(new BorderLayout());
		
		add(p, "North");
		add(jsp, "Center");
		add(south, "South");
		
		//p.setBackground(Color.DARK_GRAY);
		p.add(combo);
		p.add(jtf);
		p.add(search);
	    JTableHeader jtHeader = jt.getTableHeader();
	    Font headerFont = new Font("���ü", Font.BOLD, 13);
	    jtHeader.setFont(headerFont);
		jt.setRowHeight(24);
		jt.setFont(new Font("���ü", Font.PLAIN, 13));
		
		
		south.setLayout(new GridLayout(1, 3, 10, 10));
		south.add(in);
		south.add(update);	
		south.add(del);
		
		setVisible(true);
		jt.getTableHeader().setReorderingAllowed(false);
		jt.getTableHeader().setResizingAllowed(false);
		
		eventUp();
		dao.sangpumSelectAll(dt); //��ǰ�ǿ��� ��ü��ǰ����
		if (dt.getRowCount() > 0) jt.setRowSelectionInterval(0, 0); //������ ù��° �� ����
	}

	
	public void eventUp() { //login�������� �̰� �ϳ� ���ߴٰ� �ϼ���
		combo.addActionListener(this);
		in.addActionListener(this);
		del.addActionListener(this);
		update.addActionListener(this);
		search.addActionListener(this);
	}
	
	
	public static void main(String[] args) {
		new AdminSang(); 
	}
	
	public static void messageBox(Object obj, String message) { //search���� �޼����ڽ�
		JOptionPane.showMessageDialog((Component) obj, message);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == combo) { //��ü��ǰ ����
			dao.sangpumSelectAll(dt);
			jtf.setText(""); //�˻�â �����ϰ�
			if (dt.getRowCount() > 0) jt.setRowSelectionInterval(0, 0); //�����Ǽ���
		
		} else if (ob == search) { //�˻��κ�
			//JComboBox�� ���õ� value ��������
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("�ʵ��"+fieldName);
			if(fieldName.trim().equals("��ü �˻�")) { //��ü�˻�
				dao.getSangpumSearch(dt, fieldName, jtf.getText());
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				if (jtf.getText().trim().equals("")) {
					AdminSang.messageBox(this, "�˻�� �Է����ּ���.");
					jtf.requestFocus(); //������Ʈ(jtf)�� �̺�Ʈ�� ���� �� �ְ� ��. 
				} else {
					dao.getSangpumSearch(dt, fieldName, jtf.getText());
					if (dt.getRowCount() > 0)
						jt.setRowSelectionInterval(0, 0);
				}
			} // �˻� else if(ob == search)
		} else if (ob == del) {
			int str = JOptionPane.showConfirmDialog(this, "��ǰ�� �����Ͻðڽ��ϱ�?", "���� ���", JOptionPane.YES_NO_OPTION);
			if (str == JOptionPane.YES_OPTION) {
				int row = jt.getSelectedRow();
				System.out.println("���� �� : " + row);
				Object obj = jt.getValueAt(row, 0);
				System.out.println("�� : " + obj);
				if (dao.SangpumDelete(obj.toString()) > 0) {
					AdminSang.messageBox(this, "��ǰ�� �����Ǿ����ϴ�.");
					//����Ʈ ����
					dao.sangpumSelectAll(dt);
					if (dt.getRowCount() > 0) jt.setRowSelectionInterval(0, 0);
				} else {
					AdminSang.messageBox(this, "��ǰ�� �������� ����");
				}
			} else if (str == JOptionPane.NO_OPTION) {
				AdminSang.messageBox(this, "��ǰ�� �������� �ʾҽ��ϴ�.");
			} // ���� else if(ob == del)
		} else if (ob == update) {			
			new AdminSangDialog(this, "����");
		} else if (ob == in) { //������ ��ǰ�߰�
			AdminSangIN D = new AdminSangIN();
		    D.setVisible(true);	
		}
		
	} //actionPerformed
		
}