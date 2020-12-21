package com.example.mobileappbook.src.page.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebViewClient;

import com.example.mobileappbook.R;
import com.example.mobileappbook.utils.Constain;

public class WebView extends AppCompatActivity implements View.OnClickListener {
    private android.webkit.WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        init();
        listenerOnclicked();
    }

    private void listenerOnclicked() {
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    private void init() {
        mWebView.loadUrl(Constain.courseUrl);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {

            }
        });
    }

    private void initView() {
        mWebView = findViewById(R.id.webview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }
}