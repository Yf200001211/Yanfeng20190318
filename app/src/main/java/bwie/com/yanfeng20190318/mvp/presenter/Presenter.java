package bwie.com.yanfeng20190318.mvp.presenter;

import java.lang.ref.Reference;

import bwie.com.yanfeng20190318.MainActivity;
import bwie.com.yanfeng20190318.XiangActivity;
import bwie.com.yanfeng20190318.api.Api;
import bwie.com.yanfeng20190318.mvp.model.IModel;
import bwie.com.yanfeng20190318.mvp.model.Model;

public class Presenter<T> implements  IPresenter {
    private Reference<T>tReference;
   public  static String Show="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?&page=1&count=10&keyword=";
    public static  String Xiang="http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=";
    MainActivity mainActivity;
    XiangActivity xiangActivity;
    private Model model;

    public Presenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        model = new Model();
    }
    public Presenter(XiangActivity xiangActivity) {
        this.xiangActivity = xiangActivity;
        model = new Model();
    }

    @Override
    public void ShowPre(String s) {
        model.Show(Show+s, new IModel.Callback() {
            @Override
            public void Success(String data) {
                mainActivity.ShowView(data);
            }
        });
    }

    @Override
    public void XiangPre(String id) {
        model.Xiang(Xiang+id, new IModel.XiangCallback() {
            @Override
            public void Success(String datas) {
                xiangActivity.XiangView(datas);
            }
        });
    }

    //泛型的内存泄露
    public void deatchView(){
        if (tReference!=null){
            tReference.clear();
            tReference =null;
        }
    }
}
