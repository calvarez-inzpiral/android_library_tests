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
import com.inzpiral.consumer.controllers.SlideMenuController;
import com.inzpiral.consumer.controllers.SlideMenuController.SlideMenuControllerListener;
import com.inzpiral.consumer.models.BaseNode;
import com.inzpiral.consumer.models.Node;
import com.inzpiral.consumer.views.SlideMenuView;

public class SlideExpandableFragment extends Fragment implements SlideMenuControllerListener{
	private ExpandableListView mExpListView;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.slidebar, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// Activity links the view and the controller
		SlideMenuController slideMenuController = new SlideMenuController((SlideMenuView) getView().findViewById(R.id.slide_menu_view), this);

		// Intercept the events of MainView
		((SlideMenuView) getView().findViewById(R.id.slide_menu_view)).setListeners(slideMenuController);
	}

	// Interfaces
	@Override
	public void setAdapter(ExpandableListView expListView, ArrayList<String> parentItems, ArrayList<Object> childItems) {
		SlidebarExpandableAdapter expListAdapter = new SlidebarExpandableAdapter(getActivity(), parentItems, childItems);
		expListView.setAdapter(expListAdapter);
	}

}
