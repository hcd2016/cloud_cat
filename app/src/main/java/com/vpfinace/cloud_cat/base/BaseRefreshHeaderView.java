package com.vpfinace.cloud_cat.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.vpfinace.cloud_cat.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义下拉刷新startRefresh 的 HeaderView
 */
public class BaseRefreshHeaderView extends FrameLayout implements RefreshHeader {
    Context context;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_refresh_time)
    TextView tvRefreshTime;
    @BindView(R.id.tv_refresh_desc)
    TextView tvRefreshDesc;
    @BindView(R.id.ll_desc_container)
    LinearLayout llDescContainer;
    @BindView(R.id.rl_container)
    RelativeLayout rlContainer;
    private long lastUpdateTime = System.currentTimeMillis();

    public BaseRefreshHeaderView(@NonNull Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    public BaseRefreshHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BaseRefreshHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.base_refresh_header_view, null);
        ButterKnife.bind(this, view);
        addView(view);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//平移
    }

    @Override
    public void setPrimaryColors(int... colors) {
        rlContainer.setBackgroundColor(colors[0]);
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (!success) {
            tvRefreshDesc.setText("刷新失败,请稍后重试!");
//            ToastUtils.showShort("刷新失败,请稍后重试!");
        } else {
            tvRefreshDesc.setText("刷新完成");
        }
        return 1000;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
        SPUtils.getInstance().put("last_update_time", System.currentTimeMillis());
//        SharedPreferencesHelper.getInstance(context).putLongValue("last_update_time", System.currentTimeMillis());
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        long last_update_time = SPUtils.getInstance().getLong("last_update_time", System.currentTimeMillis());
//        long last_update_time = SharedPreferencesHelper.getInstance(context).getLongValue("last_update_time", System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String lastTime = dateFormat.format(new Date(last_update_time));
        switch (newState) {
            case None:
            case PullDownToRefresh:
                ivImg.setVisibility(VISIBLE);
                tvRefreshDesc.setText("下拉刷新");
                tvRefreshTime.setText("最后更新 " + lastTime);
                ivImg.setImageResource(R.drawable.loading0);
                break;
            case Refreshing:
                ivImg.setVisibility(VISIBLE);
                tvRefreshDesc.setText("正在刷新");
                tvRefreshTime.setText("最后更新 " + lastTime);
                Glide.with(context).load(R.drawable.loading2).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivImg);
                break;
            case ReleaseToRefresh:
                ivImg.setVisibility(VISIBLE);
                tvRefreshDesc.setText("松开刷新");
                tvRefreshTime.setText("最后更新 " + lastTime);
                ivImg.setImageResource(R.drawable.loading1);
                break;
        }
    }

    /**
    *属性文档
    *  //下面示例中的值等于默认值
        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
        refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）

        refreshLayout.setHeaderHeight(100);//Header标准高度（显示下拉高度>=标准高度 触发刷新）
        refreshLayout.setHeaderHeightPx(100);//同上-像素为单位 （V1.1.0删除）
        refreshLayout.setFooterHeight(100);//Footer标准高度（显示上拉高度>=标准高度 触发加载）
        refreshLayout.setFooterHeightPx(100);//同上-像素为单位 （V1.1.0删除）

        refreshLayout.setFooterHeaderInsetStart(0);//设置 Header 起始位置偏移量 1.0.5
        refreshLayout.setFooterHeaderInsetStartPx(0);//同上-像素为单位 1.0.5 （V1.1.0删除）
        refreshLayout.setFooterFooterInsetStart(0);//设置 Footer 起始位置偏移量 1.0.5
        refreshLayout.setFooterFooterInsetStartPx(0);//同上-像素为单位 1.0.5 （V1.1.0删除）

        refreshLayout.setHeaderMaxDragRate(2);//最大显示下拉高度/Header标准高度
        refreshLayout.setFooterMaxDragRate(2);//最大显示下拉高度/Footer标准高度
        refreshLayout.setHeaderTriggerRate(1);//触发刷新距离 与 HeaderHeight 的比率1.0.4
        refreshLayout.setFooterTriggerRate(1);//触发加载距离 与 FooterHeight 的比率1.0.4

        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        refreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        refreshLayout.setEnablePureScrollMode(false);//是否启用纯滚动模式
        refreshLayout.setEnableNestedScroll(false);//是否启用嵌套滚动
        refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
        refreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
        refreshLayout.setEnableHeaderTranslationContent(true);//是否下拉Header的时候向下平移列表或者内容
        refreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
        refreshLayout.setEnableLoadMoreWhenContentNotFull(true);//是否在列表不满一页时候开启上拉加载功能
        refreshLayout.setEnableFooterFollowWhenLoadFinished(false);//是否在全部加载结束之后Footer跟随内容1.0.4
        refreshLayout.setEnableOverScrollDrag(false);//是否启用越界拖动（仿苹果效果）1.0.4

        refreshLayout.setEnableScrollContentWhenRefreshed(true);//是否在刷新完成时滚动列表显示新的内容 1.0.5
        refreshLayout.srlEnableClipHeaderWhenFixedBehind(true);//是否剪裁Header当时样式为FixedBehind时1.0.5
        refreshLayout.srlEnableClipFooterWhenFixedBehind(true);//是否剪裁Footer当时样式为FixedBehind时1.0.5

        refreshLayout.setDisableContentWhenRefresh(false);//是否在刷新的时候禁止列表的操作
        refreshLayout.setDisableContentWhenLoading(false);//是否在加载的时候禁止列表的操作

        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener());//设置多功能监听器
        refreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDecider());//设置滚动边界判断
        refreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDeciderAdapter());//自定义滚动边界

        refreshLayout.setRefreshHeader(new ClassicsHeader(context));//设置Header
        refreshLayout.setRefreshFooter(new ClassicsFooter(context));//设置Footer
        refreshLayout.setRefreshContent(new View(context));//设置刷新Content（用于非xml布局代替addView）1.0.4

        refreshLayout.autoRefresh();//自动刷新
        refreshLayout.autoLoadMore();//自动加载
        refreshLayout.autoRefreshAnimationOnly();//自动刷新，只显示动画不执行刷新
        refreshLayout.autoLoadMoreAnimationOnly();//自动加载，只显示动画不执行加载
        refreshLayout.autoRefresh(400);//延迟400毫秒后自动刷新
        refreshLayout.autoLoadMore(400);//延迟400毫秒后自动加载
        refreshLayout.finishRefresh();//结束刷新
        refreshLayout.finishLoadMore();//结束加载
        refreshLayout.finishRefresh(3000);//延迟3000毫秒后结束刷新
        refreshLayout.finishLoadMore(3000);//延迟3000毫秒后结束加载
        refreshLayout.finishRefresh(false);//结束刷新（刷新失败）
        refreshLayout.finishLoadMore(false);//结束加载（加载失败）
        refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
        refreshLayout.closeHeaderOrFooter();//关闭正在打开状态的 Header 或者 Footer（1.1.0）
        refreshLayout.resetNoMoreData();//恢复没有更多数据的原始状态 1.0.4（1.1.0删除）
        refreshLayout.setNoMoreData(false);//恢复没有更多数据的原始状态 1.0.5

    }
}

//全局一次性设置默认属性和默认Header
public class App extends Application {
    static {//使用static代码段可以防止内存泄漏

        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                //开始设置全局的基本参数（可以被下面的DefaultRefreshHeaderCreator覆盖）
                layout.setReboundDuration(1000);
                layout.setReboundInterpolator(new DropBounceInterpolator());
                layout.setFooterHeight(100);
                layout.setDisableContentWhenLoading(false);
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            }
        });

        //全局设置默认的 Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //开始设置全局的基本参数（这里设置的属性只跟下面的MaterialHeader绑定，其他Header不会生效，能覆盖DefaultRefreshInitializer的属性和Xml设置的属性）
                layout.setEnableHeaderTranslationContent(false);
                return new MaterialHeader(context).setColorSchemeResources(R.color.colorRed,R.color.colorGreen,R.color.colorBlue);
            }
        });
 --------------------------------------------------------------------------------------------------------------------------------------------------------

 xml设置:
 <!- 下面示例中的值等于默认值 -->
<com.scwang.smartrefresh.layout.SmartRefreshLayout
    android:id="@+id/refreshLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:srlAccentColor="@android:color/white"
    app:srlPrimaryColor="@color/colorPrimary"
    app:srlReboundDuration="300"
    app:srlDragRate="0.5"

    app:srlHeaderMaxDragRate="2"
    app:srlFooterMaxDragRate="2"
    app:srlHeaderTriggerRate="1"
    app:srlFooterTriggerRate="1"

    app:srlHeaderHeight="100dp"
    app:srlFooterHeight="100dp"
    app:srlHeaderInsetStart="0dp"
    app:srlFooterInsetStart="0dp"

    app:srlEnableRefresh="true"
    app:srlEnableLoadMore="true"
    app:srlEnableAutoLoadMore="true"
    app:srlEnablePureScrollMode="false"
    app:srlEnableNestedScrolling="false"
    app:srlEnableOverScrollDrag="true"
    app:srlEnableOverScrollBounce="true"
    app:srlEnablePreviewInEditMode="true"
    app:srlEnableScrollContentWhenLoaded="true"
    app:srlEnableScrollContentWhenRefreshed="true"
    app:srlEnableHeaderTranslationContent="true"
    app:srlEnableFooterTranslationContent="true"
    app:srlEnableLoadMoreWhenContentNotFull="false"
    app:srlEnableFooterFollowWhenLoadFinished="false"

    app:srlEnableClipHeaderWhenFixedBehind="true"
    app:srlEnableClipFooterWhenFixedBehind="true"

    app:srlDisableContentWhenRefresh="false"
    app:srlDisableContentWhenLoading="false"

    app:srlFixedFooterViewId="@+id/header_fixed"
    app:srlFixedHeaderViewId="@+id/footer_fixed"
    app:srlHeaderTranslationViewId="@+id/header_translation"
    app:srlFooterTranslationViewId="@+id/footer_translation"
    />

    <!-srlAccentColor:强调颜色-->
    <!-srlPrimaryColor:主题颜色-->
    <!-srlEnablePreviewInEditMode:是否启用Android Studio编辑xml时预览效果-->
    <!-srlFixedFooterViewId:指定一个View在内容列表滚动时固定-->
    <!-srlFixedHeaderViewId:指定一个View在内容列表滚动时固定-->
    <!-srlHeaderTranslationViewId:指定下拉Header时偏移的视图Id-->
    <!-srlFooterTranslationViewId:指定上拉Footer时偏移的视图Id-->
    <!-未说明的：看上面的set方法说明-->

 java代码设置
 --------------------------------------------------------------------------------------------------------------------------------------------------------
 public class RefreshActivity extends Activity {
    static {
        ClassicsHeader.REFRESH_HEADER_PULLDOWN = "下拉可以刷新";
        ClassicsHeader.REFRESH_HEADER_REFRESHING = "正在刷新...";
        ClassicsHeader.REFRESH_HEADER_LOADING = "正在加载...";
        ClassicsHeader.REFRESH_HEADER_RELEASE = "释放立即刷新";
        ClassicsHeader.REFRESH_HEADER_FINISH = "刷新完成";
        ClassicsHeader.REFRESH_HEADER_FAILED = "刷新失败";
        ClassicsHeader.REFRESH_HEADER_SECONDARY = "释放进入二楼";
        ClassicsHeader.REFRESH_HEADER_LASTTIME = "上次更新 M-d HH:mm";
        ClassicsHeader.REFRESH_HEADER_LASTTIME = "'Last update' M-d HH:mm";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ClassicsHeader.REFRESH_HEADER_PULLDOWN = getString(R.string.header_pulldown);//"下拉可以刷新";
        ClassicsHeader.REFRESH_HEADER_REFRESHING = getString(R.string.header_refreshing);//"正在刷新...";
        ClassicsHeader.REFRESH_HEADER_LOADING = getString(R.string.header_loading);//"正在加载...";
        ClassicsHeader.REFRESH_HEADER_RELEASE = getString(R.string.header_release);//"释放立即刷新";
        ClassicsHeader.REFRESH_HEADER_FINISH = getString(R.string.header_finish);//"刷新完成";
        ClassicsHeader.REFRESH_HEADER_FAILED = getString(R.string.header_failed);//"刷新失败";
        ClassicsHeader.REFRESH_HEADER_SECONDARY = getString(R.string.header_secondary);//"释放进入二楼";
        ClassicsHeader.REFRESH_HEADER_LASTTIME = getString(R.string.header_lasttime);//"上次更新 M-d HH:mm";
        ClassicsHeader.REFRESH_HEADER_LASTTIME = getString(R.string.header_lasttime);//"'Last update' M-d HH:mm"
        //下面示例中的值等于默认值
        ClassicsHeader header = (ClassicsHeader)findViewById(R.id.header);
        header.setAccentColor(android.R.color.white);//设置强调颜色
        header.setPrimaryColor(R.color.colorPrimary);//设置主题颜色
        header.setTextSizeTitle(16);//设置标题文字大小（sp单位）
        header.setTextSizeTitle(16, TypedValue.COMPLEX_UNIT_SP);//同上（1.1.0版本删除）
        header.setTextSizeTime(10);//设置时间文字大小（sp单位）
        header.setTextSizeTime(10, TypedValue.COMPLEX_UNIT_SP);//同上（1.1.0版本删除）
        header.setTextTimeMarginTop(10);//设置时间文字的上边距（dp单位）
        header.setTextTimeMarginTopPx(10);//同上-像素单位（1.1.0版本删除）
        header.setEnableLastTime(true);//是否显示时间
        header.setFinishDuration(500);//设置刷新完成显示的停留时间（设为0可以关闭停留功能）
        header.setDrawableSize(20);//同时设置箭头和图片的大小（dp单位）
        header.setDrawableArrowSize(20);//设置箭头的大小（dp单位）
        header.setDrawableProgressSize(20);//设置图片的大小（dp单位）
        header.setDrawableMarginRight(20);//设置图片和箭头和文字的间距（dp单位）
        header.setDrawableSizePx(20);//同上-像素单位
        header.setDrawableArrowSizePx(20);//同上-像素单位（1.1.0版本删除）
        header.setDrawableProgressSizePx(20);//同上-像素单位（1.1.0版本删除）
        header.setDrawableMarginRightPx(20);//同上-像素单位（1.1.0版本删除）
        header.setArrowBitmap(bitmap);//设置箭头位图（1.1.0版本删除）
        header.setArrowDrawable(drawable);//设置箭头图片
        header.setArrowResource(R.drawable.ic_arrow);//设置箭头资源
        header.setProgressBitmap(bitmap);//设置图片位图（1.1.0版本删除）
        header.setProgressDrawable(drawable);//设置图片
        header.setProgressResource(R.drawable.ic_progress);//设置图片资源
        header.setTimeFormat(new DynamicTimeFormat("上次更新 %s"));//设置时间格式化（时间会自动更新）
        header.setLastUpdateText("上次更新 3秒前");//手动更新时间文字设置（将不会自动更新时间）
        header.setSpinnerStyle(SpinnerStyle.Translate);//设置移动样式（不支持：MatchLayout）
    }
}

--------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------
ClassicsFooter
java代码设置

public class RefreshActivity extends Activity {
    static {
        ClassicsFooter.REFRESH_FOOTER_PULLING = "上拉加载更多";
        ClassicsFooter.REFRESH_FOOTER_RELEASE = "释放立即加载";
        ClassicsFooter.REFRESH_FOOTER_REFRESHING = "正在刷新...";
        ClassicsFooter.REFRESH_FOOTER_LOADING = "正在加载...";
        ClassicsFooter.REFRESH_FOOTER_FINISH = "加载完成";
        ClassicsFooter.REFRESH_FOOTER_FAILED = "加载失败";
        ClassicsFooter.REFRESH_FOOTER_NOTHING = "没有更多数据了";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ClassicsFooter.REFRESH_FOOTER_PULLING = getString(R.string.footer_pulling);//"上拉加载更多";
        ClassicsFooter.REFRESH_FOOTER_RELEASE = getString(R.string.footer_release);//"释放立即加载";
        ClassicsFooter.REFRESH_FOOTER_REFRESHING = getString(R.string.footer_refreshing);//"正在刷新...";
        ClassicsFooter.REFRESH_FOOTER_LOADING = getString(R.string.footer_loading);//"正在加载...";
        ClassicsFooter.REFRESH_FOOTER_FINISH = getString(R.string.footer_finish);//"加载完成";
        ClassicsFooter.REFRESH_FOOTER_FAILED = getString(R.string.footer_failed);//"加载失败";
        ClassicsFooter.REFRESH_FOOTER_NOTHING = getString(R.string.footer_nothing);//"没有更多数据了";

        //下面示例中的值等于默认值
        ClassicsFooter footer = (ClassicsFooter)findViewById(R.id.footer);
        footer.setAccentColor(android.R.color.white);//设置强调颜色
        footer.setPrimaryColor(R.color.colorPrimary);//设置主题颜色
        footer.setTextSizeTitle(16);//设置标题文字大小（sp单位）
        footer.setTextSizeTitle(16, TypedValue.COMPLEX_UNIT_SP);//同上
        footer.setFinishDuration(500);//设置刷新完成显示的停留时间
        footer.setDrawableSize(20);//同时设置箭头和图片的大小（dp单位）
        footer.setDrawableArrowSize(20);//设置箭头的大小（dp单位）
        footer.setDrawableProgressSize(20);//设置图片的大小（dp单位）
        footer.setDrawableMarginRight(20);//设置图片和箭头和文字的间距（dp单位）
        footer.setDrawableSizePx(20);//同上-像素单位（1.1.0版本删除）
        footer.setDrawableArrowSizePx(20);//同上-像素单位（1.1.0版本删除）
        footer.setDrawableProgressSizePx(20);//同上-像素单位（1.1.0版本删除）
        footer.setDrawableMarginRightPx(20);//同上-像素单位（1.1.0版本删除）
        footer.setArrowBitmap(bitmap);//设置箭头位图（1.1.0版本删除）
        footer.setArrowDrawable(drawable);//设置箭头图片
        footer.setArrowResource(R.drawable.ic_arrow);//设置箭头资源
        footer.setProgressBitmap(bitmap);//设置图片位图（1.1.0版本删除）
        footer.setProgressDrawable(drawable);//设置图片
        footer.setProgressResource(R.drawable.ic_progress);//设置图片资源
        footer.setSpinnerStyle(SpinnerStyle.Translate);//设置移动样式（不支持：MatchLayout）
    }
}
xml代码设置

<!- 下面示例中的值等于默认值 -->
<com.scwang.smartrefresh.layout.SmartRefreshLayout>
    <com.scwang.smartrefresh.layout.header.ClassicsFooter
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:srlAccentColor="@android:color/white"
        app:srlPrimaryColor="@color/colorPrimary"
        app:srlTextSizeTitle="16sp"
        app:srlFinishDuration="500"
        app:srlDrawableSize="20dp"
        app:srlDrawableArrowSize="20dp"
        app:srlDrawableProgressSize="20dp"
        app:srlDrawableMarginRight="20dp"
        app:srlDrawableArrow="@drawable/ic_arrow"
        app:srlDrawableProgress="@drawable/ic_progress"
        app:srlClassicsSpinnerStyle="Translate"
        app:srlTextPulling="@string/srl_footer_pulling"
        app:srlTextRelease="@string/srl_footer_release"
        app:srlTextLoading="@string/srl_footer_loading"
        app:srlTextRefreshing="@string/srl_footer_refreshing"
        app:srlTextFinish="@string/srl_footer_finish"
        app:srlTextFailed="@string/srl_footer_failed"
        app:srlTextNothing="@string/srl_footer_nothing"/>
</com.scwang.smartrefresh.layout.SmartRefreshLayout>
*/
}
