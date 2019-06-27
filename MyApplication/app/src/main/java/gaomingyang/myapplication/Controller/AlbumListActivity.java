package gaomingyang.myapplication.Controller;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import gaomingyang.myapplication.Controller.Adapter.MusicListAdapter;
import gaomingyang.myapplication.Controller.Base.BaseActivity;
import gaomingyang.myapplication.Controller.OnClickListener.RecyclerViewListOnItemClickListener;
import gaomingyang.myapplication.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AlbumListActivity extends BaseActivity implements RecyclerViewListOnItemClickListener {
    private RecyclerView mRvList;
    private MusicListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        initView();
    }

    private void initView() {
        initNavBar(true,"专辑列表",false);

        this.mRvList = this.findViewById(R.id.rv_list);
        this.mRvList.setLayoutManager(new LinearLayoutManager(this));
        this.mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        this.mAdapter = new MusicListAdapter(this,null);
        this.mAdapter.itemClickListener = this;
        this.mRvList.setAdapter(this.mAdapter);
    }

    @Override
    public void onListClick(View view, int position) {
        Intent intent = new Intent(this,PlayMusicActivity.class);
        this.startActivity(intent);
    }
}
