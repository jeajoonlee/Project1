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
	String title[] = {"아이디", "이름", "예약 날짜", "개인/룸", "좌석", "옵션", "Voucher"};

	DefaultTableModel model = new DefaultTableModel(title, 0) {
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	
	JTable table = new JTable(model);

	JScrollPane pan = new JScrollPane(table);
	
	/*JPanel north = new JPanel();
	String[] comboName = { "전체 검색", "코드 번호", "아이디", "이름", "예약일", "개인/룸", "좌석", "옵션" };
	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(40);
	JButton sr = new JButton("검색");*/

	
	JPanel south = new JPanel();
	JButton ur = new JButton("예약 변경");
	JButton dr = new JButton("예약 삭제");

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
		case "코드 번호":
			comname = "mcode";
			break;
		case "아이디":
			comname = "mid";
			break;
		case "이름":
			comname = "mname";
			break;
		case "예약일":
			comname = "mdate";
			break;
		case "개인/룸":
			comname = "mtype";
			break;
		case "좌석":
			comname = "mseat";
			break;
		case "옵션":
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
		if(obj == ur) { //수정
			new ClientResDialog(this, "수정");
		}
		/*if (obj == sr) {//검색버튼 클릭시
			System.out.println("필드명"+fieldName);

			if(fieldName.trim().equals("전체 검색")) {
				dao.showProcess(model);

				if (model.getRowCount() > 0)
					table.setRowSelectionInterval(0, 0);

			} else { //그 외
				if (jtf.getText().trim().equals("")) {
					ResTab.messageBox(this, "검색어를 입력해주세요.");
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
				messageBox(this, "삭제할 행을 선택하세요");
				return;
			}*/
			
			int choise = JOptionPane.showConfirmDialog(this, "해당 예약을 삭제할까요?", "삭제 경고", JOptionPane.YES_NO_OPTION);
			
			if (choise == JOptionPane.YES_OPTION) {
				System.out.println("선택 행 : " + row);
				Object obj2 = table.getValueAt(row, 0);
				System.out.println("값 : " + obj2);
				
				if (dao.clientdelete(this) > 0) {
					messageBox(this, "예약 삭제 성공");
					dao.clientshowProcess(model);
					if (model.getRowCount() > 0) table.setRowSelectionInterval(0, 0);
					
				} else {
					messageBox(this, "예약 삭제 실패");
				}
				
			} else if (choise == JOptionPane.NO_OPTION) {
				messageBox(this, "예약 삭제 취소");
			}
		}
	}
}