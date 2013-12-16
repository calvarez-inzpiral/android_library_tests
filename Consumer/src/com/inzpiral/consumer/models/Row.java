package com.inzpiral.consumer.models;

import android.app.Activity;


public class Row extends PresentationNode implements IDisplayable {

	@Override
	public void display(Activity activity, int parentId) {
		System.out.println("MOSTRANDOME! Soy un 'Row' de nombre: " + getName());
		
		for (BaseNode baseNode : getChildren()) {
			if(baseNode instanceof IDisplayable) {
				((IDisplayable)baseNode).display(activity, parentId);
			}
		}
	}
	
}
