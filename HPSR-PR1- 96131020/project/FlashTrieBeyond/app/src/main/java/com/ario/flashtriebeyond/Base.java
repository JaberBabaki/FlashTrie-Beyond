package com.ario.flashtriebeyond;


import android.app.Application;
import android.graphics.Typeface;

public class Base extends Application {
  public static Typeface font1;
  public static String FONT1_NAME = "font/IRAN-Sans-Light.otf";
  //orginal
   /* public final static int level0=15;
  public final static int level1L=16;
  public final static int level1T=25;
  public final static int level2=26;

  public final static int limit0=2;
  public final static int limit1=3;
  public final static int limit2=4;*/


  //examp1
  public static int level0 = 3;
  public static int level1L = 4;
  public static int level1T = 7;
  public static int level2 = 8;
  public static int limit0 = 0;
  public static int limit1 = 0;
  public static int limit2 = 1;

  //examp2
  /*public final static int level0=3;
  public final static int level1L=4;
  public final static int level1T=7;
  public final static int level2=8;
  public final static int limit0=0;
  public final static int limit1=0;
  public final static int limit2=0;*/


  @Override
  public void onCreate() {
    super.onCreate();
    font1 = Typeface.createFromAsset(getAssets(), FONT1_NAME);
  }

  public static void setInit(int a1, int a2, int a3, int a4, int a5, int a6, int a7) {
    level0 = a1;
    level1L = a2;
    level1T = a3;
    level2 = a4;
    limit0 = a5;
    limit1 = a6;
    limit2 = a7;
  }
}
