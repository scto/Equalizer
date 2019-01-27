package com.jazibkhan.equalizer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jazibkhan.equalizer.LicensesDialogFragment;
import com.jazibkhan.equalizer.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(sharedPreferences.getBoolean("dark_theme",false)){
            setTheme(R.style.AppTheme_Dark);
            // MainActivity.this.recreate();
        }
        else {
            setTheme(R.style.AppTheme);
            //MainActivity.this.recreate();
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Element license = new Element();
        license.setTitle("Open Source Licenses");

        license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LicensesDialogFragment dialog = LicensesDialogFragment.newInstance();
                dialog.show(getSupportFragmentManager(), "LicensesDialog");

            }
        });

        Element privacyP = new Element();
        privacyP.setTitle("Privacy Policy");
        privacyP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://docs.google.com/document/d/1GoypBqSTuSi_h3k18q9xpnKA0itwb9LvV-izrsOzPOM/edit?usp=sharing";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.mipmap.ic_launcher_round).setDescription("An open source, light weight Equalizer for all devices.").
                addGroup("Connect with us")
                .addEmail("jazib27@hotmail.com")
                .addPlayStore("com.jazibkhan.equalizer")
                .addGitHub("JazibOfficial/Equalizer")
                .addItem(license).addItem(privacyP)
                .create();

        setContentView(aboutPage);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
