package com.inzpiral.consumer.controllers;

import android.view.View;

import com.inzpiral.consumer.models.Node;
import com.inzpiral.consumer.models.PresentationNode;
import com.inzpiral.consumer.utils.EvaluationHelper;
import com.inzpiral.consumer.views.TabView;

/**
 * LoginController intercepts the on click login button event, verify the inputs 
 *
 */
public class TabController {
	
	private TabView mTabView;
	private TabControllerListener mListener;
	private EvaluationHelper mHelper;

	public TabController(TabView tabView, TabControllerListener listener) {
		this.mTabView = tabView;
		this.mListener = listener;
		this.mHelper = EvaluationHelper.getInstance();
		
    	if (mHelper.getQuestionTypes().size() == 0){
    		return;
    	}
		
		Node sale = mHelper.getQuestionTypeByName(mListener.getNodeName());
		mListener.displayChildren(mTabView, sale);

//		for (Node node : mHelper.getQuestionTypes()) {
//			if(node instanceof PresentationNode) {
//				PresentationNode presentationNode = (PresentationNode)node;
//				System.out.println(node.getName() + ": " + presentationNode.countAnswers());
//				System.out.println(node.getName() + ": " + presentationNode.totalAnswers());
//			}
//		}
	}

	// Interfaces
	public interface TabControllerListener {
		void displayChildren(View view, Node node);
		String getNodeName();
	}

}
