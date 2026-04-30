package com.liu.studentmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.studentmanagement.entity.StudentScore;
import com.liu.studentmanagement.mapper.StudentScoreMapper;
import com.liu.studentmanagement.service.StudentScoreService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 成绩管理 服务实现类
 * 这里是写具体“业务逻辑”的地方，是连接 Controller 和 Mapper 的桥梁
 */
@Service // 必须加这个注解！否则 Spring 找不到这个干活的人
public class StudentScoreServiceImpl extends ServiceImpl<StudentScoreMapper, StudentScore> implements StudentScoreService {

    @Override
    public List<StudentScore> findByBlurry(String keyword) {
        // baseMapper 是继承来的，直接调用我们在 Mapper 接口里定义的模糊查询方法
        return baseMapper.selectByBlurry(keyword);
    }

    @Override
    public void deleteById(Integer id) {
        // 因为你继承了 MyBatis-Plus 的 IService，所以这里直接调用 baseMapper
        // 或者直接调用 this.removeById(id); 也可以
        baseMapper.deleteById(id);
    }
}