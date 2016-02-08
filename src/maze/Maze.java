/*
 * A way to represent a simple maze.
 */
package maze;

import java.util.Arrays;

/**
 *
 * @author RlonRyan
 */
public class Maze {

	public static final char OPEN_CHAR = ' ';
	public static final char WALL_CHAR = 'X';

	public final int width, height, size;

	private final char[] map;

	public Maze(int size) {
		this(size, size);
	}

	public Maze(int width, int height) {
		this.width = width;
		this.height = height;
		this.size = width * height;
		this.map = new char[size];
		Arrays.fill(this.map, WALL_CHAR);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(size + height);

		int counter = 1;

		for (char e : map) {
			sb.append(e);
			if (counter == width) {
				sb.append('\n');
				counter = 1;
			} else {
				counter++;
			}
		}

		return sb.toString();
	}

	public boolean isEdge(int x, int y) {
		return (x == 0) || (x == (width - 1)) || (y == 0) || (y == (height - 1));
	}

	public boolean contains(int x, int y) {
		return ((x > -1) && (x < width)) && ((y > -1) && (y < width));
	}

	public char get(int x, int y) {
		return contains(x, y) ? map[x + y * width] : 0;
	}

	public void put(int x, int y, char c) {
		if (contains(x, y)) {
			map[x + y * width] = c;
		}
	}

}
