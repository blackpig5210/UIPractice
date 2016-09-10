package com.blackpig.www.webview;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private String url = "http://www.baidu.com";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        webView = (WebView) findViewById(R.id.webview);
        //webView加载本地资源
        //webView.loadUrl("file:///android_asset/example.html");
        //WebView加载web资源
        webView.loadUrl(url);
        //覆盖WebView默认通过第三方或者系统自带浏览器打开网页的行为，使得网页可以在WebView内打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值为true的时候控制网页在WebView中打开，如果为False则在第三方浏览器或者系统自带浏览器中打开
                webView.loadUrl(url);
                return true;
            }
        });//WebViewClient帮助WebView去处理一些页面控制和请求通知

        //启用JavaScript支持
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //设置WebView页面优先使用缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        //Webview加载进度条相关
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //newProgress为1-100之间的整数
                if (newProgress == 100) {
                    //网页加载完成，关闭ProgressDialog
                    closeDialog();
                }else {
                    //网页正在加载，打开ProgressDialog
                    openDialog(newProgress);
                }
            }

            private void openDialog(int newProgress) {
                if (dialog == null) {
                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle("正在加载");
                    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    dialog.setProgress(newProgress);
                    dialog.show();
                }
            }

            private void closeDialog() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
                }
            }
        });

    }

    //改写物理按键--返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            }else {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
