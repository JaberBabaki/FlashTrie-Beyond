package com.ario.flashtriebeyond.utils;

import android.util.Log;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by jaberALU on 16/01/2018.
 */

public class IpToPrefix {
  public String convert(String str) {
    byte[] bytes = new byte[0];
    String prefix="";
    try {
      String inputIP = str.substring(0, str.indexOf("/"));
      String strSub = str.substring(str.indexOf("/") + 1, str.length());
      int subNet = Integer.parseInt(strSub);
      bytes = InetAddress.getByName(inputIP).getAddress();
      String binaryIp = new BigInteger(1, bytes).toString(2);
      int f = 32 - binaryIp.length();
      for (int y = 0; y < f; y++) {
        binaryIp = "0" + binaryIp;
      }
      prefix= binaryIp.substring(0, subNet);
      Log.i("PRE", str + "  " + subNet + " " + inputIP + "   " + binaryIp + "    " + binaryIp.substring(0, subNet) + "  " + binaryIp.length());
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return prefix;
  }

}
