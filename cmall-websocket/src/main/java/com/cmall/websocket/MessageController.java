package com.cmall.websocket;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

@Controller
@RequestMapping("message")
public class MessageController {
    @Bean
    public TextMessageHandler textMessageHandler() {
        return new TextMessageHandler();
    }

    @RequestMapping
    public String view() {
        return "message";
    }

    /**
     * 发送消息到页面
     * @param request
     * @param username
     * @return
     */
    @RequestMapping("send")
    @ResponseBody
    public String send(String msg) {
	//System.out.println(msg);
        TextMessage message = new TextMessage(msg);
        textMessageHandler().sendMessageToUsers(message);
        return "true";
    }
}
