package com.example.mppproject;

public class RecordInfo {
	private String isbn;
	private String copyNum;
	private String title;
	private String memberId;
	private String name;
	private String outDate;
	private String dueDate;

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\t");
		sb.append("Book :");
		sb.append(isbn);
		sb.append(", ");
		sb.append("Title: " + title);

		sb.append(", ");
		sb.append("Copy number: " + copyNum);

		sb.append(", ");
		sb.append("Member ID: " + memberId);

		sb.append(", ");
		sb.append("Member Name: " + name);

		sb.append(", ");
		sb.append("Checkout Date: " + outDate);

		sb.append(", ");
		sb.append("Due Date: " + dueDate);

		return sb.toString();
	}

}
