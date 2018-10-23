package sanderling;

public class Rectangle {
	private int width;
	private int height;
	
	
	public Rectangle(int width, int length) {
		this.width = width;
		this.height = length;
	}

	public int getArea() {
		return width*height;
	}
	
	public Rectangle rotate() {
		return new Rectangle(height, width);
	}
}
