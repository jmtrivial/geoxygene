/*******************************************************************************
 * This software is released under the licence CeCILL
 * 
 * see Licence_CeCILL-C_fr.html see Licence_CeCILL-C_en.html
 * 
 * see <a href="http://www.cecill.info/">http://www.cecill.info/a>
 * 
 * @copyright IGN
 ******************************************************************************/
package fr.ign.cogit.geoxygene.appli.plugin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.flickr4java.flickr.FlickrException;

import fr.ign.cogit.geoxygene.api.feature.IFeature;
import fr.ign.cogit.geoxygene.api.feature.IFeatureCollection;
import fr.ign.cogit.geoxygene.api.feature.IPopulation;
import fr.ign.cogit.geoxygene.api.spatial.geomprim.IPoint;
import fr.ign.cogit.geoxygene.appli.GeOxygeneApplication;
import fr.ign.cogit.geoxygene.appli.api.ProjectFrame;
import fr.ign.cogit.geoxygene.feature.FT_FeatureCollection;
import fr.ign.cogit.geoxygene.feature.Population;
import fr.ign.cogit.geoxygene.filter.PropertyIsEqualTo;
import fr.ign.cogit.geoxygene.filter.expression.Literal;
import fr.ign.cogit.geoxygene.filter.expression.PropertyName;
import fr.ign.cogit.geoxygene.schema.schemaConceptuelISOJeu.FeatureType;
import fr.ign.cogit.geoxygene.style.ExternalGraphic;
import fr.ign.cogit.geoxygene.style.FeatureTypeStyle;
import fr.ign.cogit.geoxygene.style.Fill;
import fr.ign.cogit.geoxygene.style.Graphic;
import fr.ign.cogit.geoxygene.style.Layer;
import fr.ign.cogit.geoxygene.style.Mark;
import fr.ign.cogit.geoxygene.style.PointSymbolizer;
import fr.ign.cogit.geoxygene.style.Rule;
import fr.ign.cogit.geoxygene.style.Style;
import fr.ign.cogit.geoxygene.style.StyledLayerDescriptor;
import fr.ign.cogit.geoxygene.style.Symbolizer;
import fr.ign.cogit.geoxygene.style.UserStyle;
import fr.ign.cogit.geoxygene.vgi.FlickRFeature;
import fr.ign.cogit.geoxygene.vgi.FlickRLoader;

