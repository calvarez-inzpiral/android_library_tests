package com.inzpiral.loginjoshuamvc.controllers;

public interface ControllerState {
	
	boolean handleMessage(int what);
	boolean handleMessage(int what, Object data);
	void dispose();

}
