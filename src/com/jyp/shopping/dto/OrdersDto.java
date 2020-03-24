package com.jyp.shopping.dto;

import java.util.Date;

public class OrdersDto {
	private int oId;
	private Date oDate;
	private String mId;
	private String oName;
	private String oPhone;
	private String oAddress;
	private String oPayment;
	private int oInvoice;
	
	public OrdersDto() {
	
	}

	public OrdersDto(int oId, Date oDate, String mId, String oName, String oPhone, String oAddress, String oPayment,
			int oInvoice) {
		super();
		this.oId = oId;
		this.oDate = oDate;
		this.mId = mId;
		this.oName = oName;
		this.oPhone = oPhone;
		this.oAddress = oAddress;
		this.oPayment = oPayment;
		this.oInvoice = oInvoice;
	}

	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public Date getoDate() {
		return oDate;
	}

	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public String getoPhone() {
		return oPhone;
	}

	public void setoPhone(String oPhone) {
		this.oPhone = oPhone;
	}

	public String getoAddress() {
		return oAddress;
	}

	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}

	public String getoPayment() {
		return oPayment;
	}

	public void setoPayment(String oPayment) {
		this.oPayment = oPayment;
	}

	public int getoInvoice() {
		return oInvoice;
	}

	public void setoInvoice(int oInvoice) {
		this.oInvoice = oInvoice;
	}

	@Override
	public String toString() {
		return "OrdersDto [oId=" + oId + ", oDate=" + oDate + ", mId=" + mId + ", oName=" + oName + ", oPhone=" + oPhone
				+ ", oAddress=" + oAddress + ", oPayment=" + oPayment + ", oInvoice=" + oInvoice + "]";
	}

	

	
}
