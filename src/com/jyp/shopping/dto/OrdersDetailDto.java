package com.jyp.shopping.dto;

import java.sql.Date;

public class OrdersDetailDto {
	private int odId;
	private int oId;
	private int pId;
	private int odAmount;
	private String pImage1;
	private String pName;
	private int pPrice;
	private int odTotsum;
	private Date oDate;
	
	public OrdersDetailDto() {
		
	}

	public OrdersDetailDto(int odId, int oId, int pId, int odAmount, String pImage1, String pName, int pPrice,
			int odTotsum, Date oDate) {
		super();
		this.odId = odId;
		this.oId = oId;
		this.pId = pId;
		this.odAmount = odAmount;
		this.pImage1 = pImage1;
		this.pName = pName;
		this.pPrice = pPrice;
		this.odTotsum = odTotsum;
		this.oDate = oDate;
	}

	public int getOdId() {
		return odId;
	}

	public void setOdId(int odId) {
		this.odId = odId;
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

	public int getOdAmount() {
		return odAmount;
	}

	public void setOdAmount(int odAmount) {
		this.odAmount = odAmount;
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

	public int getOdTotsum() {
		return odTotsum;
	}

	public void setOdTotsum(int odTotsum) {
		this.odTotsum = odTotsum;
	}

	public Date getoDate() {
		return oDate;
	}

	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}

	@Override
	public String toString() {
		return "OrdersDetailDto [odId=" + odId + ", oId=" + oId + ", pId=" + pId + ", odAmount=" + odAmount
				+ ", pImage1=" + pImage1 + ", pName=" + pName + ", pPrice=" + pPrice + ", odTotsum=" + odTotsum
				+ ", oDate=" + oDate + "]";
	}

	
}
