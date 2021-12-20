package reservation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class AdminRes extends JPanel implements ActionListener {
	String data[][] = new String[0][7];
	String title[] = { "�ڵ� ��ȣ", "���̵�", "�̸�", "���� ��¥", "����/��", "�¼�", "�ɼ�" };

	DefaultTableModel model = new DefaultTableModel(title, 0) {
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	
	JTable table = new JTable(model);

	JScrollPane pan = new JScrollPane(table);
	
	JPanel north = new JPanel();
	String[] comboName = { "��ü �˻�", "�ڵ� ��ȣ", "���̵�", "�̸�", "������", "����/��", "�¼�", "�ɼ�" };
	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(40);
	JButton sr = new JButton("�˻�");

	
	JPanel south = new JPanel();
	JButton ur = new JButton("���� ����");
	JButton dr = new JButton("���� ����");

	ResDAO dao = new ResDAO();
	
	public AdminRes() {
		setSize(650, 300);
		setLayout(new BorderLayout());

		north.add(combo);
		north.add(jtf);
		north.add(sr);
		
		south.setLayout(new GridLayout(1, 2, 10, 10));
		south.add(ur);
		south.add(dr);
		
		add("North", north);
		add("Center", pan);
		add("South", south);

		setVisible(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		sr.addActionListener(this);
		ur.addActionListener(this);
		dr.addActionListener(this);
		combo.addActionListener(this);
		
		dao.adminshowProcess(model);
	}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}
	
	public String getComboName(String fieldName) {
		String comname = "";
		
		switch(fieldName) {
		case "�ڵ� ��ȣ":
			comname = "mcode";
			break;
		case "���̵�":
			comname = "mid";
			break;
		case "�̸�":
			comname = "mname";
			break;
		case "������":
			comname = "mdate";
			break;
		case "����/��":
			comname = "mtype";
			break;
		case "�¼�":
			comname = "mseat";
			break;
		case "�ɼ�":
			comname = "mopt";
			break;
		}
		return comname;
	}
	

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String fieldName = combo.getSelectedItem().toString();

		int row = table.getSelectedRow();
		
		if (obj == combo) {
			dao.adminshowProcess(model);
			jtf.setText("");
			if (model.getRowCount() > 0) table.setRowSelectionInterval(0, 0);
		} 
		if(obj == ur) { //����
			new AdminResDialog(this);
		}
		if (obj == sr) {//�˻���ư Ŭ����
			System.out.println("�ʵ��"+fieldName);

			if(fieldName.trim().equals("��ü �˻�")) {
				dao.adminshowProcess(model);

				if (model.getRowCount() > 0)
					table.setRowSelectionInterval(0, 0);

			} else { //�� ��
				if (jtf.getText().trim().equals("")) {
					AdminRes.messageBox(this, "�˻�� �Է����ּ���.");
					jtf.requestFocus();

				} else {
					String rfieldName = getComboName(fieldName);
					dao.adminsearch(model, rfieldName, jtf.getText());
					if (model.getRowCount() > 0) table.setRowSelectionInterval(0, 0);
				}
			} 
		}
		
		else if (obj == dr) {
			if (row == -1) {
				messageBox(this, "������ ���� �����ϼ���");
				return;
			}
			int choise = JOptionPane.showConfirmDialog(this, "�ش� ������ �����ұ��?", "���� ���", JOptionPane.YES_NO_OPTION);
			
			if (choise == JOptionPane.YES_OPTION) {
				System.out.println("���� �� : " + row);
				Object obj2 = table.getValueAt(row, 0);
				System.out.println("�� : " + obj2);
				
				if (dao.admindelete(this) > 0) {
					messageBox(this, "���� ���� ����");
					dao.adminshowProcess(model);
					if (model.getRowCount() > 0) table.setRowSelectionInterval(0, 0);
					
				} else {
					messageBox(this, "���� ���� ����");
				}
				
			} else if (choise == JOptionPane.NO_OPTION) {
				messageBox(this, "���� ���� ���");
			}
		}
	}
}