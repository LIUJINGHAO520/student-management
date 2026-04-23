package com.liu.studentmanagement.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("student_score")
public class StudentScore implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID (社长要求用 Long，对应数据库 bigint)
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    private String studentName;
    private String courseName;
    private String teacherName;
    private Integer score;
    private String semester;

    /**
     * 创建时间：由 MyMetaObjectHandler 自动填充
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间：由 MyMetaObjectHandler 自动填充
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // --- 手动生成 Setter 方法，防止 IDEA 报错 ---
    public void setId(Long id) { this.id = id; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }
    public void setScore(Integer score) { this.score = score; }
    public void setSemester(String semester) { this.semester = semester; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}