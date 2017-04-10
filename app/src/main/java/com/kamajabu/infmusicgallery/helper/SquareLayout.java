package com.kamajabu.infmusicgallery.helper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.RelativeLayout;

class SquareLayout extends RelativeLayout {

    int globalHeightMeasureSpec;
    int height;

    public SquareLayout(Context context) {
        super(context);

        getHeight(context);

    }
    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHeight(context);
    }

    public SquareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHeight(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



    private void getHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        if(metrics.heightPixels!=0){
            height = metrics.heightPixels;
        }
    }


}
