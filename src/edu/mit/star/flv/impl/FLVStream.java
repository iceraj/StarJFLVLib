package edu.mit.star.flv.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class FLVStream
{
	final static byte VIDEO = 1;
	final static byte AUDIO = 4;
	final static byte FLV_1 = 1;
	
	DataOutputStream os;
	FLVTag lastTag = null ;
	public FLVStream(OutputStream os, byte type) throws IOException
	{
		this.os = new DataOutputStream(os);
		writeHeader(type);
	}

	public void writeImage(BufferedImage image, int timestamp) throws IOException
	{
		FLVTag tag = new FLVTag(image,timestamp);
		tag.write(os);
		lastTag = tag ;
	}

	public void writeInterframeImage(BufferedImage image, int timestamp) throws IOException
	{
		FLVTag tag = new FLVTag(image,timestamp);
		tag.write(os,lastTag);
		//lastTag = tag ;
	}
	
	private void writeHeader(byte type) throws IOException
	{
		os.writeBytes("FLV");
		os.write8(FLV_1);
		os.write8(type);
		os.writeInt(9);
		os.writeInt(0);
	}
}