package qrCodeGeneration;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Generate QRCodes with file names and encoded data for each card in a deck. This does not generate cards for Jokers
 * This Class uses the zxing library to generate the QrCodes
 * @author Jeffr
 * @version 1
 * @since 5/26/19
 */
public class QRCodeCardGeneration 
{
	/**
	 * Main method in QR Code Generation. This method creates all of the card in the deck
	 * @param args
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws WriterException, IOException
    {
    	final String[] types = new String[] {"diamonds","hearts","spades","clovers"}; // <-- Suit Name
    	final int width = 350;                                                        // <-- Image Width
    	final int height = 350;                                                       // <-- Image Height
    	final String fileExtension = ".png";                                          // <-- File Extension
    	
    	// Generation Occurs here
    	for(String s: types)                       
	    	for(int number = 1; number<=13; number++)
		        generateQRCodeImage(s.charAt(0) + " " + number, width, height, number + " of " + s + fileExtension);    
    	                          //Encoded Data              |   image size |     file Name                
    }
	
	/**
	 * Generate a QR code image with the file the given width and height
	 * @param text hold the text that will be encoded into the QRcode
	 * @param width The width of the image
	 * @param height The height of the image
	 * @param filePath The File Extension
	 * @throws WriterException
	 * @throws IOException
	 */
    private static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException 
    {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}