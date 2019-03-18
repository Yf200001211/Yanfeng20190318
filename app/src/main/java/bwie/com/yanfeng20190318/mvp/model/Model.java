package bwie.com.yanfeng20190318.mvp.model;


import java.io.IOException;

import bwie.com.yanfeng20190318.okhttp.OkHttp;
import okhttp3.Call;
import okhttp3.Response;

public class Model implements IModel {
    @Override
    public void Show(String url, final Callback callback) {
        OkHttp.doGet(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            callback.Success(response.body().string());
            }
        });
    }

    @Override
    public void Xiang(String url, final XiangCallback xiangCallback) {
        OkHttp.doGet(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                xiangCallback.Success(response.body().string());
            }
        });
    }
}
