/*
 * A simple way to generate a maze.
 */
package maze;

import java.awt.Point;
import java.util.List;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author RlonRyan
 */
public class MazeGenerator {

	private static final Point[] DIRECTIONS_1 = {new Point(0, 1), new Point(1, 0), new Point(0, -1), new Point(-1, 0)};
	private static final Point[] DIRECTIONS_2 = {new Point(0, 2), new Point(2, 0), new Point(0, -2), new Point(-2, 0)};

	public static Pair<Point, Point> step(Maze map, List<Point> points, Random rand) {
		final Point p = points.remove(rand.nextInt(points.size()));
		final int dir = rand.nextInt(4);
		final Point n1 = new Point(p.x + DIRECTIONS_1[dir].x, p.y + DIRECTIONS_1[dir].y);
		final Point n2 = new Point(p.x + DIRECTIONS_2[dir].x, p.y + DIRECTIONS_2[dir].y);

		if (!isFull(map, p)) {
			points.add(p);
		}

		if (map.contains(n2.x, n2.y) && (map.get(n2.x, n2.y) == Maze.WALL_CHAR)) {
			map.put(n1.x, n1.y, Maze.OPEN_CHAR);
			map.put(n2.x, n2.y, Maze.OPEN_CHAR);
			if (!map.isEdge(n2.x, n2.y)) {
				points.add(n2);
			}
			return new Pair<>(n1, n2);
		}

		return null;
	}

	private static boolean isFull(Maze map, Point p) {
		return (!map.contains(p.x + 0, p.y + 2) || map.get(p.x + 0, p.y + 2) == Maze.OPEN_CHAR)
				&& (!map.contains(p.x + 2, p.y + 0) || map.get(p.x + 2, p.y + 0) == Maze.OPEN_CHAR)
				&& (!map.contains(p.x - 0, p.y - 2) || map.get(p.x - 0, p.y - 2) == Maze.OPEN_CHAR)
				&& (!map.contains(p.x - 2, p.y - 0) || map.get(p.x - 2, p.y - 0) == Maze.OPEN_CHAR);
	}

}
