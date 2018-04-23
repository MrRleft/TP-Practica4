package es.ucm.fdi.Utils;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilities class for games.
 */
public class Utils {

	/**
	 * An {@link Executor} created using
	 * {@link Executors#newCachedThreadPool()}.
	 */
	public final static ExecutorService worker = Executors.newCachedThreadPool();

	/**
	 * Reads an image from a file inside the current classpath.
	 * 
	 * @return the image; can be displayed and used in ImageIcons.
	 */
	public static Image loadImage(String path) {
		URL imgUrl = Utils.class.getClassLoader().getResource(path);
		return Toolkit.getDefaultToolkit().createImage(imgUrl);
	}

	/**
	 * Extracts the first match of a pattern against a string.
	 * 
	 * @param pattern
	 *            to use, which MUST contain parenthesis. For example, to
	 *            extract 15 from <code>"a: 12, b: 15, c: -8"</code>, the
	 *            pattern <code>"b: ([0-9-]+)"</code> could be used.
	 * @param source
	 *            string, which is expected to contain the pattern
	 * @return the first match; or an exception if no matches are found.
	 */
	public static String extractMatch(String pattern, String source) {
		Matcher m = Pattern.compile(pattern).matcher(source);
		if (m.find()) {
			return m.group(1);
		} else {
			throw new IllegalArgumentException("Not found: " + pattern + " in " + source);
		}
	}

	/**
	 * Generates an iterator for generating random colors. It generates the same
	 * sequences of colors over different runs since it always uses the same
	 * seed.
	 * 
	 * 
	 * @return An iterator for generating random colors.
	 */

	public static Iterator<Color> colorsGenerator() {

		Iterator<Color> i = new Iterator<Color>() {
			// since we use a fixed seed, we always get the same sequence of
			// colors
			//
			private Random r = new Random(314159265);

			@Override
			public Color next() {
				return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
			}

			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("read-only iterator");
			}
		};

		return i;

	}

}
