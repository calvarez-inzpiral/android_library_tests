package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inzpiral.consumer.R;


public class Section extends PresentationNode implements IDisplayable {
	
	transient protected Activity mActivity;
	transient protected View mParentView;
	transient protected int mParentId;
	
	@Override
	public void display(Activity activity, View parentView, int parentId) {
		mActivity = activity;
		mParentView = parentView;
		mParentId = parentId;
		
		System.out.println("MOSTRANDOME! Soy un 'Section' de nombre: " + getName());
		SectionController controller = new SectionController(new SectionView());
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

	
	private class SectionController {
		public SectionController(SectionView sectionView) {
			((LinearLayout) mParentView.findViewById(mParentId)).addView(sectionView);
			sectionView.getSectionTitle().setText(getName());
			
			for (BaseNode baseNode : getChildren()) {
				if(baseNode instanceof IDisplayable) {
					((IDisplayable)baseNode).display(mActivity, sectionView, R.id.section_content);
				}
			}
		}
	}
	
	private class SectionView extends LinearLayout {
		public SectionView() {
			super(mActivity);
			LayoutInflater mInflater = LayoutInflater.from(mActivity);
			addView(mInflater.inflate(R.layout.module_section, null));
		}
		
		public TextView getSectionTitle() {
			return (TextView)findViewById(R.id.textview_section_title);
		}
	}
	
}
