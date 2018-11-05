package com.sktelecom.t1.Applications;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {

	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的
	private static int onlineCount = 0;
	//concurretn包的线程安全set，用来存放每个客户端对应的MyWebSocket对象
	private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
	
	//与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	
	//接收sid
	private String sid="";
	/*
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("sid") String sid){
		this.session = session;
		webSocketSet.add(this);	//加入Set中
		addOnlineCount();
		System.out.println("有新窗口开始监听："+sid+"，当前在线人数为"+getOnlineCount());
		this.sid = sid;
		try{
			sendMessage("连接成功");
		}catch(IOException e){
			System.out.println("websocket IO异常");
		}
	}
	
	/*
	 * 连接关闭调用方法
	 */
	@OnClose
	public void onClose(){
		webSocketSet.remove(this);	//从set中删除
		subOnlineCount();			//在线人数减1
		System.out.println("有一连接关闭！当前在线人数为："+getOnlineCount());
	}
	
	/*
	 * 收到客户端消息后调用的方法
	 * @param message客户端发送过来的信息
	 */
	public void onMessage(String message){
		System.out.println("来自客户端的消息："+message);
		//群发消息
		for(WebSocketServer item : webSocketSet){
			try{
				item.sendMessage(message);
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 发生错误
	 */
	@OnError
	public void onError(Session session, Throwable error){
		System.out.println("发生错误");
		error.printStackTrace();
	}
	
	/*
	 * 实现服务器主动推送
	 */
	public void sendMessage(String message)throws IOException{
		this.session.getBasicRemote().sendText(message);
	}
	
	/*
	 * 群发自定义消息
	 */
	public static void sendInfo(String message, @PathParam("sid") String sid)throws IOException{
		System.out.println("推送消息到窗口："+sid+"，推送内容："+message);
		for(WebSocketServer item: webSocketSet){
			try{
				if(sid == null)
					item.sendMessage(message);
				else if(item.sid.equals(sid))
					item.sendMessage(message);
			}catch(IOException e){
				continue;
			}
		}
	}
	
	synchronized public static int getOnlineCount(){
		return onlineCount;
	}
	
	synchronized public static void addOnlineCount(){
		WebSocketServer.onlineCount++;
	}
	
	synchronized public static void subOnlineCount(){
		WebSocketServer.onlineCount--;
	}
}
