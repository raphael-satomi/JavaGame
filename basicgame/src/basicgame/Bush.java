package basicgame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bush extends Rectangle{

	public Bush( int x, int y ) {
		super(x, y, 32, 32);
	}
	
	public void render( Graphics g) {
		
		g.drawImage(Spritesheet.bush, x, y, 32, 32 ,null);
		
	}
}
