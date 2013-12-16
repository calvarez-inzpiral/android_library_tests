package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.inzpiral.consumer.R;


public class Row extends PresentationNode implements IDisplayable {

	@Override
	public void display(Activity activity, View parentView, int parentId) {
		mActivity = activity;
		mParentView = parentView;
		mParentId = parentId;
		
		System.out.println("MOSTRANDOME! Soy un 'Row' de nombre: " + getName());
		RowController controller = new RowController(new RowView());
	}

	
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
