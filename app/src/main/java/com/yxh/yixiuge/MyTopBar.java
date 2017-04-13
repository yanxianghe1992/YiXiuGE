package com.yxh.yixiuge;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zykj on 2017/4/11.
 */

public class MyTopBar extends RelativeLayout {
    private Drawable leftSrc;
    private String rightText;
    private String titleText;
    private int textColor;


    private TextView title, right;
    private ImageView left;

    public MyTopBar(Context context) {
        super(context);
    }

    public MyTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        //获取自定义属性
        TypedArray td = context.obtainStyledAttributes(attrs, R.styleable.MyTopBar);

        // 获取属性
        titleText = td.getString(R.styleable.MyTopBar_titleText);
        rightText = td.getString(R.styleable.MyTopBar_rightText);
        leftSrc = td.getDrawable(R.styleable.MyTopBar_leftSrc);
        textColor = td.getColor(R.styleable.MyTopBar_textColor, Color.parseColor("#00cccc"));

        //创建控件
        title = new TextView(context);
        right = new TextView(context);
        left = new ImageView(context);

        //把所有属性设置到对应控件上
        //title
        title.setText(titleText);
        title.setTextSize(25.00f);
        title.setTextColor(textColor);
        title.setGravity(CENTER_VERTICAL);
        //right
        right.setText(rightText);
        right.setTextSize(16.00f);
        right.setTextColor(textColor);
        right.setGravity(CENTER_VERTICAL);
        //left
        left.setImageDrawable(leftSrc);
        //加入并设置控件位置
        LayoutParams titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT); //中间位置
        addView(title, titleParams);
//
        LayoutParams leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(ALIGN_PARENT_LEFT); //左侧位置
        leftParams.addRule(CENTER_VERTICAL); //右侧位置
        addView(left, leftParams);

        LayoutParams rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(ALIGN_PARENT_RIGHT); //右侧位置
        rightParams.addRule(CENTER_VERTICAL); //右侧位置
        addView(right, rightParams);
        this.setBackgroundColor(Color.parseColor("#ffffff"));

    }

    public MyTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
