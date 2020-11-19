package com.ario.flashtriebeyond;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ario.flashtriebeyond.adapter.AdapterIP;
import com.ario.flashtriebeyond.dialog.SelectIPDialog;
import com.ario.flashtriebeyond.model.PrefixNextHob;
import com.ario.flashtriebeyond.model.TreeBitmapModle;
import com.ario.flashtriebeyond.model.TrieNode;
import com.ario.flashtriebeyond.utils.IpToPrefix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class MainActivity extends AppCompatActivity {

  Button btnRead;
  Button btnTrie;
  Button btnLookUp;
  Button btnIpToPrefix;
  int stateRead;
  int stateTrie;
  int stateLookUp;
  int stateToPrefix;

  int numberSelect;
  private AdapterIP adapterIP;
  private RecyclerView recyclerView;
  public static String ShowTrie;

  List<String> level1 = new ArrayList<String>();
  List<String> level2 = new ArrayList<String>();
  List<String> level3 = new ArrayList<String>();

  List<String> subTrie1 = new ArrayList<String>();
  List<String> subTrie2 = new ArrayList<String>();
  List<String> subTrie3 = new ArrayList<String>();

  List<TrieNode> subTrieModelLevel1 = new ArrayList<TrieNode>();
  List<TrieNode> subTrieModelLevel2 = new ArrayList<TrieNode>();
  List<TrieNode> subTrieModelLevel3 = new ArrayList<TrieNode>();

  List<String> NodeInSubTrie1 = new ArrayList<String>();
  List<String> NodeInSubTrie2 = new ArrayList<String>();
  List<String> NodeInSubTrie3 = new ArrayList<String>();

  List<TreeBitmapModle> pctries = new ArrayList<TreeBitmapModle>();

  List<PrefixNextHob> pre;

  String keys[] = new String[100];
  String next[] = new String[100];
  public int state = 0;
  DrawerLayout navigationView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ImageView imgDrawer = (ImageView) findViewById(R.id.img_draw);
    navigationView = (DrawerLayout) findViewById(R.id.drawer);
    imgDrawer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        navigationView.openDrawer(GravityCompat.START);
      }
    });
    keys[0] = "*";
    next[0] = "*";
    btnRead = (Button) findViewById(R.id.btn_read);
    btnTrie = (Button) findViewById(R.id.btn_tire);
    btnLookUp = (Button) findViewById(R.id.btn_lookup);
    btnIpToPrefix = (Button) findViewById(R.id.btn_ip_to_prefix);
    btnTrie.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (stateRead == 1 | state == 1 | state == 2) {
          if (stateToPrefix == 1 | state == 1 | state == 2) {
            if (state == 1) {
              String key[] = {"*", "01", "1110", "01101", "111100", "000011", "0000100", "001011101", "0010111001", "000010011", "111101000000", "00001001"};
              String nex[] = {"O", "m", "A", "B", "C", "D", "E", "F", "G", "H", "I", "L"};
              Intent intent = new Intent(MainActivity.this, Main2Activity.class);
              intent.putExtra("keys", key);
              intent.putExtra("next", nex);
              startActivity(intent);
            } else if (state == 2) {
              String key[] = {"*", "0000", "00001", "000000", "000011", "0000100", "00001000", "000011001", "0000100001", "0000110001"};
              String nex[] = {"O", "A", "B", "C", "D", "E", "F", "G", "H", "I"};
              Intent intent = new Intent(MainActivity.this, Main2Activity.class);
              intent.putExtra("keys", key);
              intent.putExtra("next", nex);
              startActivity(intent);
            }else {
              // bubbleSort();
              Intent intent = new Intent(MainActivity.this, Main2Activity.class);
              intent.putExtra("keys", keys);
              intent.putExtra("next", next);
              startActivity(intent);
            }
          } else {
            Toast.makeText(MainActivity.this, "please convert Ip to Prefix", Toast.LENGTH_LONG).show();
          }
        } else {
          Toast.makeText(MainActivity.this, "please select IP Address", Toast.LENGTH_LONG).show();
        }
      }
    });
    btnIpToPrefix.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (stateRead == 1) {
          if (state == 1 || state == 2) {
            Toast.makeText(MainActivity.this, "you select example please create Trie", Toast.LENGTH_SHORT).show();
          } else {
            for (int i = 0; i < pre.size(); i++) {
              IpToPrefix ipToPrefix = new IpToPrefix();
              String prefix = ipToPrefix.convert(pre.get(i).getEnterPort());
              String nextHop = pre.get(i).getNextHop();
              keys[i + 1] = prefix;
              next[i + 1] = nextHop;
              pre.get(i).setPrefix(prefix);
              recyclerView.setAdapter(adapterIP);
              adapterIP.notifyDataSetChanged();
              stateToPrefix = 1;
            }
            Toast.makeText(MainActivity.this, "applying convert IP to Prefix", Toast.LENGTH_SHORT).show();
          }

        } else {
          Toast.makeText(MainActivity.this, "please select IP Address", Toast.LENGTH_LONG).show();
        }
      }
    });
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    LinearLayoutManager lay = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
    recyclerView.setLayoutManager(lay);
    btnRead.setTypeface(Base.font1);
    btnRead.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final SelectIPDialog errorDialog = new SelectIPDialog(MainActivity.this, "Please enter the last two numbers of student number or select example");
        errorDialog.show();
        errorDialog.btnErrorDialog.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (errorDialog.edtInput.getText().toString().length() >= 2) {
              state = 0;
              Base.setInit(15, 16, 25, 26, 2, 3, 4);
              numberSelect = 10 * Integer.parseInt((errorDialog.edtInput.getText().toString()));
              ReadTextFile readTextFile = new ReadTextFile();
              readTextFile.setContext(MainActivity.this);
              readTextFile.setFileName("Data.txt");
              readTextFile.setSelectLine(numberSelect);
              pre = readTextFile.readFromfile();
              adapterIP = new AdapterIP(MainActivity.this, pre);
              recyclerView.setAdapter(adapterIP);

              adapterIP.notifyDataSetChanged();
              Log.i("IP", "TXT" + pre.size());
              stateRead = 1;
            }
            errorDialog.dismiss();
          }
        });
        errorDialog.layExamp1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            state = 1;
            Base.setInit(3, 4, 7, 8, 0, 0, 1);
            Toast.makeText(MainActivity.this, "example 1 is active please create Trie", Toast.LENGTH_LONG).show();
            errorDialog.dismiss();

          }
        });
        errorDialog.layExamp2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            state = 2;
            Base.setInit(3, 4, 7, 8, 0, 0, 0);
            Toast.makeText(MainActivity.this, "example 2 is active please create Trie", Toast.LENGTH_LONG).show();
            errorDialog.dismiss();

          }
        });


      }
    });
    //String keys[] = {"*", "01", "1110", "01101", "111100", "000011", "0000100", "001011101", "0010111001", "000010011", "111101000000", "00001001"};
    //String next[] = {"O", "m", "A", "B", "C", "D", "E", "F", "G", "H", "I", "L"};

