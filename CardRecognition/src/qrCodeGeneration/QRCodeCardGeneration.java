package qrCodeGeneration;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeCardGeneration {
    

    private static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException 
    {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public static void main(String[] args)
    {
    	final String[] types = new String[] {"diamonds","hearts","spades","clovers"};
    	final int width = 350;
    	final int height = 350;
    	
    	for(String s: types)
    	{
	    	for(int number = 1; number<=13; number++)
	    	{
		        try {
		            generateQRCodeImage(s.charAt(0) + " " + number, width, height, number + " of " + s + ".png");
		        } catch (WriterException e) {
		            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
		        } catch (IOException e) {
		            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
		        }
	    	}
    	}
    }
}