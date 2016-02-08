/*
 * A simple program to make cool maze generation Gifs.
 */
package mazegif;

import image.GifWriter;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javafx.util.Pair;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import maze.Maze;
import maze.MazeGenerator;
import maze.MazeImageGenerator;

/**
 *
 * @author RlonRyan
 */
public class MazeGif {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException {

		System.out.println("Starting Maze Gif Generation.");

		final int seed = args.length > 0 ? Integer.decode(args[0]) : 112358;
		final int width = args.length > 1 ? Integer.decode(args[1]) : 53;
		final int height = args.length > 2 ? Integer.decode(args[2]) : width;
		final int zoom = args.length > 3 ? Integer.decode(args[3]) : 5;
		final long startTime = System.nanoTime();

		int iterations = 0;
		int frames = 0;

		ImageOutputStream out = new FileImageOutputStream(new File("mazegif-" + seed + "-" + width + "x" + height + ".gif"));
		GifWriter writer = new GifWriter(out, BufferedImage.TYPE_BYTE_GRAY, 0, false);
		Maze map = new Maze(width, height);
		BufferedImage image = new BufferedImage(map.width * zoom, map.height * zoom, BufferedImage.TYPE_BYTE_GRAY);

		Random rand = new Random(seed);
		List<Point> points = new LinkedList<>();
		final Point mid = new Point(map.width / 2, map.height / 2);
		points.add(mid);
		map.put(mid.x, mid.y, Maze.OPEN_CHAR);

		MazeImageGenerator.writeSquare(image, mid.x, mid.y, zoom);
		writer.writeToSequence(image);

		while (!points.isEmpty()) {
			Pair<Point, Point> delta = MazeGenerator.step(map, points, rand);
			if (delta != null) {
				MazeImageGenerator.writeSquare(image, delta.getKey().x, delta.getKey().y, zoom);
				MazeImageGenerator.writeSquare(image, delta.getValue().x, delta.getValue().y, zoom);
				writer.writeToSequence(image);
				frames++;
			}
			iterations++;
			if (iterations % 100 == 0) {
				System.out.println("I: " + iterations);
			}
		}

		System.out.println(map);

		System.out.println("Generated Maze Gif in " + iterations + " iterations. (" + frames + " frames).");
		System.out.println("\tTime: " + ((System.nanoTime() - startTime) / 1000000) + "ms");

	}

}
