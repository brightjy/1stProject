package com.jyp.shopping.dto;

import java.util.Date;

public class ProductDto {
	private int pId;
	private String pCategory;
	private String pName;
	private int pPrice;
	private int pDiscount;
	private String pImage1;
	private String pImage2;
	private String pContent;
	private String pPolicy1;
	private String pPolicy2;
	private int pStock;
	private Date pDate;
	private int pHit;
	
	public ProductDto() {
		
	}

	public ProductDto(int pId, String pCategory, String pName, int pPrice, int pDiscount, String pImage1,
			String pImage2, String pContent, String pPolicy1, String pPolicy2, int pStock, Date pDate, int pHit) {
		super();
		this.pId = pId;
		this.pCategory = pCategory;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pDiscount = pDiscount;
		this.pImage1 = pImage1;
		this.pImage2 = pImage2;
		this.pContent = pContent;
		this.pPolicy1 = pPolicy1;
		this.pPolicy2 = pPolicy2;
		this.pStock = pStock;
		this.pDate = pDate;
		this.pHit = pHit;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpCategory() {
		return pCategory;
	}

	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
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

	public int getpDiscount() {
		return pDiscount;
	}

	public void setpDiscount(int pDiscount) {
		this.pDiscount = pDiscount;
	}

	public String getpImage1() {
		return pImage1;
	}

	public void setpImage1(String pImage1) {
		this.pImage1 = pImage1;
	}

	public String getpImage2() {
		return pImage2;
	}

	public void setpImage2(String pImage2) {
		this.pImage2 = pImage2;
	}

	public String getpContent() {
		return pContent;
	}

	public void setpContent(String pContent) {
		this.pContent = pContent;
	}

	public String getpPolicy1() {
		return pPolicy1;
	}

	public void setpPolicy1(String pPolicy1) {
		this.pPolicy1 = pPolicy1;
	}

	public String getpPolicy2() {
		return pPolicy2;
	}

	public void setpPolicy2(String pPolicy2) {
		this.pPolicy2 = pPolicy2;
	}

	public int getpStock() {
		return pStock;
	}

	public void setpStock(int pStock) {
		this.pStock = pStock;
	}

	public Date getpDate() {
		return pDate;
	}

	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}

	public int getpHit() {
		return pHit;
	}

	public void setpHit(int pHit) {
		this.pHit = pHit;
	}

	@Override
	public String toString() {
		return "ProductDto [pId=" + pId + ", pCategory=" + pCategory + ", pName=" + pName + ", pPrice=" + pPrice
				+ ", pDiscount=" + pDiscount + ", pImage1=" + pImage1 + ", pImage2=" + pImage2 + ", pContent="
				+ pContent + ", pPolicy1=" + pPolicy1 + ", pPolicy2=" + pPolicy2 + ", pStock=" + pStock + ", pDate="
				+ pDate + ", pHit=" + pHit + "]";
	}

	
	
}
