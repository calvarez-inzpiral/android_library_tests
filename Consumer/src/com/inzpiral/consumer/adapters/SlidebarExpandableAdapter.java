package com.inzpiral.consumer.adapters;

import java.util.ArrayList;

import com.inzpiral.consumer.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

public class SlidebarExpandableAdapter extends BaseExpandableListAdapter{

	private Activity activity;
	private ArrayList<Object> childtems;
	private LayoutInflater inflater;
	private ArrayList<String> parentItems, child;


	public SlidebarExpandableAdapter(Activity context, ArrayList<Object> childrens, ArrayList<String> parents) {
		this.childtems = childrens;
		this.parentItems = parents;
		this.activity= context;
	}

	public void setInflater(LayoutInflater inflater, Activity context) {
		this.inflater = inflater;
		this.activity = context; 
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		child = (ArrayList<String>) childtems.get(groupPosition);

		TextView textView = null;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.group_expandable, null);
		}

		textView = (TextView) convertView.findViewById(R.id.textView1);
		textView.setText(child.get(childPosition));

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Toast.makeText(activity, child.get(childPosition),
						Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;	}

	@SuppressWarnings("unchecked")
	@Override
	public int getChildrenCount(int groupPosition) {
		return ((ArrayList<String>) childtems.get(groupPosition)).size();
		//	return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public int getGroupCount() {
		return parentItems.size();
		//	return 0;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//		if (convertView == null) {
//			convertView = inflater.inflate(R.layout.group_expandable, null);
//		}

		   if (convertView == null) {
	            LayoutInflater infalInflater = (LayoutInflater) activity
	                    .getSystemService(activity.LAYOUT_INFLATER_SERVICE);
	            convertView = infalInflater.inflate(R.layout.group_item,
	                    null);
	        }
		   TextView item = (TextView) convertView.findViewById(R.id.data);
				   item.setText(parentItems.get(groupPosition));
		
//				   CheckedTextView item2 = (CheckedTextView) convertView.findViewById(R.id.data);
//				 ((CheckedTextView) item2).setChecked(isExpanded);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
