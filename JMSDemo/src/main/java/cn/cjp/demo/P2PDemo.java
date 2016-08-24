package cn.cjp.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 点对点通信 <br>
 * 
 * @author REAL
 * 
 */
public class P2PDemo {

	static Session session = null;
	static Destination dest;
	static Destination replyDest;
	// 生产者与消费者
	static MessageProducer producer = null;
	static MessageConsumer consumer = null;

	// 消费者回复与生产者接收
	static MessageConsumer consumerReply = null;
	static MessageProducer producerReceive = null;

	public static void main(String[] args) {

		ConnectionFactory connFactory;

		Connection conn = null;

		// 连接到activeMq
		connFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:8080");

		try {
			conn = connFactory.createConnection();
			conn.setClientID("client_001");
			conn.start();

			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// 创建一个消息发送队列
			dest = session.createQueue("SenderQueue");
			// 消息回复队列
			replyDest = session.createQueue("replyQueue");

			// 创建一个生产者和一个消费者
			producer = session.createProducer(dest);
			consumer = session.createConsumer(dest);
			consumerReply = session.createConsumer(replyDest);

			// 发送线程
			new Thread(new Runnable() {
				public void run() {
					try {
						send();
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}

				private void send() throws JMSException {
					String text = "hello B";
					TextMessage message = session.createTextMessage(text);
					// 设置回复的dest
					message.setJMSReplyTo(replyDest);
					producer.send(message);
				}
			}).start();

			// 接收者的回复线程
			new Thread(new Runnable() {
				public void run() {
					try {
						receiveReply();
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}

				private void receiveReply() throws JMSException {
					while (true) {
						TextMessage msgReply = (TextMessage) consumerReply
								.receive(5000);
						if (null != msgReply) {
							System.out.println("A：我也收到你的回复--内容："
									+ msgReply.getText());
						}
					}
				}
			}).start();

			// 接收并回复线程
			// 我这里是只用了一个线程，每收到一条信息，就立刻回复
			new Thread(new Runnable() {
				public void run() {
					try {
						receive();
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}

				private void receive() throws JMSException {
					while (true) {
						TextMessage message = (TextMessage) consumer
								.receive(10000);

						if (null != message) {
							System.out.println("B：我收到了--内容："
									+ message.getText());

							String text = "How are you, A ?";
							if (message.getJMSReplyTo() != null) {
								// Reply
								MessageProducer producerReply = session
										.createProducer(message.getJMSReplyTo());
								// 使用下面这种方法也可以（建议另开一个线程，随时可以回复信息）
								// MessageProducer producerReply = session
								// .createProducer(replyDest);

								TextMessage msgReply = session
										.createTextMessage(text);
								producerReply.send(msgReply);
							}
						}

					}
				}
			}).start();
		} catch (Exception e) {
		}

	}

}
