/**
 * 
 */
package edu.mit.star.flv.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;

class VideoData implements DataWritter
{
	final static byte KEYFRAME = 1 * 16;
	final static byte INTERFRAME = 2 * 16;
	final static byte DISPOSABLEINTERFRAME = 3 * 16;
	final static byte GENERATEDKEYFRAME = 4 * 16;
	final static byte INFOCOMMAND = 5 * 16;

	final static byte JPEG_CODEC = 1;
	final static byte H263_CODEC = 2;
	final static byte SCREENVIDEO_CODEC = 3;
	final static byte On2VP6_CODEC = 4;
	final static byte On2VP6_Alpha_CODEC = 5;
	final static byte SCREENVIDEO2_CODEC = 6;
	final static byte AVC = 7;

	byte FrameAndCodec;
	DataWritter videoData;

	public VideoData(BufferedImage image)
	{
		FrameAndCodec = KEYFRAME | SCREENVIDEO_CODEC;
		videoData = new ScreenVideoData(image, 5);
	}

	public void write(DataOutputStream dos) throws IOException
	{
		dos.write8(FrameAndCodec);
		videoData.write(dos);
		dos.flush();
	}

	public void write(DataOutputStream dos, DataWritter lastTag) throws IOException
	{
		if (lastTag instanceof VideoData)
		{
			VideoData last = (VideoData)lastTag;
			FrameAndCodec = INTERFRAME | SCREENVIDEO_CODEC;
			dos.write8(FrameAndCodec);
			videoData.write(dos, last.videoData);
			dos.flush();
		}
		else
		{
			write(dos);
		}
	}

}