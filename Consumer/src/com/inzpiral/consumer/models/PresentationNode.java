package com.inzpiral.consumer.models;



public class PresentationNode extends Node implements IAnwerable, IHeader {

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

	@Override
	public void displayHeader(int depth) {
		for (BaseNode headerNode : getChildren()) {
			if(headerNode instanceof IHeader) {
				System.out.println("Depth " + depth + ": " + ((IHeader)headerNode).headerName());
				((IHeader)headerNode).displayHeader(depth+1);
			}
		}
	}

	@Override
	public String headerName() {
		return getName();
	}
	
}
