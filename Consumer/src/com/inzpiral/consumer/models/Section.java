package com.inzpiral.consumer.models;

import java.util.List;

import com.inzpiral.consumer.R;
import com.inzpiral.consumer.activities.AppContext;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Section extends PresentationNode implements IDisplayable {

	@Override
	public void display(Activity activity, int parentId) {
		System.out.println("MOSTRANDOME! Soy un 'Section' de nombre: " + getName());
		SectionController controller = new SectionController(new SectionView());
		
		LayoutInflater mInflater = LayoutInflater.from(activity);
		View newView = mInflater.inflate(R.layout.module_section, null);
		((LinearLayout) activity.findViewById(parentId)).addView(newView);
		
		((TextView)newView.findViewById(R.id.textview_section_title)).setText(getName());
		
		for (BaseNode baseNode : getChildren()) {
			if(baseNode instanceof IDisplayable) {
				((IDisplayable)baseNode).display(activity, parentId);
			}
		}
	}

	
	private class SectionController {
		public SectionController(SectionView sectionView) {
			
		}
	}
	
	private class SectionView {
		public SectionView() {
			
		}
	}
	
	
	
}
