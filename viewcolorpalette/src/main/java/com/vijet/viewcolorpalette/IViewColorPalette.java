/**
 * @author Vijet Badigannavar(bvijet@gmail.com)
 * @modified 28-Feb-2015
 */
package com.vijet.viewcolorpalette;

import android.view.View;
import android.widget.TextView;

import com.vijet.viewcolorpalette.exceptions.InvalidColorCodeException;
import com.vijet.viewcolorpalette.exceptions.InvalidViewException;

/**
 * Common Interface for the ColorPalette that defines all the api.
 * More info at  {@link https://github.com/vijetb/vijet/wiki/ViewColorPalette }
 */
public interface IViewColorPalette {
    /**
     * Sets the Range of colors for Coloring View. On ActionView clicked, the start value will be
     * incremented by 1 and goes up to the endColor for each click.<p>
     * Default values (0x000000 - 0xffffff)
     * @param startColor
     *                   Integer value usually defined in Hexadecimal indicating the
     *                   starting color for the coloring view.
     * @param endColor
     *                   Integer value usually defined in Hexadecimal indicating the ending
     *                   color for the coloring view.
     * @throws InvalidColorCodeException if the value of the color is negative or more than the
     * accepted value(0xffffff).
     */
    public void setColorRange(int startColor, int endColor) throws InvalidColorCodeException;
    /**
     * Reset the coloring view to the default values. The reset values will be the one that is
     * passed into setColorRange(..) or setViewForColoring(View coloringView, int startColor, int endColor)
     * or set to default values (0x000000 - 0xffffff).
     */
    public void resetColor();
    /**
     * Sets the coloring view. The background of this view will be changed for each click on the
     * actionView. This method also sets the actionView as the coloringView so if the setActionView
     * is not set, then for each click on the actionView the value of the background color will be
     * increased.
     * @param coloringView
     *                      view whose background will be changed.
     * @throws InvalidViewException
     *                      if view is null
     */
    public void setViewForColoring(View coloringView) throws InvalidViewException;
    /**
     * This method combines the setColorRange(int startColor, int endColor) and setViewForColoring(View coloringView)
     * method.
     * @param coloringView
     *                  view whose background will be changed.
     * @param startColor
     *                  Integer value usually defined in Hexadecimal indicating the
     *                  starting color for the coloring view.
     * @param endColor
     *                  Integer value usually defined in Hexadecimal indicating the ending
     *                  color for the coloring view.
     * @throws InvalidColorCodeException
     *                 InvalidColorCodeException if the value of the color is negative or more than the
     *                 accepted value(0xffffff).
     * @throws InvalidViewException
     *                 if view is null
     */
    public void setViewForColoring(View coloringView, int startColor, int endColor) throws InvalidColorCodeException,InvalidViewException;
    /**
     * Sets the actionView for the coloringView. On each click on the actionView, the color of the
     * coloring view will be incremented by 1 value and set to the coloring view.
     * @param actionView
     *                  The view upon click, the color of the background is going to change
     * @throws InvalidViewException
     *                  if view is null.
     */
    public void setActionView(View actionView) throws InvalidViewException;
    /**
     * Sets the textview to display the current color of the coloring view. If this view is set the
     * color value will be displayed on the TextView or else it will be displayed on the Log console.
     * @param colorValueTextView
     *                          TextView that displays the value of the color of the coloring view.
     * @throws InvalidViewException
     *                              if view is null.
     */
    public void setColorValueText(TextView colorValueTextView) throws InvalidViewException;
}
