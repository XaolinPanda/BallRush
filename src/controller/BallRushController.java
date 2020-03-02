package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Ball;
import model.Tile;
import processing.core.PApplet;

public class BallRushController extends PApplet {

	List<Tile> tiles;
	
	int[] colors = {0xFFFF0000, 0xFFFFFF00, 0xFF00FF00, 0xFF0000FF};
	int rows;
	int columns;
	final int tileSize = 50;
	Random r = new Random();
	public BallRushController() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("controller.BallRushController");
	}
	
	/**
	 * Fenstergrösse definieren und erstellen
	 */
	
	public void settings() {
		size(800,600);
	}
	
	public void mouseClicked() {
		int index = mouseX / tileSize + (mouseY / tileSize) * columns;
		System.out.println(index);
		int color = tiles.get(index).getBall().getColor();
		removeBalls(index, color);
		refill();
	}
	
	private void removeBalls(int index, int color) {
		//delete ball from current index
		tiles.get(index).setBall(null);
//		getNeighbours(index).forEach(neighbour->{
//			if(color == tiles.get(neighbour).getBall().getColor()) {
//				removeBalls(neighbour, color);
//			}
//		});
		for (int i : getNeighbours(index)) {
			Ball neighbourBall = tiles.get(i).getBall();
			if(neighbourBall!=null) {
				if(color == neighbourBall.getColor()) {
					removeBalls(i, color);
					
				}
			}
		}
	}
	/**
	 * refills empty tiles with balls from above
	 */
	public void refill() {
		for (int i = tiles.size()-1; i >= 0; i--) {
			if (tiles.get(i).getBall()==null) {
				fallDown(i);
			}
		}
	}
	
	void fallDown(int index) {
		int above = index -columns;
		if(above < 0 || tiles.get(above).getBall()==null) return; //break
		
		Ball ballAbove = tiles.get(above).getBall();
		tiles.get(index).setBall(ballAbove);
		tiles.get(above).setBall(null);
		fallDown(above);
	}
	
	/**
	 * returns indices of adjacent elements for given grid index
	 * @param index
	 * @return
	 */
	private List<Integer> getNeighbours(int index) {
		List<Integer> neighbours = new ArrayList<Integer>();
		int above = index - columns;
		if(above >= 0) neighbours.add(above);
		int below = index + columns;
		if(below < tiles.size()) neighbours.add(below);
		int left = index -1;
		if(left % columns < columns - 1 && left >= 0) neighbours.add(left);
		int right = index + 1;
		if(right % columns > 0) neighbours.add(right);
		
		return neighbours;
		
	}

	public void setup() {
		rows = height / tileSize;
		columns = width / tileSize;
		tiles = new ArrayList<Tile>();
		for (int i = 0; i < rows*columns; i++) {
			int color = colors[r.nextInt(colors.length)];
			Ball ball = new Ball(color, tileSize);
			tiles.add(new Tile(tileSize, i, ball));
		}
	}
	
	public void draw() {
		background(0);
		tiles.forEach(t -> t.draw(this));
		
	}
}
