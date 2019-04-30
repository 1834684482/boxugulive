package c1725163212.mxzy.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    String id;
    ImageView img;
    TextView comments,popularity;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (getIntent()!= null &&getIntent().getStringExtra("id")!=null){
            id=getIntent().getStringExtra("id");
        }
    }
}
