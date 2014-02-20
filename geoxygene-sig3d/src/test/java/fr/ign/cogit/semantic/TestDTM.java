package fr.ign.cogit.semantic;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.net.MalformedURLException;

import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Color4f;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;

import fr.ign.cogit.geoxygene.sig3d.semantic.DTM;
import fr.ign.cogit.geoxygene.sig3d.semantic.DTMArea;
import fr.ign.cogit.geoxygene.spatial.coordgeom.DirectPosition;
import fr.ign.cogit.geoxygene.spatial.coordgeom.DirectPositionList;
import fr.ign.cogit.geoxygene.spatial.coordgeom.GM_Envelope;
import fr.ign.cogit.geoxygene.spatial.geomroot.GM_Object;



public class TestDTM extends TestCase {


	// ---------------------------------- ATTRIBUTES ----------------------------------

	//private double epsilon = Math.pow(10, -10);    // Scale error

	private static Logger log = Logger.getLogger(TestDTM.class);

	// ------------------------------------ TESTS -------------------------------------


	@Test
	// --------------------------------------------------------------------------------
	// Test for DTM constructor
	// --------------------------------------------------------------------------------
	public void testDTM1() {

		log.info("Test for DTM constructor");

		// Loading data file in constructor

		String fileName = "src\\test\\resources\\mnt.asc";
		String layerName = "Digital Terrain Model";
		boolean fill = true;
		int exager = 1;
		String imageFileName = "src\\test\\resources\\pva.jpg";

		// Converting image path to URL

		File file = new File(imageFileName);

		try {

			imageFileName = file.toURI().toURL()+"";

		} catch (MalformedURLException e) {

			e.printStackTrace();

		}

		// Defining bounding box
		DirectPosition LLP = new DirectPosition(1,1);
		DirectPosition URP = new DirectPosition(2,2);

		GM_Envelope imageEnvelope = new GM_Envelope(LLP, URP);

		DTM dtm = new DTM(fileName, layerName, fill, exager, imageFileName, imageEnvelope);

		// Tests
		assertTrue("Exageration is incorrect", dtm.getExageration() == exager);
		assertTrue("Nx dimension is incorrect", dtm.getNX() == 51);
		assertTrue("Ny dimension is incorrect", dtm.getNY() == 51);
		assertTrue("X resolution is incorrect", dtm.getStepX() == 200);
		assertTrue("Y resolution is incorrect", dtm.getStepY() == 200);
		assertTrue("Lower left corner is incorrect", dtm.getXIni() == 914900.00);
		assertTrue("Lower left corner is incorrect", dtm.getYIni() ==  6449900.00);

	}

	@Test
	// --------------------------------------------------------------------------------
	// Test for DTM color shade
	// --------------------------------------------------------------------------------
	public void testDTM2() {

		log.info("Test for DTM color shade");

		// Loading data file in constructor

		String fileName = "src\\test\\resources\\mnt.asc";
		String layerName = "Digital Terrain Model";
		boolean fill = true;
		int exager = 1;

		// Colors
		Color[] colorGradation = new Color[5];

		colorGradation[0] = Color.WHITE;
		colorGradation[1] = Color.GREEN;
		colorGradation[2] = Color.RED;
		colorGradation[3] = Color.BLUE;
		colorGradation[4] = Color.BLACK;

		DTM dtm = new DTM(fileName, layerName, fill, exager, colorGradation);

		Color4f color100 = dtm.getColor4f(100);
		Color4f color2000 = dtm.getColor4f(2000);

		// Tests
		assertTrue("Base color is incorrect", color100.getX() == 1);
		assertTrue("Base color is incorrect", color100.getY() == 1);
		assertTrue("Base color is incorrect", color100.getZ() == 1);

		assertTrue("Top color is incorrect", color2000.getX() == 0);
		assertTrue("Top color is incorrect", color2000.getY() == 0);
		assertTrue("Top color is incorrect", color2000.getZ() == 0);

	}

