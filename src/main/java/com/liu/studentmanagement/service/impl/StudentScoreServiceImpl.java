package com.liu.studentmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.studentmanagement.entity.StudentScore;
import com.liu.studentmanagement.mapper.StudentScoreMapper;
import com.liu.studentmanagement.service.IStudentScoreService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 成绩管理 服务实现类
 * 这里是写具体“业务逻辑”的地方，是连接 Controller 和 Mapper 的桥梁
 */
@Service // 必须加这个注解！否则 Spring 找不到这个干活的人
public class StudentScoreServiceImpl extends ServiceImpl<StudentScoreMapper, StudentScore> implements IStudentScoreService {

    @Override
    public List<StudentScore> findByBlurry(String keyword) {
        // baseMapper 是继承来的，直接调用我们在 Mapper 接口里定义的模糊查询方法
        return baseMapper.selectByBlurry(keyword);
    }
}