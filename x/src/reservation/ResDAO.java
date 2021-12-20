package reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableModel;

import db.DBConnec;
import member.MemberDTO;
import sangpum.SangDTO;

public class ResDAO {

	Connection conn;
	PreparedStatement pstmt;
	Statement st;
	ResultSet rs;
	
	String sql;
	ResDTO dto;
	
	public ResDAO() {	
		connected();
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
	
	public Date cdate(String sd) {
		System.out.println(sd);
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy/mm/dd");
        
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
       
        java.util.Date tempDate = null;
        
        try {
            tempDate = beforeFormat.parse(sd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        String transDate = afterFormat.format(tempDate);
        
        Date date = Date.valueOf(transDate);
        
        return date;
	}

	public void setInsert(String mcode, String mid, Date date) {
		try {
			connected();//이게 요점
			ResDTO resdto = new ResDTO();
	        
			resdto.setmCode(mcode);
			resdto.setmId(mid);
			resdto.setMdate(date);
			
			System.out.println(resdto.getmCode());
			System.out.println(resdto.getmId());
			System.out.println(resdto.getMdate());
			
			sql = "insert into res values(?,?,?)";
	        System.out.println("sql" + sql);
	        pstmt = conn.prepareStatement(sql);	        
	        pstmt.setString(1, resdto.getmCode());
	        pstmt.setString(2, resdto.getmId());
			pstmt.setDate(3, resdto.getMdate());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adminshowProcess(DefaultTableModel model) {
		try { 
			connected();
			sql = "select mcode, mid, m.mname, to_char(res.mdate, 'YYYY/MM/DD'), s.mtype, s.mseat, s.mopt "
					+ "from res join sangpum s using(mcode) join member m using(mid) order by res.mdate desc";			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			for(int i=0; i< model.getRowCount();) {
				model.removeRow(0);
			}
			
			while(rs.next()) {
				Object data[] = { rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4), 
						rs.getString(5), 
						rs.getString(6), 
						rs.getString(7)
				};

				model.addRow(data);
			}	
			rs.close();
			pstmt.close();	
		} catch(SQLException e) {
			System.out.println(e + "=> select All fail");
		} finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
	}
	
	public void clientshowProcess(DefaultTableModel model) {
		try { 
			connected();
			sql = "select mid, m.mname, to_char(res.mdate, 'YYYY/MM/DD'), s.mtype, s.mseat, s.mopt, s.mvoucher "
					+ "from res join sangpum s using(mcode) join member m using(mid) order by res.mdate desc";		
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			for(int i=0; i< model.getRowCount();) {
				model.removeRow(0);
			}
			
			while(rs.next()) {
				Object data[] = { rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4), 
						rs.getString(5), 
						rs.getString(6),
						rs.getString(7)					
				};

				model.addRow(data);
			}	
			rs.close();
			pstmt.close();	
		} catch(SQLException e) {
			System.out.println(e + "=> select All fail");
		} finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
	}

	public int adminrupdate(AdminResDialog rd, String id){
		connected();
		dto = new ResDTO();
		
		int result = 0;
		String code = rd.getCode();
		if(code.length() != 5)
			return 0;
		System.out.println("rupdate code: " + code);
		
		String sd = rd.rtd.getText().trim();
		System.out.println(sd);
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy/mm/dd");
        
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
       
        java.util.Date tempDate = null;
        
        try {
            tempDate = beforeFormat.parse(sd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        String transDate = afterFormat.format(tempDate);
        
        Date date = Date.valueOf(transDate);
//		Date date = cdate(sd);
        System.out.println(date);
		
		dto.setmCode(code);
		dto.setmId(id);
		dto.setMdate(date);
		

		try {
			String sql = "update res set mcode = ?, mdate = ?"
					+ " where mid= ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmCode());
			pstmt.setDate(2, dto.getMdate());
			pstmt.setString(3, dto.getmId());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e + "=> Update fail");
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;
	}
	
	public int clientrupdate(ClientResDialog rd, String id){
		connected();
		dto = new ResDTO();
		
		int result = 0;
		String code = rd.getCode();
		if(code.length() != 5)
			return 0;
		System.out.println("rupdate code: " + code);
		String sd = rd.rtd.getText().trim();
		System.out.println(sd);
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy/mm/dd");
        
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
       
        java.util.Date tempDate = null;
        
        try {
            tempDate = beforeFormat.parse(sd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        String transDate = afterFormat.format(tempDate);
        
        Date date = Date.valueOf(transDate);
        
        System.out.println(date);
		
		dto.setmCode(code);
		dto.setmId(id);
		dto.setMdate(date);
		

		try {
			String sql = "update res set mcode = ?, mdate = ?"
					+ " where mid= ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmCode());
			pstmt.setDate(2, dto.getMdate());
			pstmt.setString(3, dto.getmId());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e + "=> Update fail");
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;
	}
	
	public int adminpupdate(AdminResDialog rd, String id) {
		connected();
		dto = new ResDTO();
		
		int result = 0;
		String code = rd.getCode();
		if(code.length() != 5)
			return 0;
		System.out.println("pupdate code: " + code);
		
		String sd = rd.ptd.getText().trim();
		System.out.println(sd);
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy/mm/dd");
        
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
       
        java.util.Date tempDate = null;
        
        try {
            tempDate = beforeFormat.parse(sd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        String transDate = afterFormat.format(tempDate);
        
        Date date = Date.valueOf(transDate);
//		Date date = cdate(sd);
		
		dto.setmCode(code);
		dto.setmId(id);
		dto.setMdate(date);
		
		try {	
			String sql = "update res set mcode = ?, mdate = ?"
					+ " where mid= ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmCode());
			pstmt.setDate(2, dto.getMdate());
			pstmt.setString(3, dto.getmId());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e + "=> Update fail");
		} catch(Exception e){
			e.printStackTrace();
		}
			finally {
		
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;
	}
	
	public int clientpupdate(ClientResDialog rd, String id) {
		connected();
		dto = new ResDTO();
		
		int result = 0;
		String code = rd.getCode();
		if(code.length() != 5)
			return 0;
		System.out.println("pupdate code: " + code);
		String sd = rd.ptd.getText().trim();
		System.out.println(sd);

		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy/mm/dd");
        
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
        
        java.util.Date tempDate = null;
        
        try {
            tempDate = beforeFormat.parse(sd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        String transDate = afterFormat.format(tempDate);
        
        Date date = Date.valueOf(transDate);
        
		
		dto.setmCode(code);
		dto.setmId(id);
		dto.setMdate(date);
		
		try {	
			String sql = "update res set mcode = ?, mdate = ?"
					+ " where mid= ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmCode());
			pstmt.setDate(2, dto.getMdate());
			pstmt.setString(3, dto.getmId());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e + "=> Update fail");
		} catch(Exception e){
			e.printStackTrace();
		}
			finally {
		
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;
	}
	
	public int admindelete(AdminRes rt) {
		int row = rt.table.getSelectedRow();
		int result = 0;
		
		String sd = (String) rt.table.getValueAt(row, 3);	
		System.out.println(sd);
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy/mm/dd");
        
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
       
        java.util.Date tempDate = null;
        
        try {
            tempDate = beforeFormat.parse(sd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        String transDate = afterFormat.format(tempDate);
        
        Date date = Date.valueOf(transDate);
//		Date date = cdate(sd);
        
		dto = new ResDTO();
		dto.setmCode((String)rt.table.getValueAt(row, 0));
		dto.setmId((String)rt.table.getValueAt(row, 1));
		dto.setMdate(date);
		
		try {
			connected();
			sql = "delete from res where mcode = ? and  mid = ? and mdate = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmCode());
			pstmt.setString(2, dto.getmId());
			pstmt.setDate(3, dto.getMdate());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e + " => resDelete fail");
		} finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;
	}
	
	public int clientdelete(ClientRes rt) {
		int row = rt.table.getSelectedRow();
		int result = 0;
		
		String sd = (String) rt.table.getValueAt(row, 2);
		
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy/mm/dd");     
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");       
        java.util.Date tempDate = null;
        
        try {
            tempDate = beforeFormat.parse(sd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        String transDate = afterFormat.format(tempDate);        
        Date date = Date.valueOf(transDate);
        
		dto = new ResDTO();
		//dto.setmCode((String)rt.table.getValueAt(row, 0));
		dto.setmId((String)rt.table.getValueAt(row, 0));
		
		dto.setMdate(date);
		
		try {
			connected();
			sql = "delete from res where mid = ? and mdate = ?";
			//mcode = ? and 
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, dto.getmCode());
			pstmt.setString(1, dto.getmId());
			pstmt.setDate(2, dto.getMdate());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e + " => resDelete fail");
		} finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
		return result;
	}
	
	public void adminsearch(DefaultTableModel model, String fieldName, String word) { //word: 검색창에 입력한 문자열

		try {
			connected();
			st= conn.createStatement();
			
			sql = "select mcode, mid, m.mname, to_char(res.mdate, 'YYYY/MM/DD'), s.mtype, s.mseat, s.mopt "
					+ "from res join sangpum s using(mcode) join member m using(mid) where lower(" 
					+ fieldName.trim()+ ") LIKE lower('%"+ word.trim()+ "%') order by res.mdate desc";

			rs = st.executeQuery(sql);

			for(int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}

			while(rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
								  rs.getString(3), rs.getString(4), 
					           	  rs.getString(5), rs.getString(6), 
					           	  rs.getString(7) };

				model.addRow(data);
			}
			rs.close();
			st.close();

		} catch (SQLException e) {
			System.out.println(e + " => ResSearch fail");
		}  finally {
			try {
				DBConnec.close();
			} catch(SQLException e) {}
		}
	}	
}
