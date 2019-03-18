package bwie.com.yanfeng20190318.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bwie.com.yanfeng20190318.R;
import bwie.com.yanfeng20190318.bean.XiangBean;

public class XiangAdapter extends RecyclerView.Adapter<XiangAdapter.ViewHolder> {
    Context context;
    List<XiangBean.ResultBean>list;

    public XiangAdapter(Context context, List<XiangBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.item2, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.xq_title.setText(list.get(i).getCommodityName());
        viewHolder.xq_price.setText(list.get(i).getPrice()+"");
        viewHolder.xq_xnum.setText("销量"+list.get(i).getCommentNum()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView xq_price;
        private final TextView xq_xnum;
        private final TextView xq_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xq_price = itemView.findViewById(R.id.xq_price);
            xq_xnum = itemView.findViewById(R.id.xq_xnum);
            xq_title = itemView.findViewById(R.id.xq_title);
        }
    }
}
