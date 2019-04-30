package c1725163212.mzxy.com.zhihu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import c1725163212.mzxy.com.zhihu.Moder.BannerModer;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout mdotLayout;
    ArrayList<ImageView> imageViews=new ArrayList<>();
    ArrayList<BannerModer.Stories> banner=new ArrayList<>();
    MyViewPager myViewPager;
    RecyclerView mRecycleview;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("MainActivity","handler msg");
            handler.sendEmptyMessageDelayed(1,2000);
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdotLayout=findViewById(R.id.dotLayout);
        viewPager = findViewById(R.id.viewpager);
        myViewPager = new MyViewPager();
        mRecycleview = findViewById(R.id.recycleview);


        mRecycleview.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    viewPager.setCurrentItem(banner.size() - 2, false);

                }
                if (i == banner.size() - 1) {
                    viewPager.setCurrentItem(1, false);

                }
                setDots();

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        RequestParams Params = new RequestParams("https://news-at.zhihu.com/api/4/news/latest");
        x.http().get(Params, new Callback.CacheCallback<String>() {
            @SuppressLint("ResourceType")
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BannerModer bannerModer = gson.fromJson(result, BannerModer.class);
                if (bannerModer != null && bannerModer.stories != null) {
                    banner.addAll(bannerModer.stories);
                    banner.add(bannerModer.stories.get(0));
                    banner.add(0, bannerModer.stories.get(bannerModer.stories.size() - 1));
                    viewPager.setAdapter(myViewPager);
                    viewPager.setCurrentItem(1);
                    Log.i("MainActivity", "开始发送");
                    handler.sendEmptyMessageDelayed(1, 2000);
                    for (int i = 0; i <= bannerModer.stories.size(); i++) {
                        ImageView imageView = new ImageView(MainActivity.this);
                        imageView.setBackgroundResource(R.drawable.course_bar_icon);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
                        params.leftMargin = 10;
                        params.rightMargin = 10;
                        imageView.setLayoutParams(params);
                        mdotLayout.addView(imageView);
                        imageViews.add(imageView);
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });


    }
    public void setDots(){
        for (int i=0;i<imageViews.size();i++){
            if (i==viewPager.getCurrentItem()-1){
                imageViews.get(i).setSelected(true);

            }else {
                imageViews.get(i).setSelected(false);
            }

        }

    }


    public class MyViewPager extends PagerAdapter {

        @Override
        public int getCount() {
            return banner.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view ==o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView=new ImageView(MainActivity.this);
            container.addView(imageView);
            x.image().bind(imageView,banner.get(position).images);
            return imageView;
        }
    }

}


