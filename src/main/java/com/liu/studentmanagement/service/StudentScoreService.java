package com.liu.studentmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.studentmanagement.entity.StudentScore;
import java.util.List;

/**
 * 成绩管理 服务类接口
 * 注意：必须 extends IService 才能使用 MyBatis-Plus 的强大功能
 */
public interface StudentScoreService extends IService<StudentScore> {

    /**
     * 模糊查询成绩
     * @param keyword 关键字（姓名或课程）
     */
    List<StudentScore> findByBlurry(String keyword);

    void deleteById(Integer id);
}