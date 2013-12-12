package com.inzpiral.consumer.controllers;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

import com.inzpiral.consumer.views.SpinnersView;

/**
 * LoginController intercepts the on click login button event, verify the inputs 
 *
 */
public class SpinnerController implements OnItemSelectedListener {
	
	private SpinnersView mSpinnersView;
	private SpinnerControllerListener listener;


	public SpinnerController(SpinnersView spinnersView, SpinnerControllerListener listener, Bundle data) {
		this.mSpinnersView = spinnersView;
		this.listener = listener;
		
		String[] vals = new String[0];
//		String[] vals = { "Seleccione ubicacion" };
    	if (data != null){
    		System.out.println(data.getStringArray("categories"));
    		vals = data.getStringArray("categories");
    	}
		
		ArrayAdapter<String> ad = new ArrayAdapter<String>(spinnersView.getContext(), android.R.layout.simple_dropdown_item_1line, vals);
		mSpinnersView.getCategories().setAdapter(ad);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		System.out.println("SELECTED: {id:" + id + ", position:" + position + "}");
		
		listener.onLoadQuestionTypes(position);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		System.out.println("NOTHING SELECTED!");
	}
	
	// Interfaces
	public interface SpinnerControllerListener {
		public void onLoadQuestionTypes(int position);
	}

}
