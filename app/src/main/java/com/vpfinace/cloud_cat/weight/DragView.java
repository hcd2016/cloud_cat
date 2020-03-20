package com.vpfinace.cloud_cat.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * 自定义拖拽view
 */
public class DragView extends RelativeLayout {
    private int lastX;
    private int lastY;
    private int startLeft;
    private int startRight;
    private int startTop;
    private int startBottom;

    public void setOnActionUpListener(OnActionUpListener onActionUpListener) {
        this.onActionUpListener = onActionUpListener;
    }

    private OnActionUpListener onActionUpListener;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        //获取手机触摸的坐标
        int x = (int) event.getX();
        int y = (int) event.getY();
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (action){
            case MotionEvent.ACTION_DOWN://按下,获取小球初始的位置
                startLeft = getLeft();
                startRight = getRight();
                startTop = getTop();
                startBottom = getBottom();
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE://移动,小球跟随手指的移动
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                layout(getLeft()+offsetX,getTop()+offsetY,
                        getRight()+offsetX,getBottom()+offsetY);
//                if(onActionUpListener != null) {
//                    onActionUpListener.onMove(lastX,lastY,x,y,offsetX,offsetY,rawX,rawY);
//                }
                break;
            case MotionEvent.ACTION_UP://当手指抬起时,回到小球初始的位置
                if(onActionUpListener != null) {
                    onActionUpListener.onActionUp(lastX,lastY,startLeft,startTop,startRight,startBottom,rawX,rawY);
                }
                layout(startLeft, startTop, startRight, startBottom);//归位
                break;
        }
        return true;
    }

    public interface OnActionUpListener{
        void onActionUp(int lastX,int lastY,int startLeft,int startTop,int startRight,int startBottom,int rawX,int rawY);

//        void onMove(int lastX,int lastY,int x,int y,int offsetX,int offsetY ,int rawX,int rawY);
    }
}
