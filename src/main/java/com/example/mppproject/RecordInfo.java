package com.example.mppproject;

public class RecordInfo {
	private String isbn;
	private String copyNum;
	private String title;
	private String memberId;
	private String name;
	private String outDate;
	private String dueDate;
	private String hasReturned;
	private String fineAmount;
	private String returnedDate;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCopyNum() {
		return copyNum;
	}

	public void setCopyNum(String copyNum) {
		this.copyNum = copyNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getHasReturned() {
		return hasReturned;
	}

	public void setHasReturned(String hasReturned) {
		this.hasReturned = hasReturned;
	}

	public String getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(String fineAmount) {
		this.fineAmount = fineAmount;
	}

	public String getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book :");
		sb.append(isbn);
		sb.append("\n\t");
		sb.append("Title: " + title);

		sb.append("\n\t");
		sb.append("Copy number: " + copyNum);

		sb.append("\n\t");
		sb.append("Member ID: " + memberId);

		sb.append("\n\t");
		sb.append("Member Name: " + name);

		sb.append("\n\t");
		sb.append("Checkout Date: " + outDate);

		sb.append("\n\t");
		sb.append("Due Date: " + dueDate);

		sb.append("\n\t");
		sb.append("Returned?: " + hasReturned);
		sb.append("\n\t");
		sb.append("Fine: " + fineAmount);
		sb.append("\n\t");
		sb.append("Returned Date: " + returnedDate);
		return sb.toString();
	}

}
