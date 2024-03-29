package com.ario.flashtriebeyond;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;


public class SplashScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.splash_screen);

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {

      @Override
      public void run() {

        finish();

        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);

      }

    }, 4000);
  }
}
