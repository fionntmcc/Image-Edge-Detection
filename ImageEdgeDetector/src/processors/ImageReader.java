package processors;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageReader {
	public BufferedImage read(String fileName) throws IOException {
		int width = 936;
    	int height = 640;
    	
    	BufferedImage img = null;
    	
    	File input_file = new File( 
                fileName); 
  
    	// image file path create an object of 
        // BufferedImage type and pass as parameter the 
    	// width,  height and image int 
    	// type. TYPE_INT_ARGB means that we are 
    	// representing the Alpha , Red, Green and Blue 
    	// component of the image pixel using 8 bit 
    	// integer value. 
  
    	img = new BufferedImage( 
    		width, height, BufferedImage.TYPE_INT_ARGB); 
  
    	// Reading input file 
        img = ImageIO.read(input_file); 
  
        System.out.println("Reading complete."); 
        
        return img;
            
        /*
        File output_file = new File( 
        	"out.jpg"); 
      
        // Writing to file taking type and path as 
        ImageIO.write(img, "jpg", output_file); 
      
        System.out.println("Writing complete."); 
         */
	}
}
