package com.ario.flashtriebeyond.model;

/**
 * Created by jaberALU on 20/01/2018.
 */

public class HashModel {




  private  String verifyBit;
  private String rootNodeNHI;
  private Integer collision;
  public HashModel(String verifyBit,String rootNodeNHI, Integer collision) {
    this.verifyBit = verifyBit;
    this.rootNodeNHI= rootNodeNHI;
    this.collision = collision;
  }
  public String getVerifyBit() {
    return verifyBit;
  }

  public String getRootNodeNHI() {
    return rootNodeNHI;
  }

  public Integer getCollision() {
    return collision;
  }
  public void setVerifyBit(String verifyBit) {
    this.verifyBit = verifyBit;
  }

  public void setRootNodeNHI(String rootNodeNHI) {
    this.rootNodeNHI = rootNodeNHI;
  }

  public void setCollision(Integer collision) {
    this.collision = collision;
  }
}
