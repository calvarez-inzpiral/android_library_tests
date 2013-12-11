package com.inzpiral.consumer.controllers;

import com.inzpiral.consumer.views.MainView;

/**
 * LoginController intercepts the on click login button event, verify the inputs 
 *
 */
public class MainController {
	
	private MainView mMainView;
	private MainControllerListener mListener;

	public MainController(MainView loginView, MainControllerListener listener) {
		this.mMainView = loginView;
		this.mListener = listener;
	}

	// Interfaces
	public interface MainControllerListener {
		
	}

}
