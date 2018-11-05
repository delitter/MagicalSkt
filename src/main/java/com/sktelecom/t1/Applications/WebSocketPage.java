package com.sktelecom.t1.Applications;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
@RequestMapping("/WebSocketTest")
public class WebSocketPage {
	//页面请求
	@GetMapping("/getMessage/{cid}")
	public Map<String, Object> getMessage(@PathVariable String cid){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("cid", cid);
		return result;
	}
	
	//推送数据接口
	@RequestMapping(value="/sendMessage/{cid}", consumes="application/json")
	public String sendMessage(@PathVariable String cid, String message){
		try{
			WebSocketServer.sendInfo(message, cid);
		}catch(IOException e){
			e.printStackTrace();
			System.out.println(cid+"#"+e.getMessage());
		}
		return cid;
	}
}
