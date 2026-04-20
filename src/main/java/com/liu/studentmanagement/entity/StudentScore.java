package com.liu.studentmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

@Data
@TableName("student_score") // 对应 Navicat 里的表名
public class StudentScore {
    @TableId(type = IdType.AUTO) // 对应你 Navicat 里的 int(11) 和自增
    private Integer id;

    private String studentName; // 对应 student_name
    private String courseName;  // 对应 course_name
    private String teacherName; // 对应 teacher_name
    private Integer score;      // 对应 score
    private String semester;    // 对应 semester
}