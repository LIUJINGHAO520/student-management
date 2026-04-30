package com.liu.studentmanagement.controller;

import com.liu.studentmanagement.entity.StudentScore;
import com.liu.studentmanagement.service.StudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生成绩接口
 * 采用标准企业级流程：Controller -> Service -> Mapper
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/1/study/score") // 符合社长要求的版本化路径
public class StudentScoreController {

    @Autowired
    private StudentScoreService scoreService;

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

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteStudent(@PathVariable("id") Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 调用我们刚刚在 Service 里写好的方法
            scoreService.deleteById(id);

            result.put("code", 200);
            result.put("message", "删除成功");
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "删除失败：" + e.getMessage());
        }
        return result;
    }

    // 添加学生成绩的接口
    @PostMapping("/save")
    public Map<String, Object> saveStudent(@RequestBody StudentScore studentScore) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 直接使用 MyBatis-Plus 内置的 save 方法
            boolean success = scoreService.save(studentScore);

            if (success) {
                result.put("code", 200);
                result.put("message", "添加成功");
            } else {
                result.put("code", 500);
                result.put("message", "添加失败");
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "错误：" + e.getMessage());
        }
        return result;
    }
    // 修改学生成绩的接口
    @PutMapping("/update")
    public Map<String, Object> updateStudent(@RequestBody StudentScore studentScore) {
        Map<String, Object> result = new HashMap<>();
        try {
            // MyBatis-Plus 的 updateById 会根据实体类里的 id 自动匹配并更新
            boolean success = scoreService.updateById(studentScore);

            if (success) {
                result.put("code", 200);
                result.put("message", "修改成功");
            } else {
                result.put("code", 500);
                result.put("message", "修改失败");
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "错误：" + e.getMessage());
        }
        return result;
    }
}