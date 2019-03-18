package bwie.com.yanfeng20190318;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.Collections;

import bwie.com.yanfeng20190318.adapter.XiangAdapter;
import bwie.com.yanfeng20190318.bean.XiangBean;
import bwie.com.yanfeng20190318.mvp.presenter.Presenter;
import bwie.com.yanfeng20190318.okhttp.OkHttp;

public class XiangActivity extends AppCompatActivity {
    private WebView webview;
    private Presenter presenter;
    private FlyBanner banner;
    private RecyclerView recycles;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);

        webview = findViewById(R.id.web_view);
        banner = findViewById(R.id.banner);
        recycles = findViewById(R.id.recycles);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(XiangActivity.this, LinearLayoutManager.VERTICAL, false);
        recycles.setLayoutManager(linearLayoutManager);


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        presenter = new Presenter(this);
        presenter.XiangPre(id);

    }
    public void XiangView(final String datas) {

          handler.post(new Runnable() {
              private ArrayList<String> imageUrl;
              @Override
              public void run() {

                  Gson gson = new Gson();
                  Log.i("bbb",datas);
                XiangBean xiangBean = gson.fromJson(datas, XiangBean.class);
                XiangBean.ResultBean result = xiangBean.getResult();
                Log.i("aaa",result+"");
                  webview.getSettings().setSupportZoom(true);
                //设置扩大比列的缩放
                webview.getSettings().setUseWideViewPort(true);
                webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                webview.getSettings().setLoadWithOverviewMode(true);

                webview.setWebChromeClient(new WebChromeClient());
                webview.loadDataWithBaseURL(null,xiangBean.getResult().getDetails(),"text/html","utf-8",null);

                XiangAdapter xiangAdapter = new XiangAdapter(XiangActivity.this,Collections.singletonList(result));
                recycles.setAdapter(xiangAdapter);

                String picture = result.getPicture();
                String[] split = picture.split(",");
                imageUrl = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    imageUrl.add(split[i]);
                    imageUrl.get(i);
                }
                banner.setImagesUrl(imageUrl);

            }
        });
//        runOnUiThread(new Runnable() {
//            private ArrayList<String> imageUrl;
//            @Override
//            public void run() {
//
//                Gson gson = new Gson();
//                XiangBean xiangBean = gson.fromJson(datas, XiangBean.class);
//                XiangBean.ResultBean result = xiangBean.getResult();
//                 String s=result.getPicture();
//                 Log.i("aaaa",s);
//
//                //可以设置支持缩放
//                webview.getSettings().setSupportZoom(true);
//                //设置扩大比列的缩放
//                webview.getSettings().setUseWideViewPort(true);
//                webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//                webview.getSettings().setLoadWithOverviewMode(true);
//
//                webview.setWebChromeClient(new WebChromeClient());
//                webview.loadDataWithBaseURL(null,xiangBean.getResult().getDetails(),"text/html","utf-8",null);
//
//                XiangAdapter xiangAdapter = new XiangAdapter(XiangActivity.this,Collections.singletonList(result));
//                recycles.setAdapter(xiangAdapter);
//
//                String picture = result.getPicture();
//                String[] split = picture.split(",");
//                imageUrl = new ArrayList<>();
//                for (int i = 0; i < split.length; i++) {
//                    imageUrl.add(split[i]);
//                    imageUrl.get(i);
//                }
//                banner.setImagesUrl(imageUrl);
//
//            }
//        });
    }
}
