package com.zhy.mytest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhy
 * @Date:2020/9/3
 * @Description:
 **/
@Controller
@RequestMapping("zhy")
public class MyController {


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("name","zhy");
        resultMap.put("age","18");
        return resultMap;
    }



}
