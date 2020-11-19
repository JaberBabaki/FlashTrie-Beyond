package com.ario.flashtriebeyond.model;

import java.util.Random;

/**
 * Created by jaberALU on 31/12/2017.
 */


public class TrieNode {
  public final int ALPHABET_SIZE = 2;
  public TrieNode[] children = new TrieNode[ALPHABET_SIZE];
  public boolean isEndOfWord;
  public String next;
  public String pre;
  public String level;
  public String id;

  public TrieNode() {
    Random random = new Random();
    isEndOfWord = false;
    next = "null";
    pre = "0";
    level = "-1";
    id = ""+random.nextInt(32000+ 1 - 1) + 1;
    for (int i = 0; i < ALPHABET_SIZE; i++)
      children[i] = null;
  }
}

