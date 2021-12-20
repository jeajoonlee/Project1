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
	String[] name = {"코드번호", "개인/룸", "빔", "좌석", "가격", "이용권"};
	
	DefaultTableModel dt = new DefaultTableModel(data, name){
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	
	JPanel p = new JPanel();
	String[] comboName = {"전체 검색", "코드번호", "개인/룸", "빔", "좌석", "가격", "이용권"};
	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(40);
	JButton search = new JButton("검색");

	
	JPanel south = new JPanel();
	JButton in = new JButton("상품 추가");
	JButton update = new JButton("상품 수정");
	JButton del = new JButton("상품 삭제");
	
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
	    Font headerFont = new Font("고딕체", Font.BOLD, 13);
	    jtHeader.setFont(headerFont);
		jt.setRowHeight(24);
		jt.setFont(new Font("고딕체", Font.PLAIN, 13));
		
		
		south.setLayout(new GridLayout(1, 3, 10, 10));
		south.add(in);
		south.add(update);	
		south.add(del);
		
		setVisible(true);
		jt.getTableHeader().setReorderingAllowed(false);
		jt.getTableHeader().setResizingAllowed(false);
		
		eventUp();
		dao.sangpumSelectAll(dt); //상품탭에서 전체상품보기
		if (dt.getRowCount() > 0) jt.setRowSelectionInterval(0, 0); //무조건 첫번째 행 선택
	}

	
	public void eventUp() { //login예제에서 이거 하나 잘했다고 하셨음
		combo.addActionListener(this);
		in.addActionListener(this);
		del.addActionListener(this);
		update.addActionListener(this);
		search.addActionListener(this);
	}
	
	
	public static void main(String[] args) {
		new AdminSang(); 
	}
	
	public static void messageBox(Object obj, String message) { //search에서 메세지박스
		JOptionPane.showMessageDialog((Component) obj, message);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == combo) { //전체상품 보기
			dao.sangpumSelectAll(dt);
			jtf.setText(""); //검색창 깨끗하게
			if (dt.getRowCount() > 0) jt.setRowSelectionInterval(0, 0); //무조건선택
		
		} else if (ob == search) { //검색부분
			//JComboBox에 선택된 value 가져오기
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("필드명"+fieldName);
			if(fieldName.trim().equals("전체 검색")) { //전체검색
				dao.getSangpumSearch(dt, fieldName, jtf.getText());
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				if (jtf.getText().trim().equals("")) {
					AdminSang.messageBox(this, "검색어를 입력해주세요.");
					jtf.requestFocus(); //컴포넌트(jtf)가 이벤트를 받을 수 있게 함. 
				} else {
					dao.getSangpumSearch(dt, fieldName, jtf.getText());
					if (dt.getRowCount() > 0)
						jt.setRowSelectionInterval(0, 0);
				}
			} // 검색 else if(ob == search)
		} else if (ob == del) {
			int str = JOptionPane.showConfirmDialog(this, "상품을 삭제하시겠습니까?", "삭제 경고", JOptionPane.YES_NO_OPTION);
			if (str == JOptionPane.YES_OPTION) {
				int row = jt.getSelectedRow();
				System.out.println("선택 행 : " + row);
				Object obj = jt.getValueAt(row, 0);
				System.out.println("값 : " + obj);
				if (dao.SangpumDelete(obj.toString()) > 0) {
					AdminSang.messageBox(this, "상품이 삭제되었습니다.");
					//리스트 갱신
					dao.sangpumSelectAll(dt);
					if (dt.getRowCount() > 0) jt.setRowSelectionInterval(0, 0);
				} else {
					AdminSang.messageBox(this, "상품이 삭제되지 않음");
				}
			} else if (str == JOptionPane.NO_OPTION) {
				AdminSang.messageBox(this, "상품이 삭제되지 않았습니다.");
			} // 삭제 else if(ob == del)
		} else if (ob == update) {			
			new AdminSangDialog(this, "수정");
		} else if (ob == in) { //종국님 상품추가
			AdminSangIN D = new AdminSangIN();
		    D.setVisible(true);	
		}
		
	} //actionPerformed
		
}