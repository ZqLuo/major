package com.example.API;

import com.example.util.AESPasswordUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zqLuo
 * 常用工具接口
 */
@RestController
@RequestMapping("/common")
public class CommonAPI {

    /**
     * 加密
     * @param salt 蜜钥  字节数组
     * @param password 密码
     * @return
     */
    @GetMapping("/encrypt/{password}/{salt}")
    public String encrypt(@PathVariable String password,@PathVariable String salt){
//        String[] s = salt.split(",");
//        byte[] b = new byte[s.length];
//        for(int i=0;i<b.length;i++){
//            b[i] = (byte) Integer.parseInt(s[i]);
//        }
        return AESPasswordUtil.encryptPassword(password,salt);
    }
    /**
     * 加密
     * @param salt 蜜钥  字节数组
     * @param password 密码
     * @return
     */
    @GetMapping("/decrypt/{password}/{salt}")
    public String decrypt(@PathVariable String password,@PathVariable  String salt){
//        String[] s = salt.split(",");
//        byte[] b = new byte[s.length];
//        for(int i=0;i<b.length;i++){
//            b[i] = (byte) Integer.parseInt(s[i]);
//        }
        return AESPasswordUtil.decryptPassword(password,salt);
    }
}
