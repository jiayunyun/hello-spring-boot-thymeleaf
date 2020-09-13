package com.jy.hello.spring.boot.thymeleaf.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@ServerEndpoint("/app")
@Component
public class WebSocketUtil {

    private static CopyOnWriteArraySet<WebSocketUtil> copyOnWriteArraySet = new CopyOnWriteArraySet<WebSocketUtil>();
    private Session session;
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        copyOnWriteArraySet.add(this);
        log.info("websocket有新的连接, 总数:" + copyOnWriteArraySet.size());
    }

    @OnClose
    public void onClose() {
        copyOnWriteArraySet.remove(this);
        log.info("websocket连接断开, 总数:" + copyOnWriteArraySet.size());
    }

    @OnMessage
    public void onMessage(String message,Session session) {
        sendMessage(message);
        log.info("websocket收到客户端发来的消息:" + message);
    }


    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：" + error.getLocalizedMessage(), session.getId());
    }


    public void sendMessage(String message) {
        //遍历客户端
        for (WebSocketUtil webSocket : copyOnWriteArraySet) {
            log.info("websocket广播消息：" + message);
            try {
                //服务器主动推送
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(Object action, Object message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        sendMessage(mapper.writeValueAsString(ImmutableMap.of("key", action, "data", message)));
    }
}
