package fr.lernejo.chat;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class,args);
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Launcher.class);
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        Scanner sc = new Scanner(System.in);
        String line;
        System.out.println("Input a message, we will send it for you (q for quit)");
        while (true){
            line = sc.nextLine();
            if (line.equals("q")){
                System.out.println("bye");
                ctx.close();
                break;}
            System.out.println("Message sent. Input a message, we will send it for you");
            template.convertAndSend("","chat_messages",line);
        }
    }
}
