package com.ario.flashtriebeyond.model;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by jaberALU on 20/01/2018.
 */

public class MemberShip {
  public Hashtable<Integer, HashModel> hashTable;
  public List<BlackSheepModel> BlackSheep;

  public Hashtable<Integer, HashModel> getHashTable() {
    return hashTable;
  }

  public List<BlackSheepModel> getBlackSheep() {
    return BlackSheep;
  }

  public void setHashTable(Hashtable<Integer, HashModel> hashTable) {
    this.hashTable = hashTable;
  }

  public void setBlackSheep(List<BlackSheepModel> blackSheep) {
    BlackSheep = blackSheep;
  }

}
