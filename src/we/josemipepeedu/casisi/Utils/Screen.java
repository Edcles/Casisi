package we.josemipepeedu.casisi.Utils;

import java.awt.image.BufferedImage;

public abstract class Screen extends ImagePanel {
	public Screen(BufferedImage texture) {
		super(texture);
	}
	private boolean openned = false;
	public boolean isOpen() {
		return openned;
	}
	public void open() {
		openned = true;
		onOpen();
	}
	public void close() {
		openned = false;
		onClose();
	}
	public abstract void onOpen();
	public abstract void onClose();
	
}
