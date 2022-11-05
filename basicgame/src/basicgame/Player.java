package basicgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {
	
	public int spd = 4;
	public boolean right, up, down, left;

	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 10;
	public int lengthAnimation = 0;
	public int newPosition = 0;
	
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	public static List<Arrow> arrows = new ArrayList<Arrow>();
	public static List<Boomerang> boomerangs = new ArrayList<Boomerang>();
	
	public boolean shoot = false;
	public int dir = 1;
	
	public Player( int x, int y) {
		super( x, y, 32, 32 );
		
	}
	
	public void tick() {
		boolean moved = false;
		if( right && World.isFree( x+spd, y) ) {
			x+= spd;
			moved = true;
			dir = 1;
		}else if( left && World.isFree( x-spd, y ) ){
			x -= spd;
			moved = true;
			dir = -1;
		}
		
		if( up && World.isFree( x, y-spd ) ) {
			y-= spd;
			moved = true;
		}else if( down && World.isFree( x, y+spd )){
			y += spd;
			moved = true;
		}
		
		if( moved == true ) {			
			curFrames++;
			
			
			if( curFrames == targetFrames) {
				curFrames = 0;
				
				if( down == true && curAnimation >= 1) {					
					curAnimation = 0;
				}else if( down == true ){
					curAnimation++;
				}
				
				if( up == true && curAnimation >= 3) {					
					curAnimation = 2;
				}else if( up == true && (curAnimation < 2 || curAnimation > 3)  ){
					curAnimation = 2;
				}else if( up == true ){
					curAnimation++;
				}
				
				if( right == true && curAnimation >= 5) {					
					curAnimation = 4;
				}else if( right == true && (curAnimation < 4 || curAnimation > 5)  ){
					curAnimation = 4;
				}else if( right == true ){
					curAnimation++;
				}
				
				if( left == true && curAnimation >= 7) {					
					curAnimation = 6;
				}else if( left == true && (curAnimation < 6 || curAnimation > 7)  ){
					curAnimation = 6;
				}else if( left == true ){
					curAnimation++;
				}
				
			}
		}
		
		if( shoot ) {
			shoot = false;
			//bullets.add( new Bullet(x, y, dir) );
			//arrows.add( new Arrow(x, y, dir) );
			boomerangs.add( new Boomerang(x, y, dir));
		}
		
		for( int i = 0; i < bullets.size(); i++ ) {
			bullets.get(i).tick();
		}
		for( int i = 0; i < arrows.size(); i++ ) {
			arrows.get(i).tick();
		}
		for( int i = 0; i < boomerangs.size(); i++ ) {
			boomerangs.get(i).tick();
		}
		
	}
	
	public void render( Graphics g) {
		//g.setColor( Color.blue );
		//g.fillRect(x, y, width, height);
		
		g.drawImage( Spritesheet.player_front[curAnimation], x, y, 32, 32, null);
		
		for( int i = 0; i < bullets.size(); i++ ) {
			bullets.get(i).render(g);
		}
		for( int i = 0; i < arrows.size(); i++ ) {
			arrows.get(i).render(g);
		}
		for( int i = 0; i < boomerangs.size(); i++ ) {
			boomerangs.get(i).render(g);
		}
		
		for( int i = 0; i < boomerangs.size(); i++ ) {
			Boomerang boom = boomerangs.get(i);
			
			for( int j = 0; j < Game.inimigos.size(); j++ ) {				
				if( boom.intersects( Game.inimigos.get(j) ) ) {
					Game.inimigos.get(j).damage();
					boomerangs.get(i).removeBoomerang();
					if( Game.inimigos.get(j).vida == 0 ) {
						Game.inimigos.get(j).removeInimigo();
					}
				}
			}
		}
		
	}
	
}




















