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

	public static EvaluationHelper mInstance;
	private static Evaluation mEvaluation;
	
	private static Node mRoot;
	private static Node mCurrentLocation;
	private static Node mCurrentCategory;
	private static Node mCurrentQuestionType;
	
	private static List<Node> mLocations;
	private static List<Node> mCategories;
	private static List<Node> mQuestionTypes;
	
	public static void setEvaluation(Evaluation evaluation) {
		mEvaluation = evaluation;
	}
	
	public static EvaluationHelper getInstance(Evaluation ev) {
		mEvaluation = ev;
		return getInstance();
	}
	public static EvaluationHelper getInstance() {
		if(mEvaluation == null) {
//			throw new Exception("Evaluation no configurado!");
			System.out.println("Evaluation no configurado! la primera vez debe llamar a getInstance(Evaluation ev)");
			return null;
		}
		
		if(mInstance == null) {
			mInstance = new EvaluationHelper();
			mLocations = new ArrayList<Node>();
			mCategories = new ArrayList<Node>();
			mQuestionTypes = new ArrayList<Node>();
		}
		
		return mInstance;
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
		
		return mLocations;
	}
	public void setCurrentLocation(Node location) {
		mCurrentLocation = location;
	}
	public Node getCurrentLocation() {
		return mCurrentLocation;
	}
	
	// Categories
	public List<Node> getCategories() {
		if(mCurrentLocation == null) {
			return new ArrayList<Node>();
		}
		
		for (BaseNode baseNode : mCurrentLocation.getChildren()) {
			mCategories.add((Node) baseNode);
		}
		
		return mCategories;
	}
	public void setCurrentCategory(Node category) {
		mCurrentCategory = category;
	}
	public Node getCurrentCategory() {
		return mCurrentCategory;
	}
	
	// Tipo de pregunta
	public List<Node> getQuestionTypes() {
		if(mCurrentCategory == null) {
			return new ArrayList<Node>();
		}

		for (BaseNode baseNode : mCurrentCategory.getChildren()) {
			mQuestionTypes.add((Node) baseNode);
		}

		return mQuestionTypes;
	}
	public void setCurrentQuestionType(Node questionType) {
		mCurrentQuestionType = questionType;
	}
	public Node getCurrentQuestionType() {
		return mCurrentQuestionType;
	}
	
	
	// Otros
	public ArrayList<String> getNodesAsString(List<Node> list) {
		ArrayList<String> result = new ArrayList<String>();
		for (Node node : list) {
			result.add(node.getName());
		}
		
		return result;
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
