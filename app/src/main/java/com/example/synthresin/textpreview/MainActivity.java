package com.example.synthresin.textpreview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Resources;
import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private SeekBar mFontSizeBar;
    private SeekBar mLineSpacingBar;
    private TextView mLatinSingle;
    private TextView mLatinSingleHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        SeekBar fontSizeBar = (SeekBar) findViewById(R.id.fontSizeSeekBar);
        fontSizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int barValue, boolean isFromUser) {
                mLatinSingle.setTextSize((float) barValue);
                mLatinSingleHeight.setText(String.format("%fdp", convertPixelsToDp((float) mLatinSingle.getMeasuredHeight(), getApplicationContext())));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    void initViews() {
        mFontSizeBar = (SeekBar) findViewById(R.id.fontSizeSeekBar);
        mLineSpacingBar = (SeekBar) findViewById(R.id.lineSpacingSeekBar);

        mLatinSingle = (TextView) findViewById(R.id.latinSingle);
        mLatinSingleHeight = (TextView) findViewById(R.id.latinSingleHeight);

        int fontSize = mFontSizeBar.getProgress();
        int lineSpacingExtra = mLineSpacingBar.getProgress();

        mLatinSingle.setTextSize((float) fontSize);
        mLatinSingle.setLineSpacing((float) lineSpacingExtra, 1.0f);
        mLatinSingleHeight.setText(String.format("%fdp", convertPixelsToDp((float) mLatinSingle.getMeasuredHeight(), getApplicationContext())));

    }

    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}
