package sangpum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import db.DBConnec;

public class SangDAO {
	Connection con;			Statement st;
	String sql;
	PreparedStatement ps;	ResultSet rs;
	
	SangDAO(){
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
	public void sangpumSelectAll(DefaultTableModel dt) {
		try {
			connected();
			ps = con.prepareStatement("select * from sangpum order by mtype, mprice, mcode");
			rs = ps.executeQuery();
			String temp[] = new String[6];
			int mprice;
			dt.setRowCount(0);
			while(rs.next()) {
				temp[0] = rs.getString("mcode");
				temp[1] = rs.getString("mtype");
				temp[2] = rs.getString("mopt");
				temp[3] = rs.getString("mseat");
				temp[4] = rs.getString("mprice");
				temp[5] = rs.getString("mvoucher");
				dt.addRow(temp);	
			}		
			rs.close();	ps.close();
		} catch (SQLException e) {
			System.out.println(e + " => userSelectAll fail");
		}  finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		}
	
	public void getSangpumSearch(DefaultTableModel dt, String fieldName, String word) {
		try {
			connected();
			String temp[] = new String[6];
			int mprice;
			dt.setRowCount(0);
			
			if(fieldName.equals("전체 검색")) {
				String sql = "SELECT * FROM sangpum WHERE upper (mcode) || mtype || mopt || mseat || mprice || mvoucher  LIKE upper ('%" + word.trim() + "%') order by mtype, mprice, mcode";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				}else if (fieldName.equals("코드번호")){
					String sql = "SELECT * FROM sangpum WHERE upper (mcode) LIKE upper ('%" + word.trim() + "%') order by mcode";
					st = con.createStatement();
					rs = st.executeQuery(sql);
				}else if (fieldName.equals("개인/룸")){
					String sql = "SELECT * FROM sangpum WHERE mtype LIKE '%" + word.trim() + "%' order by mtype, mcode";
					st = con.createStatement();
					rs = st.executeQuery(sql);
				}else if (fieldName.equals("빔")){
					String sql = "SELECT * FROM sangpum WHERE lower (mopt) LIKE lower ('%" + word.trim() + "%') order by mopt, mprice, mcode";
					st = con.createStatement();
					rs = st.executeQuery(sql);
				}else if (fieldName.equals("좌석")){
					String sql = "SELECT * FROM sangpum WHERE mseat LIKE '%" + word.trim() + "%' order by mseat, mprice";
					st = con.createStatement();
					rs = st.executeQuery(sql);
				}else if (fieldName.equals("가격")){
					String sql = "SELECT * FROM sangpum WHERE mprice LIKE '%" + word.trim() + "%' order by mprice, mcode";
					st = con.createStatement();
					rs = st.executeQuery(sql);
				}else if (fieldName.equals("이용권")){
					String sql = "SELECT * FROM sangpum WHERE mvoucher LIKE '%" + word.trim() + "%' order by mvoucher, mcode";
					st = con.createStatement();
					rs = st.executeQuery(sql);
				}			
			
			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}
			while(rs.next()) {
				temp[0] = rs.getString(1);
				temp[1] = rs.getString(2);
				temp[2] = rs.getString(3);
				temp[3] = rs.getString(4);
				temp[4] = rs.getString(5);
				temp[5] = rs.getString(6);
				dt.addRow(temp);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			System.out.println(e + " => getSangpumSearch fail");
		}  finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
	}
	
	public int SangpumDelete(String mcode) {
		int result = 0;
		try {
			connected();
			ps = con.prepareStatement("delete from sangpum where mcode = ?");
			ps.setString(1, mcode);
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e + " => SangpumDelete fail");
		}  finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;	
	}
	
	
	public int SangpumUpdate(AdminSangDialog dia, String mcode) {
		int result = 0;
		SangDTO dto = new SangDTO();
		try {
			connected();
			String sql = "UPDATE sangpum SET mcode=?, mtype=?, mseat=?, mopt=?, mprice=?, mvoucher=?"
					+" WHERE mcode=?";
			System.out.println("sql" + sql);
			ps = con.prepareStatement(sql);			

			
			String code = dia.txtCode.getText().toString();
			System.out.println(code);
			String type = dia.txtType.getText().toString();
			System.out.println(type);
//			String opt = dia.opt1.getText().toString();
//			String seat = dia.seat1.getText().toString();
			String opt = null;
			if(dia.opt1.isSelected()) {
				opt = "X";
			} else if (dia.opt2.isSelected()) {
				opt = "O";
			}
			System.out.println(opt);			
			String seat = null;
			if(dia.seat1.isSelected()) {
				seat = "자유석";
			} else if (dia.seat2.isSelected()) {
				seat = "지정석";
			}
			System.out.println(seat);
			String price = dia.txtPrice.getText().toString();
			int price2 = Integer.parseInt(price);
			System.out.println(price);			
			int vou = dia.combo.getSelectedIndex();
			System.out.println(vou);		
			String svou = null;
			switch(vou) {
			case 1:
				svou = "1일";
				break;
			
			case 2:
				svou = "1주";
				break;
		
			case 3:
				svou = "2주";
				break;
	
			case 4:
				svou = "한달";
				break;
			}
			System.out.println(svou);	

			
			dto.setMcode(code);
			dto.setMtype(type);
			dto.setMopt(opt);
			dto.setMseat(seat);
			dto.setMprice(price2);
			dto.setMvoucher(svou);

			System.out.println(dto.toString());
			
			
			ps.setString(1,  dto.getMcode()); //새로운코드번호
			ps.setString(2, dto.getMtype());
			ps.setString(3, dto.getMseat());
			ps.setString(4, dto.getMopt());
			ps.setInt(5, dto.getMprice());
			ps.setString(6, dto.getMvoucher());
			ps.setString(7, dia.txtCode.getText().trim()); //기존에 있던  코드번호
			
			//ps.setString(1, dto.mcode.getText());
			
			
			result = ps.executeUpdate();	
			System.out.println("update");
			
			System.out.println(result);
		} catch (SQLException e) {
			System.out.println(e + " => SangpumUpdate fail");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;	
	}
	
/*
	public void add1(String mVoucher,String mType,String mOpt,String mDate,String code,String mId) {
		try {   //이게 요점
		connected();
	       System.out.println(code);
			ps.setString(1,code);
			ps.setString(2,mId);
			ps.setString(3,mDate);
			ps.executeUpdate();
			ps.close();
		} catch(Exception e) {
			e.printStackTrace();
		}}		
	
*/	      				


} //SangpumSVC

