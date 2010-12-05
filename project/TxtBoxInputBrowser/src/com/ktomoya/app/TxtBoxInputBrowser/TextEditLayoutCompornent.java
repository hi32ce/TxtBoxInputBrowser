package com.ktomoya.app.TxtBoxInputBrowser;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class TextEditLayoutCompornent implements OnClickListener {
	private View mMyPearent;
	private EditText mEditText;
	private OnFinishInputListener mFinishInputListener;
	
	public TextEditLayoutCompornent(View view) {
		mMyPearent = view;
		view.findViewById(R.id.FinishButton).setOnClickListener(this);
		mEditText = (EditText)view.findViewById(R.id.EditText);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mFinishInputListener.onFinishInput(mEditText.getText().toString());
		mEditText.setText(null);
	}
	
	public void setFinishInputListener(OnFinishInputListener newlistener) {
		mFinishInputListener = newlistener;
	}
	
	public interface OnFinishInputListener {
		void onFinishInput(String string);
	}
}
