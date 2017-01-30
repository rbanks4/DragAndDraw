package com.bignerdranch.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbanks on 11/14/16.
 */

public class BoxDrawingView extends View {

    private static final String TAG = "BoxDrawingView";

    private Box m_currentBox;
    private List<Box> m_boxen = new ArrayList<>();
    private Paint m_boxPaint;
    private Paint m_backgroundPaint;

    //used when creating a view in code
    public BoxDrawingView(Context context) {
        this(context, null);
    }

    //used when inflating a view from xml
    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //paint the boxes a nice semitransparent red (ARGB)
        m_boxPaint = new Paint();
        m_boxPaint.setColor(0x22ff0000);

        //paint the background off-white
        m_backgroundPaint = new Paint();
        m_backgroundPaint.setColor(0xfff8efe0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());
        String action = "";

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                //reset drawing state
                m_currentBox = new Box(current);
                m_boxen.add(m_currentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                if(m_currentBox != null) {
                    m_currentBox.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                m_currentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";
                m_currentBox = null;
                break;
        }

        Log.i(TAG, action + " at x=" + current.x +
        ", y =" + current.y);

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //fill the background
        canvas.drawPaint(m_backgroundPaint);

        for(Box box : m_boxen) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            canvas.drawRect(left, top, right, bottom, m_boxPaint);
        }
    }
}
