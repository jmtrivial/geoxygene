/*******************************************************************************
 * This software is released under the licence CeCILL
 * 
 * see Licence_CeCILL-C_fr.html see Licence_CeCILL-C_en.html
 * 
 * see <a href="http://www.cecill.info/">http://www.cecill.info/a>
 * 
 * @copyright IGN
 ******************************************************************************/
package fr.ign.cogit.cartagen.pearep.vmap1PlusPlus;

import java.util.HashMap;

import org.hibernate.annotations.Type;

import fr.ign.cogit.cartagen.core.genericschema.urban.IBuildPoint;
import fr.ign.cogit.cartagen.pearep.vmap.PeaRepDbType;
import fr.ign.cogit.cartagen.pearep.vmap.VMAPFeature;
import fr.ign.cogit.geoxygene.api.feature.IFeature;
import fr.ign.cogit.geoxygene.api.spatial.geomprim.IPoint;
import fr.ign.cogit.geoxygene.schemageo.api.bati.AutreConstruction;
import fr.ign.cogit.geoxygene.schemageo.impl.bati.AutreConstructionImpl;

public class VMAP1PPBuildP extends VMAPFeature implements IBuildPoint {

  private AutreConstruction geoxObj;

  // VMAP1PlusPlus attributes
  private String date_bdi, f_code, gfid_v2i, src_date, src_info, txt, uid_,
      upd_date, upd_info, v2i_f_code, valid_date, valid_info, nam, buishp,
      natcon, status, functn, catlit, colour, litvis, valnmr;
  private long bfc, exs, fcsubtype, hgt, hwt, keep, obj_rmq, src_dim, src_name,
      upd_name, valid_stat, wid, zv2, scamax, scamin, originform, targetscal,
      v2i_acc, aoo, exclit, litchr;

  /**
   * @param type
   */
  public VMAP1PPBuildP(IPoint point, HashMap<String, Object> attributes,
      @SuppressWarnings("unused") PeaRepDbType type) {
    super();
    this.geoxObj = new AutreConstructionImpl(point);
    this.setInitialGeom(point);
    this.setEliminated(false);
    this.setAttributeMap(attributes);//

    this.date_bdi = (String) attributes.get("date_bdi");
    this.f_code = (String) attributes.get("f_code");
    this.gfid_v2i = (String) attributes.get("gfid_v2i");
    this.nam = (String) attributes.get("nam");
    this.src_date = (String) attributes.get("src_date");
    this.src_info = (String) attributes.get("src_info");
    this.txt = (String) attributes.get("txt");
    this.uid_ = (String) attributes.get("uid_");
    this.upd_date = (String) attributes.get("upd_date");
    this.upd_info = (String) attributes.get("upd_info");
    this.v2i_f_code = (String) attributes.get("v2i_f_code");
    this.valid_date = (String) attributes.get("valid_date");
    this.valid_info = (String) attributes.get("valid_info");
    this.src_info = (String) attributes.get("src_info");
    this.buishp = (String) attributes.get("buishp");
    this.status = (String) attributes.get("status");
    this.functn = (String) attributes.get("functn");
    this.catlit = (String) attributes.get("catlit");
    this.colour = (String) attributes.get("colour");
    this.litvis = (String) attributes.get("litvis");
    this.valnmr = (String) attributes.get("valnmr");

    this.exs = getLongAttribute("exs");
    this.fcsubtype = getLongAttribute("fcsubtype");
    this.keep = getLongAttribute("keep");
    this.obj_rmq = getLongAttribute("obj_rmq");
    this.src_dim = getLongAttribute("src_dim");
    this.src_name = getLongAttribute("src_name");
    this.valid_stat = getLongAttribute("valid_stat");
    this.scamax = getLongAttribute("scamax");
    this.scamin = getLongAttribute("scamin");
    this.targetscal = getLongAttribute("targetscal");
    this.bfc = getLongAttribute("bfc");
    this.hgt = getLongAttribute("hgt");
    this.hwt = getLongAttribute("hwt");
    this.wid = getLongAttribute("wid");
    this.zv2 = getLongAttribute("zv2");
    this.v2i_acc = getLongAttribute("v2i_acc");
    this.aoo = getLongAttribute("aoo");
    this.exclit = getLongAttribute("exclit");
    this.litchr = getLongAttribute("litchr");

    this.setAttributeMap(null);//

  }

  @Override
  public IFeature getGeoxObj() {
    return this.geoxObj;
  }

  @Override
  @Type(type = "fr.ign.cogit.cartagen.software.interfaceCartagen.hibernate.GeOxygeneGeometryUserType")
  public IPoint getGeom() {
    return (IPoint) super.getGeom();
  }

  public String getDate_bdi() {
    return date_bdi;
  }

  public void setDate_bdi(String date_bdi) {
    this.date_bdi = date_bdi;
  }

  public String getF_code() {
    return f_code;
  }

  public void setF_code(String f_code) {
    this.f_code = f_code;
  }

  public String getGfid_v2i() {
    return gfid_v2i;
  }

  public void setGfid_v2i(String gfid_v2i) {
    this.gfid_v2i = gfid_v2i;
  }

