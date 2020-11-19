package com.ario.flashtriebeyond.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by jaberALU on 16/01/2018.
 */

public class ExampleTrie {
  public TrieNode exampPaper(){
    TrieNode t = new TrieNode();
    t.next = "A";
    t.children[0] = new TrieNode();
    t.children[1] = new TrieNode();

    t.children[0].next = "null";
    t.children[0].children[0] = new TrieNode();
    t.children[0].children[0].next = "C";

    t.children[0].children[1] = new TrieNode();
    t.children[0].children[1].next = "null";

    t.children[1].next = "B";
    t.children[1].children[0] = new TrieNode();
    t.children[1].children[0].next = "null";

    t.children[1].children[1] = new TrieNode();
    t.children[1].children[1].next = "D";

    t.children[0].children[0].children[0] = new TrieNode();
    t.children[0].children[0].children[0].next = "null";

    t.children[0].children[0].children[1] = new TrieNode();
    t.children[0].children[0].children[1].next = "null";

    t.children[0].children[1].children[0] = new TrieNode();
    t.children[0].children[1].children[0].next = "null";

    t.children[0].children[1].children[1] = new TrieNode();
    t.children[0].children[1].children[1].next = "null";

    t.children[1].children[0].children[0] = new TrieNode();
    t.children[1].children[0].children[0].next = "E";

    t.children[1].children[0].children[1] = new TrieNode();
    t.children[1].children[0].children[1].next = "null";

    t.children[1].children[1].children[0] = new TrieNode();
    t.children[1].children[1].children[0].next = "null";

    t.children[1].children[1].children[1] = new TrieNode();
    ;
    t.children[1].children[1].children[1].next = "null";

    return t;
  }

  public TrieNode exampDeep5(){
    TrieNode t = new TrieNode();
    t.next = "A";
    t.children[0] = new TrieNode();
    t.children[1] = new TrieNode();

    t.children[0].next = "null";
    t.children[0].children[0] = new TrieNode();
    ;
    t.children[0].children[0].next = "C";

    t.children[0].children[1] = new TrieNode();
    ;
    t.children[0].children[1].next = "null";

    t.children[1].next = "B";
    t.children[1].children[0] = new TrieNode();
    ;
    t.children[1].children[0].next = "null";

    t.children[1].children[1] = new TrieNode();
    ;
    t.children[1].children[1].next = "D";

    t.children[0].children[0].children[0] = new TrieNode();
    ;
    t.children[0].children[0].children[0].next = "null";

    t.children[0].children[0].children[1] = new TrieNode();
    ;
    t.children[0].children[0].children[1].next = "null";

    t.children[0].children[1].children[0] = new TrieNode();
    ;
    t.children[0].children[1].children[0].next = "null";

    t.children[0].children[1].children[1] = new TrieNode();
    ;
    t.children[0].children[1].children[1].next = "null";

    t.children[1].children[0].children[0] = new TrieNode();
    ;
    t.children[1].children[0].children[0].next = "E";

    t.children[1].children[0].children[1] = new TrieNode();
    ;
    t.children[1].children[0].children[1].next = "null";

    t.children[1].children[1].children[0] = new TrieNode();
    ;
    t.children[1].children[1].children[0].next = "null";

    t.children[1].children[1].children[1] = new TrieNode();
    ;
    t.children[1].children[1].children[1].next = "null";

    t.children[1].children[1].children[1].children[0] = new TrieNode();
    ;
    t.children[1].children[1].children[1].children[0].next = "null";

    t.children[1].children[1].children[1].children[1] = new TrieNode();
    ;
    t.children[1].children[1].children[1].children[1].next = "N";

    t.children[1].children[1].children[1].children[1].children[0] = new TrieNode();
    ;
    t.children[1].children[1].children[1].children[1].children[0].next = "K";
    fullTrie(t);
    t.children[0].children[1].children[0].children[0].children[0].next = "P";
    t.children[0].children[0].children[1].children[1].next = "W";

    t.children[1].children[1].children[1].next = "H";
    t.children[1].children[1].children[0].next = "X";
    return t;
  }

  public void fullTrie(TrieNode trie) {
    int i = 0;
    if (trie == null)
      return;
    Queue<TrieNode> q = new LinkedList<TrieNode>();
    q.add(trie);
    while (true) {
      int nodeCount = q.size();
      if (nodeCount == 0 || i >= 5)
        break;
      while (nodeCount > 0) {
        TrieNode node = q.peek();
        q.remove();
        if (node.children[0] != null) {
          q.add(node.children[0]);
        } else {
          node.children[0] = new TrieNode();
          Random random = new Random();
          node.children[0].id=""+random.nextInt(32000+ 1 - 1) + 1;
          q.add(node.children[0]);
        }
        if (node.children[1] != null) {
          q.add(node.children[1]);
        } else {
          node.children[1] = new TrieNode();
          Random random = new Random();
          node.children[1].id=""+random.nextInt(32000+ 1 - 1) + 1;
          q.add(node.children[1]);
        }
        nodeCount--;

      }
      i++;
    }
  }
}
