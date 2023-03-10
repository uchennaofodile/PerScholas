package com.perscholas.java_basics;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.*;

class LinkedListPractice {

	public static void main(String[] args) {
    
        LinkedList<String> namesLinknedList = new LinkedList<>();
        ArrayList<String> namesArrayList = new ArrayList<>();
        namesLinknedList.add("Uchenna");
        namesLinknedList.add("Ifeoma");
        namesLinknedList.add("Mom");
        namesLinknedList.add(3, "Grandma");
        System.out.println(namesLinknedList.get(3));
        
        namesArrayList.add("Obi");
        namesArrayList.add("Clement");
        namesArrayList.add(0, "Clara");
        System.out.println(namesArrayList.get(0));
        
        LinkedList<String> linky = new LinkedList<>();
        linky.add("Rob");
        linky.add("Ifeanyi");
        linky.add("Chika");
        linky.add(1, "Zuko");
        linky.addFirst("Aang");
        linky.addLast("Soka");
        linky.contains("Aang");
        System.out.println("instance.element(), first element " + linky.element());
        System.out.println("getFirst(): "+linky.getFirst());
        System.out.println("getLast(): "+linky.getLast());
        System.out.println("indexOf() gives first occurrence: "+linky.indexOf("Aang"));
        System.out.println("lastOf() gives last occurrence: "+linky.lastIndexOf("Aang"));
        System.out.println("offerFirst(): returns boolean, e.g.:  " + linky.offerFirst("Katara"));
        //linky.clear();//removes everything
        
        
    //Iteration without knowing the index
        Iterator it = linky.iterator();
        while(it.hasNext()){
            if(it.next()=="Zuko"){
                System.out.println("We found Zuko!");
            }


}
        
	}
}
        
	
