package com.atguigu.eduservice.controller;
import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.*;
//模拟登陆
@RestController
//表示EduLoginController交给spring管理
@RequestMapping("/eduservice/user")
//请求地址

@CrossOrigin//解决跨域问题
public class EduLoginController {

    //login
    @PostMapping("login")
    //起名字
public R login(){
        return  R.ok().data("token","admin");
        //名称是token，值随便写写
    }

    //info
    @GetMapping("info")
    public  R info(){
        return  R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://tse1-mm.cn.bing.net/th/id/R-C.4d9cd2e53dddfc238a06e750b73cd023?rik=MsMCKPGumufOyQ&riu=http%3a%2f%2fwww.desktx.com%2fd%2ffile%2fwallpaper%2fscenery%2f20170209%2fc2accfe637f86fb6f11949cb8651a09b.jpg&ehk=ia2TVXcow6ygWUVZ1yod5xH4aGd8565SYn6CRpxkNoo%3d&risl=&pid=ImgRaw&r=0");

    }

}
