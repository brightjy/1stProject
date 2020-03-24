package com.jyp.shopping.dto;

import java.util.Date;

public class CartDto {
	private int cId;
	private String mId;
	private int cAmount;
	private Date cDate;
	private int pId;
	private String pImage1;
	private String pName;
	private int pPrice;
	private int cTotsum;
	
	public CartDto() {
		
	}

	public CartDto(int cId, String mId, int cAmount, Date cDate, int pId, String pImage1, String pName, int pPrice,
			int cTotsum) {
		super();
		this.cId = cId;
		this.mId = mId;
		this.cAmount = cAmount;
		this.cDate = cDate;
		this.pId = pId;
		this.pImage1 = pImage1;
		this.pName = pName;
		this.pPrice = pPrice;
		this.cTotsum = cTotsum;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public int getcAmount() {
		return cAmount;
	}

	public void setcAmount(int cAmount) {
		this.cAmount = cAmount;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpImage1() {
		return pImage1;
	}

	public void setpImage1(String pImage1) {
		this.pImage1 = pImage1;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public int getcTotsum() {
		return cTotsum;
	}

	public void setcTotsum(int cTotsum) {
		this.cTotsum = cTotsum;
	}

	@Override
	public String toString() {
		return "CartDto [cId=" + cId + ", mId=" + mId + ", cAmount=" + cAmount + ", cDate=" + cDate + ", pId=" + pId
				+ ", pImage1=" + pImage1 + ", pName=" + pName + ", pPrice=" + pPrice + ", cTotsum=" + cTotsum + "]";
	}

	
	
}
