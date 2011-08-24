/**
 * @author julien Gaffuri 25 juin 2009
 */
package fr.ign.cogit.geoxygene.shemageo.bati;

import fr.ign.cogit.geoxygene.api.schemageo.bati.Batiment;
import fr.ign.cogit.geoxygene.api.spatial.coordgeom.IPolygon;
import fr.ign.cogit.geoxygene.api.spatial.geomroot.IGeometry;
import fr.ign.cogit.geoxygene.shemageo.support.elementsIndependants.MicroImpl;

/**
 * @author julien Gaffuri 25 juin 2009
 * 
 */
public class BatimentImpl extends MicroImpl implements Batiment {

  /**
   * Constructeur simple avec seulement la géométrie
   * @param geom
   */
  public BatimentImpl(IGeometry geom) {
    super(geom);
  }

  /**
   * Constructeur à partir des caractaréristiques du bâtiment (nature et hauteur)
   * et de sa géométrie.
   * @param geom
   * @param nature
   * @param hauteur
   */
  public BatimentImpl(IPolygon geom, String nature, double hauteur) {
    super(geom);
  }
  
  /**
   * l'altitude maximale de l'objet
   */
  private double zMax;

  public double getZMax() {
    return this.zMax;
  }

  public void setZMax(double zMax) {
    this.zMax = zMax;
  }

  /**
   * l'altitude minimale de l'objet
   */
  private double zMin;

  public double getZMin() {
    return this.zMin;
  }

  public void setZMin(double zMin) {
    this.zMin = zMin;
  }

  /**
   * la hauteur de l'objet
   */
  private double hauteur;

  public double getHauteur() {
    return this.hauteur;
  }

  public void setHauteur(double hauteur) {
    this.hauteur = hauteur;
  }

  /**
   * la nature du bâtiment
   */
  private String nature;
  
  @Override
  public String getNature() {return nature;}

  @Override
  public void setNature(String nature) {this.nature = nature;}

}