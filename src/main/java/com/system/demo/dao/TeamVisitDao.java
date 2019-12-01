package com.system.demo.dao;

import com.system.demo.enity.TeamVisitEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface TeamVisitDao {
    void insertTV(TeamVisitEntity teamVisitEntity);
    ArrayList<TeamVisitEntity> getForm();
    void updateTeam (@Param("id") Long id, @Param("status") int status);
}
