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
 * Class that create a QrCode as an image
 * @author Jeffr
 * @version 1
 * @since 5/28/19
 */
public class TestingQRCodeGeneration 
{
	/**
	 * Main Method edit this method to create new QRCodes
	 * @param args
	 */
    public static void main(String[] args)
    {
    	String fileName = "test.png"; 
    	
    	final int width  = 30;
    	final int height = 30;
    	
    	final String qrCodeEncoded = "this is a test";
    	final String fileExtension = ".png";
    	fileName += fileExtension;
    	 	
        try {
            generateQRCodeImage(qrCodeEncoded, width, height, fileName); //<-- this generates the QrCode
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
    }
    
    /**
     * Generate a QrCode with the given parameter. This method will create a file
     * @param text The message that will be encoded
     * @param width The width of the image created
     * @param height The height of the image created
     * @param filePath The file path for where the file will be saved
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