package basicgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Boomerang extends Rectangle{
	
	public int dir = 1;
	public int speed = 8;
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 10;
	public int frames = 0;
	
	public Boomerang(int x, int y, int dir) {
		super(x+16, y+16, 16, 5);
		this.dir = dir;
	}
	
	public void tick() {
		x+=speed*dir;
		frames++;
		if( frames == 60) {
			Player.boomerangs.remove(this);
			return;
		}
		curFrames++;
		if( curFrames == targetFrames) {
			curFrames = 0;
			curAnimation++;
			if( curAnimation == Spritesheet.boomerang.length ) {
				curAnimation = 0;
			}
		}
	}
	public void removeBoomerang() {
		Player.boomerangs.remove(this);
	}
	
	public void render( Graphics g) {

		g.drawImage(Spritesheet.boomerang[curAnimation], x, y, 8, 8 ,null);
		
	}

}
