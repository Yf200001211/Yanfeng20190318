package bwie.com.yanfeng20190318.mvp.model;

import javax.security.auth.callback.Callback;

public interface IModel {
    void Show(String url, Callback callback);
    interface Callback{
        void Success(String data);
    }
    void Xiang(String url, XiangCallback c);
    interface XiangCallback{
        void Success(String datas);
    }
}
