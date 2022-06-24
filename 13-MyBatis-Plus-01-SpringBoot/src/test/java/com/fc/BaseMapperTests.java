package com.fc;

import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class BaseMapperTests {
    @Autowired
    private StudentDao studentDao;

    @Test
    void testFindAll() {
        List<Student> students = studentDao.selectList(null);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    void testFindById() {
        Student student = studentDao.selectById(1);

            System.out.println(student);
    }

    @Test
    void testFindBatchIds() {
        List<Student> students = studentDao.selectBatchIds(Arrays.asList(1, 2, 3, 4));

        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    void testCount() {
        Long count = studentDao.selectCount(null);

        System.out.println("总数据量:" + count);
    }

    @Test
    void testFindMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("name", "123");
        map.put("gender", "男");

        List<Student> students = studentDao.selectByMap(map);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    void testInsert() {
        Student student = new Student();

        student.setName("原石");
        student.setAge(21);
        student.setGender("女");
        student.setBirthday(new Date());
        student.setInfo("648");

        int i = studentDao.insert(student);

        System.out.println(i > 0 ? "插入成功" : "插入失败");
    }

    @Test
    void testDeleteById() {
        int i = studentDao.deleteById(-943718399);

        System.out.println(i > 0 ? "删除成功" : "删除失败");
    }

    @Test
    void testDeleteByConditional() {
        Map<String, Object> map = new HashMap<>();

        map.put("name", "原石");

        studentDao.deleteByMap(map);
    }

    @Test
    void testBatchDelete() {
        int i = studentDao.deleteBatchIds(Arrays.asList(1413533698, 1451237378));

        System.out.println(i > 0 ? "删出成功" : "删除失败");
    }

    @Test
    void testUpdate() {
        Student student = new Student();

        student.setId(1);
        student.setAge(21);
        student.setInfo("修改之后的信息");

        int i = studentDao.updateById(student);

        System.out.println(i > 0 ? "修改成功" : "修改失败");
    }
}
