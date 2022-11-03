package basicgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World extends Rectangle{
	
	public static List<Blocks> blocos = new ArrayList<Blocks>();
	
	public World() {
		for( int i = 0; i < 15 * 2; i++ ) {
			blocos.add( new Blocks(i * 32, 0) ); 		// Cima
			blocos.add( new Blocks(0, i * 32) ); 		// Esquerda
			
			blocos.add( new Blocks(i * 32, 480 - 32) ); // Baixo
			blocos.add( new Blocks(640-32, i * 32) );	// Direita
		}
		
	}
	
	public static boolean isFree( int x, int y ) {
		for( int i = 0; i < blocos.size(); i++ ) {
			Blocks blocoAtual = blocos.get(i);
			if( blocoAtual.intersects( new Rectangle(x, y, 32, 32) ) ) {
				return false;
			}
		}
		return true;
	}
	

	public void render( Graphics g ) {
		for( int i = 0; i < blocos.size(); i++ ) {
			blocos.get(i).render(g);
		}
		
	}
}
