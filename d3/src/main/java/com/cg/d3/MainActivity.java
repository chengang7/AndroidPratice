package com.cg.d3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @butterknife.BindView(R.id.btn_relative_xml)
    Button btnRelativeXml;
    @butterknife.BindView(R.id.btn_relative_code)
    Button btnRelativeCode;
    @butterknife.BindView(R.id.btn_frame)
    Button btnFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);
    }

    @butterknife.OnClick({R.id.btn_relative_xml, R.id.btn_relative_code, R.id.btn_frame})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_relative_xml:
                Intent intent = new Intent(this,RelativeXmlActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_relative_code:
                break;
            case R.id.btn_frame:
                break;
        }
    }
}
