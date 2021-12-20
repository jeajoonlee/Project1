package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.table.DefaultTableModel;

import db.DBConnec;

public class MemberDAO {
	Connection con; 	Statement st;	ResultSet rs;
	PreparedStatement ps;
	String sql;			String mid;		String mpass;
	int row;			boolean flag = false;
	IndexMain index;		Sign sign;
	AdminMemberDGUI amgui;
	ClientMemberDGUI clgui;
	MemberDTO mdata;
	String userid;
	
	
	public MemberDAO() {
		connected();
	}
	
	public void connected() {
		try {
			con = DBConnec.getConnection();
			if (con == null) {
				System.out.println("연결 실패");
				System.exit(0);
			}
			System.out.println("연결 성공");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
/*	public void dbClose() {
		try {
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(ps!=null) ps.close();
		} catch(Exception e) {
			System.out.println(e + " => dbClose fail");
		}
	}
*/
	
	
	public boolean getIdByCheck(String id) {
		boolean result=true;
		try {
			ps=con.prepareStatement("SELECT * FROM member "	+ " WHERE mid=?");
			ps.setString(1, id.trim());
			rs=ps.executeQuery();
			if(rs.next()) {
				rs.close();
				ps.close();
				result=false;
			}
		} catch(SQLException e) {
			System.out.println(e + " => getIdByCheck fail");
		} finally { 
//			dbClose();
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;
	}
	
	public int adminuserListInsert(AdminMemberDGUI user) {
		int result=0;
		try {
			connected();
			ps=con.prepareStatement("INSERT INTO member (mid, mpass, mname , mtel, memail) " + " VALUES(?,?,?,?,?)");
			ps.setString(1, user.text_Id.getText());
			ps.setString(2, user.text_Pass.getText());
			ps.setString(3, user.text_Name.getText());
			ps.setString(4, user.text_Tel.getText());
			ps.setString(5, user.text_Email.getText());
			
			result=ps.executeUpdate();
			
			ps.close();
		} catch(SQLException e) {
			System.out.println(e + " => userListInsert fail");
		} finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;
	}
	
	public int ClientuserListInsert(ClientMemberDGUI user) {
		int result=0;
		try {
			ps=con.prepareStatement("INSERT INTO member (mid, mpass, mname , mtel, memail) " + " VALUES(?,?,?,?,?)");
			ps.setString(1, user.ctext_Id.getText());
			ps.setString(2, user.ctext_Pass.getText());
			ps.setString(3, user.ctext_Name.getText());
			ps.setString(4, user.ctext_Tel.getText());
			ps.setString(5, user.ctext_Email.getText());
			
			result=ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			System.out.println(e + " => userListInsert fail");
		} finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;
	}
	
	public void userSelectAll(DefaultTableModel t_model) {
		try {
			connected();
			sql = "SELECT mid, mpass, mname, mtel, memail, to_char(mday, 'yyyy-mm-dd') FROM member ORDER BY mid";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			 
			// DefaultTableModel 에 있는 기존 데이터 지우기
			for(int i=0; i<t_model.getRowCount(); ) {
				t_model.removeRow(0);
			}
			
			while(rs.next()) {
				Object data[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6) };
				t_model.addRow(data); // DefaultTableModel에 레코드 추가
			}
			rs.close();
			ps.close();
		} catch(SQLException e) {
			System.out.println(e + " => userSelectAll fail");
		}  finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
	}
	
	public void userSelectOne(DefaultTableModel t_model, String userid) {
		try {
			connected();
			
			sql = "SELECT mid, mpass, mname, mtel, memail, to_char(mday, 'yyyy-mm-dd') FROM member where mid = '" + userid + "'";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			 
			// DefaultTableModel 에 있는 기존 데이터 지우기
			for(int i=0; i<t_model.getRowCount(); ) {
				t_model.removeRow(0);
			}
			
			while(rs.next()) {
				Object data[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6) };
				t_model.addRow(data); // DefaultTableModel에 레코드 추가
			}
			rs.close();
			ps.close();
			
		} catch(SQLException e) {
			System.out.println(e + " => userSelectOne fail");
		}  finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
	}
	public int userDelete(String id) {
		int result=0;
		try {
			connected();
			
			ps=con.prepareStatement("DELETE member WHERE mid=?");
			ps.setString(1, id.trim());
			result=ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			System.out.println(e + " => userDelete fail");
		}  finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;
	}
	
	public int adminuserUpdate(AdminMemberDGUI user) {
		int result=0;
		String sql="UPDATE member SET mpass=?, mname=?, mtel=?, memail=? " + " WHERE mid=?";
		try {
			connected();
			ps=con.prepareStatement(sql);
			
			ps.setString(1, user.text_Pass.getText());
			ps.setString(2, user.text_Name.getText());
			ps.setString(3, user.text_Tel.getText());
			ps.setString(4, user.text_Email.getText());
			ps.setString(5, user.text_Id.getText());
			
			result=ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e + " => userUpdate fail");
		}  finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;
	}
	
	public int clientuserUpdate(ClientMemberDGUI user) {
		int result=0;
		String sql="UPDATE member SET mpass=?, mname=?, mtel=?, memail=? " + " WHERE mid=?";
		try {
			connected();
			ps=con.prepareStatement(sql);
			
			ps.setString(1, user.ctext_Pass.getText());
			ps.setString(2, user.ctext_Name.getText());
			ps.setString(3, user.ctext_Tel.getText());
			ps.setString(4, user.ctext_Email.getText());
			ps.setString(5, user.ctext_Id.getText());
			
			result=ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			System.out.println(e + " => userUpdate fail");
		}  finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;
	}
	public void admingetUserSearch(DefaultTableModel dt,
			String word) {
		String comboname = AdminMember.combo.getSelectedItem().toString() ;
		
		try {
			connected();
			if(comboname.equals("ALL")) {
			String sql = "SELECT * FROM member WHERE mid or mname or mtel or memail  LIKE '%" + word.trim() + "%'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			}else if (comboname.equals("아이디")){
				String sql = "SELECT * FROM member WHERE mid LIKE '%" + word.trim() + "%' order by mid";
				st = con.createStatement();
				rs = st.executeQuery(sql);
			}else if (comboname.equals("이름")){
				String sql = "SELECT * FROM member WHERE mname LIKE '%" + word.trim() + "%' order by mname";
				st = con.createStatement();
				rs = st.executeQuery(sql);
			}else if (comboname.equals("전화번호")){
				String sql = "SELECT * FROM member WHERE mtel LIKE '%" + word.trim() + "%' order by mtel";
				st = con.createStatement();
				rs = st.executeQuery(sql);
			}else if (comboname.equals("이메일")){
				String sql = "SELECT * FROM member WHERE memail LIKE '%" + word.trim() + "%' order by memail";
				st = con.createStatement();
				rs = st.executeQuery(sql);
			}
			
			
			// DefaultTableModel 에 있는 기존 데이터 지우기
			for(int i=0; i< dt.getRowCount(); ) {
				dt.removeRow(0);
			}
			
			while(rs.next()) {
				Object data[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6) };
				dt.addRow(data);
			}
			rs.close();
			st.close();
		} catch(SQLException e) {
			System.out.println(e + "=> getUserSearch fail");
		}  finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
	}

    public static void main(String[] args) {
		new IndexDAO();		
	}
}