package net.slipp.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageRender {

	public void render(){
		MessageProvider mp = new HelloWorldMessageProvider();
		System.out.println(mp.getMessage());
	}
	
	public static void main(String[] args) {

		MessageRender render = new MessageRender();
		render.render();
	}
	
}