package com.liu.studentmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.studentmanagement.entity.StudentScore;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentScoreMapper extends BaseMapper<StudentScore> {
    // 继承了 BaseMapper，增删改查方法已经自动有了，不需要写代码！
}
