package com.ario.flashtriebeyond;

import android.util.Log;

import com.ario.flashtriebeyond.model.TreeBitmapModle;
import com.ario.flashtriebeyond.model.TrieNode;

import java.util.LinkedList;
import java.util.Queue;

public class Trie {

  public TrieNode root = new TrieNode();
  public static String nexLlable = "";
  public static String strrr = "";

  public void createTrie(String key, String next) {
    int level;
    int length = key.length();
    int index;
    TrieNode pCrawl = root;
    if (key.equals("*")) {
      pCrawl.isEndOfWord = true;
      pCrawl.next = next;
      pCrawl.pre = key;
      pCrawl.level = "0";
    } else {
      char[] arr = key.toCharArray();
      for (level = 0; level < length; level++) {
        index = Integer.parseInt("" + arr[level]);
        if (pCrawl.children[index] == null) {
          pCrawl.children[index] = new TrieNode();
        }
        pCrawl = pCrawl.children[index];
        if (key.length() <= Base.level0 && (pCrawl.level.equals("-1"))) {
          pCrawl.level = "0";
        } else if (key.length() >= Base.level1L && key.length() <= Base.level1T && (pCrawl.level.equals("-1"))) {
          pCrawl.level = "1";
        } else if (key.length() >= Base.level2 && (pCrawl.level.equals("-1"))) {
          pCrawl.level = "2";
        }

      }
      pCrawl.isEndOfWord = true;
      pCrawl.next = next;
      pCrawl.pre = key;
    }

  }


  public TrieNode searchInTrie(String key, TrieNode trie) {
    nexLlable = "";
    int level;
    int length = key.length();
    int index;
    TrieNode pCrawl = trie;
    if (key.equals("*")) {
      return trie;
    } else {
      char[] arr = key.toCharArray();
      for (level = 0; level < length; level++) {
        index = Integer.parseInt("" + arr[level]);
        if (!pCrawl.next.equals("null")) {
          nexLlable = pCrawl.next;
        }
        if (pCrawl.children[index] == null)
          return null;
        pCrawl = pCrawl.children[index];
      }
      return pCrawl;
    }
  }

  public String printDFS(String prefix, TrieNode n, boolean isLeft) {
    if (n != null) {
      System.out.println(prefix + (isLeft ? "L--> " : "R--> ") + n.next);
      strrr = strrr + "\n" + prefix + (isLeft ? "L--> " : "R--> ") + n.next;
      printDFS(prefix + (isLeft ? "|   " : "    "), n.children[0], true);
      printDFS(prefix + (isLeft ? "|   " : "    "), n.children[1], false);
    }
    return strrr;
  }


  public void newTrie() {
    root.children[0] = null;
    root.children[1] = null;
  }


  public void fullTrie(TrieNode trie, int lev) {
    int f = 0;
    if (lev == 0) {
      f = 3;
    } else if (lev == 1) {
      f = 4;
    } else if (lev == 2) {
      f = 15;
    } else if (lev == 3) {
      f = 10;
    } else if (lev == 4) {
      f = 7;
    }
    int i = 0;
    if (trie == null)
      return;
    Queue<TrieNode> q = new LinkedList<TrieNode>();
    q.add(trie);
    while (true) {
      int nodeCount = q.size();
      if (nodeCount == 0 || i >= f)
        break;
      while (nodeCount > 0) {
        TrieNode node = q.peek();
        q.remove();
        if (node.children[0] != null) {
          q.add(node.children[0]);
        } else {
          node.children[0] = new TrieNode();
          q.add(node.children[0]);
        }
        if (node.children[1] != null) {
          q.add(node.children[1]);
        } else {
          node.children[1] = new TrieNode();
          q.add(node.children[1]);
        }
        nodeCount--;
      }
      i++;
    }
  }


  public void eliminate(TrieNode root) {
    if (root == null)
      return;
    Queue<TrieNode> q = new LinkedList<TrieNode>();
    q.add(root);
    while (true) {
      int nodeCount = q.size();
      if (nodeCount == 0)
        break;
      TrieNode nodeFather = q.peek();
      while (nodeCount > 0) {
        TrieNode node = q.peek();
        q.remove();
        if (node.children[0] != null && node.children[1] != null && !node.children[0].next.equals("null") && !node.children[1].next.equals("null")) {
          Log.i("OMD", "1--->");
          if (node.children[0].children[0] != null && node.children[0].children[1] != null &&
            node.children[1].children[0] != null && node.children[1].children[1] != null &&
            !node.children[0].children[0].next.equals("null") && !node.children[0].children[1].next.equals("null") &&
            !node.children[1].children[0].next.equals("null") && !node.children[1].children[1].next.equals("null")) {
            node.children[0].next = "null";
            node.children[1].next = "null";
          }
        }
        if (node.children[0] != null) {
          q.add(node.children[0]);
        }
        if (node.children[1] != null) {
          q.add(node.children[1]);
        }
        nodeCount--;
      }
    }
  }
//, List<String> nodes, List<String> nodesNext
  public void createPCTrie(TrieNode root) {
    if (root == null)
      return;
    Queue<TrieNode> q = new LinkedList<TrieNode>();
    q.add(root);
    while (true) {
      int nodeCount = q.size();
      if (nodeCount == 0)
        break;
      while (nodeCount > 0) {
        TrieNode node = q.peek();
        q.remove();

        if (node.children[0] != null) {
          q.add(node.children[0]);
        }
        if (node.children[1] != null) {
          q.add(node.children[1]);
        }
        if (node.children[0] != null && node.children[0].next.equals("null") && node.children[1] != null && !node.children[1].next.equals("null")) {
          if (node.next.equals("null")) {
            searchInTrie(node.children[1].pre, root);
            node.children[0].next = nexLlable;
          } else {
            node.children[0].next = node.next;
          }
        }
        if (node.children[0] != null && !node.children[0].next.equals("null") && node.children[1] != null && node.children[1].next.equals("null")) {
          if (node.next.equals("null")) {
            searchInTrie(node.children[0].pre, root);
            node.children[1].next = nexLlable;
          } else {
            node.children[1].next = node.next;
          }
        }
        nodeCount--;
      }
    }
  }
  public TreeBitmapModle treeBitmap(TrieNode root) {
    TreeBitmapModle PC = new TreeBitmapModle();
    int i = 0;
    int j = 0;
    PC.root = root.next;
    root.next = "null";
    if (root == null)
      return PC;
    Queue<TrieNode> q = new LinkedList<TrieNode>();
    q.add(root);
    while (true) {
      int nodeCount = q.size();
      if (nodeCount == 0)
        break;
      while (nodeCount > 0) {
        TrieNode node = q.peek();
        q.remove();
        if (node.children[0] != null && node.children[1] != null) {
          if (!node.children[0].next.equals("null") && !node.children[1].next.equals("null")) {
            j = 0;
            PC.bitmap = PC.bitmap + "1";
            PC.children[i][j] = node.children[0].next;
            j++;
            PC.children[i][j] = node.children[1].next;
            i++;
          } else if (node.children[0].next.equals("null") && node.children[1].next.equals("null")) {
            PC.bitmap = PC.bitmap + "0";
          }
        }
        if (node.children[0] != null)
          q.add(node.children[0]);
        if (node.children[1] != null)
          q.add(node.children[1]);
        nodeCount--;
      }
      System.out.println();
    }
    return PC;
  }
}