package basicgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World extends Rectangle{
	
	public static List<Blocks> blocos = new ArrayList<Blocks>();
	public static List<Bush> bush = new ArrayList<Bush>();
	public static List<Inimigo> inimigo = new ArrayList<Inimigo>();
	public int centerX = (640 / 32) / 2;
	public int diffTopDistance = 0;
	
	public World() {
		for( int i = 0; i < 20; i++ ) {
			blocos.add( new Blocks(i * 32, diffTopDistance - 32) ); 		// Cima
			blocos.add( new Blocks(-32, i * 32 + diffTopDistance) ); 		// Esquerda
			
			blocos.add( new Blocks(i * 32, 480 + diffTopDistance) ); // Baixo
			blocos.add( new Blocks(640, i * 32 + diffTopDistance) );	// Direita
			
			
			if( 320 != i * 32 && 352 != i * 32 ) {				
				bush.add( new Bush( i * 32, diffTopDistance ) );
				bush.add( new Bush( i * 32, diffTopDistance + 32 ) );
				
			}
			if( (i * 32) % 3 == 0) {
				bush.add( new Bush( i * 32, 160 + diffTopDistance ) );
				bush.add( new Bush( i * 32, 416 + diffTopDistance ) );
			}
			if( (i * 32) % 5 == 0) {
				bush.add( new Bush( i * 32, 288 + diffTopDistance) );
			}
		}
		
	}
	
	public static boolean isFree( int x, int y ) {
		for( int i = 0; i < blocos.size(); i++ ) {
			Blocks blocoAtual = blocos.get(i);
			if( blocoAtual.intersects( new Rectangle(x, y, 32, 32) ) ) {
				return false;
			}
		}
		for( int i = 0; i < bush.size(); i++ ) {
			Bush bushAtual = bush.get(i);
			if( bushAtual.intersects( new Rectangle(x, y, 32, 32) ) ) {
				return false;
			}
		}
		for( int i = 0; i < inimigo.size(); i++ ) {
			Inimigo inimigoAtual = inimigo.get(i);
			if( inimigoAtual.intersects( new Rectangle(x, y, 32, 32) ) ) {
				return false;
			}
		}
		return true;
	}
	

	public void render( Graphics g ) {
		for( int i = 0; i < blocos.size(); i++ ) {
			blocos.get(i).render(g);
		}
		for( int i = 0; i < bush.size(); i++ ) {
			bush.get(i).render(g);
		}
	}
}
