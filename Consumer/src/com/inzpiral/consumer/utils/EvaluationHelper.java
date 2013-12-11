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
	
	public EvaluationHelper(Evaluation evaluation) {
		mEvaluation = evaluation;
	}
	
	public BaseNode getRoot() {
		return mEvaluation.getChildren().get(0);
	}

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
