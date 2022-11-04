package basicgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Inimigo extends Rectangle {
	
	public int spd = 1;
	public int right = 0, up = 0, down = 0, left = 0;

	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 15;
	
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	
	public boolean shoot = false;
	public int dir = 1;
	
	public Inimigo( int x, int y) {
		super( x, y, 32, 32 );
		
	}
	
	public void tick() {
		boolean moved = true;
		
		if( right == 1 && World.isFree( x+spd, y)) {
			x++;
		}else if( left == 1 && World.isFree( x-spd, y ) ){
			x -= spd;
			moved = true;
			dir = -1;
		}
		
		if( up == 1 && World.isFree( x, y-spd ) ) {
			y-= spd;
			moved = true;
		}else if( down == 1 && World.isFree( x, y+spd )){
			y += spd;
			moved = true;
		}
		
		if( moved ) {			
			curFrames++;
			if( curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if( curAnimation == Spritesheet.inimigo_front.length ) {
					curAnimation = 0;
				}
				
			}
		}
		
		if( shoot ) {
			shoot = false;
			bullets.add( new Bullet(x, y, dir) );
		}
		
		for( int i = 0; i < bullets.size(); i++ ) {
			bullets.get(i).tick();
		}
		
	}
	
	public void render( Graphics g) {
		//g.setColor( Color.blue );
		//g.fillRect(x, y, width, height);
		
		g.drawImage( Spritesheet.inimigo_front[curAnimation], x, y, 32, 32, null);
		
		for( int i = 0; i < bullets.size(); i++ ) {
			bullets.get(i).render(g);
		}
		
		
	}
	
}




















