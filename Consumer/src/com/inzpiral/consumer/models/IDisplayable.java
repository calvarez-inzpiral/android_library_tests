package com.inzpiral.consumer.models;


/**
 * Lo implementan las clases que tienen Views que desplegar (es decir, si es una actividad)
 * @author inzpiral
 */
import android.app.Activity;
import android.view.View;

public interface IDisplayable  {
	public void display(Activity activity, View parentView, int parentId);
}
