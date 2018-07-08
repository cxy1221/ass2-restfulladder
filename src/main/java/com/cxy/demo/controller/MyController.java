package com.example.demo.controller;

import com.example.demo.Wordladder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration  
public class MyController {
	
	@RequestMapping("/hello")
	public String say() {
		Wordladder w = new Wordladder();
		System.out.println("ctrl wordladder");
		return "helloworld";
	}
	
	@RequestMapping("/hello/{myName}")  
    String index(@PathVariable String myName) {  
        return "Hello "+myName+"!!!";  
    }  
	
	@RequestMapping("/wordladder")
    public String word(String fn, String word1, String word2) {
        Wordladder w = new Wordladder();
        w.filename=fn;
        w.words[0]=word1;
        w.words[1]=word2;
        return w.start();
    }
	
	@RequestMapping("/hello4")
    public String hello3(int id, String name) {
        System.out.println("id:" + id + " name:" + name);
        return "word1:" + id + " word2:" + name;
    }

}