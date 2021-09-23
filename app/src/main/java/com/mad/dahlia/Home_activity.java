package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class Home_activity extends AppCompatActivity {

    ImageButton menubutton;

    SliderView sliderView;
    int[] images = {
            R.drawable.homeslideimg1,
            R.drawable.homeslideimg2,
            R.drawable.homeslideimg3,
            R.drawable.homeslideimg4,
            R.drawable.homeslideimg5,
            R.drawable.homeslideimg6
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton menubutton = (ImageButton) findViewById(R.id.btn_sidenav);
        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menubtn = new Intent(Home_activity.this,menu.class);
                startActivity(menubtn);
            }
        });

        sliderView = findViewById(R.id.image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


    }
}