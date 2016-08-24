package cn.cjp.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.TopicSession;

/**
 * 包装连接和消息列表的内部类
 * 
 */
class GroupChat {
	private TopicSession session = null;
	private List<GroupMessage> messages = new ArrayList<GroupMessage>();

	public TopicSession getSession() {
		return session;
	}

	public void setSession(TopicSession session) {
		this.session = session;
	}

	public List<GroupMessage> getMessages() {
		return messages;
	}
}

// 群消息类
public class GroupMessage implements Serializable {

	private static final long serialVersionUID = 8933293907620444570L;
	double id = 0.0;
	int groupId = 0;
	int userId = 0;
	String userName = "";
	String body = "";
	Date sendDate = null;

	public GroupMessage() {
		id = Math.random();
	}

	public double getId() {
		return id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}
