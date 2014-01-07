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
		if(getChildren() == null) return nodeDepth;
		for (BaseNode headerNode : getChildren()) {
			if(depth < Config.LEVEL_NUMBER && headerNode instanceof IHeader) {
				IHeader header = (IHeader)headerNode;
				nodeDepth.add(new TreeStructure(headerNode.getCode(), header.headerName(), depth, "0%"));
				System.out.println("Depth " + depth + ": " + header.headerName());
				header.displayHeader(nodeDepth, depth+1);
			}
		}
		return nodeDepth;
	}

	@Override
	public String headerName() {
		return getName();
	}
	
}
