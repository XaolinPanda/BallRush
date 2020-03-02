package model;

import processing.core.PApplet;

/**
 * represents a Tile in a Grid, contains Ball object
 * @author XaolinPanda
 *
 */
public class Tile {
	int tileSize;
	int index;
	Ball ball;
	
	/**
	 * @param tileSize
	 * @param index
	 */
	public Tile(int tileSize, int index, Ball ball) {
		this.tileSize = tileSize;
		this.index = index;
		this.ball = ball;
	}
	
	public void draw(PApplet window) {
		int columns = window.width / tileSize;
		
		int x = index % columns * tileSize;
		int y = index / columns * tileSize;
		
		window.pushMatrix(); //create new Layer
		window.translate(x, y); // move Layer
		window.stroke(0Xff0075ff);
		window.strokeWeight(2);
		window.fill(0);
		window.rect(0, 0, tileSize, tileSize);
		if(ball != null) {
			ball.draw(window);
		}
		window.popMatrix(); // merge new Layer with existing Layer
	}
	
	public Ball getBall() {
		return this.ball;
	}

	public void setBall(Ball object) {
		this.ball = object;
		
	}
}
