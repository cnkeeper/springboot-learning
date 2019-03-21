package com.github.cnkeep.service.impl;

import com.github.cnkeep.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/6
 */
@Service
public class EchoServiceImpl implements EchoService {

    @Autowired
    private ServerProperties serverProperties;

    @Override
    public String echo() throws UnknownHostException {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        Integer port = serverProperties.getPort();
        return MessageFormat.format("serverip:{0}, port:{1}", hostAddress, port);
    }
}
