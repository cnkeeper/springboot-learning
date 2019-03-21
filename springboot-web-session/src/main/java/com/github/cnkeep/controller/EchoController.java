package com.github.cnkeep.controller;

import com.github.cnkeep.resp.JsonResponse;
import com.github.cnkeep.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/6
 */
@RestController
public class EchoController {

    @Autowired
    private EchoService echoService;

    @GetMapping(path = "info",produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse echo(@RequestParam("word") String word) throws UnknownHostException {
        return JsonResponse.builder()
                .data(echoService.echo()+word)
                .build();
    }
}
