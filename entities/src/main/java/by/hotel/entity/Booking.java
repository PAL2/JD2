package by.hotel.entity;

import java.time.LocalDate;

public class Booking extends Entity {
	private int bookingId;
	private LocalDate startDate;
	private LocalDate endDate;
	private int place;
	private String category;
	private int roomId;
	private int userId;
	private int accountId;
	private String status;

	public Booking(int bookingId, LocalDate startDate, LocalDate endDate, int place, String category, int roomId,
			int userId, int accountId, String status) {
		this.bookingId = bookingId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.place = place;
		this.category = category;
		this.roomId = roomId;
		this.userId = userId;
		this.accountId = accountId;
		this.status = status;
	}

	public Booking() {
	}

	public Booking(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public String getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", startDate=" + startDate + ", endDate=" + endDate + ", place="
				+ place + ", category=" + category + ", roomId=" + roomId + ", userId=" + userId + ", accountId="
				+ accountId + ", status=" + status + "]";
	}

	public void setCategoryRoom(String category) {
		this.category = category;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}