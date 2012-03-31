/**
 * 
 */
package edu.mit.star.flv.impl;

import java.io.IOException;
import java.io.OutputStream;

public class DataOutputStream extends java.io.DataOutputStream
{
	public DataOutputStream(OutputStream out)
    {
        super(out);
    }

	public void write8(byte data) throws IOException
	{
		writeByte(data);
	}

	public void write24(int data) throws IOException
	{
		writeByte((byte) (data >> 16 & 0xff));
		writeByte((byte) (data >> 8 & 0xff));
		writeByte((byte) (data >> 0 & 0xff));
	}
}