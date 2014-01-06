package com.inzpiral.consumer.fragments;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.inzpiral.consumer.R;
import com.inzpiral.consumer.adapters.ExpandableAdapter;
import com.inzpiral.consumer.controllers.SlideMenuController;
import com.inzpiral.consumer.views.SlideMenuView;

import pl.polidea.treeview.InMemoryTreeStateManager;
import pl.polidea.treeview.TreeBuilder;
import pl.polidea.treeview.TreeStateManager;
import pl.polidea.treeview.TreeViewList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class SlideTreeFragment extends Fragment{
	
	 private enum TreeType implements Serializable {
	        SIMPLE,
	        FANCY
	    }
	
	private final Set<Long> selected = new HashSet<Long>();


    private TreeViewList treeView;

    private static final int[] DEMO_NODES = new int[] { 0, 0, 1, 1, 1, 2, 2, 1,
            1, 2, 1, 0, 0, 0, 1, 2, 3, 2, 0, 0, 1, 2, 0, 1, 2, 0, 1 };
    private static final int LEVEL_NUMBER = 4;
    private TreeStateManager<Long> manager = null;
 
    private ExpandableAdapter simpleAdapter;
    private TreeType treeType;
    private boolean collapsible;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.slidebar, null);
	}

//	public void onActivityCreated(Bundle savedInstanceState) {
//		super.onActivityCreated(savedInstanceState);
//		
//		// Activity links the view and the controller
//		SlideMenuController slideMenuController = new SlideMenuController((SlideMenuView) getView().findViewById(R.id.slide_menu_view), this);
//
//		// Intercept the events of MainView
//		((SlideMenuView) getView().findViewById(R.id.slide_menu_view)).setListeners(slideMenuController);
//	}

    @SuppressWarnings("unchecked")
	@Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    	super.onViewCreated(view, savedInstanceState);
    	 TreeType newTreeType = null;
         boolean newCollapsible;
         if (savedInstanceState == null) {
             manager = new InMemoryTreeStateManager<Long>();
             final TreeBuilder<Long> treeBuilder = new TreeBuilder<Long>(manager);
             for (int i = 0; i < DEMO_NODES.length; i++) {
                 treeBuilder.sequentiallyAddNextNode((long) i, DEMO_NODES[i]);
             }
           
             newTreeType = TreeType.SIMPLE;
             newCollapsible = true;
         } else {
             manager = (TreeStateManager<Long>) savedInstanceState
                     .getSerializable("treeManager");
             if (manager == null) {
                 manager = new InMemoryTreeStateManager<Long>();
             }
             newTreeType = (TreeType) savedInstanceState
                     .getSerializable("treeType");
             if (newTreeType == null) {
                 newTreeType = TreeType.SIMPLE;
             }
             newCollapsible = savedInstanceState.getBoolean("collapsible");
         }
         
         treeView = (TreeViewList) getActivity().findViewById(R.id.mainTreeView);
     
         simpleAdapter = new ExpandableAdapter(getActivity(), selected, manager,
                 LEVEL_NUMBER);
         treeView.setAdapter(simpleAdapter);
         setCollapsible(newCollapsible);
         registerForContextMenu(treeView);
    }
    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
    }

//    @Override
//    protected void onSaveInstanceState(final Bundle outState) {
//        outState.putSerializable("treeManager", manager);
//        outState.putSerializable("treeType", treeType);
//        outState.putBoolean("collapsible", getActivity().collapsible);
//        super.onSaveInstanceState(outState);
//    }


    protected final void setCollapsible(final boolean newCollapsible) {
        this.collapsible = newCollapsible;
        treeView.setCollapsible(this.collapsible);
    }

   

}
