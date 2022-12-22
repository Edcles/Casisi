package we.josemipepeedu.casisi.Screen.Ruleta;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import we.josemipepeedu.casisi.Screen.Ruleta.Objetos.Ficha;
import we.josemipepeedu.casisi.Screen.Ruleta.Objetos.Ficha1;
import we.josemipepeedu.casisi.Screen.Ruleta.Objetos.Ficha10;
import we.josemipepeedu.casisi.Screen.Ruleta.Objetos.Ficha100;
import we.josemipepeedu.casisi.Screen.Ruleta.Objetos.Ficha20;
import we.josemipepeedu.casisi.Screen.Ruleta.Objetos.Ficha5;
import we.josemipepeedu.casisi.Screen.Ruleta.Objetos.Ficha50;
import we.josemipepeedu.casisi.Utils.GameLocation;
import we.josemipepeedu.casisi.Utils.RenderableObject;

public class Game extends Canvas {
	private static final long serialVersionUID = -6895564443654717344L;
	private Ruleta ruleta;
	private int width; // Variable que contiene el ancho del panel
	private int height; // variable que contiene el alto del panel	
	private int mouseX; // Variable que almacena la posicion X del raton
	private int mouseY; // Variable que almacena la posicion Y del raton
	private int rotation = 0;
	private int force = 0;
	private int numWin;
	private int lastPos = 0;
	
	private int[] numeros = {0, 26, 3, 35, 12, 28, 7, 29, 18, 22, 9, 31, 14, 20, 1, 33, 16, 24, 5, 10, 23, 8, 30, 11, 36, 13, 27, 6, 34, 17, 25, 2, 21, 4, 19, 15, 32}; // Array que contiene la posicion de los numeros y su contenido
	
	private boolean ascend = false; //
	private boolean rotate = false; // Estas variables se encargan de hacer que la ruleta gire
	private boolean enableTirar = true; //
	
	private static HashMap<String, RenderableObject> objetos = new HashMap<String, RenderableObject>(); // HashMap que contiene los objetos rendeables
	//private static HashMap<>

	public Game(Ruleta ruleta, int width, int height) {
		this.ruleta = ruleta;
		this.width = width;
		this.height = height;
		//System.out.println(width + " - " + height);
		setBounds(0, 0, width, height);
		setBackground(new Color(3, 76, 3));
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}			
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				System.out.println(mouseX + " - " + mouseY);
			}
		});
		
		try {
			addObject("ruleta", new RenderableObject("ruleta", 5, 20, (int) (452 - (452 * 0.08)), (int) (452 - (452 * 0.08)), ImageIO.read(getClass().getClassLoader().getResource("ruleta/ruleta.png"))));
			addObject("tabla", new RenderableObject("tabla", (int) (452 - (452 * 0.08)) + 5, 5, 712, 341, ImageIO.read(getClass().getClassLoader().getResource("ruleta/tabla.png"))));
			addObject("ficha1", new Ficha1("ficha1", 0, 500, 50, 50, ImageIO.read(getClass().getClassLoader().getResource("coins/ficha_1.png")), 1));
			addObject("ficha5", new Ficha5("ficha5", 50, 500, 50, 50, ImageIO.read(getClass().getClassLoader().getResource("coins/ficha_5.png")), 5));
			addObject("ficha10", new Ficha10("ficha10", 100, 500, 50, 50, ImageIO.read(getClass().getClassLoader().getResource("coins/ficha_10.png")), 10));
			addObject("ficha20", new Ficha20("ficha20", 150, 500, 50, 50, ImageIO.read(getClass().getClassLoader().getResource("coins/ficha_20.png")), 20));
			addObject("ficha50", new Ficha50("ficha50", 200, 500, 50, 50, ImageIO.read(getClass().getClassLoader().getResource("coins/ficha_50.png")), 50));
			addObject("ficha100", new Ficha100("ficha100", 250, 500, 50, 50, ImageIO.read(getClass().getClassLoader().getResource("coins/ficha_100.png")), 100));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addObject(String nombre, RenderableObject objeto) {
		objetos.put(nombre, objeto);
	}
	
	@Override
	public void paint(Graphics g) {
		paintt();
	}
	
	private void paintt() {
		int[] a = {204, 210, 207};
		int[] b = {5, 5, 30};
		try {
			if (getGraphics() != null) {
				BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics g = img.getGraphics();
				g.setColor(new Color(3, 76, 3));
				g.fillRect(0, 0, getWidth(), getHeight());
				for (RenderableObject object : objetos.values()) {
					object.paint(g);
				}
				g.setColor(Color.green);
				g.fillPolygon(a, b, 3);
				if (getGraphics() != null) {
					getGraphics().drawImage(img, 0, 0, getWidth(), getHeight(), null);
				}
			}
		} catch (Exception e) {
			System.out.println("Error al pintar los objetos");
			e.printStackTrace();
		}
	}
	
	public void paintThread() {
		new Thread() {
			@Override
			public void run() {
				while (ruleta.isOpen()) {
					try {
						sleep(10);
						paintt();
						if (rotate) {
							if (force < new Random().nextInt(75) + 50 && ascend) {
								force++;
							} else {
								ascend = false;
								force--;
								if (force <= 0) {
									rotate = false;
									enableTirar = false;
								}
							}
							
							rotation += force;
							//System.out.println(Math.floor(22.61111111111));
						} else if (!rotate && !enableTirar) {
							checkWin(rotation);
						}
						for(String objeto : objetos.keySet()) {
							if (objetos.get(objeto) instanceof Ficha1) {
								if (isTouch(objetos.get(objeto))) {
									System.out.println("AAAA");
								}
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void setRotation(int rotation) {
		this.rotation = rotation;
		objetos.get("ruleta").setAngle(rotation);
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public HashMap<String, RenderableObject> getObjetos() {
		return objetos;
	}
	
	public void setAscend(boolean ascend) {
		this.ascend = ascend;
	}

	public void setRotate(boolean rotate) {
		this.rotate = rotate;
	}
	
	private void checkWin(int rotation) {
		double vueltas = (double) rotation / 360; // El resultado del giro se divide entre 360 q sería una vuelta
		int entera = (int) vueltas; // Cojo la parte entera para quitar las vueltas completas
		double decimal = vueltas - entera; // Resto el resultado de giros menos los giros totales enteros
		
		double giro = decimal * 360;
		int pos = (int) Math.floor(giro / (360 / 37));
		
		System.out.println("Rotacion: " + rotation + " -  Vueltas: " + vueltas + " - Decimal: " + decimal + " - Giro: " + giro + " - Pos: " + pos);
		
		pos -= lastPos;
		
		if (numeros[pos] == numWin) {
			System.out.println("Premio: " + numWin + " - " + numeros[pos]);
		} else {
			System.out.println("Pierdes: " + numWin + " - " + numeros[pos]);
		}
		enableTirar = true;
	}
	
	public void setNumWin(int numWin) {
		this.numWin = numWin;
	}
	
	public int getNumWin() {
		return numWin;
	}
	
	public boolean isTouch(RenderableObject object) {
		if (object instanceof Ficha) {
			Ficha fichas = (Ficha) object;
			GameLocation loc = new GameLocation(fichas.getX(), fichas.getY());
			//System.out.println("Ficha: " + fichas.getX() + " - " + fichas.getY());
			//System.out.println("Mouse: " + mouseX + " - " + mouseY);
			if (mouseX >= loc.getX() && mouseX <= loc.getX() + 50 && mouseY >= loc.getY() && mouseY <= loc.getY() + 50) {
				//System.out.println("entro");
				return true;
			}
		}
		return false;
	}
}
