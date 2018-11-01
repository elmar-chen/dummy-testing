package elmar.sanderlings;


public class Rectangle {
	private Point a;
	private Point b;
	
	public Rectangle(Point a, Point b) {
		this.a = a;
		this.b = b;
	}

	public int getArea() {
		
		int width = a.getX() - b.getX();
		int height = a.getY() - b.getY();
		return Math.abs(width*height);
	}
	
	
}
