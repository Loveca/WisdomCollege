package com.xuecheng.content.service;


import com.xuecheng.content.model.dto.BindTeachplanMediaDto;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.TeachplanMedia;

import java.util.List;

//课程计划管理相关接口
public interface TeachplanService {

    public List<TeachplanDto> findTeachplanTree(Long courseId);

    public void saveTeachplan(SaveTeachplanDto teachplanDto);

    /**
     * @description 教学计划绑定媒资
     * @param bindTeachplanMediaDto
     * @return com.xuecheng.content.model.po.TeachplanMedia

     */
    public TeachplanMedia associationMedia(BindTeachplanMediaDto bindTeachplanMediaDto);
}