  public String getNam() {
    return nam;
  }

  public void setNam(String nam) {
    this.nam = nam;
  }

  public String getSrc_date() {
    return src_date;
  }

  public void setSrc_date(String src_date) {
    this.src_date = src_date;
  }

  public String getSrc_info() {
    return src_info;
  }

  public void setSrc_info(String src_info) {
    this.src_info = src_info;
  }

  public String getTxt() {
    return txt;
  }

  public void setTxt(String txt) {
    this.txt = txt;
  }

  public String getUid_() {
    return uid_;
  }

  public void setUid_(String uid_) {
    this.uid_ = uid_;
  }

  public String getUpd_date() {
    return upd_date;
  }

  public void setUpd_date(String upd_date) {
    this.upd_date = upd_date;
  }

  public String getUpd_info() {
    return upd_info;
  }

  public void setUpd_info(String upd_info) {
    this.upd_info = upd_info;
  }

  public String getV2i_f_code() {
    return v2i_f_code;
  }

  public void setV2i_f_code(String v2i_f_code) {
    this.v2i_f_code = v2i_f_code;
  }

  public String getValid_date() {
    return valid_date;
  }

  public void setValid_date(String valid_date) {
    this.valid_date = valid_date;
  }

  public String getValid_info() {
    return valid_info;
  }

  public void setValid_info(String valid_info) {
    this.valid_info = valid_info;
  }

  public long getExs() {
    return exs;
  }

  public void setExs(long exs) {
    this.exs = exs;
  }

  public long getFcsubtype() {
    return fcsubtype;
  }

  public void setFcsubtype(long fcsubtype) {
    this.fcsubtype = fcsubtype;
  }

  public long getKeep() {
    return keep;
  }

  public void setKeep(long keep) {
    this.keep = keep;
  }

  public long getObj_rmq() {
    return obj_rmq;
  }

  public void setObj_rmq(long obj_rmq) {
    this.obj_rmq = obj_rmq;
  }

  public long getSrc_dim() {
    return src_dim;
  }

  public void setSrc_dim(long src_dim) {
    this.src_dim = src_dim;
  }

  public long getSrc_name() {
    return src_name;
  }

  public void setSrc_name(long src_name) {
    this.src_name = src_name;
  }

  public long getValid_stat() {
    return valid_stat;
  }

  public void setValid_stat(long valid_stat) {
    this.valid_stat = valid_stat;
  }

  public long getScamax() {
    return scamax;
  }

  public void setScamax(long scamax) {
    this.scamax = scamax;
  }

  public long getScamin() {
    return scamin;
  }

  public void setScamin(long scamin) {
    this.scamin = scamin;
  }

  public long getTargetscale() {
    return targetscal;
  }

  public void setTargetscale(long targetscale) {
    this.targetscal = targetscale;
  }

  public String getNatcon() {
    return natcon;
  }

  public void setNatcon(String natcon) {
    this.natcon = natcon;
  }

  public long getUpd_name() {
    return upd_name;
  }

  public void setUpd_name(long upd_name) {
    this.upd_name = upd_name;
  }

  public long getOriginform() {
    return originform;
  }

  public void setOriginform(long originform) {
    this.originform = originform;
  }

  public String getBuishp() {
    return buishp;
  }

  public void setBuishp(String buishp) {
    this.buishp = buishp;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getFunctn() {
    return functn;
  }

  public void setFunctn(String functn) {
    this.functn = functn;
  }

  public long getBfc() {
    return bfc;
  }

  public void setBfc(long bfc) {
    this.bfc = bfc;
  }

  public long getHgt() {
    return hgt;
  }

  public void setHgt(long hgt) {
    this.hgt = hgt;
  }

  public long getHwt() {
    return hwt;
  }

  public void setHwt(long hwt) {
    this.hwt = hwt;
  }

  public String getCatlit() {
    return catlit;
  }

  public void setCatlit(String catlit) {
    this.catlit = catlit;
  }

  public String getColour() {
    return colour;
  }

  public void setColour(String colour) {
    this.colour = colour;
  }

  public String getLitvis() {
    return litvis;
  }

  public void setLitvis(String litvis) {
    this.litvis = litvis;
  }

  public String getValnmr() {
    return valnmr;
  }

  public void setValnmr(String valnmr) {
    this.valnmr = valnmr;
  }

  public long getWid() {
    return wid;
  }

  public void setWid(long wid) {
    this.wid = wid;
  }

  public long getZv2() {
    return zv2;
  }

  public void setZv2(long zv2) {
    this.zv2 = zv2;
  }

  public long getV2i_acc() {
    return v2i_acc;
  }

  public void setV2i_acc(long v2i_acc) {
    this.v2i_acc = v2i_acc;
  }

  public long getAoo() {
    return aoo;
  }

  public void setAoo(long aoo) {
    this.aoo = aoo;
  }

  public long getExclit() {
    return exclit;
  }

  public void setExclit(long exclit) {
    this.exclit = exclit;
  }

  public long getLitchr() {
    return litchr;
  }

  public void setLitchr(long litchr) {
    this.litchr = litchr;
  }

}
