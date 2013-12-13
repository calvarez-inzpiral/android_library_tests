package com.inzpiral.consumer.controllers;

import java.util.Arrays;

import android.os.Bundle;

import com.inzpiral.consumer.views.MainView;

/**
 * LoginController intercepts the on click login button event, verify the inputs 
 *
 */
public class MainController {
	
	private MainView mMainView;
	private MainControllerListener mListener;

	public MainController(MainView loginView, MainControllerListener listener, Bundle arguments) {
		this.mMainView = loginView;
		this.mListener = listener;
    	
    	String msg = "Debe seleccionar Ubicacion y categoria";
    	if (arguments != null){
    		System.out.println(arguments.getStringArray("question_types"));
    		msg = Arrays.toString(arguments.getStringArray("question_types"));
    	}
		
		mMainView.getTestTextView().setText(msg);
	}

	// Interfaces
	public interface MainControllerListener {
		
	}

}
