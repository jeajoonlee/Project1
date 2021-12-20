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
	
	String[] name = { "아이디", "비밀번호", "이름", "전화번호", "이메일", "가입날짜" };

	DefaultTableModel dt = new DefaultTableModel(name, 0) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	JTable jt = new JTable(dt);
	JScrollPane center = new JScrollPane(jt);
	JPanel north = new JPanel();
	
	static String[] comboName = { "ALL", "아이디", "이름", "전화번호", "이메일" };
	//String[] comboName = { "ALL", "아이디", "이름", "전화번호", "이메일" };

	static JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(20);
	JButton search = new JButton("검색");
	MemberDAO dao = new MemberDAO();
	JLabel empty =  new JLabel("           ");
	JLabel empty1 =  new JLabel("        ");
	//JLabel empty1 =  new JLabel("             ");
	
	public AdminMember() {
		insert = new JButton("가입");
		update = new JButton("수정");
		delete = new JButton("삭제");
		

		setLayout(new BorderLayout());
		south.setBackground(Color.white);
		south.add(combo);		south.add(jtf);			south.add(search);
		south.add(empty);		south.add(empty1);
		south.add(insert);		south.add(update);		south.add(delete);
		
		add(center, "Center");
		add(south, "South");
		JTableHeader jtHeader = jt.getTableHeader();
  	    Font headerFont = new Font("고딕체", Font.BOLD, 13);
	    jtHeader.setFont(headerFont);
		jt.setRowHeight(24);
		jt.setFont(new Font("고딕체", Font.PLAIN, 13));
		
		

		jt.getTableHeader().setReorderingAllowed(false);
		jt.getTableHeader().setResizingAllowed(false);
		setSize(500, 400);
		
		setVisible(true);

		
		combo.addActionListener(this);
		insert.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		search.addActionListener(this);

		// 모든 레코드를 검색하여 DefaultTableModel 에 올리기
		dao.userSelectAll(dt);

		// 첫 번째 행 선택
		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {
			new AdminMemberDGUI(this, "가입");
		} else if (e.getSource() == combo) { //전체상품 보기
			dao.userSelectAll(dt);
			jtf.setText(""); //검색창 깨끗하게
		} else if (e.getSource() == update) {
			new AdminMemberDGUI(this, "수정");
		} else if (e.getSource() == delete) {
			// 현재 JTable의 선택된 행과 열의 값 얻어오기
			int row = jt.getSelectedRow();
			System.out.println("선택 행 : " + row);
			Object obj = jt.getValueAt(row, 0);
			System.out.println("값 : " + obj);

			if (dao.userDelete(obj.toString()) > 0) {
				AdminMemberDGUI.messageBox(this, "레코드 삭제");
				// 리스트 갱신
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				AdminMemberDGUI.messageBox(this, "레코드 삭제되지 않음");
			}
		} else if(e.getSource()==search) {
			// JComboBox에 선택된 value 가져오기
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("필드명" + fieldName);
			
			if(fieldName.trim().equals("ALL")) {
				dao.userSelectAll(dt);  
				if(dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				if(jtf.getText().trim().equals("")) {
					AdminMemberDGUI.messageBox(this,"검색어 입력!");
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