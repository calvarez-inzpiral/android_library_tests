package com.inzpiral.consumer.fragments;

import java.util.Set;

import pl.polidea.treeview.TreeStateManager;
import pl.polidea.treeview.TreeViewList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inzpiral.consumer.R;
import com.inzpiral.consumer.adapters.ExpandableAdapter;
import com.inzpiral.consumer.controllers.SlideMenuController;
import com.inzpiral.consumer.controllers.SlideMenuController.SlideMenuControllerListener;
import com.inzpiral.consumer.views.SlideMenuView;

public class SlideTreeFragment extends Fragment implements SlideMenuControllerListener {
	
    private static final int LEVEL_NUMBER = 4;
    
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


	@Override
	public void setAdapter(TreeViewList treeView, Set<Long> selected,
			TreeStateManager<Long> manager) {
		ExpandableAdapter simpleAdapter = new ExpandableAdapter(getActivity(), selected, manager, LEVEL_NUMBER);
		treeView.setAdapter(simpleAdapter);
	}
   

}
