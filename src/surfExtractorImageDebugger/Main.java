package surfExtractorImageDebugger;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;

import boofcv.abst.feature.detdesc.DetectDescribePoint;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.struct.feature.SurfFeature;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageUInt8;
import ij.IJ;
import ij.ImageJ;
import surfExtractor.image_set.Image;
import surfExtractor.image_set.ImageClass;
import surfExtractor.image_set.ImageSet;
import surfExtractor.surf_extractor.SurfExtractor;

public class Main {

	public static void main(String[] args) {
		try {
			new Main();
		} catch (FileNotFoundException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Main() throws FileNotFoundException, InterruptedException {
		ImageSet is = new ImageSet("c:/polen23e");
		ArrayList<ImageClass> images = is.getImageClasses();

		ImageVisualization iv = new ImageVisualization();
		iv.setVisible(true);
		iv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		surfExtractor.surf_extractor.SurfExtractor se = new surfExtractor.surf_extractor.SurfExtractor();
		
		for(ImageClass ic : images) {
			for(Image i : ic.getImages()) {
				String path = i.getFile().getAbsolutePath();
				iv.setImagePath(path);
				ImageFloat32 temp = null;
				iv.setImageFeatures(se.easy(ConvertBufferedImage.convertFrom(IJ.openImage(path).getBufferedImage(), temp)));
				iv.repaint();
				Thread.sleep(500);
				System.out.println("Drawing image");
			}
			
		}

	}
}
