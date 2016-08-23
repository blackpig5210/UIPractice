package com.blackpig.www.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TextView textView;
    private Spinner spinner;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        spinner = (Spinner) findViewById(R.id.spinner);

        textView.setText("您选择的是北京");
        //设置数据
        list.add("北京");
        list.add("上海");
        list.add("广州");
        list.add("深圳");
        //新建数组适配器ArrayAdapter
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        //为adapter设置下拉列表样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //让spinner加载适配器
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String cityName = adapter.getItem(position);
        textView.setText("您选择的是" + cityName);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
