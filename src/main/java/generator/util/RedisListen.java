package generator.util;

import generator.service.LoginLogService;
import generator.service.impl.LoginLogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Component

public class RedisListen implements MessageListener {
    @Autowired
    LoginLogService loginLogService;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    @PostConstruct
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        loginLogService.notLogout(CONSTANT.getCurrentTime(),expiredKey);
    }
}