/*
    //create orginal root
    Trie trieRoot = new Trie();
    for (int i = 0; i < keys.length; i++) {
      trieRoot.createTrie(keys[i], next[i]);
    }

    //subTrie level 0
    Trie trie1 = new Trie();
    for (int i = 0; i < keys.length; i++) {
      if (keys[i].length() <= 3) {
        level1.add(keys[i]);
        trie1.createTrie(keys[i], next[i]);
      }
    }
    subTrieModelLevel1.add(trie1.lookUp(keys[0]));

    //subTrie level 1
    Trie trie = new Trie();
    for (int i = 0; i < keys.length; i++) {
      //Log.i("IP", "j2" + keys[i]);
      if (keys[i].length() <= 3) {
        trie.createTrie(keys[i], next[i]);
      } else if (keys[i].length() >= 4 && keys[i].length() <= 7) {
        trie.createTrie(keys[i], next[i]);
      }

    }

    for (int i = 0; i < keys.length; i++) {
      if (keys[i].length() >= 4 && keys[i].length() <= 7) {
        level2.add(keys[i]);
        if (contain(subTrie2, level2.get(level2.size() - 1).substring(0, 4))) {
          subTrie2.add(level2.get(level2.size() - 1).substring(0, 4));
          subTrieModelLevel2.add(trie.searchInTrie(subTrie2.get(subTrie2.size() - 1), trie.root));
        }
      }
    }

    //subTrie level 2
    trie.newTrie();
    for (int i = 0; i < keys.length; i++) {
      trie.createTrie(keys[i], next[i]);
    }

    for (int i = 0; i < keys.length; i++) {
      if (keys[i].length() >= 8) {
        level3.add(keys[i]);
        if (contain(subTrie3, level3.get(level3.size() - 1).substring(0, 8))) {
          subTrie3.add(level3.get(level3.size() - 1).substring(0, 8));
          subTrieModelLevel3.add(trie.searchInTrie(subTrie3.get(subTrie3.size() - 1), trie.root));
        }
      }
    }

    //if not root find parent
    for (int i = 0; i < subTrieModelLevel2.size(); i++) {
      if (subTrieModelLevel2.get(i).next.equals("null")) {
        trie.searchInTrie(subTrie2.get(i), trie.root);
        subTrieModelLevel2.get(i).next = trie.nexLlable;
      }
    }
    for (int i = 0; i < subTrieModelLevel3.size(); i++) {
      if (subTrieModelLevel3.get(i).next.equals("null")) {
        trie.searchInTrie(subTrie3.get(i), trie.root);
        subTrieModelLevel3.get(i).next = trie.nexLlable;
      }
    }


    trie.fullTrie(subTrieModelLevel1.get(0));

    for (int i = 0; i < subTrieModelLevel2.size(); i++) {
      trie1.fullTrie(subTrieModelLevel2.get(i));
    }

    for (int i = 0; i < subTrieModelLevel3.size(); i++) {
      trie.fullTrie(subTrieModelLevel3.get(i));
    }

    trie1.printBFS(subTrieModelLevel1.get(0));
    Log.i("KKK", "===============");
    trie.printBFS(subTrieModelLevel2.get(0));
    trie.printBFS(subTrieModelLevel2.get(1));
    trie.printBFS(subTrieModelLevel2.get(2));
    trie.printBFS(subTrieModelLevel2.get(3));
    Log.i("KKK", "===============");
    trie.printBFS(subTrieModelLevel3.get(0));
    trie.printBFS(subTrieModelLevel3.get(1));
    trie.printBFS(subTrieModelLevel3.get(2));

    ShowTrie = trie.printDFS("", trieRoot.root, true);
    Log.i("KKK", "===============");
    Log.i("KKK", "===============" + ShowTrie);


    Log.i("PRE", "level_1_size " + level1.size());
    Log.i("PRE", "level_2_size " + level2.size());
    Log.i("PRE", "level_3_size " + level3.size());

    Log.i("PRE", "subtrie_1_size " + subTrie1.size());
    Log.i("PRE", "subtrie_2_size " + subTrie2.size());
    Log.i("PRE", "subtrie_3_size " + subTrie3.size());

    Log.i("PRE", "subTrieModelLevel_2_size" + subTrieModelLevel2.size());
    Log.i("PRE", "subTrieModelLevel_3_size" + subTrieModelLevel3.size());

    ExampleTrie exampleTrie = new ExampleTrie();
    TrieNode t1 = exampleTrie.exampPaper();
    TrieNode t2 = exampleTrie.exampDeep5();

    //trie.printBFS(t);
    TrietoArray(t1);
    createPCTrie(t1);
    eliminate(t1);
    treeBitmap(t1);
    Log.i("SHW", "" + pctries.get(0).bitmap);
    Log.i("SHW", "" + pctries.get(0).children[0][0] + pctries.get(0).children[0][1]);
    Log.i("SHW", "" + pctries.get(0).children[1][0] + pctries.get(0).children[1][1]);
    Log.i("SHW", "" + pctries.get(0).children[2][0] + pctries.get(0).children[2][1]);

    trie.printBFS(t1);
    trie.printDFS("", t1, true);

    //createPCTrie(t);
    //trie.printBFS(t);

    /*for (int i = 0; i < keys.length; i++) {
      TrieNode pre = trie.lookUp(keys[i]);
      if (pre == null) {
        Log.i("IP", "not exist"+ keys[i]);
      } else {
        Log.i("IP", "" + pre.next + "--->" + pre.level);
      }
    }*/
  }

  public void treeBitmap(TrieNode root) {
    TreeBitmapModle PC = new TreeBitmapModle();
    int i = 0;
    int j = 0;
    PC.root = root.next;
    root.next = "null";
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
    pctries.add(PC);
  }

  public void TrietoArray(TrieNode root) {
    nodes = new ArrayList<String>();
    nodesNext = new ArrayList<String>();
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
        nodes.add(node.id);
        nodesNext.add(node.next);
        System.out.print(node.next + " ");
        q.remove();
        if (node.children[0] != null)
          q.add(node.children[0]);
        if (node.children[1] != null)
          q.add(node.children[1]);
        nodeCount--;
      }
      System.out.println();
    }
  }

  List<String> nodes = new ArrayList<String>();
  List<String> nodesNext = new ArrayList<String>();

  public void printPC(TrieNode root) {

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
        //System.out.print(node.next + " ");
        q.remove();

        if (node.children[0] != null) {
          q.add(node.children[0]);
        }
        if (node.children[1] != null) {
          q.add(node.children[1]);
        }

        if (node.children[0] != null && node.children[0].next.equals("null") && node.children[1] != null && !node.children[1].next.equals("null")) {
          Log.i("OMD", "1--->");
          if (node.next.equals("null")) {
            node.children[0].next = getNearFather(nodes, nodesNext, node.children[0].id);
          } else {
            node.children[0].next = node.next;
          }
        }

        if (node.children[0] != null && !node.children[0].next.equals("null") && node.children[1] != null && node.children[1].next.equals("null")) {
          Log.i("OMD", "2--->");
          if (node.next.equals("null")) {
            node.children[1].next = getNearFather(nodes, nodesNext, node.children[1].id);
          } else {
            Log.i("OMD", "2--->");
            node.children[1].next = node.next;
          }
        }
        nodeCount--;
      }
      //System.out.println();
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
        //System.out.print(node.next + " ");
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
      //System.out.println();
    }


  }

  public void bubbleSort() {
    boolean swapped = true;
    int j = 0;
    String tmp;
    while (swapped) {
      swapped = false;
      j++;
      for (int i = 0; i < keys.length - j; i++) {
        if (keys[i].length() > keys[i + 1].length()) {
          tmp = keys[i];
          keys[i] = keys[i + 1];
          next[i] = next[i + 1];
          keys[i + 1] = tmp;
          swapped = true;
        }
      }
    }
  }


  public String getNearFather(List<String> arr, List<String> arrNext, String item) {
    Log.i("ITM1", arr + " item 1-->" + item);
    for (int i = 0; i < arr.size(); i++) {
      if (i == 0 && item.equals(arr.get(i))) {
        return arrNext.get(i);
      }
      Log.i("ITM2", "item 2-->" + item);
      if (item.equals(arr.get(i))) {
        Log.i("ITM3", "item 3-->" + item);
        int m = (int) (Math.round(i / 1.9) - 1);
        while (arrNext.get(m).equals("null")) {
          Log.i("ITM4", "item 4-->" + m);
          m = (int) (Math.round(m / 1.9) - 1);
        }
        Log.i("ITM5", "item 5-->" + arrNext.get(m));
        return arrNext.get(m);
      }
    }
    return "-V";
  }

  public boolean contain(List<String> arr, String item) {
    boolean ok = false;
    for (int i = 0; i < arr.size(); i++) {
      if (arr.get(i).equals(item)) {
        ok = true;
        break;
      }
    }
    return ok;
  }
}
