package edu.mit.star.flvexample;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import edu.mit.star.flv.Capture;
import edu.mit.star.flv.CaptureFactory;

public class Main
{
	static void paint(BufferedImage image, int time, int xx, int yy)
	{
		Graphics g = image.getGraphics();
		g.setColor(java.awt.Color.red);
		g.fillOval(0, 0, (int) (xx * Math.abs(Math.sin(time / 500.0f))), (int) (yy * Math.abs(Math.sin(time / 500.0f))));
		image.flush();
	}

	public static void main(String[] args)
	{
		try
		{
			OutputStream os = new java.io.FileOutputStream("z:\\temp\\test.flv");
			OutputStream osi = new java.io.FileOutputStream("z:\\temp\\test_i.flv");

			int xx = 256;
			int yy = 512;
			Capture c = CaptureFactory.getCapturer(os, new Dimension(xx, yy));
			Capture ci = CaptureFactory.getCapturer(osi, new Dimension(xx, yy), 1000);
			int time = 0;
			while (time < 10000)
			{
				System.out.println("Generating " + time + " ms frame.");
				BufferedImage image = c.newFrame();
				BufferedImage image_i = ci.newFrame();

				paint(image, time, xx, yy);
				paint(image_i, time, xx, yy);

				c.writeFrame(image, time);
				ci.writeFrame(image_i, time);

				time += 100;
			}
			os.close();
			osi.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
