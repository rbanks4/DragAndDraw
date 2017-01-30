package com.bignerdranch.draganddraw;

import android.graphics.PointF;

/**
 * Created by rbanks on 11/14/16.
 */

public class Box {
    private PointF m_origin;
    private PointF m_current;

    public PointF getCurrent() {
        return m_current;
    }

    public void setCurrent(PointF current) {
        m_current = current;
    }

    public PointF getOrigin() {
        return m_origin;
    }

    public Box(PointF origin) {
        m_origin = origin;
        m_current = origin;
    }
}
