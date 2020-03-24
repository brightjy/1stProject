package com.jyp.shopping.dto;

import java.util.Date;

public class ReviewDto {
	private int rId;
	private int oId;
	private int pId;
	private String mId;
	private String aId;
	private String rPw;
	private String rTitle;
	private String rContent;
	private String rFile1;
	private String rFile2;
	private Date rDate;
	private int rHit;
	private int rGroup;
	private int rStep;
	private int rIndent;
	private String rIp;
	private String pName;
	private String pImage1;
	
	public ReviewDto() {
		
	}

	public ReviewDto(int rId, int oId, int pId, String mId, String aId, String rPw, String rTitle, String rContent,
			String rFile1, String rFile2, Date rDate, int rHit, int rGroup, int rStep, int rIndent, String rIp,
			String pName, String pImage1) {
		super();
		this.rId = rId;
		this.oId = oId;
		this.pId = pId;
		this.mId = mId;
		this.aId = aId;
		this.rPw = rPw;
		this.rTitle = rTitle;
		this.rContent = rContent;
		this.rFile1 = rFile1;
		this.rFile2 = rFile2;
		this.rDate = rDate;
		this.rHit = rHit;
		this.rGroup = rGroup;
		this.rStep = rStep;
		this.rIndent = rIndent;
		this.rIp = rIp;
		this.pName = pName;
		this.pImage1 = pImage1;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getrPw() {
		return rPw;
	}

	public void setrPw(String rPw) {
		this.rPw = rPw;
	}

	public String getrTitle() {
		return rTitle;
	}

	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public String getrFile1() {
		return rFile1;
	}

	public void setrFile1(String rFile1) {
		this.rFile1 = rFile1;
	}

	public String getrFile2() {
		return rFile2;
	}

	public void setrFile2(String rFile2) {
		this.rFile2 = rFile2;
	}

	public Date getrDate() {
		return rDate;
	}

	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}

	public int getrHit() {
		return rHit;
	}

	public void setrHit(int rHit) {
		this.rHit = rHit;
	}

	public int getrGroup() {
		return rGroup;
	}

	public void setrGroup(int rGroup) {
		this.rGroup = rGroup;
	}

	public int getrStep() {
		return rStep;
	}

	public void setrStep(int rStep) {
		this.rStep = rStep;
	}

	public int getrIndent() {
		return rIndent;
	}

	public void setrIndent(int rIndent) {
		this.rIndent = rIndent;
	}

	public String getrIp() {
		return rIp;
	}

	public void setrIp(String rIp) {
		this.rIp = rIp;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpImage1() {
		return pImage1;
	}

	public void setpImage1(String pImage1) {
		this.pImage1 = pImage1;
	}

	@Override
	public String toString() {
		return "ReviewDto [rId=" + rId + ", oId=" + oId + ", pId=" + pId + ", mId=" + mId + ", aId=" + aId + ", rPw="
				+ rPw + ", rTitle=" + rTitle + ", rContent=" + rContent + ", rFile1=" + rFile1 + ", rFile2=" + rFile2
				+ ", rDate=" + rDate + ", rHit=" + rHit + ", rGroup=" + rGroup + ", rStep=" + rStep + ", rIndent="
				+ rIndent + ", rIp=" + rIp + ", pName=" + pName + ", pImage1=" + pImage1 + "]";
	}
	
	
}
