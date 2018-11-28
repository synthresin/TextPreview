package com.example.synthresin.textpreview;

import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Resources;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private float mFontSize;
    private float mLineSpacingExtra;

    private SeekBar mFontSizeBar;
    private SeekBar mLineSpacingBar;
    private TextView mFontSizeValueLabel;
    private TextView mLineSpacingValueLabel;

    private TextView mLatinSingle;
    private TextView mLatinSingleHeight;

    private TextView mLatinMultiple;
    private TextView mLatinMultipleHeight;

    private TextView mCJKSingle;
    private TextView mCJKSingleHeight;

    private TextView mCJKMultiple;
    private TextView mCJKMultipleHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVariables(); // 모든 뷰들을 인스턴스 변수에 박는다.
        initFont();
        initListeners();
        refreshViews();


        mFontSizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int barValue, boolean isFromUser) {
                mFontSize = fontSizeProgressToVal(barValue);
                refreshViews();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mLineSpacingBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int barValue, boolean isFromUser) {
                mLineSpacingExtra = lineSpacingProgressToVal(barValue);
                refreshViews();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    void initVariables() {
        mFontSizeBar = (SeekBar) findViewById(R.id.fontSizeSeekBar);
        mLineSpacingBar = (SeekBar) findViewById(R.id.lineSpacingSeekBar);

        mFontSize = fontSizeProgressToVal(mFontSizeBar.getProgress());
        mLineSpacingExtra = lineSpacingProgressToVal(mLineSpacingBar.getProgress());

        mFontSizeValueLabel = findViewById(R.id.fontSizeValue);
        mLineSpacingValueLabel = findViewById(R.id.lineSpacingValue);

        mLatinSingle = (TextView) findViewById(R.id.latinSingle);
        mLatinSingleHeight = (TextView) findViewById(R.id.latinSingleHeight);

        mLatinMultiple = (TextView) findViewById(R.id.latinMultiple);
        mLatinMultipleHeight = (TextView) findViewById(R.id.latinMultipleHeight);

        mCJKSingle = (TextView) findViewById(R.id.CJKSingle);
        mCJKSingleHeight = (TextView) findViewById(R.id.CJKSingleHeight);

        mCJKMultiple = (TextView) findViewById(R.id.CJKMultiple);
        mCJKMultipleHeight = (TextView) findViewById(R.id.CJKMultipleHeight);
    }

    void initFont() {
        Typeface roboto = ResourcesCompat.getFont(this, R.font.roboto_regular);
        Typeface noto = ResourcesCompat.getFont(this, R.font.noto_regular);

//        mLatinSingle.setTypeface(roboto);
//        mLatinMultiple.setTypeface(roboto);
//        mCJKSingle.setTypeface(noto);
//        mCJKMultiple.setTypeface(noto);
//
//        mLatinSingle.setTypeface(Typeface.DEFAULT);
//        mLatinMultiple.setTypeface(Typeface.DEFAULT);
//        mCJKSingle.setTypeface(Typeface.DEFAULT);
//        mCJKMultiple.setTypeface(Typeface.DEFAULT);
    }

    void initListeners() {
        mLatinSingle.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                mLatinSingleHeight.setText(String.format("%fdp", convertPixelsToDp((float) (bottom - top), v.getContext())));
            }
        });

        mLatinMultiple.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                mLatinMultipleHeight.setText(String.format("%fdp", convertPixelsToDp((float) (bottom - top), v.getContext())));
            }
        });

        mCJKSingle.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                mCJKSingleHeight.setText(String.format("%fdp", convertPixelsToDp((float) (bottom - top), v.getContext())));
            }
        });

        mCJKMultiple.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                mCJKMultipleHeight.setText(String.format("%fdp", convertPixelsToDp((float) (bottom - top), v.getContext())));
            }
        });
    }

    void refreshViews() {
        mFontSizeValueLabel.setText(String.format("%fdp", mFontSize));
        mLineSpacingValueLabel.setText(String.format("%fdp", mLineSpacingExtra));

        mLatinSingle.setTextSize(mFontSize);
        mLatinSingle.setLineSpacing(mLineSpacingExtra, 1.0f);

        mLatinMultiple.setTextSize(mFontSize);
        mLatinMultiple.setLineSpacing(mLineSpacingExtra, 1.0f);

        mCJKSingle.setTextSize(mFontSize);
        mCJKSingle.setLineSpacing(mLineSpacingExtra, 1.0f);

        mCJKMultiple.setTextSize(mFontSize);
        mCJKMultiple.setLineSpacing(mLineSpacingExtra, 1.0f);
    }

    float fontSizeProgressToVal(int progress) {
        return (float) progress + 11.0f;
    }

    float lineSpacingProgressToVal(int progress) {
        return (float) progress - 10.0f;
    }

    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}