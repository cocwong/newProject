package com.example.cocwong.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocwong.test.R;

public class TitleBar extends FrameLayout implements View.OnClickListener {
    private Drawable drawableLeft, drawableRight;
    private String title;

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainResources(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_title, this);
    }

    private void obtainResources(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        drawableLeft = array.getDrawable(R.styleable.TitleBar_img_left);
        drawableRight = array.getDrawable(R.styleable.TitleBar_img_right);
        title = array.getString(R.styleable.TitleBar_text);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        if (mode != MeasureSpec.EXACTLY) {
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getContext().getResources().getDisplayMetrics());
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ImageView imgLeft = findViewById(R.id.img_left);
        ImageView imgRight = findViewById(R.id.img_right);
        TextView tvTitle = findViewById(R.id.title_tv);
        if (drawableLeft != null) {
            imgLeft.setImageDrawable(drawableLeft);
        }
        if (drawableRight != null) {
            imgRight.setImageDrawable(drawableRight);
        }
        if (title != null) {
            tvTitle.setText(title);
        }
        imgLeft.setOnClickListener(this);
        imgRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_left:
                if (listener != null) {
                    listener.onLeftClick();
                }
                break;
            case R.id.img_right:
                if (listener != null) {
                    listener.onRightClick();
                }
                break;
        }
    }

    private OnTitleClickListener listener;

    public void setOnTitleClickListener(OnTitleClickListener listener) {
        this.listener = listener;
    }

    public interface OnTitleClickListener {
        /**
         * 点击左边
         */
        void onLeftClick();

        /**
         * 点击右边
         */
        void onRightClick();
    }
}
