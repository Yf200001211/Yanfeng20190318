package bwie.com.yanfeng20190318;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import bwie.com.yanfeng20190318.adapter.MyAdapter;
import bwie.com.yanfeng20190318.bean.ShowBean;
import bwie.com.yanfeng20190318.mvp.presenter.Presenter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycle;
    private Presenter presenter;
    private Button but_sou;
    private MyAdapter myAdapter;
    private EditText sou;
    private RefreshLayout rmartRefres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycle = findViewById(R.id.recycle);
        but_sou = findViewById(R.id.but_sou);
        sou = findViewById(R.id.sou);
        rmartRefres = findViewById(R.id.rmartRefres);
        rmartRefres.setEnableRefresh(true);
        rmartRefres.setEnableLoadmore(true);

        //刷新
        rmartRefres.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                myAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });
        //加载
        rmartRefres.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                myAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });

        rmartRefres.finishLoadmore();
        rmartRefres.finishRefresh();
        but_sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = sou.getText().toString();
                presenter.ShowPre(s);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recycle.setLayoutManager(gridLayoutManager);
        presenter = new Presenter(this);
        presenter.ShowPre(String.valueOf(this));
    }

    public void ShowView(final String data) {
        runOnUiThread(new Runnable() {
            private ShowBean showBean;
            @Override
            public void run() {
                Gson gson = new Gson();
                showBean = gson.fromJson(data, ShowBean.class);
                List<ShowBean.ResultBean> list = showBean.getResult();
                myAdapter = new MyAdapter(MainActivity.this,list);
                recycle.setAdapter(myAdapter);
                myAdapter.setOnClick(new MyAdapter.OnClicks() {
                    @Override
                    public void Oncallback(String id, int position) {
                        Toast.makeText(MainActivity.this,"点击成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,XiangActivity.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deatchView();
    }
}
