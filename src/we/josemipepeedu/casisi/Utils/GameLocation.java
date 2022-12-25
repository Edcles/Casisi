package we.josemipepeedu.casisi.Utils;

public class GameLocation {
	private int x, y;
	public GameLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		//System.out.println("Cojo la x de GameLocation");
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
}
