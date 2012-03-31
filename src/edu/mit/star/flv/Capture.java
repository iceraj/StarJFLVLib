package edu.mit.star.flv;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *  An interface to render movies
 * @author ceraj
 *
 */
public interface Capture
{
	/**
	 * Creates new frame optimized for this capturer
	 * @return BufferedImage that can be written to, optimized for this capturer
	 */
	BufferedImage newFrame();

	/**
	 * Writes Image to capture stream
	 * @param image Image to write
	 * @param timestamp Time stamp in milliseconds
	 * @throws IOException
	 */
	void writeFrame( BufferedImage image , int timestamp ) throws IOException;
}
