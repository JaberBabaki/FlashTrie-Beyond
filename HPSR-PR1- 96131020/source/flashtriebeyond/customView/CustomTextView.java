package com.ario.flashtriebeyond.customView;

import android.content.Context;
import android.util.AttributeSet;

import com.ario.flashtriebeyond.Base;


public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }


    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }


    public CustomTextView(Context context) {
        super(context);
        initialize();
    }


    private void initialize() {
        if ( !isInEditMode()) {
            setTypeface(Base.font1);
        }
    }
}