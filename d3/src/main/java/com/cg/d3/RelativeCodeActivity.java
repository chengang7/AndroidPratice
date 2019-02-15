package com.cg.d3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
            R.id.btn_add_center, R.id.btn_add_parent_left, R.id.btn_add_parent_top, R.id.btn_add_parent_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_left:
                addNewView(RelativeLayout.LEFT_OF,RelativeLayout.ALIGN_TOP,view.getId());
                break;
            case R.id.btn_add_above:
                break;
            case R.id.btn_add_right:
                break;
            case R.id.btn_add_below:
                break;
            case R.id.btn_add_center:
                break;
            case R.id.btn_add_parent_left:
                break;
            case R.id.btn_add_parent_top:
                break;
            case R.id.btn_add_parent_right:
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
        //RelativeLayout.LayoutParams layoutParams = new

    }
}
