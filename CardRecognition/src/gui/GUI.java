package gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashSet;

import blackJack.*;
import processing.core.PApplet;
import processing.video.*;
import qrCodeReading.QRScanners;

public class GUI extends PApplet
{
	public static void main(String[] args)
	{ PApplet.main("gui.GUI"); }
	
	private Capture cam;
	private QRScanners qrScan= new QRScanners();
	private HashSet<Card> usedCards = new HashSet<Card>();
	private CardStack dealersCards = new CardStack();
	private CardStack playersCards = new CardStack();
	
	
	public void settings()
    {
        size(1280, 720);
    }
    
    public void setup()
    {
    	setupCam(0);
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
			System.out.println(Arrays.toString(qrCodes));
    	}

    }
    
    public void renderBackground(int x, int y)
    {
    	fill(255);
    	noStroke();
    	rect(cam.width + x,y,width - cam.width + x, height);
    	rect(x,cam.height + y, cam.width,height-cam.height + y);
    }
    
    public void setupCam(int choosen)
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
	{ return qrScan.scan(((BufferedImage) cam.getImage()).getSubimage(x, y, w, h)); }
	
 	public String[] scan(int x, int y)
	{ 	return qrScan.scan((BufferedImage) cam.getImage()); }	

}
