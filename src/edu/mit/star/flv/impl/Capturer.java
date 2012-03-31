package edu.mit.star.flv.impl;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import edu.mit.star.flv.Capture;

public class Capturer implements Capture
{
	FLVStream worker;
	Dimension size;
	int timeBetweenKeyframes = 0;
	int lastKeyFrameTimestamp = Integer.MIN_VALUE/4;
	public Capturer(OutputStream os, Dimension captureSize) throws IOException
	{
		if (captureSize.width % 16 != 0 || captureSize.height % 16 != 0)
		{
			throw new RuntimeException("size needs to be dividibe with 16");
		}
		if( captureSize.height > 4095 || captureSize.width > 4095 )
		{
			throw new RuntimeException("size needs to be less than 4096");
		}
		worker = new FLVStream(os, FLVStream.VIDEO);
		size = captureSize;
	}
	
	public Capturer(OutputStream os, Dimension captureSize, int timeBetweenKeyframes ) throws IOException
	{
		if (captureSize.width % 16 != 0 || captureSize.height % 16 != 0)
		{
			throw new RuntimeException("size needs to be dividibe with 16");
		}
		if( captureSize.height > 4095 || captureSize.width > 4095 )
		{
			throw new RuntimeException("size needs to be less than 4096");
		}
		this.timeBetweenKeyframes = timeBetweenKeyframes ;
		worker = new FLVStream(os, FLVStream.VIDEO);
		size = captureSize;
	}
	

	public BufferedImage newFrame()
	{
		return new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
	}

	public void writeFrame(BufferedImage image, int timestamp) throws IOException
	{
		if( timestamp - lastKeyFrameTimestamp > timeBetweenKeyframes )
		{
			worker.writeImage(image, timestamp);
			lastKeyFrameTimestamp = timestamp ;
		}
		else
		{
			worker.writeInterframeImage(image, timestamp);
		}
	}
}
