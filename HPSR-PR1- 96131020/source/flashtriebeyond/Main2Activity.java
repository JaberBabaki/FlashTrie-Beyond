package com.ario.flashtriebeyond;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ario.flashtriebeyond.dialog.SelectIPDialog;
import com.ario.flashtriebeyond.dialog.SelectLevelDialog;
import com.ario.flashtriebeyond.model.BlackSheepModel;
import com.ario.flashtriebeyond.model.HashModel;
import com.ario.flashtriebeyond.model.MemberShip;
import com.ario.flashtriebeyond.model.PrefixNextHob;
import com.ario.flashtriebeyond.model.TreeBitmapModle;
import com.ario.flashtriebeyond.model.TrieNode;
import com.ario.flashtriebeyond.utils.IpToPrefix;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

  Button btnShowTrie;
  Button btnLookupTrie;
  Button btnSubTrieCreate;
  Button btnSubTrieShow;
  Button btnPCTrieCreate;
  Button btnPCTrieShow;
  Button btnMember;
  Button btnHash;

  TextView txtShowTrie;
  String keys[];
  String next[];
  Trie trieRoot;
  Trie trielevel0;
  Trie trielevel1and2;
  
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

  List<TreeBitmapModle> pctries2 = new ArrayList<TreeBitmapModle>();
  List<TreeBitmapModle> pctries3 = new ArrayList<TreeBitmapModle>();

  List<PrefixNextHob> pre;

  List<String> nodes = new ArrayList<String>();
  List<String> nodesNext = new ArrayList<String>();

  int statePCTrie = 0;
  int stateSubtri = 0;
  int stateLookup = 0;
  int stateHash = 0;

  DrawerLayout navigationView;

  MemberShip MemberShip2;
  MemberShip MemberShip3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.second_activity);
    ImageView imgDrawer = (ImageView) findViewById(R.id.img_draw);
    navigationView = (DrawerLayout) findViewById(R.id.drawer);
    imgDrawer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        navigationView.openDrawer(GravityCompat.START);
      }
    });
    btnShowTrie = (Button) findViewById(R.id.btn_show_trie);
    btnLookupTrie = (Button) findViewById(R.id.btn_lookup_trie);
    btnSubTrieCreate = (Button) findViewById(R.id.btn_sub_trie);
    btnSubTrieShow = (Button) findViewById(R.id.btn_show_subtrir);
    btnPCTrieCreate = (Button) findViewById(R.id.btn_pctrie_create);
    btnPCTrieShow = (Button) findViewById(R.id.btn_show_pctrie);
    btnMember = (Button) findViewById(R.id.btn_member);
    btnHash = (Button) findViewById(R.id.btn_hash);
    txtShowTrie = (TextView) findViewById(R.id.txt_show_trie);

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      keys = extras.getStringArray("keys");
      next = extras.getStringArray("next");
    }

    btnLookupTrie.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (stateLookup == 1) {
          Toast.makeText(Main2Activity.this, " please createTrie your IP address or prefix ", Toast.LENGTH_SHORT).show();
          final SelectIPDialog errorDialog = new SelectIPDialog(Main2Activity.this, "please createTrie your IP address or prefix");
          errorDialog.show();
          errorDialog.layExamp1.setVisibility(View.GONE);
          errorDialog.layExamp2.setVisibility(View.GONE);
          errorDialog.btnErrorDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Trie.nexLlable = "";
              if (Base.limit2 != 4) {
                if (errorDialog.edtInput.getText().toString().length() >= 1) {
                  String prefix = errorDialog.edtInput.getText().toString();
                  TrieNode pre = trieRoot.searchInTrie(prefix, trieRoot.root);
                  if (pre != null && !pre.next.equals("null")) {
                    txtShowTrie.setText("" + pre.next);
                  } else if (pre != null && pre.next.equals("null")) {
                    if (!Trie.nexLlable.equals("")) {
                      txtShowTrie.setText("" + Trie.nexLlable);
                    }
                  } else if (pre == null) {
                    if (!Trie.nexLlable.equals("")) {
                      txtShowTrie.setText("" + Trie.nexLlable);
                    }
                  }
                }
              } else {
                if (errorDialog.edtInput.getText().toString().length() >= 9) {
                  IpToPrefix ipToPrefix = new IpToPrefix();
                  String prefix = ipToPrefix.convert(errorDialog.edtInput.getText().toString());
                  TrieNode pre = trieRoot.searchInTrie(prefix, trieRoot.root);
                  if (pre != null && !pre.next.equals("null")) {
                    txtShowTrie.setText("" + pre.next);
                  } else if (pre != null && pre.next.equals("null")) {
                    if (!Trie.nexLlable.equals("")) {
                      txtShowTrie.setText("" + Trie.nexLlable);
                    }
                  } else if (pre == null) {
                    if (!Trie.nexLlable.equals("")) {
                      txtShowTrie.setText("" + Trie.nexLlable);
                    }
                  }
                }
              }
              errorDialog.dismiss();
            }

          });
        } else {
          Toast.makeText(Main2Activity.this, " please create Trie ", Toast.LENGTH_SHORT).show();
        }
      }
    });
    btnShowTrie.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        clearAllArrayList();
        clearShow();
        clearState();
        stateLookup = 1;
        trieRoot = new Trie();
        for (int i = 0; i < keys.length; i++) {
          trieRoot.createTrie(keys[i], next[i]);
        }
        txtShowTrie.setText(trieRoot.printDFS("", trieRoot.root, true));
      }
    });
    btnSubTrieCreate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (stateLookup == 1) {
          clearArrayList();
          statePCTrie = 0;
          //subTrie level 0
          trielevel0 = new Trie();
          for (int i = 0; i < keys.length; i++) {
            if (keys[i].length() <= Base.level0) {

              level1.add(keys[i]);
              trielevel0.createTrie(keys[i], next[i]);
            }
          }
          //Log.i("QQQ", "skeys[i] " + trielevel0.lookUp(keys[1]).next);
          subTrieModelLevel1.add(trielevel0.searchInTrie(keys[0], trieRoot.root));
          //subTrie level 1
          trielevel1and2 = new Trie();
          for (int i = 0; i < keys.length; i++) {
            //Log.i("IP", "j2" + keys[i]);
            if (keys[i].length() <= Base.level0) {
              trielevel1and2.createTrie(keys[i], next[i]);
            } else if (keys[i].length() >= Base.level1L && keys[i].length() <= Base.level1T) {
              trielevel1and2.createTrie(keys[i], next[i]);
            }

          }

          for (int i = 0; i < keys.length; i++) {
            if (keys[i].length() >= Base.level1L && keys[i].length() <= Base.level1T) {
              level2.add(keys[i]);
              if (contain(subTrie2, level2.get(level2.size() - 1).substring(0, Base.level1L))) {
                subTrie2.add(level2.get(level2.size() - 1).substring(0, Base.level1L));
                subTrieModelLevel2.add(trielevel1and2.searchInTrie(subTrie2.get(subTrie2.size() - 1), trielevel1and2.root));
              }
            }
          }

          //subTrie level 2
          trielevel1and2.newTrie();
          for (int i = 0; i < keys.length; i++) {
            trielevel1and2.createTrie(keys[i], next[i]);
          }

          for (int i = 0; i < keys.length; i++) {
            if (keys[i].length() >= Base.level2) {
              level3.add(keys[i]);
              if (contain(subTrie3, level3.get(level3.size() - 1).substring(0, Base.level2))) {
                subTrie3.add(level3.get(level3.size() - 1).substring(0, Base.level2));
                subTrieModelLevel3.add(trielevel1and2.searchInTrie(subTrie3.get(subTrie3.size() - 1), trielevel1and2.root));
              }
            }
          }


          Log.i("PRE", "level_1_size " + level1.size());
          Log.i("PRE", "level_2_size " + level2.size());
          Log.i("PRE", "level_3_size " + level3.size());

          Log.i("PRE", "subtrie_1_size " + subTrie1.size());
          Log.i("PRE", "subtrie_2_size " + subTrie2.size());
          Log.i("PRE", "subtrie_3_size " + subTrie3.size());

          Log.i("PRE", "subTrieModelLevel_1_size" + subTrieModelLevel1.size());
          Log.i("PRE", "subTrieModelLevel_2_size" + subTrieModelLevel2.size());
          Log.i("PRE", "subTrieModelLevel_3_size" + subTrieModelLevel3.size());
          Toast.makeText(Main2Activity.this, "applying subTrie ", Toast.LENGTH_SHORT).show();
          stateSubtri = 1;
        } else {
          Toast.makeText(Main2Activity.this, "please create Trie ", Toast.LENGTH_SHORT).show();
        }
      }
    });
    btnSubTrieShow.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (stateSubtri == 1) {
          //txtShowTrie.setText("");
          clearShow();
          final SelectLevelDialog errorDialog = new SelectLevelDialog(Main2Activity.this, "please select level for print ");
          errorDialog.show();
          errorDialog.btnErrorDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              errorDialog.dismiss();
            }
          });
          errorDialog.level0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String str = "";
              for (int i = 0; i < subTrieModelLevel1.size(); i++) {
                Log.i("WWW", "jaber " + subTrieModelLevel1.size());
                str = str + "\n" + trielevel0.printDFS("", subTrieModelLevel1.get(i), true);
                clearShow();
              }
              Log.i("WWW", "jaber " + str);
              txtShowTrie.setText("" + str);
              errorDialog.dismiss();
            }
          });
          errorDialog.level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String str = "";
              for (int i = 0; i < subTrieModelLevel2.size(); i++) {
                str = str + "\n" + trielevel1and2.printDFS("", subTrieModelLevel2.get(i), true);
                clearShow();
              }
              txtShowTrie.setText("" + str);
              errorDialog.dismiss();
            }
          });
          errorDialog.level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String str = "";
              for (int i = 0; i < subTrieModelLevel3.size(); i++) {
                Log.i("PRE", "subTrieModelLevel_3_size  " + i);
                str = str + "\n" + trielevel1and2.printDFS("", subTrieModelLevel3.get(i), true);
                clearShow();
              }
              txtShowTrie.setText("" + str);
              errorDialog.dismiss();
            }
          });
        } else {
          Toast.makeText(Main2Activity.this, "please do subTrie ", Toast.LENGTH_LONG).show();
        }
      }
    });
    btnPCTrieCreate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (stateSubtri == 1) {
          Toast.makeText(Main2Activity.this, "please wait until show finish message ", Toast.LENGTH_SHORT).show();

          //if not root find parent
          for (int i = 0; i < subTrieModelLevel2.size(); i++) {
            if (subTrieModelLevel2.get(i).next.equals("null")) {
              trielevel1and2.searchInTrie(subTrie2.get(i), trielevel1and2.root);
              subTrieModelLevel2.get(i).next = trielevel1and2.nexLlable;
            }
          }
          for (int i = 0; i < subTrieModelLevel3.size(); i++) {
            if (subTrieModelLevel3.get(i).next.equals("null")) {
              trielevel1and2.searchInTrie(subTrie3.get(i), trielevel1and2.root);
              subTrieModelLevel3.get(i).next = trielevel1and2.nexLlable;
            }
          }

          //fullTrie
          trielevel0.fullTrie(subTrieModelLevel1.get(0), Base.limit0);
          for (int i = 0; i < subTrieModelLevel2.size(); i++) {
            trielevel1and2.fullTrie(subTrieModelLevel2.get(i), Base.limit1);
          }
          for (int i = 0; i < subTrieModelLevel3.size(); i++) {
            trielevel1and2.fullTrie(subTrieModelLevel3.get(i), Base.limit2);
          }

          //PCTrie
          for (int i = 0; i < subTrieModelLevel2.size(); i++) {
            trielevel1and2.createPCTrie(subTrieModelLevel2.get(i));
            trielevel0.eliminate(subTrieModelLevel2.get(i));
            pctries2.add(trielevel1and2.treeBitmap(subTrieModelLevel2.get(i)));
          }

          for (int i = 0; i < subTrieModelLevel3.size(); i++) {
            trielevel1and2.createPCTrie(subTrieModelLevel3.get(i));
            trielevel1and2.eliminate(subTrieModelLevel3.get(i));
            pctries3.add(trielevel1and2.treeBitmap(subTrieModelLevel3.get(i)));
          }

          Toast.makeText(Main2Activity.this, "applying PCTrie ", Toast.LENGTH_SHORT).show();
          statePCTrie = 1;
        } else {
          Toast.makeText(Main2Activity.this, "please do subTrie ", Toast.LENGTH_LONG).show();
        }
      }
    });
    btnPCTrieShow.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (statePCTrie == 1) {
          stateHash = 0;
          final SelectLevelDialog errorDialog = new SelectLevelDialog(Main2Activity.this, "please select level for print ");
          errorDialog.show();
          errorDialog.btnErrorDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              errorDialog.dismiss();
            }
          });
          errorDialog.level0.setVisibility(View.GONE);
          errorDialog.level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String str = "";
              str = printAllPCTrie(pctries2);
              txtShowTrie.setText("" + str);
              /*for (int j = 0; j < pctries2.size(); j++) {
                str = str + "\n" + "\n";
                for (int i = 0; i < pctries2.get(j).children.length; i++) {
                  for (int f = 0; f <= 1; f++) {
                    if (pctries2.get(j).children[i][f] != null) {
                      str = str + " | " + pctries2.get(j).children[i][f];
                    }
                  }
                  if (pctries2.get(j).children[i][0] != null) {
                    str = str + "\n";
                  }
                }
                str = str + pctries2.get(j).bitmap;
              }
              txtShowTrie.setText("" + str);*/
              errorDialog.dismiss();
            }
          });

          errorDialog.level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String str = "";
              str = printAllPCTrie(pctries3);
              txtShowTrie.setText("" + str);
              errorDialog.dismiss();
            }
          });
        } else {
          Toast.makeText(Main2Activity.this, "please do PCTrie ", Toast.LENGTH_LONG).show();
        }

      }
    });
    btnHash.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (statePCTrie == 1) {

          MemberShip2 = new MemberShip();
          MemberShip2 = hashing(subTrie2, subTrieModelLevel2, pctries2);
          for (Map.Entry<Integer, HashModel> entry : MemberShip2.getHashTable().entrySet()) {
            entry.getKey();
            HashModel b = entry.getValue();
            Log.i("PRE", entry.getKey() + " " + b.getVerifyBit() + "  " + b.getRootNodeNHI() + "    " + b.getCollision());
          }

          for (int i = 0; i < MemberShip2.getBlackSheep().size(); i++) {
            Log.i("PRE", "i " + MemberShip2.getBlackSheep().get(i).getVerifyBit() + "  " + MemberShip2.getBlackSheep().get(i).getRootNodeNHI());
          }
          Log.i("PRE", " " + "=========================");

          MemberShip3 = new MemberShip();
          MemberShip3 = hashing(subTrie3, subTrieModelLevel3, pctries3);
          for (Map.Entry<Integer, HashModel> entry : MemberShip3.getHashTable().entrySet()) {
            HashModel b = entry.getValue();
            Log.i("PRE", " " + b.getVerifyBit() + "  " + b.getRootNodeNHI() + "    " + b.getCollision());
          }

          for (int i = 0; i < MemberShip3.getBlackSheep().size(); i++) {
            Log.i("PRE", "i " + MemberShip3.getBlackSheep().get(i).getVerifyBit() + "  " + MemberShip3.getBlackSheep().get(i).getRootNodeNHI());
          }
          Toast.makeText(Main2Activity.this, "applying Hashing ", Toast.LENGTH_LONG).show();
          stateHash = 1;
        } else {
          Toast.makeText(Main2Activity.this, "please do PCTrie ", Toast.LENGTH_LONG).show();
        }
      }
    });
    btnMember.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (stateHash == 1) {
          final SelectIPDialog errorDialog = new SelectIPDialog(Main2Activity.this, "please createTrie your IP address or prefix");
          errorDialog.show();
          errorDialog.layExamp1.setVisibility(View.GONE);
          errorDialog.layExamp2.setVisibility(View.GONE);
          errorDialog.btnErrorDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Trie.nexLlable = "";
              String in = errorDialog.edtInput.getText().toString();
              String inputIP = in.substring(0, in.indexOf("/"));
              String strSub = in.substring(in.indexOf("/") + 1, in.length());
              int subNet = Integer.parseInt(strSub);

              if (Base.limit2 != 4) {
                if (subNet <= Base.level0) {
                  lookUp(inputIP);
                } else if (subNet >= Base.level1L && subNet <= Base.level1T) {
                  String prefix = inputIP.substring(0, 4);
                  int thk = (Integer.parseInt(prefix, 2)) % 4;
                  Hashtable<Integer, HashModel> hash2 = MemberShip2.getHashTable();
                  List<BlackSheepModel> blackSheep = MemberShip2.getBlackSheep();
                  Log.i("PRE", " thk  == " + thk);
                  HashModel hModel = hash2.get(thk);
                  if (hModel.getCollision() == 0) {
                    String nextHopRoot = hModel.getVerifyBit();
                    Log.i("PRE", " " + nextHopRoot);
                    txtShowTrie.setText("" + printPCTrie(getPCtrieIndex(subTrie2, nextHopRoot, pctries2)));
                  } else if (hModel.getCollision() == 1) {
                    String nextHopRoot = hModel.getRootNodeNHI();
                    String[] str = nextHopRoot.split(",");
                    for (int i = 0; i < str.length; i++) {
                      if (blackSheep.get(Integer.parseInt(str[i])).getVerifyBit().equals(prefix)) {
                        if (blackSheep.get(Integer.parseInt(str[i])).getTrieNode() == null) {
                          txtShowTrie.setText("" + printPCTrie(getPCtrieIndex(subTrie2, blackSheep.get(Integer.parseInt(str[i])).getVerifyBit(), pctries2)));
                        } else {
                          Log.i("PRE", " " + blackSheep.get(Integer.parseInt(str[i])).getTrieNode().bitmap);
                          txtShowTrie.setText("" + printPCTrie(blackSheep.get(Integer.parseInt(str[i])).getTrieNode()));
                        }
                        break;
                      }
                    }
                    Log.i("PRE", " " + nextHopRoot);
                  }
                } else if (subNet >= Base.level2) {
                  String prefix = inputIP.substring(0, 8);
                  int thk = (Integer.parseInt(prefix, 2)) % 4;
                  Hashtable<Integer, HashModel> hash3 = MemberShip3.getHashTable();
                  List<BlackSheepModel> blackSheep = MemberShip3.getBlackSheep();
                  Log.i("PRE", " thk  == " + thk);
                  HashModel hModel = hash3.get(thk);
                  if (hModel.getCollision() == 0) {
                    String nextHopRoot = hModel.getVerifyBit();
                    Log.i("PRE", " " + nextHopRoot);
                    txtShowTrie.setText("" + printPCTrie(getPCtrieIndex(subTrie3, nextHopRoot, pctries3)));
                  } else if (hModel.getCollision() == 1) {
                    String nextHopRoot = hModel.getRootNodeNHI();
                    String[] str = nextHopRoot.split(",");
                    for (int i = 0; i < str.length; i++) {
                      if (blackSheep.get(Integer.parseInt(str[i])).getVerifyBit().equals(prefix)) {
                        if (blackSheep.get(Integer.parseInt(str[i])).getTrieNode() == null) {
                          TreeBitmapModle tree = getPCtrieIndex(subTrie3, blackSheep.get(Integer.parseInt(str[i])).getVerifyBit(), pctries3);
                          String a= lookUp(inputIP);
                          a=searchInTreeBitmap(tree, inputIP);
                          txtShowTrie.setText("" + printPCTrie(tree) + "\n" + a);
                        } else {
                          Log.i("PRE", " " + blackSheep.get(Integer.parseInt(str[i])).getTrieNode().bitmap);
                          txtShowTrie.setText("" + printPCTrie(blackSheep.get(Integer.parseInt(str[i])).getTrieNode()));
                        }
                        break;
                      }
                    }
                    Log.i("PRE", " " + nextHopRoot);
                  }
                }
              } else {
                if (errorDialog.edtInput.getText().toString().length() >= 9) {
                  IpToPrefix ipToPrefix = new IpToPrefix();
                  String prefix = ipToPrefix.convert(errorDialog.edtInput.getText().toString());
                  TrieNode pre = trieRoot.searchInTrie(prefix, trieRoot.root);
                  if (pre != null && !pre.next.equals("null")) {
                    txtShowTrie.setText("" + pre.next);
                  } else if (pre != null && pre.next.equals("null")) {
                    if (!Trie.nexLlable.equals("")) {
                      txtShowTrie.setText("" + Trie.nexLlable);
                    }
                  } else if (pre == null) {
                    if (!Trie.nexLlable.equals("")) {
                      txtShowTrie.setText("" + Trie.nexLlable);
                    }
                  }
                }
              }
              errorDialog.dismiss();
            }

          });
        } else {
          Toast.makeText(Main2Activity.this, "please do Hashing ", Toast.LENGTH_LONG).show();
        }
      }
    });

  }

  public boolean contain(List<String> arr, String item) {
    boolean ok = true;
    for (int i = 0; i < arr.size(); i++) {
      if (arr.get(i).equals(item)) {
        ok = false;
        break;
      }
    }
    return ok;
  }

  public void clearShow() {
    Trie.strrr = "";
    trielevel0.strrr = "";
    trielevel1and2.strrr = "";
    trieRoot.strrr = "";
  }

  public void clearAllArrayList() {
    level1 = new ArrayList<String>();
    level2 = new ArrayList<String>();
    level3 = new ArrayList<String>();

    subTrie1 = new ArrayList<String>();
    subTrie2 = new ArrayList<String>();
    subTrie3 = new ArrayList<String>();

    subTrieModelLevel1 = new ArrayList<TrieNode>();
    subTrieModelLevel2 = new ArrayList<TrieNode>();
    subTrieModelLevel3 = new ArrayList<TrieNode>();
  }

  public void clearArrayList() {
    level2 = new ArrayList<String>();
    level3 = new ArrayList<String>();

    subTrie2 = new ArrayList<String>();
    subTrie3 = new ArrayList<String>();

    subTrieModelLevel2 = new ArrayList<TrieNode>();
    subTrieModelLevel3 = new ArrayList<TrieNode>();
  }

  public void clearState() {
    statePCTrie = 0;
    stateSubtri = 0;
    stateLookup = 0;
    stateHash = 0;
  }

  public TreeBitmapModle getPCtrieIndex(List<String> subTrie, String item, List<TreeBitmapModle> pctries) {
    for (int i = 0; i < subTrie.size(); i++) {
      if (item.equals(subTrie.get(i))) {
        return pctries.get(i);
      }
    }
    return null;
  }

  public String printAllPCTrie(List<TreeBitmapModle> pctries) {
    String str = "";
    for (int j = 0; j < pctries.size(); j++) {
      str = str + "\n" + "\n";
      for (int i = 0; i < pctries.get(j).children.length; i++) {
        for (int f = 0; f <= 1; f++) {
          if (pctries.get(j).children[i][f] != null) {
            str = str + " | " + pctries.get(j).children[i][f];
          }
        }
        if (pctries.get(j).children[i][0] != null) {
          str = str + "\n";
        }
      }
      str = str + pctries.get(j).bitmap;
    }
    return str;
  }

  public String printPCTrie(TreeBitmapModle pctries) {
    String str = "";
    str = str + "\n" + "\n";
    for (int i = 0; i < pctries.children.length; i++) {
      for (int f = 0; f <= 1; f++) {
        if (pctries.children[i][f] != null) {
          str = str + " | " + pctries.children[i][f];
        }
      }
      if (pctries.children[i][0] != null) {
        str = str + "\n";
      }
    }
    str = str + pctries.bitmap;
    return str;
  }

  public String lookUp(String inputIP) {
    String str = "";
    TrieNode pre = trieRoot.searchInTrie(inputIP, trieRoot.root);
    if (pre != null && !pre.next.equals("null")) {
      return pre.next;
    } else if (pre != null && pre.next.equals("null")) {
      if (!Trie.nexLlable.equals("")) {
        return Trie.nexLlable;
      }
    } else if (pre == null) {
      if (!Trie.nexLlable.equals("")) {
        return Trie.nexLlable;
      }
    }
    return str;
  }

  public MemberShip hashing(List<String> subTrie, List<TrieNode> subTrieModelLevel, List<TreeBitmapModle> pctries) {
    Hashtable<Integer, HashModel> hashTable2 = new Hashtable<Integer, HashModel>();
    List<BlackSheepModel> BlackSheep2 = new ArrayList<BlackSheepModel>();
    for (int i = 0; i < subTrie.size(); i++) {
      //Log.i("PRE", " " + subTrie2.get(i) + "  " + Integer.parseInt(subTrie2.get(i), 2) + "  " + (Integer.parseInt(subTrie2.get(i), 2)) % 4);
      int thk = (Integer.parseInt(subTrie.get(i), 2)) % 4;
      HashModel hashModel = hashTable2.get(thk);
      if (hashModel == null) {
        //Log.i("PRE", " " + "n.getCollision()");
        hashTable2.put(thk, new HashModel(subTrie.get(i), subTrieModelLevel.get(i).next, 0));
        // Log.i("PRE", " " + n.getCollision());
      } else {
        //Log.i("PRE", " " + "0");
        BlackSheepModel blackSheepModel = new BlackSheepModel(subTrie.get(i), subTrieModelLevel.get(i).next, pctries.get(i));
        if (hashModel.getCollision() == 0) {
          BlackSheep2.add(blackSheepModel);
          //Log.i("PRE", " " + "1");
          BlackSheepModel blackSheepModel2 = new BlackSheepModel(hashModel.getVerifyBit(), hashModel.getRootNodeNHI(), null);
          BlackSheep2.add(blackSheepModel2);
          hashTable2.put(thk, new HashModel("!", "" + ((BlackSheep2.size() - 1) + "," + (BlackSheep2.size() - 2)), 1));
        } else {
          // Log.i("PRE", " " + "2");
          BlackSheep2.add(blackSheepModel);
          hashTable2.put(thk, new HashModel("!", hashModel.getRootNodeNHI() + "," + (BlackSheep2.size() - 1), 1));
        }

      }
    }

    MemberShip memberShip = new MemberShip();
    memberShip.setBlackSheep(BlackSheep2);
    memberShip.setHashTable(hashTable2);
    return memberShip;
  }

  public String searchInTreeBitmap(TreeBitmapModle tree, String prefix) {
    String result = "";
    String bitmap = prefix.substring(8, prefix.length() - 1);
    String NHI = prefix.substring(prefix.length() - 1, prefix.length());
    int m = 0;
    for (int i = 0; i < tree.bitmap.length(); i++) {
      String h = tree.bitmap.substring(0, 1);
      for (int f = 0; f <= 1; f++) {
        if (tree.children[i][f] != null) {
          if (h.equals("1")) {
            m = m++;
          }
        }
      }
    }
    if (NHI.equals("0")) {
      result = tree.children[m][0];
    } else {
      result = tree.children[m][1];
    }
    return result;
  }

}
