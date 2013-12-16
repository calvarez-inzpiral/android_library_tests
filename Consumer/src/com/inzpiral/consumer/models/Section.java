package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inzpiral.consumer.R;


public class Section extends PresentationNode implements IDisplayable {
	
	@Override
	public void display(Activity activity, int parentId) {
		mActivity = activity;
		mParentId = parentId;
		
		System.out.println("MOSTRANDOME! Soy un 'Section' de nombre: " + getName());
		SectionController controller = new SectionController(new SectionView());

		for (BaseNode baseNode : getChildren()) {
			if(baseNode instanceof IDisplayable) {
				((IDisplayable)baseNode).display(mActivity, R.id.section_content);
			}
		}
	}

	
	private class SectionController {
		public SectionController(SectionView sectionView) {
			((LinearLayout) mActivity.findViewById(mParentId)).addView(sectionView);
			sectionView.getSectionTitle().setText(getName());
			
		}
	}
	
	private class SectionView extends RelativeLayout {
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
