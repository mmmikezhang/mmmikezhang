package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;


import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-02-24
 */
//创建controller



    //requestBody使用json传递数据，把json数据分装到对应的对象里面
    //responseBody返回数据，返回json数据，

@Api(description = "讲师管理")
@RestController//返回Jason数据，把list集合转换成Jason数据进行返回
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin

public class EduTeacherController {

    //访问地址：http://localhost:8001//eduservice/edu-teacher/findAll


    //把service注入


    @Autowired
    private EduTeacherService teacherService;


    //1**** 查询讲师的所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        //调用service里面的方法实现查询的所有操作
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("result", list);
    }


    //2 ******逻辑删除讲师的方法
    @ApiOperation(value ="逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                           @PathVariable String id) {
        boolean b = teacherService.removeById(id);
        if (b) {

            return R.ok();
        } else {
            return R.error();
        }


    }


//3 ****分页查询讲师的方法(函数)
    //current 当前页
    //limit 每页记录数

    //路径传值
    @GetMapping("pageTeacher/{current}/{limit}")


    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);


        try {
            int i=11/0;


        }catch (Exception e)  {
            //执行自定义异常
            throw new GuliException(20001,"自定义异常处理。。。。。");
        }

        //调用方法实现分页

        //调用方法时候，底层封装，把所有数据分装到pageTeacher对象里面
        teacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();//得到总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
        Map map = new HashMap();
        map.put("total", total);//键是key，值是total
        map.put("rows", records);
        return R.ok().data(map);
    }
//多条件组合查询

//模块4
    //4 ***条件查询带分页的方法

    @ApiOperation(value = "条件查询带分页")
    @PostMapping("pageTeacherCondition/{current}/{limit}")//用了
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                               @RequestBody(required =false)   TeacherQuery teacherQuery) {//把条件值封装到对象里面，把对象传递到接口里面
        //requried=false表示值可以没有为空

        //current当前页，limit每页记录数
        //用teacherQuery对象作分装


        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //mybatis学过动态sql
        //判断条件值是否为空，如果不为空拼接条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();


        //根据条件值进行判断，拼接条件
        if (!StringUtils.isEmpty(name)) {
            //构建条件模糊查询
            wrapper.like("name", name);

            //"name""level""gmt_create"是表中的字段名称
        }
        //级别是等于
        if (!StringUtils.isEmpty(level)) {


            wrapper.eq("level",level);


        }
        //开始是大于等于
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",level);


        }
        //结束是小于等于
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", level);


        }

         //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);


//        int a=10/0;

        //调用分页方法
        //调用方法是底层分装，把分页所有数据装到pageTeacher对象里面
        teacherService.page(pageTeacher, wrapper);


        long total = pageTeacher.getTotal();//得到总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
        return R.ok().data("total", total).data("rows", records);//集合总记录数叫records



    }
//添加讲师接口的方法
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) //传数据通过RequestBody
    {
        boolean save = teacherService.save(eduTeacher);
        if (save){
            return R.ok();

        }else {
            return R.error();
        }

    }


    //模块5
    //讲师修改功能，
    // 1、根据讲师id进行查询
   // 2、讲师修改
    //5******根据讲师id查询
    @ApiOperation(value = "根据id查询")
    @GetMapping("getTeacher/{id}")
public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);//查出讲师对象
        return R.ok().data("teacher",eduTeacher);

    }

    //模块6
    //讲师修改功能
    @ApiOperation(value = "更新teacher")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);

        if (flag){
            return R.ok();

        }else {
            return R.error();
        }

    }




}

//接口作用返回数据和操作数据的









