package com.vijet.viewcolorpalette;

import android.view.View;
import android.widget.TextView;

import com.vijet.viewcolorpalette.exceptions.InvalidColorCodeException;
import com.vijet.viewcolorpalette.exceptions.InvalidViewException;

public interface IViewColorPalette {
    public void setColorRange(int startColor, int endColor) throws InvalidColorCodeException;
    public void resetColor();
    public void setViewForColoring(View coloringView) throws InvalidViewException;
    public void setViewForColoring(View coloringView, int startColor, int endColor) throws InvalidColorCodeException,InvalidViewException;
    public void setActionView(View actionView) throws InvalidViewException;
    public void setColorValueText(TextView colorValueTextView) throws InvalidViewException;
}
