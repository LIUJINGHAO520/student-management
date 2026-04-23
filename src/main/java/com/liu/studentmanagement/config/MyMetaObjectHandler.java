package com.liu.studentmanagement.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component // 必须确保有这个注解！
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final Logger log = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("======= 正在执行插入时的自动填充 =======");
        log.info("开始插入自动填充...");
        // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("======= 正在执行更新时的自动填充 =======");
        log.info("开始更新自动填充...");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}