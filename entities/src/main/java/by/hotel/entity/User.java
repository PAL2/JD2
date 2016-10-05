package by.hotel.entity;

public class User extends Entity {

	private int userId;
	private String firstName;
	private String lastName;
	private String userRole;
	private String login;
	private String password;

	public User(int userId, String firstName, String lastName, String userRole, String login, String password) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userRole = userRole;
		this.login = login;
		this.password = password;
	}

	public User() {
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
