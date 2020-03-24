package com.jyp.shopping.dto;

import java.sql.Date;

public class MemberDto {
	private String mId;
	private String mPw;
	private String mName;
	private String mEmail;
	private String mPhone;
	private String mAddress;
	private Date mBirth;
	private Date mDate;
	private int mDrop;
	
	public MemberDto() {
	}

	public MemberDto(String mId, String mPw, String mName, String mEmail, String mPhone, String mAddress, Date mBirth,
			Date mDate, int mDrop) {
		super();
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mEmail = mEmail;
		this.mPhone = mPhone;
		this.mAddress = mAddress;
		this.mBirth = mBirth;
		this.mDate = mDate;
		this.mDrop = mDrop;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getmAddress() {
		return mAddress;
	}

	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}

	public Date getmBirth() {
		return mBirth;
	}

	public void setmBirth(Date mBirth) {
		this.mBirth = mBirth;
	}

	public Date getmDate() {
		return mDate;
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}

	public int getmDrop() {
		return mDrop;
	}

	public void setmDrop(int mDrop) {
		this.mDrop = mDrop;
	}

	@Override
	public String toString() {
		return "MemberDto [mId=" + mId + ", mPw=" + mPw + ", mName=" + mName + ", mEmail=" + mEmail + ", mPhone="
				+ mPhone + ", mAddress=" + mAddress + ", mBirth=" + mBirth + ", mDate=" + mDate + ", mDrop=" + mDrop
				+ "]";
	}
	
	
	
}
	