package cardRecognitionGUI;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

import processing.core.PApplet;
import processing.video.*;
import qrCodeReading.QRScanners;
import textToSpeechAlt.*;
import blackjack.Suit;

/**
 * This is the graphical user interface generation for the project
 * @author Jeffr
 * @version 1
 * @Since 5/28/19
 */
public class CardRecognitionGUI extends PApplet
{
	public static void main(String[] args)
	{ PApplet.main("cardRecognitionGUI.CardRecognitionGUI"); }
	
	private Capture cam;                                                         // Create a camera which access data from a real camera
	private QRScanners qrScan = new QRScanners();                                // Class that allows easy scanning access for buffered images
	private HashMap<String,String> cardToImg = new HashMap<String,String>();     // Map that converts QrCode Encoding into the qrCodes file path
	private HashSet<String> set = new HashSet<String>();
	private Voice voice = new Voice("kevin16");
	
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
    
    public void mousePressed()
    {
    	set.clear();
    	voice.say("Set cleared");
    	
    }
    
    public void keyPressed()
    {
    	
    }
    
    /**
     * General render method. All displaying methods should be run inside here
     * @param x the X position
     * @param y the Y position
     */
    public void render(int x, int y)
    {
    	renderBackground(x,y);
    	if(cam.available())
    	{
			renderCam(x,y);
			String[] qrCodes = scan(x,y,cam.width,cam.height);

			if(qrCodes != null)
			{
				for(String s: qrCodes)
				{
					String suit = "";
					
					/* Checks the first character of the string to determine the suit */
					
					if(s.substring(0, 1).toLowerCase().equals("s"))
						suit = Suit.SPADES.toString();
					else if(s.substring(0, 1).toLowerCase().equals("d"))
						suit = Suit.DIAMONDS.toString();
					else if(s.substring(0, 1).toLowerCase().equals("c"))
						suit = Suit.CLUBS.toString();
					else
						suit = Suit.HEARTS.toString();
					
					/* 
					 * Checks the number in the string. If the number is not between 2 and 10 inclusive,
					 * the card is either a jack (11), queen (12), king (13), or an ace (1).
					 */
					
					if(!set.contains(s))
					{
						if(s.indexOf("11") != -1)
						{
							System.out.println("jack of" + suit);
							voice.say("jack of " + suit);
						}
						else if(s.indexOf("12") != -1)
						{
							System.out.println("queen of " + suit);
							voice.say("queen of " + suit);
						}
						else if(s.indexOf("13") != -1)
						{
							System.out.println("king of " + suit);
							voice.say("king of " + suit);
						}
						else if(s.indexOf("1") != -1)
						{
							System.out.println("ace of" + suit);
							voice.say("ace of " + suit);
						}
						else
						{
							System.out.println(suit + " ");
							voice.say(Integer.parseInt(s) + "of " + suit);
						}
						
						set.add(s);
					}
				}
			}
    	}
    }

    /**
     * Renders the background. Because camera does not update fast enough the background convert everything white except the area where the camera is
     * @param x the x Position of the camera
     * @param y the y Position of the camera
     */
    public void renderBackground(int x, int y)
    {
    	fill(255);
    	noStroke();
    	rect(cam.width + x,y,width - cam.width + x, height);
    	rect(x,cam.height + y, cam.width,height-cam.height + y);
    }
          
    /**
     * Renders the video feed from the camera
     * @param x the x position for where the video will be
     * @param y the y position for where the video will be
     */
	public void renderCam(int x, int y)
	{
        cam.read();
        image(cam, x, y);
        
	}

	/**
	 * This method uses the scan a certain area of the camera in the rectangle bounded by the parameter
	 * @param x The x Position
	 * @param y The y Position
	 * @param w the Width that will be scanned
	 * @param h the Height that will be scanned
	 * @return a array of string containing all the results return from decoding the QRcodes inside the video feed
	 */
	public String[] scan(int x, int y, int w, int h)
	{ return qrScan.Multiscan(((BufferedImage) cam.getImage()).getSubimage(x, y, w, h)); } 
	
	/**
	 * Scans the entire camera area
	 * @return a array of string containing all the results return from decoding the QRcodes inside the video feed
	 */
 	public String[] scan()
	{ 	return qrScan.Multiscan((BufferedImage) cam.getImage()); }	

 	/**
     * Set the camera up be choosing which camera it will access
     * @param choosen the camera that will be chosen
     */
    public void setUpCam(int choosen)
    {
    	String[] cameras = Capture.list();
        System.out.println("Current Camera: " + cameras[choosen].toString()); 
        cam = new Capture(this, cameras[choosen]);
        cam.start();
    }
 	
    /**
     * Set up the map that will be used to convert encoded information to file location
     */
    public void setUpMap()
 	{
 		final String[] types = new String[] {"diamonds","hearts","spades","clovers"};
 		for(String suit: types)
 			for(int number=1; number<13; number++)
 				cardToImg.put(suit.charAt(0) + " " + number, number + " of " + suit + ".png" );
 	}
 	
}
