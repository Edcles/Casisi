package we.josemipepeedu.casisi.Utils;

import javax.swing.JPanel;

public abstract class Screen extends JPanel {
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
