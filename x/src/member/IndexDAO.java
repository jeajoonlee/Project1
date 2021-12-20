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
				System.out.println("���� ����");
				System.exit(0);
			}
			System.out.println("���� ����");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void loginCheck() {
		mid = index.tf_id.getText().trim();
		mpass = index.tf_pass.getText().trim();
		if (mid.length() == 0) {
			JOptionPane.showMessageDialog(index, "ID �Է�");
			index.tf_pass.setText(null);
			index.tf_id.requestFocus();
			return;
		} else if (mpass.length() == 0) {
			JOptionPane.showMessageDialog(index, "��й�ȣ �Է�");
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
						JOptionPane.showMessageDialog(index, "������ ����");
						index.setVisible(false);
						admingui = new GaiaAdminMain();
						admingui.setVisible(true);	rs.close();  pstmt.close();
						return;
					} else {
						JOptionPane.showMessageDialog(index, "ȯ���մϴ�.");;
						index.setVisible(false);
						clientgui = new GaiaClientMain();
						clientgui.setVisible(true);	rs.close();  pstmt.close();
						return;
					}
				} else {
					JOptionPane.showMessageDialog(index, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					index.tf_pass.setText("");
					index.tf_pass.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(index, "�������� �ʴ� ���̵��Դϴ�.");
				index.tf_id.setText("");
				index.tf_pass.setText("");
				index.tf_id.requestFocus();
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println("ID, pass �˻� ����" + e);
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
			JOptionPane.showMessageDialog(sign, "������� Ȯ��");;
			return;
		}
		
		if (!d.getmPass().equals(sign.stf_pass2.getText().trim())) {
			JOptionPane.showMessageDialog(sign, "PassWord �ٽ� Ȯ��");
			return;
		}
		try {
			connected();
			sql = "INSERT INTO member (mid, mpass, mname, mtel, memail) values (" + d.toString() + ")";
			System.out.println("sql" + sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(sign, "���� ���ԵǾ����ϴ�.");
			sign.setVisible(false);
			index.setVisible(true);
			pstmt.close();	
		} catch (Exception e) {
			System.out.println("DB �߰� ����" + e);
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
			System.out.println("ID �˻� ����!" + e);
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
			loginCheck(); // index ��ư ����
		} else if (ob == sign.submit) {
			String la = sign.submit.getText();
			if (la.equals("��   ��")) {
				if (!checkID(mid)) {
					JOptionPane.showMessageDialog(sign, "�̹� ��ϵ� ID�Դϴ�.");
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
				JOptionPane.showMessageDialog(sign, "���̵� �Է�");
				sign.stf_id.requestFocus();
				return;
			}
			if (checkID(mid)) {
				JOptionPane.showMessageDialog(sign, "��밡���� ID�Դϴ�.");
				sign.stf_pass1.requestFocus();
			} else {
				JOptionPane.showMessageDialog(sign, "��ϵ� ID�Դϴ�. �ٽ� �Է��ϼ���.");
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