package com.hotel.entity;

public class Account extends AbstractEntity {

	private int accountId;
	private int summa;

	public Account() {
	}

	public Account(int accountId, int summa) {
		this.accountId = accountId;
		this.summa = summa;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getSumma() {
		return summa;
	}

	public void setSumma(int summa) {
		this.summa = summa;
	}

}
