package com.ario.flashtriebeyond.model;

/**
 * Created by jaber babaki on 7/21/2016.
 */
public class PrefixNextHob {
  private int id;
  private String enterPort;
  private String prefix;
  private String nextHop;
  private int View;

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setEnterPort(String enterPort) {
    this.enterPort = enterPort;
  }

  public void setNextHop(String nextHop) {
    this.nextHop = nextHop;
  }

  public void setView(int view) {
    View = view;
  }


  public String getEnterPort() {
    return enterPort;
  }

  public String getNextHop() {
    return nextHop;
  }

  public int getView() {
    return View;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


}
