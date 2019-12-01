package com.system.demo.dao;

import com.system.demo.enity.LearnVisitEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface LearnVisitDao {
    void insertLv(LearnVisitEntity learnVisitEntity);
    ArrayList<LearnVisitEntity> getForm();
    void UpdateLearn (@Param("id") Long id, @Param("status") int status);
}
