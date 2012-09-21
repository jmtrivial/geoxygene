package fr.ign.cogit.geoxygene.sig3d.equation;

import fr.ign.cogit.geoxygene.api.spatial.coordgeom.IDirectPosition;
import fr.ign.cogit.geoxygene.contrib.geometrie.Vecteur;
import fr.ign.cogit.geoxygene.spatial.coordgeom.DirectPosition;

/**
 * 
 *        This software is released under the licence CeCILL
 * 
 *        see LICENSE.TXT
 * 
 *        see <http://www.cecill.info/ http://www.cecill.info/
 * 
 * 
 * 
 * @copyright IGN
 * 
 * @author Brasebin Mickaël
 * 
 * @version 0.1
 * 
 *
 * 
 * Classe permettant de définir une équation de ligne en 3D
 * 
 * Class to define a 3D line equation
 * 
 */
public class LineEquation {

  /**
   * Equation de la forme : x = a1 * t + a0 y = b1 * t + b0 z = c1 * t + c0
   */
  private double a0, a1, b0, b1, c0, c1;
  public static double EPSILON = 0.01;

  /**
   * Permet de générer une équation sous la forme
   * 
   * x = a1 * t + a0 y = b1 * t + b0 z = c1 * t + c0
   * 
   * @param a0
   * @param a1
   * @param b0
   * @param b1
   * @param c0
   * @param c1
   */
  public LineEquation(double a0, double a1, double b0, double b1, double c0,
      double c1) {
    super();
    this.a0 = a0;
    this.a1 = a1;
    this.b0 = b0;
    this.b1 = b1;
    this.c0 = c0;
    this.c1 = c1;
  }

  /**
   * Genère à partir d'un point et d'un vecteur
   * @param dp
   * @param v
   */
  public LineEquation(IDirectPosition dp, Vecteur v) {

    this(dp.getX(), v.getX(), dp.getY(), v.getY(), dp.getZ(), v.getZ());

  }

  /**
   * Genère à partir d'un point et d'un vecteur
   * @param dp
   * @param v
   */
  public LineEquation(IDirectPosition dp, IDirectPosition dp2) {

    this(dp, new Vecteur(dp, dp2));

  }

  /**
   * 
   * Calcule la valeur en t
   * 
   * @param t
   * 
   * @return
   */
  public DirectPosition valueAt(double t) {

    return new DirectPosition(this.a1 * t + this.a0, this.b1 * t + this.b0,
        this.c1 * t + this.c0);

  }

  /**
   * Indique si le point dp est sur la ligne
   * 
   * @param dp
   * @return
   */
  public boolean isPointOnLine(DirectPosition dp) {

    Vecteur v1 = new Vecteur(dp, new DirectPosition(this.a0, this.b0, this.c0));
    Vecteur v2 = new Vecteur(this.a1, this.b1, this.c1);
    
    return (v1.prodVectoriel(v2).norme() < LineEquation.EPSILON);

  }

  /**
   * Renvoie les coordonnées d'intersection entre un point et un plan Renvoie
   * null si pas d'intersection ou si tout est l'intersection
   * 
   * @param eq
   * @return
   */
  public IDirectPosition intersectionLinePlan(PlanEquation eq) {

    IDirectPosition dp = new DirectPosition(this.a0, this.b0, this.c0);
    IDirectPosition dp2 = this.valueAt(10);

    // Normale au plan orthogonale au plan (pas d'intersection ou tout est
    // intersection)
    if (Math.abs(eq.getNormale().prodScalaire(new Vecteur(dp, dp2))) < LineEquation.EPSILON) {

      return null;
    }

    return eq.intersectionLinePlan(dp, dp2);

  }

  /**
   * @return the a0
   */
  public double getA0() {
    return this.a0;
  }

  /**
   * @param a0 the a0 to set
   */
  public void setA0(double a0) {
    this.a0 = a0;
  }

  /**
   * @return the a1
   */
  public double getA1() {
    return this.a1;
  }

  /**
   * @param a1 the a1 to set
   */
  public void setA1(double a1) {
    this.a1 = a1;
  }

  /**
   * @return the b0
   */
  public double getB0() {
    return this.b0;
  }

  /**
   * @param b0 the b0 to set
   */
  public void setB0(double b0) {
    this.b0 = b0;
  }

  /**
   * @return the b1
   */
  public double getB1() {
    return this.b1;
  }

  /**
   * @param b1 the b1 to set
   */
  public void setB1(double b1) {
    this.b1 = b1;
  }

  /**
   * @return the c0
   */
  public double getC0() {
    return this.c0;
  }

  /**
   * @param c0 the c0 to set
   */
  public void setC0(double c0) {
    this.c0 = c0;
  }

  /**
   * @return the c1
   */
  public double getC1() {
    return this.c1;
  }

  /**
   * @param c1 the c1 to set
   */
  public void setC1(double c1) {
    this.c1 = c1;
  }
  
  public Vecteur getVecteur(){
    return new Vecteur(a1,b1,c1);
  }

}
