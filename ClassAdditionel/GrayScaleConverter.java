package ClassAdditionel;
/**
 * Create a gray scale version of an image by setting all color components of each pixel to the same value.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {

	public static void runGrayScaleConverter (String[] args) {

		ClassAdditionel.GrayScaleConverter run = new ClassAdditionel.GrayScaleConverter();
		//run.testGray();
		//run.doBatchGrey();
		run.doBatchInversion();

	}


	//I started with the image I wanted (inImage)
	public ImageResource makeGray(ImageResource inImage) {
		//I made a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's red + inPixel's blue + inPixel's green
			//divide that sum by 3 (call it average)
			int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
			//set pixel's red to average
			pixel.setRed(average);
			//set pixel's green to average
			pixel.setGreen(average);
			//set pixel's blue to average
			pixel.setBlue(average);
		}

		return outImage;
	}

	public ImageResource makeInversion(ImageResource inImage) {
		//A blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//invert the value of all pixels
			pixel.setRed(255 - inPixel.getRed());
			pixel.setGreen(255 - inPixel.getGreen());
			pixel.setBlue(255 - inPixel.getBlue());
		}

		return outImage;
	}

	public void doBatchGrey() {
		DirectoryResource dr = new DirectoryResource();
		for (File f: dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource gray = makeGray(inImage);
			gray.draw();
			doSave(inImage, gray, "gray-");
		}
	}

	public void doBatchInversion() {
		DirectoryResource dr = new DirectoryResource();
		for (File f: dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource inverted = makeInversion(inImage);
			inverted.draw();
			doSave(inImage, inverted, "inverted-");
		}
	}

	public void doSave(ImageResource originalImage, ImageResource newImage, String typeSuffix) {
		String fname = originalImage.getFileName();
		String newName = typeSuffix + fname;
		newImage.setFileName(newName);
		newImage.save();

	}

	public void testGray() {
		ImageResource ir = new ImageResource();
		ImageResource gray = makeGray(ir);
		gray.draw();
	}
}
