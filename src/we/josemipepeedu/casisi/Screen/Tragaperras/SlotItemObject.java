package we.josemipepeedu.casisi.Screen.Tragaperras;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import we.josemipepeedu.casisi.Utils.RenderableObject;

public class SlotItemObject extends RenderableObject {
	private SlotItem slotItem;
	public SlotItemObject(String id, SlotItem slotItem, int x, int y) throws IOException {
		super(id, x, y, 50, 50, ImageIO.read(SlotItemObject.class.getClassLoader().getResource(slotItem.getID() + ".png")));
		this.slotItem = slotItem;
	}
	public SlotItem getSlotItem() {
		return slotItem;
	}
}
