package com.linchuan.textandgraphics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;

/**
 * 主页
 * Created by 林川 on 2016/12/6.
 */
public class MainActivity extends AppCompatActivity {

    private ScrollView sv_main;
    private String[] mData;//图文数据的列表
    private TextAndGraphicsView mTextAndGraphicsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parseData();//解析数据
        initView();//初始化ui
    }

    private void parseData() {
        //根据分割线来把文字和图片地址存入数组中
        mData = DataUtil.getData().split(DataUtil.SPLIT_TAG);
    }

    private void initView() {
        sv_main = (ScrollView) findViewById(R.id.sv_main);
        mTextAndGraphicsView = new TextAndGraphicsView(this,mData);
        sv_main.addView(mTextAndGraphicsView);
    }
}
