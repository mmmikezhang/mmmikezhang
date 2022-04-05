package com.atguigu.servicebase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
//通过接口MetaObjectHandler实现类MyMetaObjectHandler，通过类实现方法insertFill,updateFill
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    //"gmtCreate""gmtModified""gmtModified"是属性名称不是字段名称
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);

    }
}
