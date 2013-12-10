package com.inzpiral.consumer.fragments;

import java.io.InputStream;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.inzpiral.consumer.R;
import com.inzpiral.consumer.controllers.MainController;
import com.inzpiral.consumer.controllers.MainController.MainControllerListener;
import com.inzpiral.consumer.controllers.SpinnerController;
import com.inzpiral.consumer.controllers.SpinnerController.SpinnerControllerListener;
import com.inzpiral.consumer.models.Evaluation;
import com.inzpiral.consumer.utils.NetworkUtils;
import com.inzpiral.consumer.views.MainView;

public class MainFragment extends SherlockFragment implements MainControllerListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	return inflater.inflate(R.layout.fragment_main, container, false);
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	super.onViewCreated(view, savedInstanceState);
		
		// Activity links the view and the controller
    	MainController mainController = new MainController((MainView) view.findViewById(R.id.main_view), this);
		
		// Intercept the events of MainView
//		((MainView) view.findViewById(R.id.main_view)).setListeners(spinnerController);
		
		// Ejecutar la consulta
		Evaluation ev = mainController.parseConsumer();
		System.out.println(ev.mName);
	}

	@Override
	public InputStream retrieveStream(String url) {
		InputStream asd;
		
		asd = NetworkUtils.retrieveStream(url);
		
		return asd;
	}

}
