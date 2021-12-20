package reservation;

import java.sql.Date;

public class ResDTO {
	
	private String mCode;
	private String mId;
	private Date mdate;
	
	
	public String getmCode() {
		return mCode;
	}
	public void setmCode(String mCode) {
		this.mCode = mCode;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	
	@Override
	public String toString() {
		return "'" + mCode + "', '" + mId + "', '" + mdate + "'";
	}
		
}
