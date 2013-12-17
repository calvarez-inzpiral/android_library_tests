package com.inzpiral.consumer.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.inzpiral.consumer.R;
import com.inzpiral.consumer.activities.HomeActivity;
import com.inzpiral.consumer.customs.RingGraphic;
import com.inzpiral.consumer.models.Evaluation;
import com.inzpiral.consumer.models.Node;
import com.inzpiral.consumer.utils.EvaluationHelper;



public class LocationSlideMenu extends ListFragment {
	
	private EvaluationHelper mHelper;


	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.slidebar, null);
		
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListeners();
		((RingGraphic)getActivity().findViewById(R.id.ring_graph)).setPercent(66);
		
		mHelper = EvaluationHelper.getInstance();
		ArrayList<String> locations = mHelper.getNodesAsString(mHelper.getLocations());

		rowAdapter adapter = new rowAdapter(getActivity());

		for (String item : locations) {
			adapter.add(new rowItem(item, "0%", android.R.drawable.ic_menu_search));
		}

		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		if (getActivity() == null) return;

		if (getActivity() instanceof HomeActivity) {
			((HomeActivity) getActivity()).loadCategories(id, position);
		}
	}
	
	private class rowItem {
		public String tag, tag2;
		public int iconRes;
		public rowItem(String tag,String tag2, int iconRes) {
			this.tag = tag; 
			this.tag2 = tag2; 
			this.iconRes = iconRes;
		}
	}

	private void setListeners(){
		 Button submit = (Button) getView().findViewById(R.id.btnEnd); 
		 submit.setOnClickListener(mSubmitButtonListener); 
	}
	private OnClickListener mSubmitButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			HomeActivity.saveOnSD();
		} 
		
	};
  
	public class rowAdapter extends ArrayAdapter<rowItem> {

		public rowAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.sidebar_row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);
			TextView percent = (TextView) convertView.findViewById(R.id.row_percent);
			percent.setText(getItem(position).tag2);


			return convertView;
		}

	}
	


}

