package com.inzpiral.consumer.models;



public class PresentationNode extends Node implements IAnwerable {

	@Override
	public int countAnswers() {
		int count = 0;
		for (BaseNode baseNode : getChildren()) {
			if(baseNode instanceof IAnwerable) {
				count += ((IAnwerable) baseNode).countAnswers();
			}
		}
		return count;
	}

	@Override
	public int totalAnswers() {
		int count = 0;
		for (BaseNode baseNode : getChildren()) {
			if(baseNode instanceof IAnwerable) {
				count += ((IAnwerable) baseNode).totalAnswers();
			}
		}
		return count;
	}
	
}
