package member;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import db.DBConnec;
import main.GaiaAdminMain;
import main.GaiaClientMain;

public class IndexDAO extends Frame implements ActionListener {
	Connection conn;	Statement stmt;		PreparedStatement pstmt;		ResultSet rs;
	String sql;			String mid;			String mpass;
	String a = "admin";
	int row;			boolean flag = false;
	IndexMain index;		Sign sign;
	GaiaAdminMain admingui;	GaiaClientMain clientgui;
	MemberDTO mdata;
	   
	
	IndexDAO() {
//		connected();
		index = new IndexMain();	sign = new Sign();
		eventUp();
		
	}
	
	public void connected() {
		try {
			conn = DBConnec.getConnection();
			if (conn == null) {
				System.out.println("연결 실패");
				System.exit(0);
			}
			System.out.println("연결 성공");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void loginCheck() {
		mid = index.tf_id.getText().trim();
		mpass = index.tf_pass.getText().trim();
		if (mid.length() == 0) {
			JOptionPane.showMessageDialog(index, "ID 입력");
			index.tf_pass.setText(null);
			index.tf_id.requestFocus();
			return;
		} else if (mpass.length() == 0) {
			JOptionPane.showMessageDialog(index, "비밀번호 입력");
			index.tf_pass.requestFocus();
			return;
		}
		connected();
		sql = "select mPass from member where mID='" + mid + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (mpass.equals(rs.getString("mPass"))) {
					if (mid.equals(a)) {
						JOptionPane.showMessageDialog(index, "관리자 접속");
						index.setVisible(false);
						admingui = new GaiaAdminMain();
						admingui.setVisible(true);	rs.close();  pstmt.close();
						return;
					} else {
						JOptionPane.showMessageDialog(index, "환영합니다.");;
						index.setVisible(false);
						clientgui = new GaiaClientMain();
						clientgui.setVisible(true);	rs.close();  pstmt.close();
						return;
					}
				} else {
					JOptionPane.showMessageDialog(index, "비밀번호가 일치하지 않습니다.");
					index.tf_pass.setText("");
					index.tf_pass.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(index, "존재하지 않는 아이디입니다.");
				index.tf_id.setText("");
				index.tf_pass.setText("");
				index.tf_id.requestFocus();
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println("ID, pass 검색 실패" + e);
		} finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
	}
	
	public void insertProcess() {
		MemberDTO d = new MemberDTO();
		d.setmID(sign.stf_id.getText().trim());
		d.setmPass(sign.stf_pass1.getText().trim());
		d.setmName(sign.stf_name.getText().trim());
		d.setmTel(sign.stf_tel.getText().trim());
		d.setmEmail(sign.stf_email.getText().trim());
		if (!d.isFull()) {
			JOptionPane.showMessageDialog(sign, "기재사항 확인");;
			return;
		}
		
		if (!d.getmPass().equals(sign.stf_pass2.getText().trim())) {
			JOptionPane.showMessageDialog(sign, "PassWord 다시 확인");
			return;
		}
		try {
			connected();
			sql = "INSERT INTO member (mid, mpass, mname, mtel, memail) values (" + d.toString() + ")";
			System.out.println("sql" + sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(sign, "새로 가입되었습니다.");
			sign.setVisible(false);
			index.setVisible(true);
			pstmt.close();	
		} catch (Exception e) {
			System.out.println("DB 추가 실패" + e);
		} finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
	}
	
	public void eventUp() {
		index.newid.addActionListener(this);
		sign.submit.addActionListener(this);
		index.enter.addActionListener(this);
		sign.cancel.addActionListener(this);
		sign.idCheck.addActionListener(this);
	}
	
	public boolean checkID(String id) {
		this.mid = id;
		connected();
		
		sql = "SELECT mid FROM member WHERE mid = '" + id + "'";
		try { 
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.close();
				pstmt.close();
				return false;
			}
		} catch (SQLException e) {
			System.out.println("ID 검색 실패!" + e);
		} finally { 
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return true;
	} 
		
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == index.newid) {
			index.setVisible(false);
			sign.setVisible(true);
		} else if (ob == index.enter) {
			loginCheck(); // index 버튼 동작
		} else if (ob == sign.submit) {
			String la = sign.submit.getText();
			if (la.equals("등   록")) {
				if (!checkID(mid)) {
					JOptionPane.showMessageDialog(sign, "이미 등록된 ID입니다.");
					return;
				}
				insertProcess();
				sign.stf_id.setText("");
				sign.stf_pass1.setText("");
				sign.stf_pass2.setText("");
				sign.stf_name.setText("");
				sign.stf_tel.setText("");
				sign.stf_email.setText("");
			}
		} else if (ob == sign.idCheck) {
			mid = sign.stf_id.getText().trim();
			if (mid.equals("")) {
				JOptionPane.showMessageDialog(sign, "아이디 입력");
				sign.stf_id.requestFocus();
				return;
			}
			if (checkID(mid)) {
				JOptionPane.showMessageDialog(sign, "사용가능한 ID입니다.");
				sign.stf_pass1.requestFocus();
			} else {
				JOptionPane.showMessageDialog(sign, "등록된 ID입니다. 다시 입력하세요.");
				sign.stf_id.setText(null);
				sign.stf_id.requestFocus();
				return;
			}
		} else if (ob == sign.cancel) {
			sign.setVisible(false);
			index.setVisible(true);
		}
		
	}
	
	public static void main(String[] args) {
		new IndexDAO();
	}
}