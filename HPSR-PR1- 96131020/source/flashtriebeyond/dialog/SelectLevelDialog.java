package com.ario.flashtriebeyond.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ario.flashtriebeyond.Base;
import com.ario.flashtriebeyond.R;


public class SelectLevelDialog extends Dialog {

  private String dialogText;
  private String buttonLabel;

  private TextView txtErrorDialogText;
  public Button btnErrorDialog ;

  public LinearLayout level0;
  public LinearLayout level1;
  public LinearLayout level2;
  public LinearLayout allLevel;

  public Context context;

  public SelectLevelDialog(Context context, String str) {
    super(context);
    this.context = context;
    dialogText=str;
    buttonLabel = "cancel";
  }

  public SelectLevelDialog(Context context, String dialogText, String buttonLabel) {
    super(context);
    this.dialogText = dialogText;
    this.buttonLabel = buttonLabel;
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setCanceledOnTouchOutside(false);
    setContentView(R.layout.select_level);
    setCancelable(false);
    getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    btnErrorDialog  = (Button) findViewById(R.id.btnErrorDialog);
    txtErrorDialogText  = (TextView) findViewById(R.id.txtErrorDialogText);

    level0  = (LinearLayout) findViewById(R.id.lay_level0);
    level1  = (LinearLayout) findViewById(R.id.lay_level1);
    level2  = (LinearLayout) findViewById(R.id.lay_level2);
    allLevel  = (LinearLayout) findViewById(R.id.lay_all_level);


    btnErrorDialog.setTypeface(Base.font1);
    txtErrorDialogText.setText(dialogText);

    btnErrorDialog.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        dismiss();
      }
    });
  }

  @Override
  public void show() {
    try {
      super.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
