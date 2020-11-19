package com.ario.flashtriebeyond.model;

/**
 * Created by jaberALU on 20/01/2018.
 */

public class BlackSheepModel {




  private  String verifyBit;
  private String rootNodeNHI;
  private TreeBitmapModle trieNode;
  public BlackSheepModel(String verifyBit, String rootNodeNHI, TreeBitmapModle trieNode) {
    this.verifyBit = verifyBit;
    this.rootNodeNHI= rootNodeNHI;
    this.trieNode = trieNode;
  }
  public String getVerifyBit() {
    return verifyBit;
  }

  public String getRootNodeNHI() {
    return rootNodeNHI;
  }

  public TreeBitmapModle getTrieNode() {
    return trieNode;
  }
  public void setVerifyBit(String verifyBit) {
    this.verifyBit = verifyBit;
  }

  public void setRootNodeNHI(String rootNodeNHI) {
    this.rootNodeNHI = rootNodeNHI;
  }

  public void setTrieNode(TreeBitmapModle trieNode) {
    this.trieNode = trieNode;
  }
}
