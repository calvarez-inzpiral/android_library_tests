package com.inzpiral.consumer.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;

import com.inzpiral.consumer.utils.EvaluationHelper;
import com.inzpiral.consumer.views.MainView;

/**
 * LoginController intercepts the on click login button event, verify the inputs 
 *
 */
public class MainController {
	
	private MainView mMainView;
	private MainControllerListener mListener;
	private EvaluationHelper mHelper;

	public MainController(MainView loginView, MainControllerListener listener) {
		this.mMainView = loginView;
		this.mListener = listener;
		this.mHelper = EvaluationHelper.getInstance();
		
    	String msg = "Debe seleccionar Ubicacion y categoria";
    	if (mHelper.getQuestionTypes().size() > 0){
    		ArrayList<String> questionTypes = mHelper.getNodesAsString(mHelper.getQuestionTypes());
    		msg = Arrays.toString(questionTypes.toArray(new String[0]));
    	}
		
		mMainView.getTestTextView().setText(msg);
	}

	// Interfaces
	public interface MainControllerListener {
		
	}

}
