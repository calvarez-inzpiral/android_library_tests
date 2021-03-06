package com.inzpiral.consumer.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.inzpiral.consumer.models.BaseNode;
import com.inzpiral.consumer.models.Evaluation;
import com.inzpiral.consumer.models.Node;


public class EvaluationHelper implements Iterable<BaseNode> {

	public static EvaluationHelper mInstance;
	private static Evaluation mEvaluation;

	private static Node mRoot;
	private static Node mCurrentCategory;
	private static Node mCurrentBrand;
	private static Node mCurrentLocation;

	private static List<Node> mCategories;
	private static List<Node> mBrands;
	private static List<Node> mLocations;
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
			mBrands = new ArrayList<Node>();
			mLocations = new ArrayList<Node>();
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

	// Categories
	public List<Node> getCategories() {
		mCategories = new ArrayList<Node>();
		for (BaseNode baseNode : getRoot().getChildren()) {
			mCategories.add((Node) baseNode);
		}

		return mCategories;
	}
	public void setCurrentCategory(Node category) {
		mBrands = new ArrayList<Node>();
		mLocations = new ArrayList<Node>();
		mQuestionTypes = new ArrayList<Node>();
		
		mCurrentCategory = category;
		mCurrentBrand = null;
		mCurrentLocation = null;
	}
	public Node getCurrentCategory() {
		return mCurrentCategory;
	}

	// Brands
	public List<Node> getBrands() {
		mBrands = new ArrayList<Node>();
		if(mCurrentCategory == null) {
			return mBrands;
		}
		
		for (BaseNode baseNode : mCurrentCategory.getChildren()) {
			mBrands.add((Node) baseNode);
		}

		return mBrands;
	}
	public void setCurrentBrand(Node brand) {
		mLocations = new ArrayList<Node>();
		mQuestionTypes = new ArrayList<Node>();
		
		mCurrentBrand = brand;
		mCurrentLocation = null;
	}
	public Node getCurrentBrand() {
		return mCurrentBrand;
	}

	// Locations
	public List<Node> getLocations() {
		mLocations = new ArrayList<Node>();
		if(mCurrentBrand == null) {
			return mLocations;
		}

		for (BaseNode baseNode : mCurrentBrand.getChildren()) {
			mLocations.add((Node) baseNode);
		}

		return mLocations;
	}
	public void setCurrentLocation(Node location) {
		mQuestionTypes = new ArrayList<Node>();
		mCurrentLocation = location;
	}
	public Node getCurrentLocation() {
		return mCurrentLocation;
	}

	// Tipo de pregunta
	public List<Node> getQuestionTypes() {
		mQuestionTypes = new ArrayList<Node>();

		for (BaseNode baseNode : mCurrentLocation.getChildren()) {
			mQuestionTypes.add((Node) baseNode);
		}

		return mQuestionTypes;
	}

	// Find question type by node's name
	public Node getQuestionTypeByName(String name) {
		for (Node node : mQuestionTypes) {
			if(node.getName().equals(name)) {
				return node;
			}
		}

		return null;
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
	 * (BORRAR?)
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
				List<? extends BaseNode> children = ((Node)current).getChildren();
				if(children == null || (children != null && children.size() == 0)) {

				}
			}
			return null;
		}

		@Override
		public void remove() {
			
		}
	}

	public Evaluation getEvaluations() {
		return mEvaluation;
	}

}
