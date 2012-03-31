Nov 3, 2008 - FLV & SWT PDFs
http://www.adobe.com/devnet/flv/pdf/video_file_format_spec_v9.pdf
http://www.adobe.com/devnet/swf/pdf/swf_file_format_spec_v9.pdf

Oct 17, 2008 - jflvlib-0.1 released

JFlvLib is a library that allows writing of Flash Videos (FLV format).

To use library user instantiates Capture instance using CaptureFactory class.
	Capture c = CaptureFactory.getCapturer( os , new Dimension(xx , yy) ) ;
	
Capture interface provides 2 methods:
	BufferedImage newFrame(); // generates new frame in compatible format for writing frame
	void writeFrame( BufferedImage image , int timestamp ) throws IOException;	// writes a frame to FLV stream
	
At this stage library implements video only, SCREENVIDEO_CODEC and it generates only key frames. This is good for producting videos but they are larger than they need to be.
  
That's it.

TODO:
	Optimize FLV creation writing not only keyframes but also interframes.
	Implement other codecs (JPEG, H263, ...)
	Add audio support
	
Example:
	package edu.mit.star.flvexample;
	
	import java.awt.Dimension;
	import java.awt.image.BufferedImage;
	import java.io.IOException;
	import java.io.OutputStream;
	
	import edu.mit.star.flv.Capture;
	import edu.mit.star.flv.CaptureFactory;
	
	
	public class Main
	{
	
		public static void main(String[] args)
	    {
			try
			{
				OutputStream os = new java.io.FileOutputStream( "z:\\temp\\test.flv" ); 
				int xx = 256 ;
				int yy = 512 ;
				Capture c = CaptureFactory.getCapturer( os , new Dimension(xx , yy) ) ;
				int time = 0 ;
				while( time < 5000 )
				{
					BufferedImage image = c.newFrame() ;
					
					image.getGraphics().drawOval(0, 0, xx*time/5000, yy*time/5000);
					image.flush();
					
					c.writeFrame(image, time);
					time += 250;
					System.out.println( "Generating " + time + " ms frame.");
				}
				os.close();
			}
			catch( IOException ex )
			{
				ex.printStackTrace();
			}
	    }
	}
	
		