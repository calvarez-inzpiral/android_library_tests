package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.inzpiral.consumer.R;


public class Row extends PresentationNode implements IDisplayable {
	
	transient protected Activity mActivity;
	transient protected View mParentView;
	transient protected int mParentId;

	@Override
	public void display(Activity activity, View parentView, int parentId) {
		mActivity = activity;
		mParentView = parentView;
		mParentId = parentId;
		
		System.out.println("MOSTRANDOME! Soy un 'Row' de nombre: " + getName());
		RowController controller = new RowController(new RowView());
	}

//	@Override
//	public int countAnswers() {
//		int count = 0;
//		for (BaseNode baseNode : getChildren()) {
//			if(baseNode instanceof IAnwerable) {
//				count += ((IAnwerable) baseNode).countAnswers();
//			}
//		}
//		return count;
//	}
//
//	@Override
//	public int totalAnswers() {
//		int count = 0;
//		for (BaseNode baseNode : getChildren()) {
//			if(baseNode instanceof IAnwerable) {
//				count += ((IAnwerable) baseNode).totalAnswers();
//			}
//		}
//		return count;
//	}

	
	private class RowController {
		public RowController(RowView rowView) {
			((LinearLayout) mParentView.findViewById(mParentId)).addView(rowView);
			
			for (BaseNode baseNode : getChildren()) {
				if(baseNode instanceof IDisplayable) {
					((IDisplayable)baseNode).display(mActivity, rowView, R.id.row_content);
				}
			}
		}
	}
	
	private class RowView extends LinearLayout {
		public RowView() {
			super(mActivity);
			LayoutInflater mInflater = LayoutInflater.from(mActivity);
			addView(mInflater.inflate(R.layout.module_row, null));
		}
	}
	
}
