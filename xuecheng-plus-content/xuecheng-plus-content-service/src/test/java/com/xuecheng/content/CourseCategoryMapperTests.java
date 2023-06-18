package com.xuecheng.content;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.po.CourseCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseCategoryMapperTests {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;


    @Test
    void testCourseCategory() {
        CourseCategory courseCategory = courseCategoryMapper.selectById("1");
        System.out.println(courseCategory);

    }

}