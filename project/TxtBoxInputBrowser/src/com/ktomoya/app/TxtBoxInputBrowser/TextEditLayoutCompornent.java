package com.ktomoya.app.TxtBoxInputBrowser;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class TextEditLayoutCompornent implements OnClickListener {
	private View mMyPearent;
	
	private OnFinishInputListener mFinishInputListener;
	public TextEditLayoutCompornent(View view) {
		mMyPearent = view;
		view.findViewById(R.id.FinishButton).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mFinishInputListener.onFinishInput("test");
	}
	
	public void setFinishInputListener(OnFinishInputListener newlistener) {
		mFinishInputListener = newlistener;
	}
	
	public interface OnFinishInputListener {
		void onFinishInput(String string);
	}
}
