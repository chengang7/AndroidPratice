package com.cg.d3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cg.d3.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 通过代码在相对布局下面添加新视图
 */
public class RelativeCodeActivity extends AppCompatActivity {


    @BindView(R.id.btn_relative)
    TextView btnRelative;
    @BindView(R.id.btn_add_left)
    Button btnAddLeft;
    @BindView(R.id.btn_add_above)
    Button btnAddAbove;
    @BindView(R.id.btn_add_right)
    Button btnAddRight;
    @BindView(R.id.btn_add_below)
    Button btnAddBelow;
    @BindView(R.id.btn_add_center)
    Button btnAddCenter;
    @BindView(R.id.btn_add_parent_left)
    Button btnAddParentLeft;
    @BindView(R.id.btn_add_parent_top)
    Button btnAddParentTop;
    @BindView(R.id.btn_add_parent_right)
    Button btnAddParentRight;
    @BindView(R.id.btn_add_parent_bottom)
    Button btnAddParentBottom;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_code);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_add_left, R.id.btn_add_above, R.id.btn_add_right, R.id.btn_add_below, 
            R.id.btn_add_center, R.id.btn_add_parent_left, R.id.btn_add_parent_top,
            R.id.btn_add_parent_right,R.id.btn_add_parent_bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_left:
                addNewView(RelativeLayout.LEFT_OF,RelativeLayout.ALIGN_TOP,view.getId());
                break;
            case R.id.btn_add_above:
                addNewView(RelativeLayout.ABOVE,RelativeLayout.ALIGN_LEFT,view.getId());
                break;
            case R.id.btn_add_right:
                addNewView(RelativeLayout.RIGHT_OF,RelativeLayout.ALIGN_BOTTOM,view.getId());
                break;
            case R.id.btn_add_below:
                addNewView(RelativeLayout.BELOW,RelativeLayout.ALIGN_RIGHT,view.getId());
                break;
            case R.id.btn_add_center:
                addNewView(RelativeLayout.CENTER_IN_PARENT,-1,view.getId());
                break;
            case R.id.btn_add_parent_left:
                addNewView(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.CENTER_VERTICAL,view.getId());
                break;
            case R.id.btn_add_parent_top:
                addNewView(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.CENTER_HORIZONTAL,view.getId());
                break;
            case R.id.btn_add_parent_right:
                addNewView(RelativeLayout.ALIGN_PARENT_RIGHT,-1,view.getId());
                break;
            case R.id.btn_add_parent_bottom:
                addNewView(RelativeLayout.ALIGN_PARENT_BOTTOM,-1,view.getId());
                break;
        }
    }

    /**
     * 通过代码在相对布局下面添加新视图，referId代表参考对象的编号
     * @param firstAlign
     * @param secondAlign
     * @param referId
     */
    private void addNewView(int firstAlign, int secondAlign, int referId) {

        //创建一个新的视图对象
        View view = new View(this);
        //设置视图的颜色为半透明
        view.setBackgroundColor(0xaa66ff66);
        //声明一个布局参数，其中宽度为100dp,高度也为100dp
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                Utils.dip2px(this,100),Utils.dip2px(this,100));
        //给布局参数添加第一个相对位置的规则，firstAlign 代表位置类型，referId 代表参考对象
        layoutParams.addRule(firstAlign,referId);
        //如果存在第二个相对位置，则同时给布局参数添加第二个相对位置的规则
        if (secondAlign >= 0){
            layoutParams.addRule(secondAlign,referId);
        }
        //给该视图添加布局参数
        view.setLayoutParams(layoutParams);
        //设置该视图的长按监听器
        view.setOnLongClickListener(new View.OnLongClickListener() {
            //在用户长按该视图时触发
            @Override
            public boolean onLongClick(View v) {
                //监听到长按事件，则从相对布局中删除该视图
                rlContent.removeView(v);
                return true;
            }
        });
        //往相对布局中添加该视图
        rlContent.addView(view);
    }
}