public class VGIPlugin implements ProjectFramePlugin,
    GeOxygeneApplicationPlugin {

  private GeOxygeneApplication application = null;
  private JCheckBoxMenuItem showPhotos;
  private final static String FLICKR_LAYER = "FlickR photos";
  private final static String FLICKR_API_KEY = "d4b5eda00cb0aa5729a984b8068661ae";
  private final static String FLICKR_API_SECRET = "9265cf056ce5c42c";

  @Override
  public void initialize(GeOxygeneApplication application) {
    this.application = application;
    JMenu menu = new JMenu("VGI");
    JMenu flickrMenu = new JMenu("FlickR");
    flickrMenu.add(new JMenuItem(new LoadFlickRAction()));
    showPhotos = new JCheckBoxMenuItem(new ShowFlickRAction());
    flickrMenu.add(showPhotos);
    JMenu twitterMenu = new JMenu("Twitter");
    menu.add(flickrMenu);
    menu.add(twitterMenu);
    application.getMainFrame().getMenuBar()
        .add(menu, application.getMainFrame().getMenuBar().getMenuCount() - 2);

  }

  @Override
  public void initialize(ProjectFrame projectFrame) {
    // TODO Auto-generated method stub

  }

  /**
   * Load FlickR photos in the selected extent, and creates a new layer with the
   * features.
   * 
   * @author GTouya
   * 
   */
  class LoadFlickRAction extends AbstractAction {

    /**
   * 
   */
    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(ActionEvent arg0) {
      LoadFlickRFrame frame = new LoadFlickRFrame();
      frame.setVisible(true);

    }

    public LoadFlickRAction() {
      this.putValue(Action.SHORT_DESCRIPTION,
          "Load FlickR data and add as a new layer");
      this.putValue(Action.NAME, "Load FlickR data");
    }
  }

  class LoadFlickRFrame extends JFrame implements ActionListener,
      ChangeListener {

    /****/
    private static final long serialVersionUID = 1L;

    private JSpinner spinLongmin, spinLongmax, spinLatmin, spinLatmax,
        spinAccuracy;
    private JCheckBox checkBox;

    LoadFlickRFrame() {
      super("Load FlickR Data");
      this.setSize(400, 500);
      this.setAlwaysOnTop(true);

      // a panel for the selection box
      JPanel pSelBox = new JPanel();
      checkBox = new JCheckBox("use a selection box");
      checkBox.setSelected(false);
      checkBox.addChangeListener(this);
      JPanel pBounds = new JPanel();
      spinLongmin = new JSpinner(new SpinnerNumberModel(2.1, -180.0, 180.0,
          0.0001));
      spinLongmin.setEnabled(false);
      spinLongmin.setMaximumSize(new Dimension(100, 20));
      spinLongmin.setMinimumSize(new Dimension(100, 20));
      spinLongmin.setPreferredSize(new Dimension(100, 20));
      JSpinner.NumberEditor longminEditor = new JSpinner.NumberEditor(
          spinLongmin, "0.0000");
      spinLongmin.setEditor(longminEditor);
      spinLongmax = new JSpinner(new SpinnerNumberModel(2.2, -180.0, 180.0,
          0.0001));
      spinLongmax.setEnabled(false);
      spinLongmax.setMaximumSize(new Dimension(100, 20));
      spinLongmax.setMinimumSize(new Dimension(100, 20));
      spinLongmax.setPreferredSize(new Dimension(100, 20));
      JSpinner.NumberEditor longmaxEditor = new JSpinner.NumberEditor(
          spinLongmax, "0.0000");
      spinLongmax.setEditor(longmaxEditor);
      spinLatmin = new JSpinner(new SpinnerNumberModel(48.0, -90.0, 90.0,
          0.0001));
      spinLatmin.setEnabled(false);
      spinLatmin.setMaximumSize(new Dimension(100, 20));
      spinLatmin.setMinimumSize(new Dimension(100, 20));
      spinLatmin.setPreferredSize(new Dimension(100, 20));
      JSpinner.NumberEditor latminEditor = new JSpinner.NumberEditor(
          spinLatmin, "0.0000");
      spinLatmin.setEditor(latminEditor);
      spinLatmax = new JSpinner(new SpinnerNumberModel(49.0, -90.0, 90.0,
          0.0001));
      spinLatmax.setEnabled(false);
      spinLatmax.setMaximumSize(new Dimension(100, 20));
      spinLatmax.setMinimumSize(new Dimension(100, 20));
      spinLatmax.setPreferredSize(new Dimension(100, 20));
      JSpinner.NumberEditor latmaxEditor = new JSpinner.NumberEditor(
          spinLatmax, "0.0000");
      spinLatmax.setEditor(latmaxEditor);
      spinAccuracy = new JSpinner(new SpinnerNumberModel(0, 0, 16, 1));
      spinAccuracy.setEnabled(false);
      spinAccuracy.setMaximumSize(new Dimension(50, 20));
      spinAccuracy.setMinimumSize(new Dimension(50, 20));
      spinAccuracy.setPreferredSize(new Dimension(50, 20));
      spinAccuracy.setToolTipText("from 0 (world level) to 16 (street level)");
      JPanel pYBounds = new JPanel();
      pYBounds.add(spinLatmax);
      pYBounds.add(Box.createVerticalStrut(40));
      pYBounds.add(spinLatmin);
      pYBounds.setLayout(new BoxLayout(pYBounds, BoxLayout.Y_AXIS));
      pBounds.add(spinLongmin);
      pBounds.add(pYBounds);
      pBounds.add(spinLongmax);
      pBounds.setLayout(new BoxLayout(pBounds, BoxLayout.X_AXIS));
      pSelBox.add(checkBox);
      pSelBox.add(pBounds);
      pSelBox.add(new JLabel("accuracy: "));
      pSelBox.add(spinAccuracy);
      pSelBox.setLayout(new BoxLayout(pSelBox, BoxLayout.X_AXIS));

      // a panel for the buttons
      JPanel pButtons = new JPanel();
      JButton btnOk = new JButton("OK");
      btnOk.addActionListener(this);
      btnOk.setActionCommand("ok");
      JButton btnCancel = new JButton("Cancel");
      btnCancel.addActionListener(this);
      btnCancel.setActionCommand("cancel");
      pButtons.add(btnOk);
      pButtons.add(btnCancel);
      pButtons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      pButtons.setLayout(new BoxLayout(pButtons, BoxLayout.X_AXIS));

      // layout of the frame
      this.getContentPane().add(Box.createVerticalGlue());
      this.getContentPane().add(pSelBox);
      this.getContentPane().add(Box.createVerticalGlue());
      this.getContentPane().add(pButtons);
      this.getContentPane().setLayout(
          new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
      this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals("ok")) {
        loadData();
        this.dispose();
      } else if (e.getActionCommand().equals("cancel")) {
        this.dispose();
      }
    }

    @Override
    public void stateChanged(ChangeEvent arg0) {
      if (checkBox.isSelected()) {
        spinLongmax.setEnabled(true);
        spinLongmin.setEnabled(true);
        spinLatmax.setEnabled(true);
        spinLatmin.setEnabled(true);
        spinAccuracy.setEnabled(true);
      } else {
        spinLongmax.setEnabled(false);
        spinLongmin.setEnabled(false);
        spinLatmax.setEnabled(false);
        spinLatmin.setEnabled(false);
        spinAccuracy.setEnabled(false);
      }
    }

    private void loadData() {
      // create the road feature collection from the selected features
      IFeatureCollection<FlickRFeature> photos = new FT_FeatureCollection<>();
      FeatureType ft = new FeatureType();
      ft.setGeometryType(IPoint.class);
      photos.setFeatureType(ft);

      FlickRLoader loader = new FlickRLoader(FLICKR_API_KEY, FLICKR_API_SECRET);
      try {
        List<FlickRFeature> features = loader.getPhotosFromExtent(
            (Double) spinLatmin.getValue(), (Double) spinLatmax.getValue(),
            (Double) spinLongmin.getValue(), (Double) spinLongmax.getValue(),
            (Integer) spinAccuracy.getValue());
        photos.addAll(features);
      } catch (FlickrException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ParserConfigurationException e) {
        e.printStackTrace();
      } catch (SAXException e) {
        e.printStackTrace();
      }

      // put the photos in a new layer
      ProjectFrame pFrame = application.getMainFrame()
          .getSelectedProjectFrame();
      Layer layer = pFrame.getSld().createLayer(FLICKR_LAYER, IPoint.class,
          Color.RED);
      // create the layer style
      Style rawStyle = new UserStyle();
      FeatureTypeStyle ftStyle = new FeatureTypeStyle();
      rawStyle.getFeatureTypeStyles().add(ftStyle);
      Rule rule = new Rule();
      ftStyle.getRules().add(rule);
      Color color = Color.RED;
      PointSymbolizer symbolizer = new PointSymbolizer();
      symbolizer.setGeometryPropertyName("geom");
      symbolizer.setUnitOfMeasure(Symbolizer.PIXEL);
      Graphic graphic = new Graphic();
      Mark mark = new Mark();
      mark.setWellKnownName("circle");
      Fill fill = new Fill();
      fill.setColor(color);
      mark.setFill(fill);
      graphic.getMarks().add(mark);
      symbolizer.setGraphic(graphic);
      rule.getSymbolizers().add(symbolizer);
      layer.getStyles().add(rawStyle);

      IPopulation<IFeature> pop = new Population<>(FLICKR_LAYER);
      pop.addAll(photos);
      pFrame.getSld().getDataSet().addPopulation(pop);
      pFrame.getSld().add(layer);
    }
  }

  /**
   * 
   * @author GTouya
   * 
   */
  class ShowFlickRAction extends AbstractAction {

    /**
   * 
   */
    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(ActionEvent arg0) {

      StyledLayerDescriptor sld = application.getMainFrame()
          .getSelectedProjectFrame().getSld();
      Layer layer = sld.getLayer(FLICKR_LAYER);
      List<Style> styles = layer.getStyles();
      if (showPhotos.isSelected()) {
        // add a new style in the SLD to show photos
        UserStyle style = new UserStyle();
        style.setName("photos");
        styles.add(style);
        FeatureTypeStyle fts = new FeatureTypeStyle();
        List<FeatureTypeStyle> ftsList = new ArrayList<FeatureTypeStyle>();
        ftsList.add(fts);
        style.setFeatureTypeStyles(ftsList);
        for (IFeature feat : layer.getFeatureCollection()) {
          if (feat instanceof FlickRFeature) {
            FlickRFeature flickrFeat = (FlickRFeature) feat;
            Rule rule = new Rule();
            fts.addRule(rule);
            rule.setName(flickrFeat.getPhoto().getFlickRId());
            PropertyIsEqualTo filter = new PropertyIsEqualTo(new PropertyName(
                "flickRId"), new Literal(flickrFeat.getPhoto().getFlickRId()));
            rule.setFilter(filter);
            PointSymbolizer symbolizer = new PointSymbolizer();
            List<Symbolizer> symbolizers = new ArrayList<>();
            symbolizers.add(symbolizer);
            rule.setSymbolizers(symbolizers);
            Graphic graphic = new Graphic();
            symbolizer.setGraphic(graphic);
            symbolizer.setGeometryPropertyName("geom");
            symbolizer.setUnitOfMeasurePixel();
            graphic.setSize(50);
            ExternalGraphic externalGraphic = new ExternalGraphic();
            List<ExternalGraphic> externalGraphics = new ArrayList<>();
            externalGraphics.add(externalGraphic);
            graphic.setExternalGraphics(externalGraphics);
            externalGraphic.setHref(flickrFeat.getPhotoUrlSmall());
            externalGraphic.setFormat("image/jpg");
          }
        }
      } else {
        // remove the style from the SLD
        Style phStyle = null;
        for (Style style : styles) {
          if ("photos".equals(style.getName())) {
            phStyle = style;
            break;
          }
        }
        if (phStyle != null)
          styles.remove(phStyle);
      }
    }

    public ShowFlickRAction() {
      this.putValue(Action.NAME, "Show FlickR photos");
    }
  }
}
