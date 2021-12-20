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
import javax.swing.table.DefaultTableModel;

public class ClientRes extends JPanel implements ActionListener {
	String data[][] = new String[0][7];
	String title[] = {"���̵�", "�̸�", "���� ��¥", "����/��", "�¼�", "�ɼ�", "Voucher"};

	DefaultTableModel model = new DefaultTableModel(title, 0) {
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	
	JTable table = new JTable(model);

	JScrollPane pan = new JScrollPane(table);
	
	/*JPanel north = new JPanel();
	String[] comboName = { "��ü �˻�", "�ڵ� ��ȣ", "���̵�", "�̸�", "������", "����/��", "�¼�", "�ɼ�" };
	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(40);
	JButton sr = new JButton("�˻�");*/

	
	JPanel south = new JPanel();
	JButton ur = new JButton("���� ����");
	JButton dr = new JButton("���� ����");

	ResDAO dao = new ResDAO();
	
	public ClientRes() {
		setSize(650, 300);
		setLayout(new BorderLayout());

		/*north.add(combo);
		north.add(jtf);
		north.add(sr);*/
		
		south.setLayout(new GridLayout(1, 2, 10, 10));
		south.add(ur);
		south.add(dr);
		
		//add("North", north);
		add("Center", pan);
		add("South", south);

		setVisible(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		//sr.addActionListener(this);
		ur.addActionListener(this);
		dr.addActionListener(this);
		//combo.addActionListener(this);
		
		dao.clientshowProcess(model);
	}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}
	
	/*public String getComboName(String fieldName) {
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
	}*/
	

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		//String fieldName = combo.getSelectedItem().toString();

		int row = table.getSelectedRow();
		
		/*if (obj == combo) {
			dao.showProcess(model);
			jtf.setText("");
			if (model.getRowCount() > 0) table.setRowSelectionInterval(0, 0);
		} */
		if(obj == ur) { //����
			new ClientResDialog(this, "����");
		}
		/*if (obj == sr) {//�˻���ư Ŭ����
			System.out.println("�ʵ��"+fieldName);

			if(fieldName.trim().equals("��ü �˻�")) {
				dao.showProcess(model);

				if (model.getRowCount() > 0)
					table.setRowSelectionInterval(0, 0);

			} else { //�� ��
				if (jtf.getText().trim().equals("")) {
					ResTab.messageBox(this, "�˻�� �Է����ּ���.");
					jtf.requestFocus();

				} else {
					String rfieldName = getComboName(fieldName);
					dao.search(model, rfieldName, jtf.getText());
					if (model.getRowCount() > 0) table.setRowSelectionInterval(0, 0);
				}
			} 
		}
		*/
		else if (obj == dr) {
			/*if (row == -1) {
				messageBox(this, "������ ���� �����ϼ���");
				return;
			}*/
			
			int choise = JOptionPane.showConfirmDialog(this, "�ش� ������ �����ұ��?", "���� ���", JOptionPane.YES_NO_OPTION);
			
			if (choise == JOptionPane.YES_OPTION) {
				System.out.println("���� �� : " + row);
				Object obj2 = table.getValueAt(row, 0);
				System.out.println("�� : " + obj2);
				
				if (dao.clientdelete(this) > 0) {
					messageBox(this, "���� ���� ����");
					dao.clientshowProcess(model);
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