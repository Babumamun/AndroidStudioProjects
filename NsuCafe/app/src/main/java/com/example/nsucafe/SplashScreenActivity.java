package com.example.nsucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    private  static int SPLASH_TIME_OUT=4000;
    Animation top_animation,bottom_animation;
    private ImageView panda,eatLogo;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        top_animation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_animation= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        panda=findViewById(R.id.im_panda);
        eatLogo=findViewById(R.id.im_logo);
        //textView=findViewById(R.id.tv_text);
        //set Animation
        panda.setAnimation(top_animation);
        eatLogo.setAnimation(bottom_animation);
      // textView.setAnimation(bottom_animation);

     new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent= new Intent(SplashScreenActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }
    },SPLASH_TIME_OUT);

     // jsone type
        // json parse
}
}