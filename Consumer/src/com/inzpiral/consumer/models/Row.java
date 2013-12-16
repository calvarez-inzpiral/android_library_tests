package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.inzpiral.consumer.R;


public class Row extends PresentationNode implements IDisplayable {

	@Override
	public void display(Activity activity, int parentId) {
		mActivity = activity;
		mParentId = parentId;
		
		System.out.println("MOSTRANDOME! Soy un 'Row' de nombre: " + getName());
		RowController controller = new RowController(new RowView());
		
		for (BaseNode baseNode : getChildren()) {
			if(baseNode instanceof IDisplayable) {
				((IDisplayable)baseNode).display(mActivity, R.id.row_content);
			}
		}
	}

	
	private class RowController {
		public RowController(RowView rowView) {
			((LinearLayout) mActivity.findViewById(mParentId)).addView(rowView);
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
