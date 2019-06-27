package gaomingyang.myapplication.Controller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import gaomingyang.myapplication.Controller.OnClickListener.RecyclerViewListOnItemClickListener;
import gaomingyang.myapplication.R;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {
    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;

    public RecyclerViewListOnItemClickListener itemClickListener;
    public MusicListAdapter(Context context,RecyclerView recyclerView) {
        this.mContext = context;
        this.mRv = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mItemView =  LayoutInflater.from(this.mContext).inflate(R.layout.item_list_music,parent,false);
        return new ViewHolder(this.mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        this.setRecyclerViewHeight();
        Glide.with(this.mContext)
                .load("http://musicugc.qianqian.com/ugcdiy/pic/e9d826ebd4f77e33af2fa379d8d7de6f.jpg")
                .into(holder.ivIcon);
        holder.itemV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null){
                    itemClickListener.onListClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    /**
     * 1.获取ItemView的高度
     * 2.获取到ItemView的数量
     * 3.高度*数量，得到整体高度
     */
    private  void setRecyclerViewHeight(){
        if (mRv == null){
            return;
        }
        //获取高度
       RecyclerView.LayoutParams itemViewLp =  (RecyclerView.LayoutParams)this.mItemView.getLayoutParams();
       //获取数量
        int itemCount = getItemCount();

       int recyclerViewHeight =  itemViewLp.height * itemCount;
       //设置RecyclerView高度
        LinearLayout.LayoutParams rvLp = (LinearLayout.LayoutParams)this.mRv.getLayoutParams();
        rvLp.height = recyclerViewHeight;
        this.mRv.setLayoutParams(rvLp);
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;
        View itemV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            itemV = itemView;
        }
    }
}
