package surfExtractorImageDebugger;

import georegression.struct.point.Point2D_F64;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import boofcv.abst.feature.detdesc.DetectDescribePoint;
import boofcv.struct.feature.SurfFeature;
import boofcv.struct.image.ImageFloat32;
import surfExtractor.surf_extractor.TaggedSurfFeature;

public class ImageJPanel extends JPanel {

	private BufferedImage image = null;

	private DetectDescribePoint<ImageFloat32, SurfFeature> features = null;

	private int rectSize = 5;

	private float factor = -1;

	/**
	 * Create the panel.
	 */
	public ImageJPanel() {
		setLayout(null);
		setSize(500, 500);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int maxSize = 0;
		if (image != null) {
			factor = -1;
			if (this.getHeight() <= this.getWidth()) {
				maxSize = this.getHeight();
				factor = (float) maxSize / (float) image.getHeight();
			} else {
				maxSize = this.getWidth();
				factor = (float) maxSize / (float) image.getWidth();
			}
			g.drawImage(this.image, 0, 0, (int) ((float) image.getWidth() * factor), (int) ((float) image.getHeight() * factor), this);

			if (this.features != null) {
				drawFeatures(g);
			}
		}
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	private void drawFeatures(Graphics g) {
		double minScale, maxScale, deltaScale;
		minScale = maxScale = this.features.getScale(0);
		for (int i = 0; i < this.features.getNumberOfFeatures(); i++) {
			if (this.features.getScale(i) < minScale)
				minScale = this.features.getScale(i);
			if (this.features.getScale(i) > maxScale)
				maxScale = this.features.getScale(i);
		}

		deltaScale = maxScale - minScale;

		for (int i = 0; i < this.features.getNumberOfFeatures(); i++) {
			double fScale = (this.features.getScale(i) - minScale) / maxScale;
			Point2D_F64 sf = this.features.getLocation(i);
			g.setColor(new Color((int) (255 * fScale), 0, 0));
			g.drawOval((int) (sf.x * factor) - (rectSize / 2), (int) (sf.y * factor) - (rectSize / 2), rectSize, rectSize);
			g.fillOval((int) (sf.x * factor) - (rectSize / 2), (int) (sf.y * factor) - (rectSize / 2), rectSize, rectSize);
		}
	}

	public void setImageFeatures(DetectDescribePoint<ImageFloat32, SurfFeature> features) {
		this.features = features;
	}

}
