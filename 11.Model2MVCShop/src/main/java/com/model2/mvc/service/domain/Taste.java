package com.model2.mvc.service.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class Taste {

	ArrayList<String> tastes;
	String[] testing;
	
	
	public ArrayList<String> getTastes() {
		return tastes;
	}
	public void setTastes(ArrayList<String> tastes) {
		this.tastes = tastes;
	}
	public String[] getTesting() {
		return testing;
	}
	public void setTesting(String[] testing) {
		this.testing = testing;
	}
	
	
	@Override
	public String toString() {
		return "Taste [tastes=" + tastes + ", testing=" + Arrays.toString(testing) + "]";
	}
	
}
