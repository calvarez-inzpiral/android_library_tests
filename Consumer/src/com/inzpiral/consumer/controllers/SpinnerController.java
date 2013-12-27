package com.inzpiral.consumer.controllers;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

import com.inzpiral.consumer.utils.EvaluationHelper;
import com.inzpiral.consumer.views.SpinnersView;

/**
 * LoginController intercepts the on click login button event, verify the inputs 
 *
 */
public class SpinnerController implements OnItemSelectedListener {
	
	private SpinnersView mSpinnersView;
	private SpinnerControllerListener listener;
	private EvaluationHelper mHelper;

	public SpinnerController(SpinnersView spinnersView, SpinnerControllerListener listener) {
		this.mSpinnersView = spinnersView;
		this.listener = listener;
		this.mHelper = EvaluationHelper.getInstance();
		
		String[] vals = new String[0];
    	if (mHelper.getLocations().size() > 0){
    		ArrayList<String> categories = mHelper.getNodesAsString(mHelper.getLocations());
    		vals = categories.toArray(new String[0]);
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
