package gaomingyang.myapplication.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import gaomingyang.myapplication.Controller.Adapter.MusicGridAdapter;
import gaomingyang.myapplication.Controller.Adapter.MusicListAdapter;
import gaomingyang.myapplication.Controller.Base.BaseActivity;
import gaomingyang.myapplication.Controller.OnClickListener.RecyclerViewListOnItemClickListener;
import gaomingyang.myapplication.Controller.OnClickListener.RecyclerViewOnItemClickListener;
import gaomingyang.myapplication.R;
import gaomingyang.myapplication.View.GridSpaceItemDecoration;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainActivity extends BaseActivity implements RecyclerViewOnItemClickListener ,RecyclerViewListOnItemClickListener {
//项目 project
    //模块 module
    //启动的时候是启动 app module
    private RecyclerView  mRvGrid ,mRvList;
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        initNavBar(false,"慕课音乐",true);
        this.mRvGrid = this.findViewById(R.id.rv_grid);
        this.mRvGrid.setLayoutManager(new GridLayoutManager(this,3));
        this.mRvGrid.addItemDecoration(new GridSpaceItemDecoration(this.getResources().getDimensionPixelSize(R.dimen.alblumMarginSize),this.mRvGrid));
        this.mRvGrid.setNestedScrollingEnabled(false);
        this.mGridAdapter = new MusicGridAdapter(this);
        this.mGridAdapter.itemClickListener = this;
        this.mRvGrid.setAdapter(this.mGridAdapter);
        /**
         * 1.在明确知道了高度的时候，可以直接设置RecyclerView高度
         * 2.在不知道展示多少的情况下，就需要计算高度了
         */
        this.mRvList = this.findViewById(R.id.rv_list);
        this.mRvList.setLayoutManager(new LinearLayoutManager(this));
        this.mRvList.setNestedScrollingEnabled(false);
        this.mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        this.mListAdapter = new MusicListAdapter(this,this.mRvList);
        this.mListAdapter.itemClickListener = this;
        this.mRvList.setAdapter(this.mListAdapter);
    }


    @Override
    public void onGridClick(View view, int position) {
        Intent intent = new Intent(this,AlbumListActivity.class);
        this.startActivity(intent);
        Log.d(TAG, "onClick: 点击了网格");
    }

    @Override
    public void onListClick(View view, int position) {
        Log.d(TAG, "onListClick: 点击了列表");
        Intent intent = new Intent(this,PlayMusicActivity.class);
        this.startActivity(intent);
    }
}
