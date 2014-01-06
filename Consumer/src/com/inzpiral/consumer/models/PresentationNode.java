package com.inzpiral.consumer.models;

import java.util.ArrayList;

import com.inzpiral.consumer.utils.Config;



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
	public ArrayList<TreeStructure> displayHeader(ArrayList<TreeStructure> nodeDepth, int depth) {
		for (BaseNode headerNode : getChildren()) {
			if(depth < Config.LEVEL_NUMBER && headerNode instanceof IHeader) {
				nodeDepth.add(new TreeStructure(((IHeader)headerNode).headerName(), depth, "0%"));
				System.out.println("Depth " + depth + ": " + ((IHeader)headerNode).headerName());
				((IHeader)headerNode).displayHeader(nodeDepth, depth+1);
			}
		}
		return nodeDepth;
	}

	@Override
	public String headerName() {
		return getName();
	}
	
}
