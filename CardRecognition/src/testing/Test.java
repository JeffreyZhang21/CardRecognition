package testing;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.decoder.Decoder;

import processing.core.PApplet;
import processing.core.PImage;

public class Test extends PApplet
{
	public static void main(String[] args)
	{
		PApplet.main("testing.Test");
	}
	
	private PImage img = new PImage();
	private PImage img2 = new PImage();
	private PImage img3 = new PImage();
	public void settings()
	{
		size(800,800);
	}
	
	public void setup()
	{
		
		img = loadImage("QrCodes//1 of diamonds.png");
		img2 = loadImage("QrCodes//2 of spades.png");
		img3 = loadImage("QrCodes//5 of hearts.png");
		image(img,0,0);
		image(img2,400,200);
		image(img3,0,400);
	}
	
	public void draw()
	{
		
	}
	
	public PImage getFrame() 
	{
		  PImage img = new PImage(width, height);
		 
		  g.loadPixels();
		  img.loadPixels();
		 
		  img.pixels = g.pixels;
		 
		  img.updatePixels();
		  g.updatePixels();
		 
		  return img;
	}
	
	public void mousePressed()
	{
		getImage();
	}	
	
	public void getImage()
	{
		BufferedImage bufferedImage = (BufferedImage) getFrame().getImage();

        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try 
        {        	
            Result[] results = new QRCodeMultiReader().decodeMultiple(bitmap);
            for(Result r : results)
            	System.out.println(r.getText());   
        } 
        catch (Exception e) 
        {
            System.out.println("There is no QR code in the image");
        }
	}
}
