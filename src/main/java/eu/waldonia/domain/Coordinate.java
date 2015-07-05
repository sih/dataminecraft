package eu.waldonia.domain;

/**
 * Represents a Minecraft coordinate.
 * <br>
 * Coordinates are immutable and need to be constructed fully fledged
 * @author sid
 *
 */
public final class Coordinate {
	
	private int yPos;
	private int zPos;
	private int xPos;
	
	/**
	 * @param y The y coordinate, can be negative
	 * @param z The z coordinate, can be negative
	 * @param x The x coordinate, can be negative
	 */
	public Coordinate(final int y, final int z, final int x) {
		this.yPos = y;
		this.zPos = z;
		this.xPos = x;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xPos;
		result = prime * result + yPos;
		result = prime * result + zPos;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (xPos != other.xPos)
			return false;
		if (yPos != other.yPos)
			return false;
		if (zPos != other.zPos)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coordinate [yPos=" + yPos + ", zPos=" + zPos + ", xPos=" + xPos
				+ "]";
	}

	public int z() {
		return zPos;
	}
	
	public int y() {
		return yPos;
	}
	
	public int x() {
		return xPos;
	}
	
	
	
	
}
