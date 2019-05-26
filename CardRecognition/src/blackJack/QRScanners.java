package blackJack;

import java.awt.image.BufferedImage;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;

public class QRScanners 
{
	public String[] scan(BufferedImage img)
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
        } 
        catch (Exception e) 
        { return null; }
		return null;
	}
}
