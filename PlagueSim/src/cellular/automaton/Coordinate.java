package cellular.automaton;

public class Coordinate {
	/**
	 * Helper Function for transforming from 2D space into 1D space (matrix to
	 * vector)
	 * 
	 * @param xPos
	 *            x coordinate in space
	 * @param yPos
	 *            y coordinate in space
	 * @param xDim
	 *            dimension of the space matrix in x direction
	 * @return 1d index equivalent
	 */
	public static Long getIndexFrom2DCoordinates(AutomatonDimension aDim,
			long xCoord, long yCoord) {
		Long l = new Long(aDim.xDim * yCoord + xCoord);
		return l;
	}

	/**
	 * Helper function for trasnforming from 3D euclidean space to 1d space (3d
	 * matrix to 1d vector)
	 * 
	 * @param xPos
	 *            x coordinate in space
	 * @param yPos
	 *            y coordinate in space
	 * @param zPos
	 *            z coordinate in space
	 * @param xDim
	 *            x dimension of the space
	 * @param yDim
	 *            y dimension of the space
	 * @return 1d index equivalent
	 */
	public static Long getIndexFrom3DCoordinates(AutomatonDimension aDim,
			long xCoord, long yCoord, long zCoord) {
		Long l = new Long(aDim.xDim * (aDim.yDim * zCoord + yCoord) + xCoord);
		return l;
	}

	/**
	 * Returns X Coordinate from given index 1d --> 2d operation
	 * 
	 * @param index
	 * @param aDim
	 * @return x Coordinate
	 * change 13.apr.06: pfeiferb: X and Y was in the wrong order
	 */
	public static long get2DXCoordinateFromIndex(long index,
			AutomatonDimension aDim) {
		return (index % aDim.xDim);
	}

	/**
	 * Returns Y Coordinate from given index 1d --> 2d operation
	 * 
	 * @param index
	 * @param aDim
	 * @return y coordinate
	 * change 13.apr.06: pfeiferb: X and Y was in the wrong order
	 */
	public static long get2DYCoordinateFromIndex(long index,
			AutomatonDimension aDim) {
		return (index / aDim.xDim);
	}

}
