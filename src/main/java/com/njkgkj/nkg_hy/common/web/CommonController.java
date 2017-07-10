package com.njkgkj.nkg_hy.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangkaihua on 2017/7/3.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    public String testPage() {
        return "login";
    }

    @ResponseBody
    public String testString() {
        return "aaaaaaa";
    }

    @ResponseBody
    public Map<String, String> testMap() {
        Map<String, String> res = new HashMap<String, String>();
        res.put("a", "aa");
        res.put("b", "bb");
        res.put("c", "cc");
        res.put("d", "dd");
        return res;
    }

    @ResponseBody
    public List<String> testList() {
        return Arrays.asList("a", "b", "c");
    }

}
