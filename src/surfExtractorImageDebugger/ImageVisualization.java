package surfExtractorImageDebugger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import boofcv.abst.feature.detdesc.DetectDescribePoint;
import boofcv.struct.feature.SurfFeature;
import boofcv.struct.image.ImageFloat32;

public class ImageVisualization extends JFrame {
	private BufferedImage image;
	private DetectDescribePoint<ImageFloat32, SurfFeature> features;
	private String path;
	private JPanelImage jpi = null;

	public ImageVisualization() {
		this.jpi = new JPanelImage();
		this.add(this.jpi);
	}

	public void repaint() {
		jpi.setPath(path);
		this.jpi.repaint();
	}

	public void setImagePath(String path) {
		this.path = path;
	}

	public void setImageFeatures(DetectDescribePoint<ImageFloat32, SurfFeature> features) {
		this.jpi.setFeatures(features);
	}
}
