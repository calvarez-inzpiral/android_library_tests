package com.inzpiral.consumer.models;

import java.util.ArrayList;

public interface IHeader {
	public ArrayList<Integer> displayHeader(ArrayList<Integer> nodeDepth, int depth);
	public String headerName();
}
