package we.josemipepeedu.casisi.Tragaperras;

import we.josemipepeedu.casisi.Utils.Utils;

public enum SlotItem {
	CEREZA(1, 0, 0),
	MANGO(2, 0, 0),
	TREBOL(3, 0, 0),
	MANZANA(4, 0, 0),
	CAMPANA(5, 0, 0),
	SANDIA(6, 0, 0),
	MELON(7, 0, 0),
	SIETE(8, 0, 0),
	CORAZON(9, 0, 0),
	UVAS(10, 0, 0),
	DOLLAR(11, 0, 0),
	DIAMANTE(12, 0, 0),
	POKER(13, 0, 0),
	HERRADURA(14, 0, 0),
	BAR(15, 0, 0);
	
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
		return getByID(Utils.random.nextInt(14) + 1);
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
