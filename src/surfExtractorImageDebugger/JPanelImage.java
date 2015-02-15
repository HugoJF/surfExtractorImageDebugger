package surfExtractorImageDebugger;

import georegression.struct.point.Point2D_F64;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import boofcv.abst.feature.detdesc.DetectDescribePoint;
import boofcv.struct.feature.SurfFeature;
import boofcv.struct.image.ImageFloat32;

public class JPanelImage extends JPanel {

	private BufferedImage image;
	private DetectDescribePoint<ImageFloat32, SurfFeature> features;
	private int rectSize = 3;
	private String path;

	public JPanelImage() {
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
		drawFeatures(g);
	}
	
	public void drawFeatures(Graphics g) {
		for (int i = 0; i < features.getNumberOfFeatures(); i++) {
			Point2D_F64 sf = features.getLocation(i);
			g.drawRect((int) sf.x - (rectSize / 2), (int) sf.y - (rectSize / 2), rectSize, rectSize);
		}
	}
	
	public void setPath(String path) {
		this.path = path;
		try {
			this.image = ImageIO.read(new File(this.path));
			this.setSize(image.getWidth(), image.getHeight());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setFeatures(DetectDescribePoint<ImageFloat32, SurfFeature> features) {
		this.features = features;
		
	}

}
