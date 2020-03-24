package com.jyp.shopping.dto;

import java.sql.Date;

public class WishListDto {
	private int wId;
	private Date wDate;
	private int pId;
	private String mId;
	private String pName;
	private String pImage1;
	private int pPrice;
	
	public WishListDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WishListDto(int wId, Date wDate, int pId, String mId, String pName, String pImage1, int pPrice) {
		super();
		this.wId = wId;
		this.wDate = wDate;
		this.pId = pId;
		this.mId = mId;
		this.pName = pName;
		this.pImage1 = pImage1;
		this.pPrice = pPrice;
	}

	public int getwId() {
		return wId;
	}

	public void setwId(int wId) {
		this.wId = wId;
	}

	public Date getwDate() {
		return wDate;
	}

	public void setwDate(Date wDate) {
		this.wDate = wDate;
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

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	@Override
	public String toString() {
		return "WishListDto [wId=" + wId + ", wDate=" + wDate + ", pId=" + pId + ", mId=" + mId + ", pName=" + pName
				+ ", pImage1=" + pImage1 + ", pPrice=" + pPrice + "]";
	}
	
	
	
	
}

	