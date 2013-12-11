package com.inzpiral.consumer.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.inzpiral.consumer.models.BaseNode;
import com.inzpiral.consumer.models.Evaluation;
import com.inzpiral.consumer.models.Node;
import com.inzpiral.consumer.models.PresentationNode;


public class EvaluationHelper implements Iterable<BaseNode> {

	private Evaluation mEvaluation;
	
	private Node mRoot;
	private Node mLocation;
	private Node mCategory;
	private Node mQuestionType;
	
	private List<Node> mLocations;
	private List<Node> mCategories;
	private List<Node> mQuestionTypes;
	
	public EvaluationHelper(Evaluation evaluation) {
		mEvaluation = evaluation;
		mLocations = new ArrayList<Node>();
		mCategories = new ArrayList<Node>();
		mQuestionTypes = new ArrayList<Node>();
	}
	
	// Root
	public Node getRoot() {
		if(mRoot == null) {
			mRoot = (Node) mEvaluation.getChildren().get(0);
		}
		return mRoot;
	}
	
	// Locations
	public List<Node> getLocations() {
		for (BaseNode baseNode : getRoot().getChildren()) {
			mLocations.add((Node) baseNode);
		}
		mLocation = mLocations.get(0);
		
		return mLocations;
	}
	
	public void setLocation(Node location) {
		mLocation = location;
	}
	
	// Categories
	public List<Node> getCategories() {
		if(mLocation == null) {
			return new ArrayList<Node>();
		}
		
		for (BaseNode baseNode : mLocation.getChildren()) {
			mCategories.add((Node) baseNode);
		}
		mCategory = mCategories.get(0);
		
		return mCategories;
	}
	
	public void setCategory(Node category) {
		mCategory = category;
	}
	
	// Tipo de pregunta
	public List<Node> getQuestionTypes() {
		if(mCategory == null) {
			return new ArrayList<Node>();
		}

		for (BaseNode baseNode : mCategory.getChildren()) {
			mQuestionTypes.add((Node) baseNode);
		}
		mQuestionType = mQuestionTypes.get(0);

		return mQuestionTypes;
	}
	
	public void setQuestionType(Node questionType) {
		mQuestionType = questionType;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * Pruebas con Iterator !
	 * 
	 */
	@Override
	public Iterator<BaseNode> iterator() {
		try{
            return new EvaluationIterator(getRoot());
        } catch(UnsupportedOperationException e){
            e.printStackTrace();
            return null;
        }
	}
	
	private class EvaluationIterator implements Iterator<BaseNode> {

		private Stack<BaseNode> mStack;
		private BaseNode root;
		private BaseNode current;

		public EvaluationIterator(BaseNode root) {
			mStack = new Stack<BaseNode>();
			this.root = root;
			this.current = root;
		}

		@Override
		public boolean hasNext() {
			
			return false;
		}

		@Override
		public BaseNode next() {
			if(current instanceof Node) {
				List<BaseNode> children = ((Node)current).getChildren();
				if(children == null || (children != null && children.size() == 0)) {
					
				}
			}
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}
	
}
