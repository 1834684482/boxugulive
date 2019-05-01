package c1725163212.mxzy.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import c1725163212.mxzy.com.myapplication.Moder.BannerModer;
import c1725163212.mxzy.com.myapplication.Moder.NewsModer;
import c1725163212.mxzy.com.myapplication.Moder.NumberModer;

public class Main2Activity extends AppCompatActivity {
    String id;
    ImageView img;
    TextView comments,popularity;
    WebView webView;
    ArrayList<BannerModer.Stories> list=new ArrayList<>();
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (getIntent()!= null &&getIntent().getStringExtra("id")!=null){
            id=getIntent().getStringExtra("id");
        }
        img=findViewById(R.id.imgbanner);
        comments=findViewById(R.id.comments);
        popularity=findViewById(R.id.popularity);
        imageButton=findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView=findViewById(R.id.news);
        RequestParams params = new RequestParams("https://news-at.zhihu.com/api/4/story-extra/9710861");
        params.addQueryStringParameter("id",id);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                NumberModer numberModer = gson.fromJson(result, NumberModer.class);
                Log.i("Main2Activity",id);
                if (numberModer != null ) {
                    comments.setText(numberModer.comments);
                    popularity.setText(numberModer.popularity);
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
        });
        RequestParams requestParams=new RequestParams("https://news-at.zhihu.com/api/4/news/9710861");
        requestParams.addQueryStringParameter("id",id);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                NewsModer newsModer=gson.fromJson(result,NewsModer.class);
                if (newsModer!=null){
                    webView.loadUrl(newsModer.share_url);

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
        });
        RequestParams params1=new RequestParams("https://news-at.zhihu.com/api/4/news/latest");
        params1.addQueryStringParameter("id",id);
        x.http().get(params1, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("CourseFragment",result);
                Gson gson=new Gson();
                BannerModer bannerModer=gson.fromJson(result,BannerModer.class);
                if (bannerModer!=null &&bannerModer.stories!=null){
                    list=bannerModer.stories;
                    x.image().bind(img,list.get(0).images.get(0));
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
        });

    }
}
