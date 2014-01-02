package com.inzpiral.consumer.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.inzpiral.consumer.R;
import com.inzpiral.consumer.adapters.SlidebarExpandableAdapter;
import com.inzpiral.consumer.models.Node;

public class SlideExpandableFragment extends Fragment{
	private ExpandableListView expListView;
	private List<Node> categories;
	private ArrayList<Object> groupList;
	private ArrayList<String> childList;
	public void onCreate(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.slidebar, null);

		return view;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		createGroupList(groupList,categories);
//		createChildList(childList,categories);
//		expListView = (ExpandableListView) getActivity().findViewById(R.id.exp_list);
//
//		final SlidebarExpandableAdapter expListAdapter = new SlidebarExpandableAdapter(
//				getActivity(), createGroupList(groupList), createChildList(childList));
//		expListView.setAdapter(expListAdapter);
	}

    private ArrayList<Object> createGroupList(ArrayList<Object> group) {
    	group = new ArrayList<Object>();
//    	for (Node category : categories) {
//			group.add(category.getName());
//		}
  
    	group.add("categoria 1");
    	group.add("categoria 2");
    	group.add("categoria 3");
    	
    	return group;
    }

    private ArrayList<String> createChildList(ArrayList<String> child) {
    	child = new ArrayList<String>();
    	child.add("marca 1");
    	child.add("marca 2");
    	child.add("marca 3");
    	return child; 
    }
//    public void onSetListAdapter(List<Node> categories) {
//		SlideMenuListAdapter adapter = new SlideMenuListAdapter(getActivity());
//		for (Node category : categories) {
//			adapter.add(new rowItem(category.getCode(), category.getName(), 0, android.R.drawable.ic_menu_search));
//		}
//
//		setListAdapter(adapter);
//	}
    
}
