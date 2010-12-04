package com.ktomoya.app.TxtBoxInputBrowser;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebClientView extends WebViewClient {
    public WebClientView() {
    	super();
    }
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}