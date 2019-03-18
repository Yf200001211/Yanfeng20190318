package bwie.com.yanfeng20190318.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.yanfeng20190318.R;
import bwie.com.yanfeng20190318.bean.ShowBean;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    List<ShowBean.ResultBean>list;

    public MyAdapter(Context context, List<ShowBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.item1, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(list.get(i).getCommodityName());
        viewHolder.price.setText(list.get(i).getPrice()+"");
        Glide.with(context).load(list.get(i).getMasterPic()).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicks.Oncallback(list.get(i).getCommodityId(), v.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView price;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            price = itemView.findViewById(R.id.price);
            title = itemView.findViewById(R.id.title);
        }
    }
    //设置点击监听
    public interface OnClicks{
        void Oncallback(String id, int position);
    }
    //声明监听
    public OnClicks onClicks;
    //设置监听

    public void setOnClick(MyAdapter.OnClicks onClicks) {
        this.onClicks = onClicks;
    }
}
