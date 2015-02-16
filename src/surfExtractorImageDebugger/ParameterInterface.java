package surfExtractorImageDebugger;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window.Type;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ParameterInterface extends JFrame {

	private JPanel contentPane;

	private Main main;

	private boolean autoUpdate = false;

	private JLabel lblRadius;

	private JLabel lblThreshold;

	private JLabel lblIgnoreBorder;

	private JToggleButton tglbtnStrictRule;

	private JSpinner radiusSpn;

	private JSpinner thresholdSpn;

	private JSpinner ignoreBorderSpn;

	private JSpinner maxfeatperscaleSpn;

	private JSpinner initSampSizeSpn;

	private JSpinner initSizeSpn;

	private JSpinner numScalePerOctaveSpn;

	private JSpinner numOctavesSpn;

	private JLabel lblMaxFeaturesPer;

	private JLabel lblInitialSampleRate;

	private JLabel lblInitialSize;

	private JLabel lblNumberOfScales;

	private JLabel lblNumberOfOctaves;

	private JToggleButton tglbtnAutoUpdate;

	private JButton btnUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParameterInterface frame = new ParameterInterface(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param main
	 */
	public ParameterInterface(final Main main) {
		this.main = main;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 232, 400);
		setLocation(getX() + 500, getY());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblRadius = new JLabel("Radius");
		lblRadius.setBounds(60, 14, 32, 14);
		contentPane.add(lblRadius);

		lblThreshold = new JLabel("Threshold");
		lblThreshold.setBounds(60, 45, 47, 14);
		contentPane.add(lblThreshold);

		lblIgnoreBorder = new JLabel("Ignore Border");
		lblIgnoreBorder.setBounds(60, 76, 67, 14);
		contentPane.add(lblIgnoreBorder);

		tglbtnStrictRule = new JToggleButton("Use Strict Rule");
		tglbtnStrictRule.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				main.strictRule = tglbtnStrictRule.isSelected();
				if (autoUpdate)
					sendUpdate();
			}
		});
		tglbtnStrictRule.setSelected(true);
		tglbtnStrictRule.setBounds(10, 104, 121, 23);
		contentPane.add(tglbtnStrictRule);

		radiusSpn = new JSpinner();
		radiusSpn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				main.radius = (int) radiusSpn.getValue();
				if (autoUpdate)
					sendUpdate();
			}
		});
		radiusSpn.setModel(new SpinnerNumberModel(new Integer(2), new Integer(0), null, new Integer(1)));
		radiusSpn.setBounds(10, 11, 40, 20);
		contentPane.add(radiusSpn);

		thresholdSpn = new JSpinner();
		thresholdSpn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				main.threshold = (float) thresholdSpn.getValue();
				if (autoUpdate)
					sendUpdate();
			}
		});
		thresholdSpn.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		thresholdSpn.setBounds(10, 42, 40, 20);
		contentPane.add(thresholdSpn);

		ignoreBorderSpn = new JSpinner();
		ignoreBorderSpn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				main.ignoreBorder = (int) ignoreBorderSpn.getValue();
				if (autoUpdate)
					sendUpdate();
			}
		});
		ignoreBorderSpn.setModel(new SpinnerNumberModel(new Integer(5), new Integer(0), null, new Integer(1)));
		ignoreBorderSpn.setBounds(10, 73, 40, 20);
		contentPane.add(ignoreBorderSpn);

		maxfeatperscaleSpn = new JSpinner();
		maxfeatperscaleSpn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				main.maxFeaturesPerScale = (int) maxfeatperscaleSpn.getValue();
				if (autoUpdate)
					sendUpdate();
			}
		});
		maxfeatperscaleSpn.setModel(new SpinnerNumberModel(new Integer(500), new Integer(0), null, new Integer(1)));
		maxfeatperscaleSpn.setBounds(10, 138, 40, 20);
		contentPane.add(maxfeatperscaleSpn);

		initSampSizeSpn = new JSpinner();
		initSampSizeSpn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				main.initialSampleRate = (int) initSampSizeSpn.getValue();
				if (autoUpdate)
					sendUpdate();
			}
		});
		initSampSizeSpn.setModel(new SpinnerNumberModel(new Integer(2), new Integer(0), null, new Integer(1)));
		initSampSizeSpn.setBounds(10, 169, 40, 20);
		contentPane.add(initSampSizeSpn);

		initSizeSpn = new JSpinner();
		initSizeSpn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				main.initialSize = (int) initSizeSpn.getValue();
				if (autoUpdate)
					sendUpdate();
			}
		});
		initSizeSpn.setModel(new SpinnerNumberModel(new Integer(9), new Integer(0), null, new Integer(1)));
		initSizeSpn.setBounds(10, 200, 40, 20);
		contentPane.add(initSizeSpn);

		numScalePerOctaveSpn = new JSpinner();
		numScalePerOctaveSpn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				main.numberScalesPerOctave = (int) numScalePerOctaveSpn.getValue();
				if (autoUpdate)
					sendUpdate();
			}
		});
		numScalePerOctaveSpn.setModel(new SpinnerNumberModel(new Integer(4), new Integer(0), null, new Integer(1)));
		numScalePerOctaveSpn.setBounds(10, 231, 40, 20);
		contentPane.add(numScalePerOctaveSpn);

		numOctavesSpn = new JSpinner();
		numOctavesSpn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				main.numberOfOctaves = (int) numOctavesSpn.getValue();
				if (autoUpdate)
					sendUpdate();
			}
		});
		numOctavesSpn.setModel(new SpinnerNumberModel(new Integer(4), new Integer(0), null, new Integer(1)));
		numOctavesSpn.setBounds(10, 262, 40, 20);
		contentPane.add(numOctavesSpn);

		lblMaxFeaturesPer = new JLabel("Max Features per Scale");
		lblMaxFeaturesPer.setBounds(60, 141, 113, 14);
		contentPane.add(lblMaxFeaturesPer);

		lblInitialSampleRate = new JLabel("Initial Sample Rate");
		lblInitialSampleRate.setBounds(60, 172, 89, 14);
		contentPane.add(lblInitialSampleRate);

		lblInitialSize = new JLabel("Initial Size");
		lblInitialSize.setBounds(60, 203, 48, 14);
		contentPane.add(lblInitialSize);

		lblNumberOfScales = new JLabel("Number of Scales per Octave");
		lblNumberOfScales.setBounds(60, 234, 140, 14);
		contentPane.add(lblNumberOfScales);

		lblNumberOfOctaves = new JLabel("Number of Octaves");
		lblNumberOfOctaves.setBounds(60, 265, 93, 14);
		contentPane.add(lblNumberOfOctaves);

		tglbtnAutoUpdate = new JToggleButton("Auto Update");
		tglbtnAutoUpdate.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tglbtnAutoUpdate.isSelected()) {
					autoUpdate = true;
				} else {
					autoUpdate = false;
				}
			}
		});
		tglbtnAutoUpdate.setBounds(10, 293, 196, 23);
		contentPane.add(tglbtnAutoUpdate);

		btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sendUpdate();
			}
		});
		btnUpdate.setBounds(10, 327, 196, 23);
		contentPane.add(btnUpdate);
	}

	public void sendUpdate() {
		System.out.println("Trying to send update to Main.class");
		main.update();
	}
}
