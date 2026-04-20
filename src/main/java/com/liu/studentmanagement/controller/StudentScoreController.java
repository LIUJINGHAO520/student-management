package com.liu.studentmanagement.controller;

import com.liu.studentmanagement.entity.StudentScore;
import com.liu.studentmanagement.mapper.StudentScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/score")
public class StudentScoreController {

    @Autowired
    private StudentScoreMapper studentScoreMapper;

    // 1. 検索 (查询所有成绩)
    @GetMapping("/all")
    public List<StudentScore> getAll() {
        return studentScoreMapper.selectList(null);
    }

    // 2. 登録 (新增成绩) - 对应老师要求的 @PostMapping
    // @RequestBody 表示接收前端传来的 JSON 数据
    @PostMapping("/add")
    public String add(@RequestBody StudentScore score) {
        studentScoreMapper.insert(score);
        return "登録成功！";
    }

    // 3. 更新 (修改成绩) - 标准使用 @PutMapping
    @PutMapping("/update")
    public String update(@RequestBody StudentScore score) {
        studentScoreMapper.updateById(score);
        return "更新成功！";
    }

    // 4. 削除 (删除成绩) - 标准使用 @DeleteMapping
    // 路径里的 {id} 会被传给方法
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        studentScoreMapper.deleteById(id);
        return "削除成功！";
    }
}