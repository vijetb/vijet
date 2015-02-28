/**
 * @author Vijet Badigannavar(bvijet@gmail.com)
 * @modified 28-Feb-2015
 */
package com.vijet.viewcolorpalette;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.vijet.viewcolorpalette.exceptions.InvalidColorCodeException;
import com.vijet.viewcolorpalette.exceptions.InvalidViewException;

/**
 * Implementation of the IViewColorPalette interface.
 * More info at  {@link https://github.com/vijetb/vijet/wiki/ViewColorPalette }
 */
public class ViewColorPalette implements IViewColorPalette{
    /**
     * Tag for the class
     */
    private final String TAG = "ViewColorPalette";
    /**
     * MIN Accepted color value
     */
    private final int MIN_COLOR_VALUE = 0x000000;
    /**
     * MAX Accepted color value
     */
    private final int MAX_COLOR_VALUE = 0xffffff;
    /**
     * HANDLER Constant that will be used to send message to change the background
     */
    private final int MSG_UPDATE_COLORING_VIEW = 123;
    /**
     * Starting Color
     */
    private int startColor = MIN_COLOR_VALUE;
    /**
     * Ending Color
     */
    private int endColor = MAX_COLOR_VALUE;
    /**
     * Temprory values that stores the Color values used for resetting
     */
    private int resetStartColor = startColor;
    private int resetEndColor = endColor;
    /**
     * Coloring View
     */
    private View mColoringView = null;
    /**
     * TextView to display the current Color on the Coloring View
     */
    private TextView mTextViewCurrentResult = null;
    /**
     * Handler
     */
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

    /**
     * Handler callback that retrives the nextvalue of the startColor and sets the
     * background of the coloring view. If the mTextViewCurrentResult is set then the value will
     * be updated on the textview else it will be displayed on the log.
     */
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

    /**
     * Setup the coloring view and add the clickListener to the coloringView
     * @param coloringView the view whose background color will be changed
     */
    private void setupColoringView(final View coloringView){
        mColoringView = coloringView;
        mColoringView.setClickable(true);
        mColoringView.setOnClickListener(actionViewClickListener);
    }

    /**
     * ActionView Listener upon click sends and empty msg to the handler.
     */
    private View.OnClickListener actionViewClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            mHandler.sendEmptyMessage(MSG_UPDATE_COLORING_VIEW);
        }
    };

    /**
     * Returns the incremented value of the startColor appended with #.
     * @return
     */
    private StringBuilder getBackgroundColor(){
        StringBuilder colorString = new StringBuilder();
        colorString.append("#");
        colorString.append(String.format("%06x",(startColor++)%MAX_COLOR_VALUE));
        return colorString;
    }

    /**
     * Validate the color values
     * @param startColor starting value of the color
     * @param endColor ending value of the color.
     */
    private void validateColorValues(final int startColor, final int endColor) {
        if((startColor < 0) || (endColor < 0)){
            throw new InvalidColorCodeException("Negative Values are not allowed for Colors");
        }
        if(startColor < MIN_COLOR_VALUE || endColor > MAX_COLOR_VALUE ){
            throw new InvalidColorCodeException("Color values are not within Permissible Range");
        }
    }

    /**
     * Validate the view for null
     * @param coloringView view that needs to be validated
     */
    private void validateView(final View coloringView){
        if(null == coloringView) throw new InvalidViewException("View object cannot be null");
    }
}
