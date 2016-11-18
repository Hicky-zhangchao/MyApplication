package com.feicuiedu.androidhousekeeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 张超 on 2016/11/3.
 */

public class DrawView extends View {


    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private RectF rf=null;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int viewWidth=MeasureSpec.getSize(widthMeasureSpec);
        int viewHeight=MeasureSpec.getSize(heightMeasureSpec);
        rf=new RectF(0,0,viewWidth,viewHeight);
        setMeasuredDimension(viewWidth,viewHeight);

    }

    private float f1=0;
    private float f2=0;

    private float f1Raw=0;
    private float f2Raw=0;

    public void setParamsWithAnim(float f11,float f22){

        this.f1Raw=f11;
        this.f2Raw=f22;

        final Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {

                f1=f1+4;
                f2=f2+4;
                //通知view刷新
                postInvalidate();
                if(f1 >= f1Raw){
                    f1=f1Raw;
                }
                if(f2 >= f2Raw){
                    f2=f2Raw;
                }
                if(f1 == f1Raw && f2 == f2Raw){
                    timer.cancel();
                }
            }
        };

        timer.schedule(timerTask,26,80);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p=new Paint();
        p.setColor(getResources().getColor(R.color.colorzy1));
        p.setAntiAlias(true);

        canvas.drawArc(rf,-90,f1,true,p);

        p.setColor(getResources().getColor(R.color.colorzy2));
        canvas.drawArc(rf,(f1-90),f2,true,p);

    }


}