	@Test
	// --------------------------------------------------------------------------------
	// Test for refreshing DTM
	// --------------------------------------------------------------------------------
	public void testDTM3() {

		log.info("Test for refreshing DTM");

		// Loading data file in constructor

		String fileName = "src\\test\\resources\\mnt.asc";
		String layerName = "Digital Terrain Model";
		boolean fill = true;
		int exager = 1;

		// Colors
		Color[] colorGradation = new Color[5];

		colorGradation[0] = Color.WHITE;
		colorGradation[1] = Color.GREEN;
		colorGradation[2] = Color.RED;
		colorGradation[3] = Color.BLUE;
		colorGradation[4] = Color.BLACK;

		DTM dtm = new DTM(fileName, layerName, fill, exager, colorGradation);

		// Refreshing
		dtm.refresh();

		// No error => test has been passed
		assertTrue(true);

	}

	@Test
	// --------------------------------------------------------------------------------
	// Test for creating multi polygon
	// --------------------------------------------------------------------------------
	public void testDTM4() {

		log.info("Test for creating multi polygon");

		// Loading data file in constructor

		String fileName = "src\\test\\resources\\mnt.asc";
		String layerName = "Digital Terrain Model";
		boolean fill = true;
		int exager = 1;

		// Colors
		Color[] colorGradation = new Color[5];

		colorGradation[0] = Color.WHITE;
		colorGradation[1] = Color.GREEN;
		colorGradation[2] = Color.RED;
		colorGradation[3] = Color.BLUE;
		colorGradation[4] = Color.BLACK;

		DTM dtm = new DTM(fileName, layerName, fill, exager, colorGradation);

		// Creating multi polygon
		@SuppressWarnings("unused")
		MultiPolygon mp = dtm.processSurfacicGrid(0,0,100,100);

		// No error => test has been passed
		assertTrue(true);

	}

	@Test
	// --------------------------------------------------------------------------------
	// Test for creating multi strings
	// --------------------------------------------------------------------------------
	public void testDTM5() {

		log.info("Test for creating multi strings");

		// Loading data file in constructor

		String fileName = "src\\test\\resources\\mnt.asc";
		String layerName = "Digital Terrain Model";
		boolean fill = true;
		int exager = 1;

		// Colors
		Color[] colorGradation = new Color[5];

		colorGradation[0] = Color.WHITE;
		colorGradation[1] = Color.GREEN;
		colorGradation[2] = Color.RED;
		colorGradation[3] = Color.BLUE;
		colorGradation[4] = Color.BLACK;

		DTM dtm = new DTM(fileName, layerName, fill, exager, colorGradation);

		// Creating multi polygon
		@SuppressWarnings("unused")
		MultiLineString mls = dtm.processLinearGrid(0,0,100,100);

		// No error => test has been passed
		assertTrue(true);

	}

	@Test
	// --------------------------------------------------------------------------------
	// Test for method to get triangles
	// --------------------------------------------------------------------------------
	public void testDTM6() {

		log.info("Test for method to get triangles");

		// Loading data file in constructor

		String fileName = "src\\test\\resources\\mnt.asc";
		String layerName = "Digital Terrain Model";
		boolean fill = true;
		int exager = 1;

		// Colors
		Color[] colorGradation = new Color[5];

		colorGradation[0] = Color.WHITE;
		colorGradation[1] = Color.GREEN;
		colorGradation[2] = Color.RED;
		colorGradation[3] = Color.BLUE;
		colorGradation[4] = Color.BLACK;

		DTM dtm = new DTM(fileName, layerName, fill, exager, colorGradation);

		// Recovering triangles
		DirectPositionList dpl1 = dtm.getTriangle(9, 9, true);
		DirectPositionList dpl2 = dtm.getTriangle(10, 10, true);
		DirectPositionList dpl3 = dtm.getTriangle(20, 20, true);

		// Recovering altitudes
		double z1 = dpl1.get(0).getZ();
		double z2 = dpl2.get(0).getZ();
		double z3 = dpl3.get(0).getZ();

		// Tests
		assertTrue("Recovered triangle is incorrect", z1 == 530);
		assertTrue("Recovered triangle is incorrect", z2 == 512);
		assertTrue("Recovered triangle is incorrect", z3 == 771);

	}

