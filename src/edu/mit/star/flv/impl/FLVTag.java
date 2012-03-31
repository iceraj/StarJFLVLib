/**
 * 
 */
package edu.mit.star.flv.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

class FLVTag
{
	final static byte AUDIO = 8;
	final static byte VIDEO = 9;
	final static byte SCRIPT = 18;

	byte tagType;
	int dataSize_24;
	int timeStamp = 0; // in ms
	final int StreamID_24 = 0;
	DataWritter data;

	public FLVTag(BufferedImage image, int timestamp)
	{
		tagType = VIDEO ;
		timeStamp = timestamp;
		data = new VideoData(image);
	}

	public void write(DataOutputStream os) throws IOException
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024 * 1024);
		data.write(new DataOutputStream(bos));
		dataSize_24 = bos.size();

		os.writeByte(tagType);
		os.write24(dataSize_24);
		os.write24(timeStamp & 0x00ffffff);
		os.write8((byte) (timeStamp >> 24 & 0xff));
		os.write24(StreamID_24);
		bos.writeTo(os);
		os.writeInt(dataSize_24 + 11);

	}

	public void write(DataOutputStream os, FLVTag lastTag ) throws IOException
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024 * 1024);
		data.write(new DataOutputStream(bos),lastTag.data);
		dataSize_24 = bos.size();

		os.writeByte(tagType);
		os.write24(dataSize_24);
		os.write24(timeStamp & 0x00ffffff);
		os.write8((byte) (timeStamp >> 24 & 0xff));
		os.write24(StreamID_24);
		bos.writeTo(os);
		os.writeInt(dataSize_24 + 11);

	}

}