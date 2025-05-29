package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmissorDCOMP {

  private static final String QUEUE_NAME = "minha-fila";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("3.93.172.254"); // Alterar
    factory.setUsername("admin"); // Alterar
    factory.setPassword("BubB1e"); // Alterar
    factory.setVirtualHost("/");    
    Connection connection = factory.newConnection(); //via TCP
    Channel channel = connection.createChannel();

    channel.exchangeDeclare("Aluno", "fanout");

                      //(queue-name, durable, exclusive, auto-delete, params);

    String message = "AULA NO LAB C2";
    
                    //  (exchange, routingKey, props, message-body             ); 
    channel.basicPublish("Aluno",       QUEUE_NAME, null,  message.getBytes("UTF-8"));
    System.out.println(" [x] Mensagem enviada: '" + message + "'");

    channel.close();
    connection.close();
  }
}