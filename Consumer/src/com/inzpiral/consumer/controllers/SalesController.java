package com.inzpiral.consumer.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;

import com.inzpiral.consumer.models.BaseNode;
import com.inzpiral.consumer.models.IDisplayable;
import com.inzpiral.consumer.models.Node;
import com.inzpiral.consumer.utils.EvaluationHelper;
import com.inzpiral.consumer.views.SalesView;

/**
 * LoginController intercepts the on click login button event, verify the inputs 
 *
 */
public class SalesController {
	
	private SalesView mMainView;
	private SalesControllerListener mListener;
	private EvaluationHelper mHelper;

	public SalesController(SalesView loginView, SalesControllerListener listener) {
		this.mMainView = loginView;
		this.mListener = listener;
		this.mHelper = EvaluationHelper.getInstance();
		
    	if (mHelper.getQuestionTypes().size() == 0){
    		return;
    	}
    	
		ArrayList<String> questionTypes = mHelper.getNodesAsString(mHelper.getQuestionTypes());
		System.out.println(Arrays.toString(questionTypes.toArray(new String[0])));
		
		Node sale = mHelper.getQuestionTypeByName("Oferta");
		mListener.displayChildren(sale);
	}

	// Interfaces
	public interface SalesControllerListener {
		void displayChildren(Node sale);
	}

}
