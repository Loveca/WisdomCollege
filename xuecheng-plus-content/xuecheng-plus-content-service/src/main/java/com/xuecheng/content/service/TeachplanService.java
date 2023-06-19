package com.xuecheng.content.service;


import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;

import java.util.List;

//课程计划管理相关接口
public interface TeachplanService {

    public List<TeachplanDto> findTeachplanTree(Long courseId);

    public void saveTeachplan(SaveTeachplanDto teachplanDto);
}
