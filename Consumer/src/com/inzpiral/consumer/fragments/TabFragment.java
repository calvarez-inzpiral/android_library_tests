package com.inzpiral.consumer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inzpiral.consumer.R;
import com.inzpiral.consumer.controllers.TabController;
import com.inzpiral.consumer.controllers.TabController.TabControllerListener;
import com.inzpiral.consumer.models.BaseNode;
import com.inzpiral.consumer.models.IDisplayable;
import com.inzpiral.consumer.models.Node;
import com.inzpiral.consumer.views.TabView;

public class TabFragment extends Fragment implements TabControllerListener {

	private String mNodeName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tab, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mNodeName = getArguments().getString("node_name");

		// Activity links the view and the controller
		TabController tabController = new TabController((TabView) view.findViewById(R.id.tab_view), this);

		// Intercept the events of MainView
//		((TabView) view.findViewById(R.id.tab_view)).setListeners(tabController);

	}

	@Override
	public void displayChildren(View parentView, Node node) {
		for (BaseNode baseNode : node.getChildren()) {
			if(baseNode instanceof IDisplayable) {
				((IDisplayable)baseNode).display(getActivity(), parentView, R.id.tab_content);
			}
		}
	}

	@Override
	public String getNodeName() {
		return mNodeName;
	}

}
