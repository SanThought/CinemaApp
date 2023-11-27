package models;

public class Chair {

	private int positionX;
	private int positionY;
	private User user;
	private boolean isTaken;

	public Chair(boolean state) {
		this.isTaken = state;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public User getUser() {
		return user;
	}

	public boolean isTaken() {
		return isTaken;
	}

	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}
}
