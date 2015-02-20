package surfExtractorImageDebugger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import boofcv.abst.feature.detdesc.DetectDescribePoint;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.feature.SurfFeature;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageUInt8;
import ij.IJ;
import ij.ImageJ;
import surfExtractor.SurfDescriptor;
import surfExtractor.image_set.Image;
import surfExtractor.image_set.ImageClass;
import surfExtractor.image_set.ImageSet;

public class Main {

	public ParameterInterface pi = new ParameterInterface(this);
	public ImageInterface ii = new ImageInterface(this);
	public SurfDescriptor se = new SurfDescriptor();

	public int radius = 2;
	public float threshold = 0;
	public int ignoreBorder = 5;
	public boolean strictRule = true;
	public int maxFeaturesPerScale = 500;
	public int initialSampleRate = 2;
	public int initialSize = 9;
	public int numberScalesPerOctave = 4;
	public int numberOfOctaves = 4;

	public long lastImageUpdate = 0;

	public String imagePath = "C:\\polen23e\\acacia_plumosa\\acacia_1.jpg";
	public DetectDescribePoint<ImageFloat32, SurfFeature> imageFeatures;
	public ImageSet is = null;
	Random rand = new Random();

	public static void main(String[] args) {
		try {
			new Main();
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Main() throws InterruptedException, IOException {
		try {
			this.is = new ImageSet("C:\\polen23e");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		pi.setVisible(true);
		ii.setVisible(true);
		BufferedImage bi = IJ.openImage(imagePath).getBufferedImage();
		ImageFloat32 if32 = new ImageFloat32(bi.getWidth(), bi.getHeight());
		ConvertBufferedImage.convertFrom(bi, if32);
		ii.setImageFeatures(se.easy(if32));
		
		ii.drawImage(ImageIO.read(new File(imagePath)));

	}

	public void update() {
		if (System.currentTimeMillis() - lastImageUpdate > 100) {
			System.out.println("Updating image");
			lastImageUpdate = System.currentTimeMillis();
			int counter = rand.nextInt(805);
			for (ImageClass ic : is.getImageClasses()) {
				for (Image i : ic.getImages()) {
					counter--;
					if (counter == 0) {
						this.imagePath = i.getFile().getAbsolutePath();
					}
				}
			}
		}
		System.out.println("Updating extractor parameters");
		se.setRadius(radius);
		se.setThreshold(threshold);
		se.setIgnoreBorder(ignoreBorder);
		se.setStrictRule(strictRule);
		se.setMaxFeaturesPerScale(maxFeaturesPerScale);
		se.setInitialSampleRate(initialSampleRate);
		se.setInitialSize(initialSize);
		se.setNumberOfOctaves(numberOfOctaves);
		se.setNumberScalesPerOctave(numberScalesPerOctave);
		imageFeatures = se.easy(UtilImageIO.loadImage(imagePath, ImageFloat32.class));
		ii.setImageFeatures(imageFeatures);
		System.out.println("Features detected: " + imageFeatures.getNumberOfFeatures());
		try {
			ii.drawImage(ImageIO.read(new File(imagePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
