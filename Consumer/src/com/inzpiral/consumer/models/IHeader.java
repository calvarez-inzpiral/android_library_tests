package com.inzpiral.consumer.models;

import java.util.ArrayList;


/**
 * Lo implementan las clases que se muestran en el TreeView
 * @author inzpiral
 */
public interface IHeader {
	public ArrayList<TreeStructure> displayHeader(ArrayList<TreeStructure> mNodeDepth, int depth);
	public String headerName();
}
