package com.ktomoya.app.TxtBoxInputBrowser;

import com.ktomoya.app.TxtBoxInputBrowser.TextEditLayoutCompornent.OnFinishInputListener;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class TxtBoxInputBrowserActivity extends Activity implements OnFocusChangeListener, OnFinishInputListener {
    /** Called when the activity is first created. */
	private WebView mWebView;
	private RelativeLayout mTextEditLayout;
	private FrameLayout mFrameLayout;
    private InputMethodManager mIme;
    private InputMethodService mImeService;
    private TextEditLayoutCompornent mTextEditLayoutCompornent;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.txtboxinputbrowser_activity);
        mFrameLayout = (FrameLayout)findViewById(R.id.MainFrame);
        mTextEditLayout = (RelativeLayout)findViewById(R.id.TextEditLayout);
        mTextEditLayoutCompornent = new TextEditLayoutCompornent(findViewById(R.id.TextEditLayout));
        mTextEditLayoutCompornent.setFinishInputListener(this);
        mIme = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
        mWebView = new ControlableWebView(this);
//        mWebView = (WebView)findViewById(R.id.wv);
        mWebView.setWebViewClient(new WebClientView());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setOnFocusChangeListener(this);
//        mWebView.loadUrl("http://www.google.com/");
        mWebView.loadData("<body><input type=\"text\"/><br><inpet type=\"text\" /><br><input type=\"text\" /><br><input type=\"text\" /><br><input type=\"text\" /><br><input type=\"text\" /><br></body>", "text/html", "utf-8");
        mFrameLayout.addView(mWebView);
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
		if (mWebView.equals(v) == false) return;
		if (hasFocus != false) return; 
		
		WebView wv = (WebView)v;
		HitTestResult result;
//		View temp;
		result = wv.getHitTestResult();
		if (result == null) return;
		if (result.getType() == HitTestResult.EDIT_TEXT_TYPE) {
			if (mIme.isActive()) {
//				temp = wv.getChildAt(0);
//				if (temp == null) return;
//				if (temp.onCheckIsTextEditor()) {
//					((EditText)temp).setText(new String("Hello world."));
//				}
//				((ControlableWebView)mWebView).mInputConnection.commitText("test", 1);
				mTextEditLayout.setVisibility(View.VISIBLE);
				mWebView.setVisibility(View.INVISIBLE);
			}
		}
	}
	@Override
	public void onFinishInput(String string) {
		// TODO Auto-generated method stub
		View temp;

		temp = mWebView.getChildAt(0);
		if (temp == null) return;
		if (temp.onCheckIsTextEditor()) {
			((EditText)temp).setText(string);
		}
		mWebView.setVisibility(View.VISIBLE);
		mTextEditLayout.setVisibility(View.INVISIBLE);
	}
}