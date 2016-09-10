package com.blackpig.www.progressbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private ProgressBar progressBar;
    private Button add;
    private Button reduce;
    private Button reset;
    private Button show;
    private TextView textView;

    private int first;
    private int second;
    private int max;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //启用窗口特征，启用带进度条和不带进度条

        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.main);
        //显示两种进度条
        setProgressBarVisibility(true);
        setProgressBarIndeterminateVisibility(true);
        setProgress(600);

        progressBar = (ProgressBar) findViewById(R.id.hori);
        add = (Button) findViewById(R.id.add);
        reduce = (Button) findViewById(R.id.reduce);
        reset = (Button) findViewById(R.id.reset);
        textView = (TextView) findViewById(R.id.text);
        show = (Button) findViewById(R.id.show);

        first = progressBar.getProgress();
        second = progressBar.getSecondaryProgress();
        max = progressBar.getMax();

        textView.setText("当前第一进度为" + (int) (first / (float) max * 100) + "%" + "," + "当前第二进度为"
                + (int) (second / (float) max * 100) + "%");

        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        reset.setOnClickListener(this);
        show.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                progressBar.incrementProgressBy(10);
                progressBar.incrementSecondaryProgressBy(10);
                break;
            case R.id.reduce:
                progressBar.incrementProgressBy(-10);
                progressBar.incrementSecondaryProgressBy(-10);
                break;
            case R.id.reset:
                progressBar.setProgress(50);
                progressBar.setSecondaryProgress(80);
                break;
            case R.id.show:

                /**
                 * 设置页面显示风格
                 */
                //新建进度条对话框
                progressDialog = new ProgressDialog(MainActivity.this);
                //设置显示风格
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //设置标题
                progressDialog.setTitle("progressDialog practice");
                //设置对话框框文本内容
                progressDialog.setMessage("good good study, day day up!");
                //设置图标
                progressDialog.setIcon(R.drawable.ic_launcher);

                /**
                 * 设置关于ProgressBar相关属性
                 */
                //设置最大进度
                progressDialog.setMax(100);
                //设定初始化已经增长的最大长度
                progressDialog.incrementProgressBy(50);
                //进度条是明确显示进度的
                progressDialog.setIndeterminate(false);

                /**
                 * 设定一个确定的按钮
                 */
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "点击ProgressDialog中的按钮",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                //是否可以通过返回按钮退出Dialog
                progressDialog.setCancelable(true);

                //显示ProgressDialog
                progressDialog.show();  
                break;
            default:
                break;
        }
        textView.setText("当前第一进度为" + (int) (progressBar.getProgress() / (float) max * 100) + "%"
                + "," + "当前第二进度为" + (int) (progressBar.getSecondaryProgress() / (float) max * 100) + "%");
    }
}
