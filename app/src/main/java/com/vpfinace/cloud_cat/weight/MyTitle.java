package com.vpfinace.cloud_cat.weight;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.utils.StatusTextUtils;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;

public class MyTitle extends FrameLayout implements View.OnClickListener {


    public Context mContext;
    public boolean isBackFinish = true;//返回finish
    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivRightIcon;
    private TextView tvRightText;
    private ImageView ivLeftIcon;
    private LinearLayout llViewContainer;
    private ImageView ivMsgPoint;

    public MyTitle(Context context) {
        this(context, null);
    }

    public MyTitle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initType(context, attrs, defStyleAttr);
    }

    private void initType(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BaseTitleView, defStyleAttr, 0);
        int indexCount = a.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = a.getIndex(i);
            switch (index) {
                case R.styleable.BaseTitleView_title://标题
                    tvTitle.setText(a.getString(index));
                    break;
                case R.styleable.BaseTitleView_back_icon://返回图标
                    ivBack.setImageResource(a.getResourceId(index, R.drawable.icon_back));
                    break;
                case R.styleable.BaseTitleView_left_icon://左图标
                    ivLeftIcon.setImageResource(a.getResourceId(index, R.drawable.icon_back));
                    break;
                case R.styleable.BaseTitleView_right_text://右文字
                    tvRightText.setText(a.getString(index));
                    break;
                case R.styleable.BaseTitleView_right_icon://右图标
                    ivRightIcon.setImageResource(a.getResourceId(index, R.drawable.icon_close));
                    break;
            }
        }
        a.recycle();
    }

    public void initView(Context context) {
        StatusTextUtils.setLightStatusBar((Activity) context,true);
        View view = View.inflate(context, R.layout.base_title_view, null);
        ButterKnife.bind(view, this);
        ivBack = view.findViewById(R.id.iv_back);
        tvTitle = view.findViewById(R.id.tv_title);
        ivRightIcon = view.findViewById(R.id.iv_right_icon);
        tvRightText = view.findViewById(R.id.tv_right_text);
        ivLeftIcon = view.findViewById(R.id.iv_left_icon);
        llViewContainer = view.findViewById(R.id.ll_view_container);
        ivMsgPoint = view.findViewById(R.id.iv_msg_point);

        ivBack.setOnClickListener(this);
        ivRightIcon.setOnClickListener(this);
        tvRightText.setOnClickListener(this);
        ivLeftIcon.setOnClickListener(this);
        addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                if (onBackListener != null) {
                    onBackListener.backClick();
                }
                if (isBackFinish) {
                    if (mContext instanceof Activity) {
                        ((Activity) mContext).onBackPressed();
                    }
                }
                break;
            case R.id.iv_right_icon:
                if (onRightIconClickListener != null) {
                    onRightIconClickListener.rightIconClick();
                }
                break;
            case R.id.tv_right_text:
                if (onRightTextClickListener != null) {
                    onRightTextClickListener.rightTextClick();
                }
                break;
            case R.id.iv_left_icon:
                if (onLeftIconClickListener != null) {
                    onLeftIconClickListener.leftIconClick();
                }
                break;
        }
    }

    //设置文字,资源
    public MyTitle setTitle(String title) {
        tvTitle.setText(title);
        return this;
    }

    public MyTitle setTitleColor(int color) {
        tvTitle.setTextColor(color);
        return this;
    }

    public MyTitle setIvRightIcon(int resouce) {
        ivRightIcon.setImageResource(resouce);
        return this;
    }

    public MyTitle setIvLeftIcon(int resouce) {
        ivLeftIcon.setImageResource(resouce);
        return this;
    }

    public MyTitle setTvRightText(String rightText) {
        tvRightText.setText(rightText);
        return this;
    }

    public MyTitle setTvRightTextColor(int tvRightTextColor) {
        tvRightText.setTextColor(tvRightTextColor);
        return this;
    }

    public MyTitle setTitleBackgroud(int backgroudColor) {
        llViewContainer.setBackgroundColor(backgroudColor);
        return this;
    }

    public MyTitle setTitleBackgroudResouce(int backgroudResouce) {
        llViewContainer.setBackgroundResource(backgroudResouce);
        return this;
    }

    //按钮显示隐藏
    public MyTitle setIsBackVisible(int visiblity) {
        ivBack.setVisibility(visiblity);
        return this;
    }

    public MyTitle setIsLeftIconVisible(int visiblity) {
        ivLeftIcon.setVisibility(visiblity);
        return this;
    }

    public MyTitle setIsRightIconVisible(int visiblity) {
        ivRightIcon.setVisibility(visiblity);
        return this;
    }

    public MyTitle setIsRightTextVisible(int visiblity) {
        tvRightText.setVisibility(visiblity);
        return this;
    }

    //背景颜色
    public MyTitle setTitleBackgroudColor(int color) {
        llViewContainer.setBackgroundColor(color);
        return this;
    }

    //背景图片
    public MyTitle setTitleBackgroudResource(int resoure) {
        llViewContainer.setBackgroundResource(resoure);
        return this;
    }

    //设置消息点显示与否
    public MyTitle setMsgPointvisiblity(int visiblity) {
        ivMsgPoint.setVisibility(visiblity);
        return this;
    }

    //设置添加状态栏高度的padding
    public MyTitle addPaddingStatusBarHeight() {
        llViewContainer.setPadding(llViewContainer.getPaddingLeft(), llViewContainer.getPaddingTop() + BarUtils.getStatusBarHeight(), llViewContainer.getPaddingRight(), llViewContainer.getPaddingBottom());
        return this;
    }

    //设置添加状态栏高度的padding并透明标题栏顶上
    public MyTitle addPaddingStatusBarHeightAndHideStatus(Activity activity) {
        llViewContainer.setPadding(llViewContainer.getPaddingLeft(), llViewContainer.getPaddingTop() + BarUtils.getStatusBarHeight(), llViewContainer.getPaddingRight(), llViewContainer.getPaddingBottom());
        //透明状态栏
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //去除半透明状态栏
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN); //全屏.显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        return this;
    }

    //设置标题高度
    public MyTitle setTitleHeight(int height_dp) {
        ViewGroup.LayoutParams layoutParams = llViewContainer.getLayoutParams();
        layoutParams.height = SizeUtils.dp2px(height_dp);
        llViewContainer.requestLayout();
        return this;
    }

    //点击回调
    public interface OnBackListener {
        void backClick();
    }

    public OnBackListener onBackListener;

    public MyTitle setOnBackListener(OnBackListener onBackListener) {
        this.onBackListener = onBackListener;
        return this;
    }

    public interface OnLeftIconClickListener {
        void leftIconClick();
    }

    public OnLeftIconClickListener onLeftIconClickListener;

    public MyTitle setOnLeftIconClickListener(OnLeftIconClickListener onLeftIconClickListener) {
        this.onLeftIconClickListener = onLeftIconClickListener;
        return this;
    }

    public interface OnRightIconClickListener {
        void rightIconClick();
    }

    public OnRightIconClickListener onRightIconClickListener;

    public MyTitle setOnRightIconClickListener(OnRightIconClickListener onRightIconClickListener) {
        this.onRightIconClickListener = onRightIconClickListener;
        return this;
    }

    public interface OnRightTextClickListener {
        void rightTextClick();
    }

    public OnRightTextClickListener onRightTextClickListener;

    public MyTitle setOnRightTextClickListener(OnRightTextClickListener onRightTextClickListener) {
        this.onRightTextClickListener = onRightTextClickListener;
        return this;
    }
}
