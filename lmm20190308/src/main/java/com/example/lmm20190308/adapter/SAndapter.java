package com.example.lmm20190308.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmm20190308.JiaView;
import com.example.lmm20190308.R;
import com.example.lmm20190308.bean.GBean;

import java.util.List;

class SAndapter extends RecyclerView.Adapter<SAndapter.VHBEN> {
    Context context;
    List<GBean.DataBean> data;
    int a , c = 0;

    public SAndapter(Context context, List<GBean.DataBean> data, int a) {
        this.context = context;
        this.data =data;
        this.a = a;
    }

    @NonNull
    @Override
    public VHBEN onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(context).inflate(R.layout.subuiess,null);

        return new VHBEN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VHBEN vhben, final int i) {
        String title = data.get(a).getList().get(i).getTitle();
        vhben.subname.setText(title);

        if (data.get(a).getSellerName().equals("1")){
            vhben.subcheck.setChecked(true);
        }else {
            vhben.subcheck.setChecked(false);
        }

        vhben.low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i1 = Integer.parseInt(vhben.shuliang.getText().toString());
                if (i1>0){
                    i1 = i1 -1;
                    vhben.shuliang.setText(i1+"");
                    int price = data.get(a).getList().get(i).getPrice();
                    int i2 = price * i1;
                    vhben.subprice.setText(i2+"");

                }else {
                    Toast.makeText(context,"不能再减了",Toast.LENGTH_SHORT).show();
                }
            }
        });


        vhben.upnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i1 = Integer.parseInt(vhben.shuliang.getText().toString());
                i1++;
                vhben.shuliang.setText(i1+"");
                int price = data.get(a).getList().get(i).getPrice();
                int i2 = price * i1;

                vhben.subprice.setText(i2+"");
            }
        });

        vhben.subcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vhben.subcheck.isChecked()){
                    c++;
                }else {
                    c--;
                }

                if (data.get(a).getList().size() == c){
                    Cheecklistener.che(true);
                }else {
                    Cheecklistener.che(false);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.get(a).getList().size();
    }

    public class VHBEN extends RecyclerView.ViewHolder {

        private final CheckBox subcheck;
        private final TextView subname;
        private final TextView subprice;
        private final ImageView img;
        private final JiaView sunjview;
        private final Button low;
        private final TextView shuliang;
        private final Button upnum;

        public VHBEN(@NonNull View itemView) {
            super(itemView);
            subcheck = itemView.findViewById(R.id.subcheck);
            subname = itemView.findViewById(R.id.subname);
            subprice = itemView.findViewById(R.id.subprice);
            img = itemView.findViewById(R.id.img);
            sunjview = itemView.findViewById(R.id.sunjview);
            low = itemView.findViewById(R.id.low);
            shuliang = itemView.findViewById(R.id.shuliang);
            upnum = itemView.findViewById(R.id.upnum);

        }
    }
    Cheecklistener Cheecklistener;

    public void setCheecklistener(Cheecklistener Cheecklistener){

        this.Cheecklistener = Cheecklistener;
    }


    public interface Cheecklistener{
        public void che(boolean b);
    }
}
