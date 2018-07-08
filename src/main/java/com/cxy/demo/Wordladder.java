package com.cxy.demo;

import java.io.*;
import java.util.*;

public class Wordladder {
	static String res;
	static PrintStream mytxt;
	public static String filename;
	public static String words[] = new String[2];
	public static Set<String> dict;
	public static String start() {
		dict = new HashSet<String>(); 
		read_dict(dict);
		Queue<Stack<String>> q = new LinkedList<Stack<String>>();
		Stack<String> tempstack = new Stack<String>();
		tempstack.push(words[0]);
		q.add(tempstack);
		if(!is_valid(words,dict)) {
			return res;
		}
		ladder(q,words,dict);
		return res;
	}
	public static void log(String mes) {
		try {
			mytxt=new PrintStream("./log.txt");
			PrintStream out = System.out;
			System.setOut(mytxt);
			System.out.println("[Time:]"+new Date()+mes);
			System.setOut(out);
			System.out.println("saved");
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
	}
	public static void ladder(Queue<Stack<String>> q, String[] words, Set<String> dict) {
		Stack<String> tempstack = new Stack<String>();
		Set<String> used_dic = new HashSet<String>();
		used_dic.add(words[0]);
		while(!q.isEmpty()) {
			while(!tempstack.isEmpty())
				tempstack.pop();
			tempstack = q.poll();
			for(int i = 0; i < tempstack.peek().length(); i++) {
				for(char c = 'a'; c <='z'; c++) {
					if(c == tempstack.peek().charAt(i))
						continue;
					String tempword = tempstack.peek();
					StringBuilder strb = new StringBuilder(tempword);
					strb.replace(i, i+1, String.valueOf(c));
					tempword = strb.toString();
					
					if(used_dic.contains(tempword))
						continue;
					if(tempword.equals(words[1])) {
						res="A ladder from "+words[0]+" back to "+words[1]+": "+words[1]+" ";
						log(res);
						while(!tempstack.isEmpty()) {
							res += (tempstack.peek()+" ");
							tempstack.pop();
						}
						return;
					}
					if(dict.contains(tempword)) {
						tempstack.push(tempword);
						Stack<String> tmps = (Stack<String>) tempstack.clone();
						q.add(tmps);
						tempstack.pop();
						used_dic.add(tempword);
					}
				}
			}
		}
	}
	public static void read_dict(Set<String> dict) {
		try {
			File file = new File("src\\main\\java\\com\\cxy\\demo\\"+filename);
			BufferedReader bufr = new BufferedReader(new FileReader(file));
			String nextline = null;
			while((nextline = bufr.readLine()) != null) {
				dict.add(nextline);
			}
			bufr.close();
			if(dict.isEmpty()) {
				res="empty.";
				log("ERROR!"+res);
				
			}
			else {
				res="ok";
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public static boolean is_valid(String[] words, Set<String> dict) {
		if(dict.contains(words[0]) && dict.contains(words[1])) {
			if(words[0].length() != words[1].length()) {
				res="The two words must be the same length.";
				log("ERROR!"+res);
				return false;
			}
			if(words[0].equals(words[1])) {
				res="The two words must be different.";
				log("ERROR!"+res);
				return false;
			}
		}else {
			res="The two words must be found in the dictionary.";	
			log("ERROR！"+res);
			return false;
		}
		return true;
	}
}
