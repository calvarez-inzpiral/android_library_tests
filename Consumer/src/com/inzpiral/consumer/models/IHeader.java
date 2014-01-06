package com.inzpiral.consumer.models;

import java.util.ArrayList;

public interface IHeader {
	public ArrayList<TreeStructure> displayHeader(ArrayList<TreeStructure> mNodeDepth, int depth);
	public String headerName();
}
