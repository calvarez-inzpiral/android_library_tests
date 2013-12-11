package com.inzpiral.consumer.controllers;

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
	private String[] vals = { "here", "are", "some", "values" };
	private String[] vals2 = { "uno", "dos", "tres" };

	public SpinnerController(SpinnersView spinnersView, SpinnerControllerListener listener) {
		this.mSpinnersView = spinnersView;
		this.listener = listener;
		
		ArrayAdapter<String> ad = new ArrayAdapter<String>(spinnersView.getContext(), android.R.layout.simple_dropdown_item_1line, vals);
		mSpinnersView.getCategories().setAdapter(ad);
		
		ArrayAdapter<String> ad2 = new ArrayAdapter<String>(spinnersView.getContext(), android.R.layout.simple_dropdown_item_1line, vals2);
		mSpinnersView.getLocations().setAdapter(ad2);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		System.out.println("SELECTED: {id:" + id + ", position:" + position + "}");
		listener.onDoSomething("SELECTED: {id:" + id + ", position:" + position + "}");
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		System.out.println("NOTHING SELECTED!");
	}
	
	// Interfaces
	public interface SpinnerControllerListener {
		public void onDoSomething(String msg);
	}

}
