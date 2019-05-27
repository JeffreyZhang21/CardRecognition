package qrCodeReading;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;

import processing.core.PApplet;
import processing.core.PImage;

public class ReadingTest extends PApplet
{
	public static void main (String[] args)
	{
		PApplet.main("qrCodeReading.ReadingTest");
	}
	
	
	PImage one,two;
	
	
	public void settings()
	{ size(800,800); }
	
	public void setup()
	{
		one = loadImage("QrCodes//10 of spades.png");
		two = loadImage("QrCodes//2 of spades.png");
	}
	
	public void draw()
	{
		image(one,0,0);
		image(two,350,350);
		
	}
	
	public void scan(BufferedImage img)
	{
		String[] vals;
		LuminanceSource source = new BufferedImageLuminanceSource(img);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try 
        {        	
            Result[] results = new QRCodeMultiReader().decodeMultiple(bitmap);
            vals = new String[results.length];
            for(int i=0; i<vals.length; i++)
            	vals[i] = results[i].getText();   
            System.out.println(Arrays.toString(vals));
        } 
        catch (Exception e) 
        {  }
		
	}
	
	public void mousePressed()
	{
		scan((BufferedImage)get().getImage());
	}
}
