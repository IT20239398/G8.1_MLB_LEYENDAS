package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;


public class LoginSignupFrag extends AppCompatActivity {

        ViewPagerAdapter viewPagerAdapter;
        CardView facebook, google, instergram;
        float v = 0;

        private Button button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_signup_frag);

            TabLayout tabLayout = findViewById(R.id.tabLayout);
            ViewPager viewPager = findViewById(R.id.viewPager);
            facebook = findViewById(R.id.facebook_login);
            google = findViewById(R.id.google_login);
            instergram = findViewById(R.id.inst_login);
            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

            //Add Fragment
            viewPagerAdapter.AddFragment(new LoginFragment(), "Login");
            viewPagerAdapter.AddFragment(new SignupFragment(), "Signup");
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);

            facebook.setTranslationY(300);
            google.setTranslationY(300);
            instergram.setTranslationY(300);
            tabLayout.setTranslationY(300);

            facebook.setTranslationY(v);
            google.setTranslationY(v);
            instergram.setTranslationY(v);
            tabLayout.setTranslationY(v);

            facebook.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
            google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
            instergram.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
            tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();


        }
    }
