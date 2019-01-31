package com.jazibkhan.equalizer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.LoudnessEnhancer;
import android.media.audiofx.Virtualizer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.kobakei.ratethisapp.RateThisApp;
import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.marcinmoskala.arcseekbar.ProgressListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, Switch.OnCheckedChangeListener, SharedPreferences.OnSharedPreferenceChangeListener {

    EqualizerViewModel equalizerViewModel;
    static final int MAX_SLIDERS = 5; // Must match the XML layout
    private static final String TAG = "MainActivity";
    public static final String AD_ID = "ca-app-pub-3247504109469111~8021644228";
    public static final String TEST_DEVICE = "59DFE37FE323A9EA004A20E6FED19D38";
    Equalizer equalizer = null;
    BassBoost bassBoost = null;
    Virtualizer virtualizer = null;
    LoudnessEnhancer loudnessEnhancer = null;
    Switch enableEq = null;
    Switch enableBass, enableVirtual, enableLoud;
    Spinner spinner;
    int minLevel = 0;
    int maxLevel = 100;
    SeekBar sliders[] = new SeekBar[MAX_SLIDERS];
    ArcSeekBar bassSlider, virtualSlider, loudSlider;
    TextView sliderLabels[] = new TextView[MAX_SLIDERS];
    TextView loudSliderText;
    int numSliders = 0;
    ArrayList<String> eqPreset;
    int spinnerPos = 0;
    boolean canPreset;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        equalizerViewModel = ViewModelProviders.of(this).get(EqualizerViewModel.class);

        if (PreferenceUtil.getInstance(this).getDarkTheme()) {
            setTheme(R.style.AppTheme_Dark);
        } else setTheme(R.style.AppTheme);
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        setContentView(R.layout.activity_main);
        RateThisApp.onCreate(this);
        RateThisApp.showRateDialogIfNeeded(this);

        MobileAds.initialize(this, AD_ID);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(TEST_DEVICE).build();
        mAdView.loadAd(adRequest);

        loudSliderText = findViewById(R.id.volTextView);
        enableEq = findViewById(R.id.switchEnable);
        enableEq.setChecked(true);
        spinner = findViewById(R.id.spinner);
        sliders[0] = findViewById(R.id.mySeekBar0);
        sliderLabels[0] = findViewById(R.id.centerFreq0);
        sliders[1] = findViewById(R.id.mySeekBar1);
        sliderLabels[1] = findViewById(R.id.centerFreq1);
        sliders[2] = findViewById(R.id.mySeekBar2);
        sliderLabels[2] = findViewById(R.id.centerFreq2);
        sliders[3] = findViewById(R.id.mySeekBar3);
        sliderLabels[3] = findViewById(R.id.centerFreq3);
        sliders[4] = findViewById(R.id.mySeekBar4);
        sliderLabels[4] = findViewById(R.id.centerFreq4);
        bassSlider = findViewById(R.id.bassSeekBar);
        virtualSlider = findViewById(R.id.virtualSeekBar);
        enableBass = findViewById(R.id.bassSwitch);
        enableVirtual = findViewById(R.id.virtualSwitch);
        enableLoud = findViewById(R.id.volSwitch);
        loudSlider = findViewById(R.id.volSeekBar);
        bassSlider.setMaxProgress(1000);
        virtualSlider.setMaxProgress(1000);
        loudSlider.setMaxProgress(10000);
        enableLoud.setChecked(true);
        enableBass.setChecked(true);
        enableVirtual.setChecked(true);
        eqPreset = new ArrayList<>();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, eqPreset);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        equalizer = equalizerViewModel.getEqualizer();
        bassBoost = equalizerViewModel.getBassBoost();
        virtualizer = equalizerViewModel.getVirtualizer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            loudnessEnhancer = equalizerViewModel.getLoudnessEnhancer();
        else {
            enableLoud.setChecked(false);
            loudSlider.setVisibility(View.GONE);
            enableLoud.setVisibility(View.GONE);
            loudSliderText.setVisibility(View.GONE);
        }
        numSliders = equalizer.getNumberOfBands();
        short r[] = equalizer.getBandLevelRange();
        minLevel = r[0];
        maxLevel = r[1];
        for (int i = 0; i < numSliders && i < MAX_SLIDERS; i++) {
            int freq_range = equalizer.getCenterFreq((short) i);
            sliders[i].setOnSeekBarChangeListener(this);
            sliderLabels[i].setText(milliHzToString(freq_range));
        }
        short noOfPresets = equalizer.getNumberOfPresets();
        for (short i = 0; i < noOfPresets; i++) {
            eqPreset.add(equalizer.getPresetName(i));
        }
        eqPreset.add("Custom");
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i < eqPreset.size() - 1) {
                    try {
                        equalizer.usePreset((short) i);
                        equalizerViewModel.setSpinnerPos(i);
                        equalizerViewModel.setIsCustomSelected(false);
                        for (int j = 0; j < 5; j++) {
                            int level = (equalizer.getBandLevel((short) j) - minLevel) * 100 / (maxLevel - minLevel);
                            sliders[j].setProgress(level);
                        }
                    } catch (Throwable e) {
                        disablePreset();
                    }
                } else {
                    equalizerViewModel.setIsCustomSelected(true);
                    equalizerViewModel.setSpinnerPos(i);
                    for (int j = 0; j < 5; j++) {
                        int level = (equalizerViewModel.getSlider(j) - minLevel) * 100 / (maxLevel - minLevel);
                        sliders[j].setProgress(level);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        virtualSlider.setOnProgressChangedListener(new ProgressListener() {
            @Override
            public void invoke(int j) {
                if (virtualizer.getRoundedStrength() != (short) j) {
                    equalizerViewModel.setVirSlider(j);
                }
                try {
                    virtualizer.setStrength((short) j);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });

        bassSlider.setOnProgressChangedListener(new ProgressListener() {
            @Override
            public void invoke(int i) {
                if (bassBoost.getRoundedStrength() != (short) i) {
                    equalizerViewModel.setBBSlider(i);
                }
                try {
                    bassBoost.setStrength((short) i);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });

        loudSlider.setOnProgressChangedListener(new ProgressListener() {
            @Override
            public void invoke(int j) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (loudnessEnhancer.getTargetGain() != j)
                        equalizerViewModel.setLoudSlider(j);
                    try {
                        loudnessEnhancer.setTargetGain(j);
                    } catch (Throwable e) {

                        e.printStackTrace();
                    }
                }
            }
        });


        enableVirtual.setOnCheckedChangeListener(this);
        enableBass.setOnCheckedChangeListener(this);
        enableLoud.setOnCheckedChangeListener(this);
        enableEq.setOnCheckedChangeListener(this);
        spinner.setSelection(equalizerViewModel.getSpinnerPos());
        enableEq.setChecked(equalizerViewModel.getEqSwitch());
        enableBass.setChecked(equalizerViewModel.getbBSwitch());
        enableLoud.setChecked(equalizerViewModel.getLoudSwitch());
        enableVirtual.setChecked(equalizerViewModel.getVirSwitch());
        bassSlider.setProgress(equalizerViewModel.getBBSlider());
        virtualSlider.setProgress(equalizerViewModel.getVirSlider());
        loudSlider.setProgress((int) equalizerViewModel.getLoudSlider());
        for (int i = 0; i < 5; i++) {
            int level = (equalizerViewModel.getSlider(i) - minLevel) * 100 / (maxLevel - minLevel);
            sliders[i].setProgress(level);
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == enableEq) {
            spinner.setEnabled(isChecked);
            equalizer.setEnabled(isChecked);
            for (int i = 0; i < 5; i++) {
                sliders[i].setEnabled(isChecked);
            }
            equalizerViewModel.setEqSwitch(isChecked);

        } else if (buttonView == enableBass) {
            bassBoost.setEnabled(isChecked);
            bassSlider.setEnabled(isChecked);
            equalizerViewModel.setbBSwitch(isChecked);
            if (isChecked)
                bassSlider.setProgressColor(ContextCompat.getColor(getBaseContext(), R.color.colorAccent));
            else
                bassSlider.setProgressColor(ContextCompat.getColor(getBaseContext(), R.color.progress_gray));

        } else if (buttonView == enableLoud) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                loudnessEnhancer.setEnabled(isChecked);
            loudSlider.setEnabled(isChecked);
            equalizerViewModel.setLoudSwitch(isChecked);
            if (isChecked) {
                loudSlider.setProgressColor(ContextCompat.getColor(getBaseContext(), R.color.colorAccent));
                Toast.makeText(getApplicationContext(), R.string.warning,
                        Toast.LENGTH_SHORT).show();
            } else
                loudSlider.setProgressColor(ContextCompat.getColor(getBaseContext(), R.color.progress_gray));

        } else if (buttonView == enableVirtual) {
            virtualizer.setEnabled(isChecked);
            virtualSlider.setEnabled(isChecked);
            equalizerViewModel.setVirSwitch(isChecked);
            if (isChecked)
                virtualSlider.setProgressColor(ContextCompat.getColor(getBaseContext(), R.color.colorAccent));
            else
                virtualSlider.setProgressColor(ContextCompat.getColor(getBaseContext(), R.color.progress_gray));
        }
        serviceChecker();
    }


    public String milliHzToString(int milliHz) {
        if (milliHz < 1000) return "";
        if (milliHz < 1000000)
            return "" + (milliHz / 1000) + "Hz";
        else
            return "" + (milliHz / 1000000) + "kHz";
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int level, boolean b) {
        for (int i = 0; i < 5; i++) {
            if (sliders[i] == seekBar) {
                int newLevel = minLevel + (maxLevel - minLevel) * level / 100;
                equalizer.setBandLevel((short) i, (short) newLevel);
                if (equalizerViewModel.getIsCustomSelected())
                    equalizerViewModel.setSlider(newLevel, i);
                break;
            }
        }
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if(s.equals("dark_theme")){
            if(sharedPreferences.getBoolean("dark_theme",false)){
                setTheme(R.style.AppTheme_Dark);
                MainActivity.this.recreate();
            }
            else {
                setTheme(R.style.AppTheme);
                MainActivity.this.recreate();
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if(!equalizerViewModel.getIsCustomSelected()) {
            for (int i = 0; i < 5; i++) {
                int newLevel = minLevel + (maxLevel - minLevel) * sliders[i].getProgress() / 100;
                equalizerViewModel.setSlider(newLevel, i);
            }
            spinner.setSelection(eqPreset.size() - 1);
            spinnerPos = eqPreset.size() - 1;
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
            MainActivity.this.startActivity(myIntent);
            return true;
        }
        if (id == R.id.action_about) {
            Intent myIntent = new Intent(MainActivity.this, AboutActivity.class);
            MainActivity.this.startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void disablePreset() {
        spinner.setVisibility(View.GONE);
        canPreset = false;
    }

    public void serviceChecker() {
        if (enableEq.isChecked() || enableBass.isChecked() || enableVirtual.isChecked() || enableLoud.isChecked()) {
            Intent startIntent = new Intent(MainActivity.this, ForegroundService.class);
            startIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
            startService(startIntent);
        } else {
            Intent stopIntent = new Intent(MainActivity.this, ForegroundService.class);
            stopIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
            startService(stopIntent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }
}
