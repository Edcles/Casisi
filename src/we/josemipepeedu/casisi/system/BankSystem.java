package we.josemipepeedu.casisi.system;

public class BankSystem {
	private int money = 0;
	
	public int getMoney() {
		return money;
	}
	public void addMoney(int money) {
		this.money += money;
	}
	public void removeMoney(int money) {
		this.money -= money;
	}
}