	@Test
	// --------------------------------------------------------------------------------
	// Test for method to cast coordinates
	// --------------------------------------------------------------------------------
	public void testDTM7() {

		log.info("Test for method to cast coordinates");

		// Loading data file in constructor

		String fileName = "src\\test\\resources\\mnt.asc";
		String layerName = "Digital Terrain Model";
		boolean fill = true;
		int exager = 1;

		// Colors
		Color[] colorGradation = new Color[5];

		colorGradation[0] = Color.WHITE;
		colorGradation[1] = Color.GREEN;
		colorGradation[2] = Color.RED;
		colorGradation[3] = Color.BLUE;
		colorGradation[4] = Color.BLACK;

		DTM dtm = new DTM(fileName, layerName, fill, exager, colorGradation);

		// Projections
		double z1 = dtm.cast(new DirectPosition(914900.00 + 200*10, 6449900.00 + 200*10)).getZ();
		double z2 = dtm.cast(new DirectPosition(914900.00 + 200*11, 6449900.00 + 200*11)).getZ();
		double z3 = dtm.cast(new DirectPosition(914900.00 + 200*12, 6449900.00 + 200*12)).getZ();

		// Tests
		assertTrue("Projected altitude is incorrect", z1 == 480);
		assertTrue("Projected altitude is incorrect", z2 == 445);
		assertTrue("Projected altitude is incorrect", z3 == 396);

	}

	@Test
	// --------------------------------------------------------------------------------
	// Test for method to cast double coordinates
	// --------------------------------------------------------------------------------
	public void testDTM8() {

		log.info("Test for method to cast double coordinates");

		// Loading data file in constructor

		String fileName = "src\\test\\resources\\mnt.asc";
		String layerName = "Digital Terrain Model";
		boolean fill = true;
		int exager = 1;

		// Colors
		Color[] colorGradation = new Color[5];

		colorGradation[0] = Color.WHITE;
		colorGradation[1] = Color.GREEN;
		colorGradation[2] = Color.RED;
		colorGradation[3] = Color.BLUE;
		colorGradation[4] = Color.BLACK;

		DTM dtm = new DTM(fileName, layerName, fill, exager, colorGradation);

		// Projections
		double z1 = dtm.castCoordinate(914900.00 + 200*10, 6449900.00 + 200*10).z;
		double z2 = dtm.castCoordinate(914900.00 + 200*11, 6449900.00 + 200*11).z;
		double z3 = dtm.castCoordinate(914900.00 + 200*12, 6449900.00 + 200*12).z;

		// Tests
		assertTrue("Projected altitude is incorrect", z1 == 480);
		assertTrue("Projected altitude is incorrect", z2 == 445);
		assertTrue("Projected altitude is incorrect", z3 == 396);

	}

	@Test
	// --------------------------------------------------------------------------------
	// Test for method to get 3D enveloppe
	// --------------------------------------------------------------------------------
	public void testDTM9() {

		log.info("Test for method to get 3D enveloppe");

		// Loading data file in constructor

		String fileName = "src\\test\\resources\\mnt.asc";
		String layerName = "Digital Terrain Model";
		boolean fill = true;
		int exager = 1;

		// Colors
		Color[] colorGradation = new Color[5];

		colorGradation[0] = Color.WHITE;
		colorGradation[1] = Color.GREEN;
		colorGradation[2] = Color.RED;
		colorGradation[3] = Color.BLUE;
		colorGradation[4] = Color.BLACK;

		DTM dtm = new DTM(fileName, layerName, fill, exager, colorGradation);

		// Getting extremal coordinates
		DirectPosition LL = (DirectPosition) dtm.get3DEnvelope().getLLDP();
		DirectPosition UR = (DirectPosition) dtm.get3DEnvelope().getURDP();

		// Comparison
		boolean bool1 = (LL.getX() == 914900.00)&&(LL.getY() == 6449900.00)&&(LL.getZ() == 206);
		boolean bool2 = (UR.getX() == 914900.00+51*200)&&(UR.getY() == 6449900.00+51*200)&&(UR.getZ() == 1477);

		// Tests
		assertTrue("Lower left coordinates of bounding bow are incorrect", bool1);
		assertTrue("Upper right coordinates of bounding bow are incorrect", bool2);

	}

