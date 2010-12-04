package com.ktomoya.app.TxtBoxInputBrowser;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class TxtBoxInputBrowserActivity extends Activity implements OnFocusChangeListener {
    /** Called when the activity is first created. */
	private WebView mWebView;
	private RelativeLayout mTextEditLayout;
	private FrameLayout mFrameLayout;
    private InputMethodManager mIme;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.txtboxinputbrowser_activity);
        mFrameLayout = (FrameLayout)findViewById(R.id.MainFrame);
        mWebView = (WebView)findViewById(R.id.wv);
        mTextEditLayout = (RelativeLayout)findViewById(R.id.TextEditLayout);
        mIme = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
//        mWebView = new ControlableWebView(this);
        mWebView.setWebViewClient(new WebClientView());
    	mWebView.getSettings().setJavaScriptEnabled(true);
    	mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setOnFocusChangeListener(this);
    	mWebView.loadUrl("http://www.google.com/");
//    	mFrameLayout.addView(mWebView);
    }
    @Override
    protected void onResume() {
    	super.onResume();
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
    	super.onSaveInstanceState(savedInstanceState);
    	savedInstanceState.putString("CurrentURL", mWebView.getUrl());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
    	mWebView.loadUrl(savedInstanceState.getString("CurrentURL"));
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		WebView wv = (WebView)v;
		HitTestResult result;
		result = wv.getHitTestResult();
		if (result.getType()==HitTestResult.EDIT_TEXT_TYPE) {
			if (mIme.isActive()) {
				mTextEditLayout.setVisibility(View.VISIBLE);
				mWebView.setVisibility(View.INVISIBLE);
			}
		}
	}
}