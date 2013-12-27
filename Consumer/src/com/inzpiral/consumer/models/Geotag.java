package com.inzpiral.consumer.models;

import android.app.Activity;
import android.view.View;


public class Geotag extends FrogmiActivity implements IAnwerable {

	@Override
	public void display(Activity activity, View ParentView, int parentId) {
		System.out.println("MOSTRANDOME! Soy un 'Geotag'");
	}

	@Override
	public int countAnswers() {
		return 1;
	}

	@Override
	public int totalAnswers() {
		return 1;
	}

	@Override
	public void formPercent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void formClear() {
		// TODO Auto-generated method stub
		
	}

}
