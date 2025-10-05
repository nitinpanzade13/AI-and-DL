// Chatbot.java
import java.io.*;
import java.util.*;

public class Chatbot {
    Map<String,String> responses=new HashMap<>();

    public Chatbot(){
        responses.put("hours","CafeByte is open 8 AM to 9 PM daily.");
        responses.put("menu","We serve coffee, sandwiches, salads, desserts.");
        responses.put("location","123 Java Street, Code City.");
        responses.put("contact","Call +1-555-CAFE.");
    }

    String reply(String msg){
        msg=msg.toLowerCase();
        if(msg.contains("hour")||msg.contains("open")) return responses.get("hours");
        if(msg.contains("menu")) return responses.get("menu");
        if(msg.contains("where")||msg.contains("location")) return responses.get("location");
        if(msg.contains("contact")||msg.contains("phone")) return responses.get("contact");
        if(msg.contains("hi")||msg.contains("hello")) return "Hello! How can I help you?";
        return "Sorry, I didn’t understand. Ask about hours, menu, location, or contact.";
    }

    public static void main(String[] args)throws Exception{
        Chatbot bot=new Chatbot();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("CafeByte Assistant (type exit to quit)");
        while(true){
            System.out.print("You: "); String q=br.readLine();
            if(q.equalsIgnoreCase("exit")) break;
            System.out.println("Bot: "+bot.reply(q));
        }
    }
}
