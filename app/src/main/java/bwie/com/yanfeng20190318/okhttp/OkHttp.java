package bwie.com.yanfeng20190318.okhttp;


import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttp {

    private static OkHttpClient okHttpClient;
    private static Request request;

    public static void doGet(String url, Callback callback){
        okHttpClient = new OkHttpClient();
        request = new Request.Builder().url(url).method("GET", null).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    public static void doPost(String url,String name,String pwd ,Callback callback){
        okHttpClient = new OkHttpClient();
        FormBody post = new FormBody.Builder().add("name", name).add("pwd", pwd).build();
        request = new Request.Builder().url(url).post(post).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}
