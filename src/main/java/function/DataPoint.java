package function;

public class DataPoint implements Comparable<DataPoint> {
	private double x;
	private double y;

	public DataPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public int compareTo(DataPoint other) {
		return Double.compare(this.x, other.x);
	}
}
