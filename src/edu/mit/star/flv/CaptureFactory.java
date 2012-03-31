package edu.mit.star.flv;

import java.awt.Dimension;
import java.io.IOException;
import java.io.OutputStream;

import edu.mit.star.flv.impl.Capturer;

public class CaptureFactory
{
	/**
	 * Creates new FLV capture Stream
	 * @param os stream to write FLV 
	 * @param captureSize size of images written
	 * @return an instance of @Capture
	 * @throws IOException
	 */
	public static Capturer getCapturer( OutputStream os , Dimension captureSize ) throws IOException
	{
		return new Capturer( os , captureSize ) ;
	}

	/**
	 * Creates new FLV capture Stream
	 * @param os stream to write FLV 
	 * @param captureSize size of images written
	 * @param timeBetweenKeyframes number of milliseconds between two keyframes
	 * @return an instance of @Capture
	 * @throws IOException
	 */
	public static Capturer getCapturer( OutputStream os , Dimension captureSize , int timeBetweenKeyframes ) throws IOException
	{
		return new Capturer( os , captureSize , timeBetweenKeyframes ) ;
	}
	
}
