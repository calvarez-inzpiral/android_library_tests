package com.inzpiral.consumer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.inzpiral.consumer.R;
import com.inzpiral.consumer.activities.HomeActivity;
import com.inzpiral.consumer.controllers.SpinnerController;
import com.inzpiral.consumer.controllers.SpinnerController.SpinnerControllerListener;
import com.inzpiral.consumer.models.Evaluation;
import com.inzpiral.consumer.views.SpinnersView;

public class SpinnerFragment extends SherlockFragment implements SpinnerControllerListener {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.spinners, container, false);
	}
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	super.onViewCreated(view, savedInstanceState);

		// Obtener evaluacion
		Evaluation ev = ((HomeActivity) getActivity()).getEvaluation();
		System.out.println("Desde Spinners: " + ev.mName);
		
		// Activity links the view and the controller
		SpinnerController spinnerController = new SpinnerController((SpinnersView) view.findViewById(R.id.spinners_view), this);
		
		// Intercept the events of MainView
		((SpinnersView) view.findViewById(R.id.spinners_view)).setListeners(spinnerController);
		
	}

	@Override
	public void onDoSomething(String msg) {
		// Do something
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
	}
}
