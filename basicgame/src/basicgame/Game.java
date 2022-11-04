package basicgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	public static int WIDTH = 640, HEIGHT = 480;
	public static int SCALE = 3;
	
	public Player player;
	public static World world;
	public List<Inimigo> inimigos = new ArrayList<Inimigo>();
	
	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize( new Dimension(WIDTH, HEIGHT) );
		new Spritesheet();
		
		world = new World();
		
		player = new Player(96, 96);
		inimigos.add( new Inimigo(226, 408) );
		inimigos.add( new Inimigo(408, 226) );
	}
	
	public void tick() { //Lógica do jogo (movimentação, colisões, etc...)
		player.tick();
		
		for( int i = 0; i < inimigos.size(); i++ ) {
			inimigos.get(i).tick();
		}
	}
	
	public void render() { //Renderizar gráficos
		BufferStrategy bs = this.getBufferStrategy();
		if( bs == null ) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor( new Color( 255, 216, 160) );
		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		
		player.render(g);
		
		for( int i = 0; i < inimigos.size(); i++ ) {
			inimigos.get(i).render(g);
			
			if( player.x == inimigos.get(i).x ) {
				inimigos.get(i).right = 0;
				inimigos.get(i).left = 0;
			}else if( player.x - inimigos.get(i).x > 0 ){
				inimigos.get(i).right = 1;
				inimigos.get(i).left = 0;
			}else {
				inimigos.get(i).right = 0;
				inimigos.get(i).left = 1;
			}
			
			if( player.y == inimigos.get(i).y ) {
				inimigos.get(i).up = 0;
				inimigos.get(i).down = 0;
			}else if( player.y - inimigos.get(i).y > 0 ){
				inimigos.get(i).up = 0;
				inimigos.get(i).down = 1;
			}else {
				inimigos.get(i).up = 1;
				inimigos.get(i).down = 0;
			}
		}
		
		world.render(g);
		
		bs.show();
		
		
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		
		frame.add(game);
		frame.setTitle("Teste");
		frame.pack(); 
		
		frame.setLocationRelativeTo(null); // Janela Centralizada
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	public void run() {
		
		while(true) {
			//Código jogo
			tick();
			render();
			
			try {
				Thread.sleep(1000/60); //Rode 60fps
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		// Mover
		if( e.getKeyCode() == KeyEvent.VK_D ) {
			player.right = true;
			
		}else if( e.getKeyCode() == KeyEvent.VK_A ) {
			player.left = true;
		}
		if( e.getKeyCode() == KeyEvent.VK_W ) {
			player.up = true;
			
		}else if( e.getKeyCode() == KeyEvent.VK_S ) {
			player.down = true;
		}
		
		// Atirar
		if ( e.getKeyCode() == KeyEvent.VK_SPACE ) {
			player.shoot = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if( e.getKeyCode() == KeyEvent.VK_D ) {
			player.right = false;
			
		}else if( e.getKeyCode() == KeyEvent.VK_A ) {
			player.left = false;
		}
		if( e.getKeyCode() == KeyEvent.VK_W ) {
			player.up = false;
			
		}else if( e.getKeyCode() == KeyEvent.VK_S ) {
			player.down = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}





















