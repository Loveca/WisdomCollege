package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {

        //调用mapper递归查询出分类信息（定义mapper，注入）
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);


        List<CourseCategoryTreeDto> courseCategoryList= new ArrayList<>();

        //找到每个节点的子节点，将列表封装成List<CourseCategoryTreeDto>
        //先将list转成map，key就是节点的id，value就是CourseCategoryTreeDto对象，目的就是为了方便从map获取节点
        //filter(item->!id.equal把根节点排除
        Map<String, CourseCategoryTreeDto> mapTemp = courseCategoryTreeDtos.stream().filter(item->!id.equals(item.getId())).collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key1));
        //从头遍历List<CourseCategoryTreeDto>，一边遍历然后找子节点放在父节点的属性中
        courseCategoryTreeDtos.stream().filter(item->!id.equals(item.getId())).forEach(item->{
            //开始处理
            //想list中写入元素
            if(item.getParentid().equals(id)) {
                courseCategoryList.add(item);
            }
            //找到节点的父节点
            CourseCategoryTreeDto courseCategoryParent = mapTemp.get(item.getParentid());
            if(courseCategoryParent != null){
                if(courseCategoryParent.getChildrenTreeNodes() == null){
                    //如果该 父节点的 属性为空，要new 一个集合
                    courseCategoryParent.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                }
                //找到每个子节点放在父节点的childrenTreeNodes中
                courseCategoryParent.getChildrenTreeNodes().add(item);
            }




        });

        return courseCategoryList;
    }
}
