package it.gds.point.utils;

import java.util.Comparator;

import it.gds.point.beans.NCarrellodett;


public class SortDetails implements Comparator<NCarrellodett> {

	public int compare(NCarrellodett d1, NCarrellodett d2) {
		
		Integer o1 = d1.getOrder();
		Integer o2 = d2.getOrder();
	
		
		return o1.compareTo(o2);
	}



}
