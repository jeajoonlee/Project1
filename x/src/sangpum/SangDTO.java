package sangpum;

public class SangDTO {
	private String mcode, mtype, mopt, mseat, mvoucher;
	private int mprice;
	
	public String getMcode() {
		return mcode;
	}


	public void setMcode(String mcode) {
		this.mcode = mcode;
	}


	public String getMtype() {
		return mtype;
	}


	public void setMtype(String mtype) {
		this.mtype = mtype;
	}


	public String getMopt() {
		return mopt;
	}


	public void setMopt(String mopt) {
		this.mopt = mopt;
	}


	public String getMseat() {
		return mseat;
	}


	public void setMseat(String mseat) {
		this.mseat = mseat;
	}


	public String getMvoucher() {
		return mvoucher;
	}


	public void setMvoucher(String mvoucher) {
		this.mvoucher = mvoucher;
	}


	public int getMprice() {
		return mprice;
	}


	public void setMprice(int mprice) {
		this.mprice = mprice;
	}


	public String toString() {
		return "'" + mcode + "', '" + mtype + "', '" + mopt + "', '"
				+ mseat + "', '" + mvoucher +  "'" +"'" + mprice + "', '"; 
	}
}

