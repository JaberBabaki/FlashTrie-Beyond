package com.ario.flashtriebeyond;

import android.content.Context;
import android.util.Log;

import com.ario.flashtriebeyond.model.PrefixNextHob;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jaberALU on 01/01/2018.
 */

public class ReadTextFile {

  private String fileName;
  private Context context;
  private int selectLine;
  private List<PrefixNextHob> prefixNextHob = new ArrayList<PrefixNextHob>();

  public void setSelectLine(int selectLine) {
    this.selectLine = selectLine;
  }


  public int getSelectLine() {
    return selectLine;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setContext(Context context) {
    this.context = context;
  }


  public String getFileName() {
    return fileName;
  }

  public Context getContext() {
    return context;
  }


  public List<PrefixNextHob> readFromfile() {
    String returnString = "\n";
    InputStream fIn = null;
    InputStreamReader isr = null;
    BufferedReader input = null;
    try {
      fIn = getContext().getResources().getAssets().open("data/" + getFileName(), Context.MODE_WORLD_READABLE);
      isr = new InputStreamReader(fIn);
      input = new BufferedReader(isr);
      String line = "";
      int counter = 0;
      int m = 0;
      Log.i("TXT", "" + getSelectLine());
      while ((line = input.readLine()) != null) {

        counter++;
        if (counter == selectLine && m <= 100) {
          Log.i("TXT", "" + counter + "===" + selectLine);
          selectLine = selectLine + 10;
          Pattern p = Pattern.compile("(?<=\\broute\\b).*?(?=\\borigin\\b)");
          Log.i("TXT", "1");
          Matcher m1 = p.matcher(line);
          Log.i("TXT", "2");
          PrefixNextHob pre = new PrefixNextHob();
          Log.i("TXT", "3");
          while (m1.find()) {
            pre.setEnterPort(m1.group().trim());
          }

          pre.setNextHop(line.substring((line.indexOf("next-hop") + 8), line.length()));

          Log.i("TXT", "7");
          m++;
          returnString = returnString + (line + "\n");
          if (m >= 100) {
            break;
          }
          prefixNextHob.add(pre);
        }

      }
    } catch (Exception e) {
      e.getMessage();
    } finally {
      try {
        if (isr != null)
          isr.close();
        if (fIn != null)
          fIn.close();
        if (input != null)
          input.close();
      } catch (Exception e2) {
        e2.getMessage();
      }
    }
    return prefixNextHob;
  }


}
