package com.inzpiral.consumer.controllers;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

import com.inzpiral.consumer.views.MainView;

/**
 * LoginController intercepts the on click login button event, verify the inputs 
 *
 */
public class MainController implements OnItemSelectedListener {
	
	private MainView mainView;
	private MainControllerListener listener;
	private String[] vals = { "here", "are", "some", "values" };
	private String[] vals2 = { "uno", "dos", "tres" };

	public MainController(MainView loginView, MainControllerListener listener) {
		this.mainView = loginView;
		this.listener = listener;
		
		ArrayAdapter<String> ad = new ArrayAdapter<String>(mainView.getContext(), android.R.layout.simple_dropdown_item_1line, vals);
		mainView.getCategories().setAdapter(ad);
		
		ArrayAdapter<String> ad2 = new ArrayAdapter<String>(mainView.getContext(), android.R.layout.simple_dropdown_item_1line, vals2);
		mainView.getLocations().setAdapter(ad2);
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
	
	
	public interface MainControllerListener {
		/**
		 * The method is called by Login controller to inform the 
		 * Login Activity about the successful login
		 */
		public void onDoSomething(String msg);
	}

}
