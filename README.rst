=================
JFlvLib - Java Library that writes Flash Videos (FLV format).
=================

To use library user instantiates Capture instance using CaptureFactory class.
``Capture c = CaptureFactory.getCapturer( os , new Dimension(xx , yy) ) ;``

Capture interface provides 2 methods:
// generates new frame in compatible format for writing frame
``BufferedImage newFrame();``

// writes a frame to FLV stream
``void writeFrame( BufferedImage image , int timestamp ) throws IOException;``

At this stage library implements video only, SCREENVIDEO_CODEC and it generates only key frames. This is good for producting videos but they are larger than they need to be.

That's it.
