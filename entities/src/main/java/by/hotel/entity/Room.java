package by.hotel.entity;

public class Room extends AbstractEntity {

	private int roomId;
	private String category;
	private int place;
	private int price;

	public Room(int roomId, String category, int place, int price) {
		this.roomId = roomId;
		this.category = category;
		this.place = place;
		this.price = price;
	}

	public Room() {
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategoryRoom(String category) {
		this.category = category;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
