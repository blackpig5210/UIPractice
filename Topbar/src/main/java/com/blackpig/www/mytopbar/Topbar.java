package com.blackpig.www.mytopbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by black on 2016/8/23/0023.
 */
public class Topbar extends RelativeLayout {
    private Button leftButton, rightButton;
    private TextView tvTitle;

    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;

    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;

    private int titleTextColor;
    private float titleTextSize;
    private String title;

    private LayoutParams leftParams, rightParams, titleParams;

    private topBarClickListener listener;

    public void setOnTopBarClickListener(topBarClickListener listener) {
        this.listener = listener;
    }



    public interface topBarClickListener {
        public void onLeftClick();

        public void onRightClick();

    };


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);

        leftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor, 0);
        leftBackground = ta.getDrawable(R.styleable.Topbar_leftBackground);
        leftText = ta.getString(R.styleable.Topbar_leftText);

        rightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor, 0);
        rightBackground = ta.getDrawable(R.styleable.Topbar_rightBackground);
        rightText = ta.getString(R.styleable.Topbar_rightText);

        titleTextColor = ta.getColor(R.styleable.Topbar_titleTextColor,0);
        titleTextSize = ta.getDimension(R.styleable.Topbar_titleTextSize, 0);
        title = ta.getString(R.styleable.Topbar_title);

        //回收ta
        ta.recycle();

        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);

        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);

        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);

        tvTitle.setText(title);
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setGravity(Gravity.CENTER);

        //设置viewGrounp的背景色
        setBackgroundColor(0xfff56696);


        //定义button和title在ViewGroup中的布局属性，并添加到ViewGroup中
        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);//定义leftButton在ViewGroup中的宽高
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);//注意此处的TRUE是RelativeLayout自己的常量

        addView(leftButton, leftParams);

        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);

        addView(rightButton, rightParams);

        titleParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);

        addView(tvTitle, titleParams);

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onLeftClick();
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRightClick();
            }
        });
    }

    public void setVisiable(boolean flag) {
        if (flag) {
            leftButton.setVisibility(View.VISIBLE);
        } else {
            leftButton.setVisibility(View.GONE);
        }
    }
}
