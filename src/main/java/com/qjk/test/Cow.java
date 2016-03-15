package com.qjk.test;

import java.util.ArrayList;
import java.util.List;

public class Cow {

	private int age = 1;
	
	private Cow mother;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Cow getMother() {
		return mother;
	}

	public void setMother(Cow mother) {
		this.mother = mother;
	}
	
	public void grow(){
		this.age = this.age+1;
	}
	
	public Cow giveBaby(){
		
		if(this.age > 4 ){
			
			return new Cow();
		}
		
		return null;
		
	}
	
	
	
	
}


class test{
	
	public static void main(String[] args) {
		
		Cow first = new Cow();
		
		first.setAge(1);
		first.setMother(new Cow());
		List<Cow> cows = new ArrayList<Cow>();
		List<Cow> cows_temp = new ArrayList<Cow>();
		
		cows.add(first);
		for(int i = 1 ; i<= 20 ; i++){
			
			cows_temp = new ArrayList<Cow>();
			
			for (Cow cow : cows) {
				
				cow.grow();
				
				Cow baby = cow.giveBaby();
				
				if(baby != null){
					
					cows_temp.add(baby);
				}	
				
			}
			
			cows.addAll(cows_temp);
				
		}
		
		System.out.println(cows.size());
		for (Cow cow : cows) {
			System.out.println(cow.getAge());
		}
		
		
		
		
	}
	
	
}

