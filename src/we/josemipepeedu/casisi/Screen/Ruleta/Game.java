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
	private int changePos;
	
	private int[] numeros = {0, 26, 3, 35, 12, 28, 7, 29, 18, 22, 9, 31, 14, 20, 1, 33, 16, 24, 5, 10, 23, 8, 30, 11, 36, 13, 27, 6, 34, 17, 25, 2, 21, 4, 19, 15, 32}; // Array que contiene la posicion de los numeros y su contenido
	
	private boolean ascend = false; //
	private boolean rotate = false; // Estas variables se encargan de hacer que la ruleta gire
	private boolean enableTirar = true; //
	
	private static HashMap<String, RenderableObject> objetos = new HashMap<String, RenderableObject>(); // HashMap que contiene los objetos rendeables
	private static HashMap<Integer, Integer> apuesta = new HashMap<Integer, Integer>();
	/*
	 * Las apuestas van:
	 * 0 - 36 los numeros
	 * 37 - 39 las columnas que serían 2 to 1 en el panel
	 * 40 - 42 que serían los grupos de 12 numeros
	 * 43 - 44 que serían los grupos de 18 numeros
	 * 45 - 46 que serían los pares e impares
	 * 47 - 48 que serían los rojos o negros
	 */
	
	private static Ficha selectedFicha;

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
				if (selectedFicha != null) {
					
				}
			}
		});
		
		try {
			addObject("ruleta", new RenderableObject("ruleta", 5, 20, (int) (452 - (452 * 0.08)), (int) (452 - (452 * 0.08)), ImageIO.read(getClass().getClassLoader().getResource("ruleta/ruleta.png"))));
			//addObject("tabla", new RenderableObject("tabla", (int) (452 - (452 * 0.08)) + 5, 5, 712, 341, ImageIO.read(getClass().getClassLoader().getResource("ruleta/tabla.png"))));
			addObject("ficha1", new Ficha("ficha1", 0, 500, 50, 50, ImageIO.read(getClass().getClassLoader().getResource("coins/ficha_1.png")), 1, false));
			addObject("ficha5", new Ficha("ficha5", 50, 500, 50, 50, ImageIO.read(getClass().getClassLoader().getResource("coins/ficha_5.png")), 5, false));
			addObject("ficha10", new Ficha("ficha10", 100, 500, 50, 50, ImageIO.read(getClass().getClassLoader().getResource("coins/ficha_10.png")), 10, false));
			addObject("ficha20", new Ficha("ficha20", 150, 500, 50, 50, ImageIO.read(getClass().getClassLoader().getResource("coins/ficha_20.png")), 20, false));
			addObject("ficha50", new Ficha("ficha50", 200, 500, 50, 50, ImageIO.read(getClass().getClassLoader().getResource("coins/ficha_50.png")), 50, false));
			addObject("ficha100", new Ficha("ficha100", 250, 500, 50, 50, ImageIO.read(getClass().getClassLoader().getResource("coins/ficha_100.png")), 100, false));
			int cont = 1;
			for(int i = 0; i < 13; i++) {
				for (int j = 0; j < 3; j++) {
					addObject("panel" + cont, new RenderableObject(""+cont, 493 + (35 * i) + 2, 30 + (50 * j) + 2, 35, 50, null));
					cont++;
				}
			}
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
		int[] a = {200, 206, 203};
		int[] b = {5, 5, 30};
		try {
			if (getGraphics() != null) {
				BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics g = img.getGraphics();
				g.setColor(new Color(3, 76, 3));
				g.fillRect(0, 0, getWidth(), getHeight());
				for (RenderableObject object : objetos.values()) {
					if (objetos.get("ruleta").equals(object)) {
						object.setAngle(rotation);
					}
					if (object.getSelected() == true) {
						g.setColor(Color.yellow);
						g.fillOval(object.getX(), object.getY(), object.getWith(), object.getHeight());
					}
					if (objetos.get("panel1").equals(object)) {
						g.setColor(Color.black);
						g.fillRect(object.getX(), object.getY(), object.getWith(), object.getHeight());
					}
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
						if (rotate && enableTirar) {
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
						for (RenderableObject object : objetos.values()) {
							if (isTouch(object) && object instanceof RenderableObject) {
								System.out.println(object.getID());
							}
							if (isTouch(object) && object instanceof Ficha) {
								object.setSelected(true);
								selectedFicha = (Ficha) object;
							} else {
								object.setSelected(false);
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
				
		int giro = (int) (decimal * 360);
		System.out.println("Suelo: " + Math.floor(giro / (360 / 37)));
		double tam = 360.0 / 37.0;
		double posDec = giro / tam;
		System.out.println(tam);
		int pos = (int) Math.ceil(posDec);
		
		System.out.println("Rotacion: " + rotation + " -  Vueltas: " + vueltas + " - Decimal: " + decimal + " - Giro: " + giro + " - Pos: " + pos + " - LastPos: " + lastPos);
		
		if (lastPos != 0) {
			changePos = pos;
			pos = lastPos - pos;
			lastPos = changePos;
		}
		
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
				return true;
			}
		}
		if (object instanceof RenderableObject) {
			if (mouseX >= object.getX() && mouseX <= (object.getX()+object.getWith()) && mouseY >= object.getY() && mouseY <= (object.getY() + object.getHeight())) {
				return true;
			}
		}
		return false;
	}
}
