package com.liu.studentmanagement.controller;

import com.liu.studentmanagement.entity.StudentScore;
import com.liu.studentmanagement.service.IStudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生成绩接口
 * 采用标准企业级流程：Controller -> Service -> Mapper
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/1/study/score") // 符合社长要求的版本化路径
public class StudentScoreController {

    @Autowired
    private IStudentScoreService scoreService;

    /**
     * 查询所有成绩
     */
    @GetMapping("/all")
    public List<StudentScore> getAllScores() {
        return scoreService.list();
    }

    /**
     * 模糊搜索成绩（包含学生姓名、课程名、教师名）
     * 访问地址：http://localhost:8080/api/1/study/score/search?keyword=刘
     */
    @GetMapping("/search")
    public List<StudentScore> search(@RequestParam String keyword) {
        return scoreService.findByBlurry(keyword);
    }
}