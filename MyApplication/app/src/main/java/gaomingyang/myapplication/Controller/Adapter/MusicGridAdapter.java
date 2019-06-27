package gaomingyang.myapplication.Controller.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import gaomingyang.myapplication.Controller.OnClickListener.RecyclerViewOnItemClickListener;
import gaomingyang.myapplication.R;
import gaomingyang.myapplication.View.WEqualsHImageView;

public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder>  {
    public RecyclerViewOnItemClickListener itemClickListener;
    private Context mContext;
    public MusicGridAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_grid_music,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(this.mContext)
                .load("http://qukufile2.qianqian.com/data2/pic/ae20ccd3d476cd478811f46c3aa462b1/614044535/614044535.jpg")
                .into(holder.ivIcon);
        holder.itemV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null){
                    itemClickListener.onGridClick(v,position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        WEqualsHImageView ivIcon;
        View itemV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.rv_icon);
            itemV = itemView;
        }
    }
}
