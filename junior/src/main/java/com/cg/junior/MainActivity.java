package com.cg.junior;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cg.junior.utils.Arith;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.btn_btn_cancel)
    Button btnBtnCancel;
    @BindView(R.id.btn_divide)
    Button btnDivide;
    @BindView(R.id.btn_multiply)
    Button btnMultiply;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.btn_seven)
    Button btnSeven;
    @BindView(R.id.btn_eight)
    Button btnEight;
    @BindView(R.id.btn_nine)
    Button btnNine;
    @BindView(R.id.btn_plus)
    Button btnPlus;
    @BindView(R.id.btn_four)
    Button btnFour;
    @BindView(R.id.btn_five)
    Button btnFive;
    @BindView(R.id.btn_six)
    Button btnSix;
    @BindView(R.id.btn_minus)
    Button btnMinus;
    @BindView(R.id.btn_one)
    Button btnOne;
    @BindView(R.id.btn_two)
    Button btnTwo;
    @BindView(R.id.btn_three)
    Button btnThree;
    @BindView(R.id.ib_sqrt)
    ImageButton ibSqrt;
    @BindView(R.id.btn_zero)
    Button btnZero;
    @BindView(R.id.btn_dot)
    Button btnDot;
    @BindView(R.id.btn_equal)
    Button btnEqual;

    /** 操作符*/
    private String operator = "";
    /** 前一个操作数*/
    private String firstNum = "";
    /** 后一个操作数*/
    private String nextNum = "";
    /** 当前的计算结果*/
    private String result = "";
    /** 显示的文本内容*/
    private String showText = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //设置 tvResult 内部文本的移动方式为滚动方式
        tvResult.setMovementMethod(new ScrollingMovementMethod());
    }

    @OnClick({R.id.btn_btn_cancel, R.id.btn_divide, R.id.btn_multiply, R.id.btn_clear,
            R.id.btn_seven, R.id.btn_eight, R.id.btn_nine, R.id.btn_plus, R.id.btn_four,
            R.id.btn_five, R.id.btn_six, R.id.btn_minus, R.id.btn_one, R.id.btn_two,
            R.id.btn_three, R.id.ib_sqrt, R.id.btn_zero, R.id.btn_dot, R.id.btn_equal})
    public void onViewClicked(View view) {
        //得到当前按钮的编号
        int resId = view.getId();
        String inputText;
        //如果是开根号按钮,因为开根号是图片
        if (resId == R.id.ib_sqrt){
            inputText = "√";
        }else {
            inputText = ((TextView)view).getText().toString();
        }
        Log.d(TAG, "resId=" + resId + ",inputText=" + inputText);
        //如果点了清除按钮,则清空输入框
        if (resId == R.id.btn_clear){
            clear("");
        // 如果点了取消按钮
        }else if (resId == R.id.btn_btn_cancel){
            //无操作符，则表示逐位取消前一个操作数
            if (operator.equals("")){
                if (firstNum.length() == 1){
                    firstNum = "0";
                }else if (firstNum.length() > 0){
                    firstNum = firstNum.substring(0,firstNum.length() - 1);
                }else {
                    Toast.makeText(this, "没有可以取消的数字了", Toast.LENGTH_SHORT).show();
                    return;
                }
                showText = firstNum;
                tvResult.setText(showText);
            }else{
                //有操作符,表示逐位取消后一个操作数

                if (nextNum.length() == 1){
                    nextNum = "";
                }else if (nextNum.length() > 0){
                    nextNum = nextNum.substring(0,nextNum.length()-1);
                }else{
                    Toast.makeText(this, "没有可以取消的数字了", Toast.LENGTH_SHORT).show();
                    return;
                }
                showText = nextNum;
                tvResult.setText(showText);
            }
        //点击了等号按钮
        }else if (resId == R.id.btn_equal){
            if (operator.length() == 0 || operator.equals("=")){
                Toast.makeText(this, "请输入运算符", Toast.LENGTH_SHORT).show();
                return;
            }else if (nextNum.length() <= 0){
                Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
            }
            //计算成功，则显示计算结果
            if (caculate()){
                operator = inputText;
                showText = showText + "="+result;
                tvResult.setText(showText);
            }else {
                //计算失败，直接返回
                return;
            }
        //点击了加、减、乘、除按钮
        }else if (resId == R.id.btn_plus || resId == R.id.btn_minus || resId == R.id.btn_multiply
                || resId == R.id.btn_divide){
            if (firstNum.length() <= 0){
                Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
                return;
            }
            if (operator.length() == 0 || operator.equals("=") || operator.equals("√")){
                operator = inputText;
                showText = showText + operator;
                tvResult.setText(showText);
            }else {
                Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
                return;
            }
        //点击了开根号的按钮
        }else if (resId == R.id.ib_sqrt){
            if (firstNum.length() <= 0){
                Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
                return;
            }
            if (Double.parseDouble(firstNum) < 0){
                Toast.makeText(this, "开根号的数值不能小于0", Toast.LENGTH_SHORT).show();
                return;
            }
            result = String.valueOf(Math.sqrt(Double.parseDouble(firstNum)));
            firstNum = result;
            nextNum = "";
            operator = inputText;
            showText = showText +"="+ result;
            tvResult.setText(showText);
            Log.d(TAG, "result=" + result + ",firstNum=" + firstNum + ",operator="+operator);
        //点击了点击了其它按钮，包括数字和小数点
        }else {
            if (operator.equals("＝")) { // 上一次点击了等号按钮，则清空操作符
                operator = "";
                firstNum = "";
                showText = "";
            }
            if (resId == R.id.btn_dot) { // 点击了小数点
                inputText = ".";
            }
            if (operator.equals("")) { // 无操作符，则继续拼接前一个操作数
                firstNum = firstNum + inputText;
            } else { // 有操作符，则继续拼接后一个操作数
                nextNum = nextNum + inputText;
            }
            showText = showText + inputText;
            tvResult.setText(showText);
        }
        return;
    }

    /**
     * 开始加减乘除四则运算，计算成功则返回true，计算失败则返回false
     * @return
     */
    private boolean caculate() {
        //当前是加法运算
        if(operator.equals("＋")){
            result = String.valueOf(Arith.add(firstNum,nextNum));
        //当前是减法运算
        }else if (operator.equals("-")){
            result = String.valueOf(Arith.sub(firstNum,nextNum));
        //当前是乘法运算
        }else if (operator.equals("×")){
            result = String.valueOf(Arith.mul(firstNum,nextNum));
        }else if (operator.equals("÷")){
            if ("0".equals(nextNum)){
                // 被除数为0，要弹窗提示用户
                Toast.makeText(this, "被除数不能为0", Toast.LENGTH_SHORT).show();
                //返回false
                return false;
            }else{
                //被除数不是0，则正常运算
                result = String.valueOf(Arith.div(firstNum,nextNum));
            }
        }
        //打印日志
        Log.d(TAG, "result ="+result);
        firstNum = result;
        nextNum = "";
        return  true;
    }

    /**
     * 清空并初始化
     * @param s
     */
    private void clear(String s) {
        showText  = s;
        tvResult.setText(showText);
        operator = "";
        firstNum = "";
        nextNum = "";
        result = "";
    }
}
