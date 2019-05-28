package qrCodeReading;

import java.awt.image.BufferedImage;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;

/**
 * This class includes two scanners designed to scan through Buffered images and find all QR Codes
 * @author Jeffr
 * @version 1
 * @since 5/28/19
 */
public class QRScanners 
{
	/**
	 * This method Scans a given image to find all QrCodes
	 * @param img a that will be scanned
	 * @return A list of item encoded in all QrCodes in the given image null if no QrCode is found or correctly scanned
	 */
	public String[] Multiscan(BufferedImage img)
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
           return vals;
        } 
        catch (Exception e) 
        { return null; }
	}
	
	/**
	 * Scan a image for a single QR codes. This method will fail if there are multiple QR codes in the given image
	 * @param img the given image in which this method will scan through
	 * @return the result stored in the QR code. Null if scanning fails or nothing is found or multiple QR codes exist
	 */
	public String singleScan(BufferedImage img)
    {
        LuminanceSource source = new BufferedImageLuminanceSource(img);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try 
        {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (Exception e) 
        {
            return null;
        }
    }
}
