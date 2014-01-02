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
import com.inzpiral.consumer.controllers.SlideMenuController.SlideMenuControllerListener;
import com.inzpiral.consumer.models.Node;

public class SlideExpandableFragment extends Fragment implements SlideMenuControllerListener{
	private ExpandableListView expListView;
	private List<Node> categories;
	private ArrayList<String> parentItems = new ArrayList<String>();
	private ArrayList<Object> childItems = new ArrayList<Object>();
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

		expListView = (ExpandableListView) getActivity().findViewById(R.id.exp_list);
		expListView.setDividerHeight(2);
		expListView.setGroupIndicator(null);
		expListView.setClickable(true);

		setGroupParents();
		setChildData();

		final SlidebarExpandableAdapter expListAdapter = new SlidebarExpandableAdapter(
				getActivity(), parentItems, childItems);
		expListView.setAdapter(expListAdapter);
	}


	public void setGroupParents() {
		
//		for (Node category : categories) {
//			parentItems.add(category.getName());
//		}
		parentItems.add("categoria 1");
		parentItems.add("categoria 2");
		parentItems.add("categoria 3");
		parentItems.add("categoria 4");
	}
	public void setChildData() {


		ArrayList<String> child = new ArrayList<String>();
		child.add("marca 1");
		child.add("marca 2");
		childItems.add(child);



		child = new ArrayList<String>();
		child.add("marca 2");
		child.add("marca 2");

		childItems.add(child);


		child = new ArrayList<String>();
		child.add("marca 2");
		child.add("marca 2");

		childItems.add(child);



		child = new ArrayList<String>();
		child.add("marca 2");
		child.add("marca 2");

		childItems.add(child);
	}

	@Override
	public void onSetListAdapter(List<Node> list) {
		// TODO Auto-generated method stub
		for (Node category : categories) {
			parentItems.add(category.getName());
		}
	}


}
