/*
 * A simple way to make cool maze generation Gifs.
 */
package maze;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author RlonRyan
 */
public class MazeImageGenerator {

	public static BufferedImage convert(Maze map, int zoom) {
		BufferedImage image = new BufferedImage(map.width * zoom, map.height * zoom, BufferedImage.TYPE_BYTE_GRAY);
		for (int y = 0; y != map.height; y++) {
			for (int x = 0; x != map.width; x++) {
				if (map.get(x, y) == Maze.OPEN_CHAR) {
					writeSquare(image, x, y, zoom);
				}
			}
		}
		return image;
	}

	public static void writeSquare(BufferedImage image, int x, int y, int zoom) {
		for (int dy = 0; dy != zoom; dy++) {
			for (int dx = 0; dx != zoom; dx++) {
				image.setRGB(x * zoom + dx, y * zoom + dy, Color.white.getRGB());
			}
		}
	}

}
