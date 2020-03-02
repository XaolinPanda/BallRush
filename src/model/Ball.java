package model;

import processing.core.PApplet;

public class Ball {
	int color;
	int size;
	
	public Ball(int color, int size) {
		this.color = color;
		this.size = size;
	}
	
	public void draw(PApplet window) {
		window.noStroke();
		window.stroke(color);
		window.fill(color);
		window.ellipse(size/2, size/2, size-10, size-10);
	}
	
	public int getColor() {
		return color;
	}
	
}
