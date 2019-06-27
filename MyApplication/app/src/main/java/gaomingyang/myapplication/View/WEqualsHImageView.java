package gaomingyang.myapplication.View;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

// AppCompatImageView
public class WEqualsHImageView extends AppCompatImageView {
    public WEqualsHImageView(Context context) {
        super(context);
    }

    public WEqualsHImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualsHImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        //获取宽度
//       int width = MeasureSpec.getSize(widthMeasureSpec);
       //获取宽度模式 match_parent,wrap_content,具体dp
//        int mode = MeasureSpec.getMode(widthMeasureSpec);


    }
}
