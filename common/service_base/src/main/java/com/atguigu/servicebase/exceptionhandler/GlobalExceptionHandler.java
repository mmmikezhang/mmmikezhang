package com.atguigu.servicebase.exceptionhandler;


import com.atguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@Slf4j
//
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法

//1全局异常处理
   @ExceptionHandler(Exception.class)
   @ResponseBody//为了返回数据
   //为了能够返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行全局异常处理");
   }

   //2特定异常处理
@ExceptionHandler(ArithmeticException.class)
@ResponseBody//为了返回数据
//为了能够返回数据
public R error(ArithmeticException e) {
    e.printStackTrace();
    return R.error().message("执行了ArithmeticException异常处理");
}

//3自定义异常处理   ashdjhjkasdkjhjkh
@ExceptionHandler(GuliException.class)
@ResponseBody//为了返回数据
//为了能够返回数据
public R error(GuliException e) {

       log.error(e.getMessage());
       e.printStackTrace();
    return R.error().code(e.getCode()).message(e.getMsg());
}


}
