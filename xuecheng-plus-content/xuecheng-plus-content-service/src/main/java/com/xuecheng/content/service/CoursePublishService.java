package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CoursePreviewDto;

/**
 * @description 课程预览、发布接口
 */
public interface CoursePublishService {


    /**
     * @description 获取课程预览信息
     * @param courseId 课程id
     */
    CoursePreviewDto getCoursePreviewInfo(Long courseId);
    /**
     * @description 提交审核
     * @param courseId  课程id
     */
    public void commitAudit(Long companyId,Long courseId);

    /**
     * @description 课程发布接口
     * @param companyId 机构id
     * @param courseId 课程id
     */
    public void publish(Long companyId,Long courseId);


}
