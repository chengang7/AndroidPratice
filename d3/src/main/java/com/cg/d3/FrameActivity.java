package com.cg.d3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.cg.d3.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrameActivity extends AppCompatActivity {

    @BindView(R.id.btn_add_frame)
    Button btnAddFrame;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    //定义一个颜色数组
    private int[] mColorArray = {
            Color.BLACK, Color.WHITE, Color.RED, Color.YELLOW, Color.GREEN,
            Color.BLUE, Color.CYAN, Color.MAGENTA, Color.GRAY, Color.DKGRAY
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_add_frame)
    public void onViewClicked(View v) {
        if (v.getId() == R.id.btn_add_frame) {
            int random = (int) (Math.random() * 10 % 10);
            //创建一个新的视图对象
            View view = new View(this);
            //把该视图的背景颜色设置为随机颜色
            view.setBackgroundColor(mColorArray[random]);
            //声明一个布局参数，其中宽度与上级一致，高度为随机高度。
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    Utils.dip2px(this, (random + 1) * 30));
            //给该视图设置布局参数
            view.setLayoutParams(layoutParams);
            // 设置该视图的长按监听器
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View vvv) {
                    // 一旦监听到长按事件，就从相对布局中删除该视图
                    flContent.removeView(vvv);
                    return true;
                }
            });
            // 往框架布局中添加该视图
            flContent.addView(view);
        }
    }
}
