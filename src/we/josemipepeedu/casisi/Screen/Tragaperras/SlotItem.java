package we.josemipepeedu.casisi.Screen.Tragaperras;

import we.josemipepeedu.casisi.Utils.Utils;

public enum SlotItem {
	CEREZA(1, 1.5, 3),
	MANGO(2, 1.5, 3),
	TREBOL(3, 2.5, 5),
	MANZANA(4, 1.5, 3),
	CAMPANA(5, 1.5, 3),
	SANDIA(6, 1.5, 3),
	MELON(7, 1.5, 3),
	SIETE(8, 5, 10),
	CORAZON(9, 1.5, 3),
	UVAS(10, 1.5, 3),
	DOLLAR(11, 3.5, 7),
	DIAMANTE(12, 3.5, 7),
	POKER(13, 2.5, 5),
	HERRADURA(14, 2.5, 5),
	BAR(15, 4, 8);
	
	private int id;
	private double twoReward; // apuesta * reward
	private double allReward;
	SlotItem(int id, double twoReward, double allReward) {
		this.id = id;
		this.twoReward = twoReward;
		this.allReward = allReward;
	}
	public int getID() {
		return id;
	}
	public double getTwoReward() {
		return twoReward;
	}
	public double getAllReward() {
		return allReward;
	}
	public static SlotItem getRandom() {
		return getByID(Utils.random.nextInt(values().length) + 1);
	}
	public static SlotItem getByID(int id) {
		for (SlotItem slotitem : values()) {
			if (id == slotitem.getID()) {
				return slotitem;
			}
		}
		return null;
	}
}
