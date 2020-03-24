package com.jyp.shopping.dto;

import java.sql.Date;

public class QnaDto {
	private int qId;
	private String qCategory;
	private String mId;
	private String aId;
	private String qPw;
	private String qTitle;
	private String qContent;
	private String qFile1;
	private String qFile2;
	private Date qDate;
	private int qHit;
	private int qGroup;
	private int qStep;
	private int qIndent;
	private String qIp;
	
	public QnaDto() {
	
	}

	public QnaDto(int qId, String qCategory, String mId, String aId, String qPw, String qTitle, String qContent,
			String qFile1, String qFile2, Date qDate, int qHit, int qGroup, int qStep, int qIndent, String qIp) {
		super();
		this.qId = qId;
		this.qCategory = qCategory;
		this.mId = mId;
		this.aId = aId;
		this.qPw = qPw;
		this.qTitle = qTitle;
		this.qContent = qContent;
		this.qFile1 = qFile1;
		this.qFile2 = qFile2;
		this.qDate = qDate;
		this.qHit = qHit;
		this.qGroup = qGroup;
		this.qStep = qStep;
		this.qIndent = qIndent;
		this.qIp = qIp;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public String getqCategory() {
		return qCategory;
	}

	public void setqCategory(String qCategory) {
		this.qCategory = qCategory;
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

	public String getqPw() {
		return qPw;
	}

	public void setqPw(String qPw) {
		this.qPw = qPw;
	}

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public String getqContent() {
		return qContent;
	}

	public void setqContent(String qContent) {
		this.qContent = qContent;
	}

	public String getqFile1() {
		return qFile1;
	}

	public void setqFile1(String qFile1) {
		this.qFile1 = qFile1;
	}

	public String getqFile2() {
		return qFile2;
	}

	public void setqFile2(String qFile2) {
		this.qFile2 = qFile2;
	}

	public Date getqDate() {
		return qDate;
	}

	public void setqDate(Date qDate) {
		this.qDate = qDate;
	}

	public int getqHit() {
		return qHit;
	}

	public void setqHit(int qHit) {
		this.qHit = qHit;
	}

	public int getqGroup() {
		return qGroup;
	}

	public void setqGroup(int qGroup) {
		this.qGroup = qGroup;
	}

	public int getqStep() {
		return qStep;
	}

	public void setqStep(int qStep) {
		this.qStep = qStep;
	}

	public int getqIndent() {
		return qIndent;
	}

	public void setqIndent(int qIndent) {
		this.qIndent = qIndent;
	}

	public String getqIp() {
		return qIp;
	}

	public void setqIp(String qIp) {
		this.qIp = qIp;
	}

	@Override
	public String toString() {
		return "QnaDto [qId=" + qId + ", qCategory=" + qCategory + ", mId=" + mId + ", aId=" + aId + ", qPw=" + qPw
				+ ", qTitle=" + qTitle + ", qContent=" + qContent + ", qFile1=" + qFile1 + ", qFile2=" + qFile2
				+ ", qDate=" + qDate + ", qHit=" + qHit + ", qGroup=" + qGroup + ", qStep=" + qStep + ", qIndent="
				+ qIndent + ", qIp=" + qIp + "]";
	}

	
}
