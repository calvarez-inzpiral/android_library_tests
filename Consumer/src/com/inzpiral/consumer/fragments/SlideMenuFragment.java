package com.inzpiral.consumer.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.inzpiral.consumer.R;
import com.inzpiral.consumer.activities.HomeActivity;
import com.inzpiral.consumer.controllers.SlideMenuController;
import com.inzpiral.consumer.controllers.SlideMenuController.SlideMenuControllerListener;
import com.inzpiral.consumer.models.Node;
import com.inzpiral.consumer.views.SlideMenuView;

public class SlideMenuFragment extends ListFragment implements SlideMenuControllerListener {
	
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
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		if (getActivity() == null) return;

		if (getActivity() instanceof HomeActivity) {
//			((HomeActivity) getActivity()).loadCategories(id, position);
		}
	}

	// Interfaces
	@Override
	public void setAdapter(ExpandableListView expListView,
			ArrayList<String> parentItems, ArrayList<Object> childItems) {
		// TODO Auto-generated method stub
		
	}
//	@Override
//	public void configureList(List<Node> categories) {
//		SlideMenuListAdapter adapter = new SlideMenuListAdapter(getActivity());
//		for (Node category : categories) {
//			adapter.add(new rowItem(category.getCode(), category.getName(), 0, android.R.drawable.ic_menu_search));
//		}
//
//		setListAdapter(adapter);
//	}
	
	// List adapter
	private class rowItem {
		public String code, title, percent;
		public int iconRes;
		public rowItem(String code, String title, int percent, int iconRes) {
			this.code = code; 
			this.title = title; 
			this.percent = percent + "%"; 
			this.iconRes = iconRes;
		}
	}
	public class SlideMenuListAdapter extends ArrayAdapter<rowItem> {

		public SlideMenuListAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.sidebar_row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(getItem(position).title);
			TextView percent = (TextView) convertView.findViewById(R.id.row_percent);
			percent.setText(getItem(position).percent);

			return convertView;
		}
	}
	@Override
	public boolean childClicked(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}