	@Test
	// --------------------------------------------------------------------------------
	// Test for method to get representation
	// --------------------------------------------------------------------------------
	public void testDTM10() {

		log.info("Test for method to get representation");

		// Loading data file in constructor

		String fileName = "src\\test\\resources\\mnt.asc";
		String layerName = "Digital Terrain Model";
		boolean fill = true;
		int exager = 1;

		// Colors
		Color[] colorGradation = new Color[5];

		colorGradation[0] = Color.WHITE;
		colorGradation[1] = Color.GREEN;
		colorGradation[2] = Color.RED;
		colorGradation[3] = Color.BLUE;
		colorGradation[4] = Color.BLACK;

		DTM dtm = new DTM(fileName, layerName, fill, exager, colorGradation);

		//  Getting representation
		@SuppressWarnings("unused")
		TriangleStripArray tsa = dtm.getRepresentation();

		// No error => test has been passed
		assertTrue(true);

	}

	@Test
	// --------------------------------------------------------------------------------
	// Test for method to get representation component
	// --------------------------------------------------------------------------------
	public void testDTM11() {

		log.info("Test for method to get representation component");

		// Loading data file in constructor

		String fileName = "src\\test\\resources\\mnt.asc";
		String layerName = "Digital Terrain Model";
		boolean fill = true;
		int exager = 1;

		// Colors
		Color[] colorGradation = new Color[5];

		colorGradation[0] = Color.WHITE;
		colorGradation[1] = Color.GREEN;
		colorGradation[2] = Color.RED;
		colorGradation[3] = Color.BLUE;
		colorGradation[4] = Color.BLACK;

		DTM dtm = new DTM(fileName, layerName, fill, exager, colorGradation);

		//  Getting representation component
		@SuppressWarnings("unused")
		Component compo = dtm.getRepresentationComponent();

		// No error => test has been passed
		assertTrue(true);

	}

	@Test
	// --------------------------------------------------------------------------------
	// Test for method to get object as triangles
	// --------------------------------------------------------------------------------
	public void testDTM12() {

		log.info(" Test for method to get object as triangles");

		// Loading data file in constructor

		String fileName = "src\\test\\resources\\mnt.asc";
		String layerName = "Digital Terrain Model";
		boolean fill = true;
		int exager = 1;

		// Colors
		Color[] colorGradation = new Color[5];

		colorGradation[0] = Color.WHITE;
		colorGradation[1] = Color.GREEN;
		colorGradation[2] = Color.RED;
		colorGradation[3] = Color.BLUE;
		colorGradation[4] = Color.BLACK;

		DTM dtm = new DTM(fileName, layerName, fill, exager, colorGradation);

		//  Getting triangles object 
		GM_Object triangle = dtm.getGeometryAt(914900.00 + 200*10, 6449900.00 + 200*10);

		// Retrieving altitudes
		double z1 = triangle.coord().get(0).getZ();
		double z2 = triangle.coord().get(1).getZ();
		double z3 = triangle.coord().get(2).getZ();
		double z4 = triangle.coord().get(3).getZ();

		// Tests
		assertTrue("Triangle altitude is incorrect", z1 == 420);
		assertTrue("Projected altitude is incorrect", z2 == 480);
		assertTrue("Projected altitude is incorrect", z3 == 445);
		assertTrue("Triangle altitude is incorrect", z4 == 420);

	}

	@Test
	// --------------------------------------------------------------------------------
	// Test for computing DTM Area
	// --------------------------------------------------------------------------------
	public void testDTM13() {

		log.info("Test for computing DTM Area");

		// Loading data file in constructor

		String fileName = "src\\test\\resources\\mnt.asc";
		String layerName = "Digital Terrain Model";
		boolean fill = true;
		int exager = 1;

		// Colors
		Color[] colorGradation = new Color[5];

		colorGradation[0] = Color.WHITE;
		colorGradation[1] = Color.GREEN;
		colorGradation[2] = Color.RED;
		colorGradation[3] = Color.BLUE;
		colorGradation[4] = Color.BLACK;

		DTMArea dtma = new DTMArea(fileName, layerName, fill, exager, colorGradation);

		// Creating multi polygon
		@SuppressWarnings("unused")
		MultiPolygon mp = dtma.processSurfacicGrid(0,0,100,100);

		// No error => test has been passed
		assertTrue(true);

	}

}