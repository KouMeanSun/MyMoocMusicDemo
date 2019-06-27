package gaomingyang.myapplication.View;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;
    public GridSpaceItemDecoration(int space,RecyclerView parent) {
        this.mSpace = space;
        this.getRecyclerViewOffsets(parent);
    }

    /**
     *
     * @param outRect 矩形边界
     * @param view    ItemView 本身
     * @param parent  RecyclerView
     * @param state   RecyclerView的状态
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.left = this.mSpace;
        //判断item是不是每一样第一个item
//        if (parent.getChildLayoutPosition(view)%3 == 0){
//            outRect.left = 0;
//        }

    }

    private void getRecyclerViewOffsets(RecyclerView parent){
        //View margin  正，产生一个距离，
        //             负，超出一个距离
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)parent.getLayoutParams();
        layoutParams.leftMargin = -this.mSpace;
        parent.setLayoutParams(layoutParams);
    }
}
