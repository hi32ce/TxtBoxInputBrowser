package com.ktomoya.app.TxtBoxInputBrowser;

import android.content.Context;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.WebView;

public class ControlableWebView extends WebView {
	public InputConnection mInputConnection;
	public ControlableWebView(Context context) {
		super(context);
	}
	@Override
	public InputConnection onCreateInputConnection (EditorInfo outAttrs) {
		mInputConnection = super.onCreateInputConnection(outAttrs);
		return mInputConnection;
	}
}