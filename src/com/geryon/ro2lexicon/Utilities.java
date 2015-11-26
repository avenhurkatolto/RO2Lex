package com.geryon.ro2lexicon;

import java.util.ArrayList;


public class Utilities {
	
	/**
	 * Function to convert milliseconds time to
	 * Timer Format
	 * Hours:Minutes:Seconds
	 * */
	public String milliSecondsToTimer(int milliseconds){
		String finalTimerString = "";
		String secondsString = "";
		
		// Convert total duration into time
		   int hours = (milliseconds / (1000*60*60));
		   int minutes = (milliseconds % (1000*60*60)) / (1000*60);
		   int seconds = ((milliseconds % (1000*60*60)) % (1000*60) / 1000);
		   // Add hours if there
		   if(hours > 0){
			   finalTimerString = hours + ":";
		   }
		   
		   // Prepending 0 to seconds if it is one digit
		   if(seconds < 10){ 
			   secondsString = "0" + seconds;
		   }else{
			   secondsString = "" + seconds;}
		   
		   finalTimerString = finalTimerString + minutes + ":" + secondsString;
		
		// return timer string
		return finalTimerString;
	}
	
	/**
	 * Function to get Progress percentage
	 * @param currentDuration
	 * @param totalDuration
	 * */
	public int getProgressPercentage(long currentDuration, long totalDuration){
		Double percentage = (double) 0;
		
		long currentSeconds = (int) (currentDuration / 1000);
		long totalSeconds = (int) (totalDuration / 1000);
		
		// calculating percentage
		percentage =(((double)currentSeconds)/totalSeconds)*100;
		
		// return percentage
		return percentage.intValue();
	}

	/**
	 * Function to change progress to timer
	 * @param progress - 
	 * @param totalDuration
	 * returns current duration in milliseconds
	 * */
	public int progressToTimer(int progress, int totalDuration) {
		int currentDuration = 0;
		totalDuration = (int) (totalDuration / 1000);
		currentDuration = (int) ((((double)progress) / 100) * totalDuration);
		
		// return current duration in milliseconds
		return currentDuration * 1000;
	}
	/**
	 * Function to convert multiple Strings into one (for database)
	 * @param Strings 
	 * returns one string, separated by ","
	 * */
	public String StringMerge(ArrayList<String> input){
		String merged = "";
		if (input.size() != 0){
		merged=input.get(0);
		if (input.size() > 1){
		for (int i=1;i<input.size()-1;i++){
			merged += ";"+input.get(i);
		}
		}
		}
		return merged;
	}
	/**
	 * Function to convert a single String into multiple one (from database)
	 * @param String 
	 * returns one Arraylist, separated by ""
	 * */
	public ArrayList<String> StringSplit(String input){
		ArrayList<String> split = new ArrayList<String>(); 
		StringBuilder merged= new StringBuilder(input);
		int j=merged.indexOf(",");
		while ( j!= -1 ){
			split.add(merged.substring(0, j));
			merged.delete(0, j+1);
		}		
		split.add(merged.toString());
				
		return split;
	}
	
}
