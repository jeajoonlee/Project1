package member;

public class MemberDTO {
	private String mID, mPass, mName, mTel, mEmail, mDay;
 
	boolean isFull() {
		if (getmID() == "" || getmPass() == "" || getmName() == "" ||
				getmTel() == "" || getmEmail() == "" )
			return false;
		return true;
	}
	
	boolean isFull2() {
		if (getmID() == "" || getmPass() == "" || getmTel() == "" || getmEmail() == "")
			return false;
		return true;
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmPass() {
		return mPass;
	}

	public void setmPass(String mPass) {
		this.mPass = mPass;
	}

	public String getmTel() {
		return mTel;
	}

	public void setmTel(String mTel) {
		this.mTel = mTel;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getmDay() {
		return mDay;
	}

	public void setmDay(String mDay) {
		this.mDay = mDay;
	}

	public String toString() {
		return "'" + getmID() + "', '" + getmPass() + "', '" + getmName() + "', '"
				+ getmTel() + "', '" + getmEmail() + "'";
	}

	
}