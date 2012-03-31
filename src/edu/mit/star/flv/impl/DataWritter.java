package edu.mit.star.flv.impl;

import java.io.IOException;

public interface DataWritter
{
	public void write(DataOutputStream os) throws IOException;

	public void write(DataOutputStream os, DataWritter last) throws IOException;

}
