package com.liu.studentmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.studentmanagement.entity.StudentScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentScoreMapper extends BaseMapper<StudentScore> {
    List<StudentScore> selectByBlurry(@Param("keyword") String keyword);
    // 继承了 BaseMapper，增删改查方法已经自动有了，不需要写代码！
}
