package gaomingyang.myapplication.View;


import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import gaomingyang.myapplication.R;

/**
 * 1.input_icon :输入框前面的小图片
 * 2.input_hint :输入框的提示内容
 * 3.is_password:是否需要密文展示
 */
public class InputView   extends FrameLayout {

    private int inputIcon;
    private String inputHint;
    private  boolean isPassword;

    private View mView;
    private ImageView mIvIcon;
    private EditText mEtInput;

    public InputView(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    /**
     * 初始化方法
     * @param context 上下文
     * @param attrs   配置属性
     */
    private  void init(Context context ,AttributeSet attrs){
            if (attrs == null){
                return;
            }
            //获取自定义属性
             TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.inputView);
             inputIcon = typedArray.getResourceId(R.styleable.inputView_input_icon,R.mipmap.ic_launcher);
             inputHint = typedArray.getString(R.styleable.inputView_input_hint);
             isPassword = typedArray.getBoolean(R.styleable.inputView_is_password,false);

             typedArray.recycle();
//  绑定layout布局
             mView =  LayoutInflater.from(context).inflate(R.layout.input_view,this,false);
             mIvIcon = mView.findViewById(R.id.iv_icon);
             mEtInput = mView.findViewById(R.id.et_input);

//             布局关联属性
           mIvIcon.setImageResource(inputIcon);
           mEtInput.setHint(inputHint);
           mEtInput.setInputType(isPassword ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_PHONE);

             addView(mView);
    }

    /**
     * 返回输入内容
     * @return
     */
    public  String getInputStr(){
        return mEtInput.getText().toString().trim();
    }
}
