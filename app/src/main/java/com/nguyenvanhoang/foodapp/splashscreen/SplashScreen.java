package com.nguyenvanhoang.foodapp.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.view.cart.ThongTinDonHangActivity;
import com.nguyenvanhoang.foodapp.view.home.MainActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash_screen);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}