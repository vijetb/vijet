package com.vijet.viewcolorpalette;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.vijet.viewcolorpalette.exceptions.InvalidColorCodeException;
import com.vijet.viewcolorpalette.exceptions.InvalidViewException;

/**
 * Created by BMH1014669 on 27/02/15.
 */
public class ViewColorPalette implements IViewColorPalette{
    private final String TAG = "ViewColorPalette";
    private final int MIN_COLOR_VALUE = 0x000000;
    private final int MAX_COLOR_VALUE = 0xffffff;
    //Handler Values
    private final int MSG_UPDATE_COLORING_VIEW = 123;
    //Colors
    private int startColor = MIN_COLOR_VALUE;
    private int endColor = MAX_COLOR_VALUE;
    private int resetStartColor = startColor;
    private int resetEndColor = endColor;
    //coloring view
    private View mColoringView = null;

    // color textview
    private TextView mTextViewCurrentResult = null;


    //Handler
    private Handler mHandler = null;

    public ViewColorPalette(){
        mHandler = new Handler(handlerCallback);
    }

    @Override
    public void setColorRange(final int startColor, final int endColor) throws InvalidColorCodeException {
        validateColorValues(startColor,endColor);
        this.startColor = startColor;
        this.endColor = endColor;
        this.resetStartColor = startColor;
        this.resetEndColor = endColor;
    }

    @Override
    public void resetColor() {
        this.startColor = this.resetStartColor;
        this.endColor = this.resetEndColor;
    }

    @Override
    public void setViewForColoring(final View coloringView) throws InvalidViewException {
        validateView(coloringView);
        setupColoringView(coloringView);
    }

    @Override
    public void setViewForColoring(View coloringView, int startColor, int endColor) throws InvalidColorCodeException, InvalidViewException {
        validateView(coloringView);
        validateColorValues(startColor,endColor);
        setupColoringView(coloringView);
    }

    @Override
    public void setActionView(View actionView) throws InvalidViewException {
        validateView(actionView);
        actionView.setClickable(true);
        actionView.setOnClickListener(actionViewClickListener);
    }

    @Override
    public void setColorValueText(TextView colorValueTextView) throws InvalidViewException {
        validateView(colorValueTextView);
        mTextViewCurrentResult = colorValueTextView;
    }

    private Handler.Callback handlerCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            mColoringView.post(new Runnable() {
                @Override
                public void run() {
                    StringBuilder colorString = new StringBuilder(getBackgroundColor());
                    mColoringView.setBackgroundColor(Color.parseColor(colorString.toString()));
                    if(null!=mTextViewCurrentResult){
                        mTextViewCurrentResult.setText(colorString.toString());
                    }else{
                        Log.d(TAG, "Color Value on the Coloring view - " + colorString.toString());
                    }
                }
            });
            return true;
        }
    };

    private void setupColoringView(final View coloringView){
        mColoringView = coloringView;
        mColoringView.setClickable(true);
        mColoringView.setOnClickListener(actionViewClickListener);
    }

    private View.OnClickListener actionViewClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            mHandler.sendEmptyMessage(MSG_UPDATE_COLORING_VIEW);
        }
    };

    private StringBuilder getBackgroundColor(){
        StringBuilder colorString = new StringBuilder();
        colorString.append("#");
        colorString.append(String.format("%06x",(startColor++)%MAX_COLOR_VALUE));
        return colorString;
    }

    private void validateColorValues(final int startColor, final int endColor) {
        if((startColor < 0) || (endColor < 0)){
            throw new InvalidColorCodeException("Negative Values are not allowed for Colors");
        }
        if(startColor < MIN_COLOR_VALUE || endColor > MAX_COLOR_VALUE ){
            throw new InvalidColorCodeException("Color values are not within Permissible Range");
        }
    }

    private void validateView(final View coloringView){
        if(null == coloringView) throw new InvalidViewException("View object cannot be null");
    }
}
