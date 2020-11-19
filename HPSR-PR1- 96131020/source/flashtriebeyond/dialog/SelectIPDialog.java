package com.ario.flashtriebeyond.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ario.flashtriebeyond.Base;
import com.ario.flashtriebeyond.R;


public class SelectIPDialog extends Dialog {

  private String dialogText;
  private String buttonLabel;

  private TextView txtErrorDialogText;
  public Button btnErrorDialog ;
  public LinearLayout layExamp1;
  public TextView layExamp2 ;

  public EditText edtInput;
  public Context context;

  public SelectIPDialog(Context context,String str) {
    super(context);
    this.context = context;
    dialogText=str;
    buttonLabel = "تایید";
  }

  public SelectIPDialog(Context context, String dialogText, String buttonLabel) {
    super(context);
    this.dialogText = dialogText;
    this.buttonLabel = buttonLabel;
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setCanceledOnTouchOutside(false);
    setContentView(R.layout.dialog_error);
    setCancelable(false);
    getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    btnErrorDialog  = (Button) findViewById(R.id.btnErrorDialog);
    txtErrorDialogText  = (TextView) findViewById(R.id.txtErrorDialogText);

    layExamp1  = (LinearLayout) findViewById(R.id.lay_exam1);
    layExamp2  = (TextView) findViewById(R.id.lay_exam2);

    edtInput = (EditText) findViewById(R.id.edt_input);
    edtInput.setTypeface(Base.font1);
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
