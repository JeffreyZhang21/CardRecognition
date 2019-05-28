package gui;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import processing.core.PApplet;
import processing.core.PImage;
import processing.video.*;
import qrCodeReading.QRScanners;

public class GUI extends PApplet
{
	public static void main(String[] args)
	{ PApplet.main("gui.GUI"); }
	
	private Capture cam;
	private QRScanners qrScan= new QRScanners();
	private HashMap<String,String> cardToImg= new HashMap<String,String>();
	
	
	public void settings()
    {
        size(1280, 720);
    }
    
    public void setup()
    {
    	setUpCam(0);
    	setUpMap();
    }
    
    public void draw()
    {
    	render(0,0);
    }
    
    public void render(int x, int y)
    {
    	renderBackground(x,y);
    	if(cam.available())
    	{
			renderCam(x,y);
			String[] qrCodes = scan(x,y,cam.width,cam.height);
			if(qrCodes != null)
			{
				for(int i=0; i<qrCodes.length; i++)
				{
					PImage img = new PImage();
					img = loadImage("QrCodes//" +cardToImg.get(qrCodes[i]));
					image(img,i * img.width,cam.height);
				}
			}
    	}
    }
    
    public void renderBackground(int x, int y)
    {
    	fill(255);
    	noStroke();
    	rect(cam.width + x,y,width - cam.width + x, height);
    	rect(x,cam.height + y, cam.width,height-cam.height + y);
    }
    
    public void setUpCam(int choosen)
    {
    	String[] cameras = Capture.list();
        System.out.println("Current Camera: " + cameras[choosen].toString()); 
        cam = new Capture(this, cameras[choosen]);
        cam.start();
    }
        
	public void renderCam(int x, int y)
	{
        cam.read();
        image(cam, x, y);
        
	}

	public String[] scan(int x, int y, int w, int h)
	{ return qrScan.Multiscan(((BufferedImage) cam.getImage()).getSubimage(x, y, w, h)); }
	
 	public String[] scan(int x, int y)
	{ 	return qrScan.Multiscan((BufferedImage) cam.getImage()); }	

 	public void setUpMap()
 	{
 		final String[] types = new String[] {"diamonds","hearts","spades","clovers"};
 		for(String suit: types)
 			for(int number=1; number<13; number++)
 				cardToImg.put(suit.charAt(0) + " " + number, number + " of " + suit + ".png" );
 	}
 	
}
