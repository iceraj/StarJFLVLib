/**
 * 
 */
package edu.mit.star.flv.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.zip.Deflater;

class ScreenVideoDataImageblock implements DataWritter
{
	int dataSize; // 16bit
	byte[] data; // zlib compressed BGR

	public ScreenVideoDataImageblock(byte[] uncompressed)
	{
		Deflater compressor = new Deflater();
		compressor.setInput(uncompressed);
		compressor.finish();
		byte[] output = new byte[uncompressed.length];
		dataSize = compressor.deflate(output);
		data = new byte[dataSize];
		System.arraycopy(output, 0, data, 0, dataSize);
	}

	public void write(DataOutputStream dos) throws IOException
	{
		dos.writeShort(dataSize);
		dos.write(data);
	}

	public void write(DataOutputStream os, DataWritter last) throws IOException
	{
		if (last instanceof ScreenVideoDataImageblock)
		{
			ScreenVideoDataImageblock lastBlock = (ScreenVideoDataImageblock) last;
			if( lastBlock.dataSize == dataSize )
			{
				if( Arrays.equals(lastBlock.data, data))
				{
					write(os);
					//os.writeShort(0);
				}
				else
				{
					write(os);
				}
			}
			else
			{
				write(os);
			}
		}
		else
		{
			write(os);
		}
	}
}