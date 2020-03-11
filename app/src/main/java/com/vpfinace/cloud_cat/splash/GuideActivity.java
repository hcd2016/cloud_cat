//package com.vpfinace.cloud_cat.splash;
//
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.blankj.utilcode.util.BarUtils;
//import com.vpfinace.cloud_cat.MainActivity;
//import com.vpfinace.cloud_cat.R;
//import com.vpfinace.cloud_cat.base.BaseActivity;
//
//import androidx.viewpager.widget.PagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//import butterknife.BindView;
//
//public class GuideActivity extends BaseActivity {
//    @BindView(R.id.view_pager)
//    ViewPager viewPager;
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_guide;
//    }
//
//    @Override
//    public void initPresenter() {
//
//    }
//
//    @Override
//    protected void initView() {
//        BarUtils.setStatusBarVisibility(this, false);
//        final int[] guideImgs = new int[]{R.drawable.guide01, R.drawable.guide02, R.drawable.guide03, R.drawable.guide04};
//        viewPager.setAdapter(new PagerAdapter() {
//            @Override
//            public int getCount() {
//                return guideImgs.length;
//            }
//
//            @Override
//            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
//                return view == o;
//            }
//
//            @NonNull
//            @Override
//            public Object instantiateItem(@NonNull ViewGroup container, int position) {
//                ImageView imageView = new ImageView(GuideActivity.this);
//                imageView.setBackgroundResource(guideImgs[position]);
////                imageView.setImageResource(guideImgs[position]);
//                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                container.addView(imageView);
//                if (position == guideImgs.length - 1) {
//                    imageView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(MainActivity.class);
//                        }
//                    });
//                }
//                return imageView;
//            }
//
//            @Override
//            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//                container.removeView((View) object);
//            }
//        });
//    }
//}
