package com.vpfinace.cloud_cat.dialog;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.utils.MyUtils;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 常用提示dialog
 */
public class CommonTipsDialog extends DialogFragment {
    public OnLeftClickListner onLeftClickListner;
    public OnRightClickListner onRightClickListner;
    public DialogInterface.OnDismissListener onDismissListener;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.view_line_id)
    View viewLineId;
    @BindView(R.id.tv_btnLeft)
    TextView tvBtnLeft;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_btnRight)
    TextView tvBtnRight;
    @BindView(R.id.v_space)
    View vSpace;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }


    public void setOnLeftClickListner(OnLeftClickListner onLeftClickListner) {
        this.onLeftClickListner = onLeftClickListner;
    }

    public void setOnRightClickListner(OnRightClickListner onRightClickListner) {
        this.onRightClickListner = onRightClickListner;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_common_tips_dialog, null);
        ButterKnife.bind(this, view);
        setData();
        return view;
    }

    public static CommonTipsDialog newInstance(String title, String content, String btnLeft, String btnRight, boolean isCancel, float width, float height, int gravity, int titleVisibility, int vSpaceVisibility, int contentColor) {
        CommonTipsDialog commonTipsDialogFragment = new CommonTipsDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("content", content);
        bundle.putString("btnLeft", btnLeft);
        bundle.putString("btnRight", btnRight);
        bundle.putBoolean("isCancel", isCancel);
        bundle.putFloat("width", width);
        bundle.putFloat("height", height);
        bundle.putInt("gravity", gravity);
        bundle.putInt("titleVisibility", titleVisibility);
        bundle.putInt("vSpaceVisibility", vSpaceVisibility);
        bundle.putInt("contentColor", contentColor);
        commonTipsDialogFragment.setArguments(bundle);
        return commonTipsDialogFragment;
    }

    private void setData() {
        String title = getArguments().getString("title");
        String content = getArguments().getString("content");
        String btnLeft = getArguments().getString("btnLeft");
        String btnRight = getArguments().getString("btnRight");
        int titleVisibility = getArguments().getInt("titleVisibility", View.VISIBLE);
        int vSpaceVisibility = getArguments().getInt("vSpaceVisibility", View.GONE);
        int contentColor = getArguments().getInt("contentColor");
        if (titleVisibility == -1) {
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(titleVisibility);
        }
        if (vSpaceVisibility == -1) {
            vSpace.setVisibility(View.GONE);
        } else {
            vSpace.setVisibility(vSpaceVisibility);
        }
        tvTitle.setText(title == null ? tvTitle.getText().toString() : title);
        tvContent.setText(content == null ? tvContent.getText().toString() : content);
        if (contentColor != 0) {
            tvContent.setTextColor(MyUtils.getColor(contentColor));
        }
        if (btnLeft == null) {//传空为只有一个按钮
            tvBtnLeft.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
        } else {
            tvBtnLeft.setText(btnLeft);
        }
        if (btnRight == null) {
            tvBtnRight.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
        } else {
            tvBtnRight.setText(btnRight);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        boolean isCancel = getArguments().getBoolean("isCancel");
        getDialog().setCancelable(isCancel);//必须在这调用才生效
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initDialog();
    }

    private void initDialog() {
        float width = getArguments().getFloat("width");
        float height = getArguments().getFloat("height");
        int gravity = getArguments().getInt("gravity");

        getDialog().getWindow().setGravity(gravity);
//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        int lastWidth;
        if (width > 0 && width < 1) {
            lastWidth = (int) (dm.widthPixels * width);
        } else if (width == 0) {//未设置默认宽度为0.8
            lastWidth = (int) (dm.widthPixels * 0.8);
        } else if (width > dm.widthPixels) {
            lastWidth = (int) (dm.widthPixels * 0.8);
        } else if (width == 1) {
            lastWidth = ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            lastWidth = (int) width;
        }

        int lastHeight;
        if (height > 0 && height < 1) {
            lastHeight = (int) (dm.heightPixels * height);
        } else if (height == 0) {//默认设置为WRAP_CONTENT
            lastHeight = WindowManager.LayoutParams.WRAP_CONTENT;
        } else {
            lastHeight = (int) height;
        }
        getDialog().getWindow().setLayout(lastWidth, lastHeight);
    }

    //点击按钮后是否需要关闭dialog控制,默认都关闭
    public static boolean isLeftClickDismiss = true;
    public static boolean isRightClickDiamiss = true;

    @OnClick({R.id.tv_btnLeft, R.id.tv_btnRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btnLeft:
                if (onLeftClickListner != null) {
                    onLeftClickListner.leftClick();
                }
                if (isLeftClickDismiss) dismiss();
                break;
            case R.id.tv_btnRight:
                if (onRightClickListner != null) {
                    onRightClickListner.rightClick();
                }
                if (isRightClickDiamiss) dismiss();
                break;
        }
    }

    public void show(FragmentActivity activity) {
        show(activity.getSupportFragmentManager(), "base_dialog_tag");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

//    //单行单独处理设置margin
//    public void setCommonMarginForSingLine() {
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        lp.setMargins(0, DensityUtil.dp2px(44), 0, 0);
//        viewLineId.setLayoutParams(lp);
//    }


    public static class Buidler {
        public String title;
        public String content;
        public String btnLeft;
        public String btnRight;
        public int titleVisibility = -1;
        public int vSpaceVisibility = -1;
        public int contentColor;
        public boolean isCancel = true;//点击外部是否关闭
        public float width = 0.8f;//dialog相对屏幕宽度的百分比,默认为屏幕的百分之80
        public int gravity = Gravity.CENTER; //默认为居中
        public float height = ViewGroup.LayoutParams.WRAP_CONTENT; //默认为包裹内容,可传0~1百分比
        public OnLeftClickListner onLeftClickListenr;
        public OnRightClickListner onRightClickListner;

        public Buidler setWidth(float width) {
            this.width = width;
            return this;
        }

        public Buidler setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Buidler setHeight(float height) {
            this.height = height;
            return this;
        }

        public Buidler setCancelable(boolean cancel) {
            isCancel = cancel;
            return this;
        }

        public Buidler setTitle(String title) {
            this.title = title;
            return this;
        }

        public Buidler setContent(String content) {
            this.content = content;
            return this;
        }

        public Buidler setBtnLeft(String btnLeft) {
            this.btnLeft = btnLeft;
            return this;
        }

        public Buidler setBtnRight(String btnRight) {
            this.btnRight = btnRight;
            return this;
        }

        public Buidler setTitleVisibility(int visibility) {
            this.titleVisibility = visibility;
            return this;
        }

        public Buidler setvSpaceVisibility(int visibility) {
            this.vSpaceVisibility = visibility;
            return this;
        }

        public Buidler setContentTextColor(int color) {
            this.contentColor = color;
            return this;
        }

        //点击左按钮是否关闭弹窗
        public Buidler setIsLeftDismiss(boolean isLeftDismiss) {
            isLeftClickDismiss = isLeftDismiss;
            return this;
        }

        public Buidler setIsRightDismiss(boolean isRightDismiss) {
            isRightClickDiamiss = isRightDismiss;
            return this;
        }

        public Buidler setOnLeftClickListenr(OnLeftClickListner leftClickListenr) {
            this.onLeftClickListenr = leftClickListenr;
            return this;
        }

        public Buidler setOnRightClickListener(OnRightClickListner rightClickListener) {
            this.onRightClickListner = rightClickListener;
            return this;
        }

        public CommonTipsDialog create() {
            CommonTipsDialog dialog = newInstance(title, content, btnLeft, btnRight, isCancel, width, height, gravity, titleVisibility, vSpaceVisibility, contentColor);
            //将回调传出去给dialog
            dialog.setOnLeftClickListner(onLeftClickListenr);
            dialog.setOnRightClickListner(onRightClickListner);
            return dialog;
        }

        public void createAndShow(FragmentActivity activity) {
            CommonTipsDialog dialog = newInstance(title, content, btnLeft, btnRight, isCancel, width, height, gravity, titleVisibility, vSpaceVisibility, contentColor);
            //将回调传出去给dialog
            dialog.setOnLeftClickListner(onLeftClickListenr);
            dialog.setOnRightClickListner(onRightClickListner);
            dialog.show(activity);
        }
    }

    public interface OnLeftClickListner {
        void leftClick();
    }

    public interface OnRightClickListner {
        void rightClick();
    }

/**
 *   调用示例


         new CommonTipsDialog
         .Buidler()
         .setTitle("我是标题")
         .setContent("我是内容")
         .setBtnRight("确定")
         .setBtnLeft("取消")
         .setOnLeftClickListenr(new CommonTipsDialog.OnLeftClickListner() {
        @Override public void leftClick() {
        ToastUtils.showShort("点击了左按钮");
        }
        })
         .setOnRightClickListener(new CommonTipsDialog.OnRightClickListner() {
        @Override public void rightClick() {
        ToastUtils.showShort("点击了右按钮");
        }
        })
         .setIsLeftDismiss(true)
         .create()
         .show(getActivity());



         CommonTipsDialogFragment.Buidler buidler = new CommonTipsDialogFragment.Buidler();
         buidler
         .setTitle("我是标题")
         .setContent("我是内容")
         .setBtnLeft("左按键")
         .setBtnRight("右按键")
         .setOnLeftClickListenr(new CommonTipsDialogFragment.OnLeftClickListner() {
        @Override public void leftClick() {
        Utils.Toast("点击了左按键");
        }
        })
         .setOnRightClickListener(new CommonTipsDialogFragment.OnRightClickListner() {
        @Override public void rightClick() {
        Utils.Toast("点击了右按键");
        }
        })
         .setIsLeftDismiss(false)
         .setWidth(0.5f)
         .setGravity(Gravity.BOTTOM)
         .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
         .setCancelable(false)
         .create()
         .show(getActivity());

*/
}
