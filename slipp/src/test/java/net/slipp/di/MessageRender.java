package net.slipp.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageRender {

	private MessageProvider messageProvider;
	
	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}
	
	public void render(){
		System.out.println(messageProvider.getMessage());
	}
	
	public static void main(String[] args) {

		MessageRender render = new MessageRender();
		render.setMessageProvider(new HelloWorldMessageProvider());
		render.render();
		
		render.setMessageProvider(new HiWorldMessageProvider());
		render.render();
		
	}
	
}