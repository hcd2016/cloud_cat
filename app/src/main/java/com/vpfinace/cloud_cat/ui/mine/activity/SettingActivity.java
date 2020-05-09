package com.vpfinace.cloud_cat.ui.mine.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.vpfinace.cloud_cat.R;
import com.vpfinace.cloud_cat.base.BaseObserver;
import com.vpfinace.cloud_cat.base.PermissionBaseActivity;
import com.vpfinace.cloud_cat.bean.User;
import com.vpfinace.cloud_cat.global.EventStrings;
import com.vpfinace.cloud_cat.http.HttpManager;
import com.vpfinace.cloud_cat.ui.user.activity.LoginActivity;
import com.vpfinace.cloud_cat.utils.GlideUtils;
import com.vpfinace.cloud_cat.utils.UserUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 设置
 */
public class SettingActivity extends PermissionBaseActivity {
    @BindView(R.id.ll_btn_nickname_container)
    LinearLayout llBtnNicknameContainer;
    @BindView(R.id.ll_btn_phone_container)
    LinearLayout llBtnPhoneContainer;
    @BindView(R.id.ll_btn_privacy_container)
    LinearLayout llBtnPrivacyContainer;
    @BindView(R.id.ll_btn_about_container)
    LinearLayout llBtnAboutContainer;
    @BindView(R.id.ll_btn_sound_container)
    LinearLayout llBtnSoundContainer;
    @BindView(R.id.btn_logo_out)
    TextView btnLogoOut;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.ll_header_container)
    LinearLayout llHeaderContainer;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.tv_phone_bind_status)
    TextView tvPhoneBindStatus;
    @BindView(R.id.iv_vodie_switch)
    ImageView ivVodieSwitch;
    @BindView(R.id.tv_version_num)
    TextView tvVersionNum;
    private PopupWindow popupWindow;
    private File tempFile;
    //静态常量
    private static final int CAMEAR_REQUEST_CODE = 1;//回调到相机
    private static final int ALBUM_REQUEST_CODE = 2;//回调到相册
    private static final int CROP_REQUEST_CODE = 3;//回调剪切
    private String[] permissions;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        requestGetUserInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (event.equals(EventStrings.SETTING_REFRESH)) {//刷新
            requestGetUserInfo();
        }
    }


    //存储权限申请上传图片
    public void requestPermissionForUpload() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
            requestPermissions(permissions, new PermissionsListener() {
                @Override
                public void onGranted() {
                    showPopupWindow();
                }

                @Override
                public void onDenied(List<String> deniedPermissions, boolean isNeverAsk) {
                    if (isNeverAsk) {
                        toAppSettings("缺少相机权限", false);
                    }
                }
            });
        }
    }

    //底部弹窗
    public void showPopupWindow() {
        //显示设置的pop布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.pop_item, null);//布局自定义,,,,也可以使用自定义的popupwindow
        TextView cream = inflate.findViewById(R.id.cream);//相机
        TextView ablum = inflate.findViewById(R.id.ablum);//相册
        TextView hide = inflate.findViewById(R.id.hide);//取消弹框
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
        cream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入到相机调用
                getPicFromCreame();
            }
        });
        ablum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入到相册调用
                getPicFromAblum();
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 从相机中取出图片
     */
    private void getPicFromCreame() {
        //用于保存调用相机拍照后所生成的文件
        //跳转到调用系统相机
        //判断版本
        // 如果在Android7.0以上,使用FileProvider获取Uri

        //先初始化出相机拍完照片后的存放路径
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机(跳转系统相机用到的是intent隐式意图来进行跳转)
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //跳转前需要对当前的Android版本进行判断,因为Android6.0以上的版本需要权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);//此操作是app之间数据传递所以这个是ContentProvider临时权限来访问
            Uri contentUri = FileProvider.getUriForFile(SettingActivity.this, "com.cloud_cat.header_provider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);//传递路径
        } else { //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        //由于需要回调
        startActivityForResult(intent, CAMEAR_REQUEST_CODE);
    }

    /**
     * 从相册中取到图片
     */
    public void getPicFromAblum() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMEAR_REQUEST_CODE:
                //调用系统相机后返回
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(SettingActivity.this, "com.cloud_cat.header_provider", tempFile);
                        cropPhoto(contentUri);
                    } else {
                        cropPhoto(Uri.fromFile(tempFile));
                    }
                }
                break;
            case ALBUM_REQUEST_CODE:
                //调用系统相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri);
                }
                break;
            case CROP_REQUEST_CODE:
                if(intent == null) {
                    return;
                }
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
//                    //设置到ImageView上
//                    GlideUtils.loadCircle(SettingActivity.this, image, ivHeader);

                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("crop", image);
                    requestUploadImg(path);
                }
                break;
        }
    }

    //生成文件路径
    public String saveImage(String name, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 图片裁剪
     */
    private void cropPhoto(Uri uri) {
        //隐式跳转到裁剪功能
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    //上传图片
    public void requestUploadImg(String url) {
        File file = new File(url);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("image/jpg"), file);
        HttpManager.toRequst(HttpManager.getApi().uploadHeaderImg(requestFile), new BaseObserver<Object>(this) {
            @Override
            public void _onNext(Object o) {
                //设置到ImageView上
                GlideUtils.loadCircle(SettingActivity.this, url, ivHeader);
                ToastUtils.showShort("修改成功!");
                EventBus.getDefault().post(EventStrings.MINE_REFRESH);
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    //获取用户信息
    public void requestGetUserInfo() {
        HttpManager.toRequst(HttpManager.getApi().getUserInfo(), new BaseObserver<User>(this) {
            @Override
            public void _onNext(User user) {
                setViewData(user);
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    private void setViewData(User user) {
        if (user == null) {
            return;
        }
        GlideUtils.loadCircle(this, user.getHeadImgUrl(), ivHeader);
        tvNickName.setText(user.getNickname());
        if (TextUtils.isEmpty(user.getPhone())) {
            tvPhoneBindStatus.setText("未绑定");
        } else {
            tvPhoneBindStatus.setText(user.getPhone());
        }
        tvVersionNum.setText(AppUtils.getAppVersionName());
    }

    @OnClick({R.id.ll_header_container, R.id.ll_btn_nickname_container, R.id.ll_btn_phone_container, R.id.ll_btn_privacy_container, R.id.ll_btn_about_container, R.id.ll_btn_sound_container, R.id.btn_logo_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_header_container://头像更换
                requestPermissionForUpload();
                break;
            case R.id.ll_btn_nickname_container:
                UpdateNickNameActivity.goThis(this,tvNickName.getText().toString());
                break;
            case R.id.ll_btn_phone_container:
                startActivity(BindPhoneActivity.class);
                break;
            case R.id.ll_btn_privacy_container:
                startActivity(PrivacyActivity.class);
                break;
            case R.id.ll_btn_about_container:
                break;
            case R.id.ll_btn_sound_container:
                break;
            case R.id.btn_logo_out:
                requestLoginOut();
                break;
        }
    }

    public void requestLoginOut() {
        HttpManager.toRequst(HttpManager.getApi().loginOut(), new BaseObserver(this) {
            @Override
            public void _onNext(Object o) {
                UserUtil.loginOut();
                startActivity(LoginActivity.class);
                ToastUtils.showShort("退出登录成功!");
            }

            @Override
            public void _onError(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
