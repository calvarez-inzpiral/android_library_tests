package com.inzpiral.consumer.customs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class RingGraphicDrawable extends Drawable {

    private Paint paint;
    private RectF rectF;
    private int color;
    private Direction angle;
    private int degrees;

    public enum Direction {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }

    public RingGraphicDrawable(int degrees) {
        this(Color.GREEN, Direction.RIGHT, degrees);
    }

    public RingGraphicDrawable(int color, Direction angle, int degrees) {
        this.color = color;
        this.angle = angle;
        this.degrees = degrees;
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Style.FILL);
        rectF = new RectF();
    }

    public int getColor() {
        return color;
    }

    /**
     * A 32bit color not a color resources.
     * @param color
     */
    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();

        Rect bounds = getBounds();

        canvas.scale(0.8F, 0.8F);
        canvas.translate(10, 10);

        rectF.set(bounds);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        
        if(angle == Direction.LEFT)
            canvas.drawArc(rectF, 90, degrees, false, paint);
        else if(angle == Direction.TOP)
            canvas.drawArc(rectF, -180, degrees, false, paint);
        else if(angle == Direction.RIGHT)
            canvas.drawArc(rectF, 270, degrees, false, paint);
        else if(angle == Direction.BOTTOM)
            canvas.drawArc(rectF, 0, degrees, false, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        // Has no effect
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        // Has no effect
    }

    @Override
    public int getOpacity() {
        // Not Implemented
        return 0;
    }
    
    public void setDegrees(int degrees) {
		this.degrees = degrees;
		invalidateSelf();
	}

}