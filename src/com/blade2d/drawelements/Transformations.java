package com.blade2d.drawelements;

public class Transformations {
	
	static Vertex rotate(Vertex p, Vertex c, double angle) {
		float x = (float) ((p.x - c.x) * Math.cos(angle) - (p.y - c.y) * Math.sin(angle) + c.x);
		float y = (float) ((p.x - c.x) * Math.sin(angle) + (p.y - c.y) * Math.cos(angle) + c.y);
		return new Vertex(x,y);
		
	}
	
	static Vertex translate(Vertex p, Vertex trans) {
		return new Vertex(p.x + trans.x, p.y + trans.y);
	}
	
	static Vertex scale(Vertex p, Vertex c, float s) {
		return new Vertex(s*(p.x - c.x) + c.x, s*(p.y - c.y) + c.y);
	}
	
	static Vertex reflect(Vertex p, Vertex c, double angle) {
		float x = (float)((p.x - c.x) * Math.cos(2*angle) + (p.y - c.y) * Math.sin(2*angle) + c.x);
		float y = (float)((p.x - c.x) * Math.sin(2*angle) - (p.y - c.y) * Math.cos(2*angle) + c.y);
		return new Vertex(x,y);
	}
	
}
