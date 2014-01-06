package com.inzpiral.consumer.adapters;

import java.util.Set;

import pl.polidea.treeview.AbstractTreeViewAdapter;
import pl.polidea.treeview.R;
import pl.polidea.treeview.TreeNodeInfo;
import pl.polidea.treeview.TreeStateManager;
import android.app.Activity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inzpiral.consumer.models.TreeStructure;

/**
 * @author JP
 * adaptador que permite mostrar un arbol de n niveles
 * 
 */
public class ExpandableAdapter extends AbstractTreeViewAdapter<TreeStructure> {

	private final Set<Long> selected;
	Activity activity;

	private final OnCheckedChangeListener onCheckedChange = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(final CompoundButton buttonView,
				final boolean isChecked) {
			final Long id = (Long) buttonView.getTag();
			changeSelected(isChecked, id);
		}

	};

	private void changeSelected(final boolean isChecked, final Long id) {
		if (isChecked) {
			selected.add(id);
		} else {
			selected.remove(id);
		}
	}

	public ExpandableAdapter(Activity context,
			final Set<Long> selected,
			final TreeStateManager<TreeStructure> treeStateManager,
			final int numberOfLevels) {
		super( context, treeStateManager, numberOfLevels);
		this.selected = selected;
		this.activity= context;
	}

	private String getDescription(final String id) {
		//		final Integer[] hierarchy = getManager().getHierarchyDescription(id);
		return "";
		//		return "Node " + id + Arrays.asList(hierarchy);
	}

	@Override
	public View getNewChildView(final TreeNodeInfo<TreeStructure> treeNodeInfo) {
		final LinearLayout viewLayout = (LinearLayout) getActivity()
				.getLayoutInflater().inflate(R.layout.demo_list_item, null);
		return updateView(viewLayout, treeNodeInfo);
	}

	@Override
	public LinearLayout updateView(final View view,
			final TreeNodeInfo<TreeStructure> treeNodeInfo) {
		final LinearLayout viewLayout = (LinearLayout) view;
		final TextView descriptionView = (TextView) viewLayout
				.findViewById(R.id.demo_list_item_description);
		//		final TextView levelView = (TextView) viewLayout
		//				.findViewById(R.id.demo_list_item_level);
		//		descriptionView.setText(getDescription(treeNodeInfo.getId()));
		descriptionView.setText(treeNodeInfo.getId().getNodeTitle());
		//levelView.setText(Integer.toString(treeNodeInfo.getLevel()));
		//		final CheckBox box = (CheckBox) viewLayout
		//				.findViewById(R.id.demo_list_checkbox);
		//		box.setTag(treeNodeInfo.getId());
		//		if (treeNodeInfo.isWithChildren()) {
		//			box.setVisibility(View.GONE);
		//		} else {
		//			box.setVisibility(View.VISIBLE);
		//			box.setChecked(selected.contains(treeNodeInfo.getId()));
		//		}
		//		box.setOnCheckedChangeListener(onCheckedChange);
		return viewLayout;
	}

	@Override
	public void handleItemClick(final View view, final Object id) {
		//		final Long longId = (Long) id;
		//		final TreeNodeInfo<TreeStructure> info = getManager().getNodeInfo(((TreeStructure)id));
		//		if (info.isWithChildren()) {
		//			super.handleItemClick(view, id);
		//		} else {
		//			final ViewGroup vg = (ViewGroup) view;
		//			final CheckBox cb = (CheckBox) vg
		//					.findViewById(R.id.demo_list_checkbox);
		//			cb.performClick();
		//		}
	}

	@Override
	public long getItemId(final int position) {
		return getTreeId(position).getDepth();
	}
}