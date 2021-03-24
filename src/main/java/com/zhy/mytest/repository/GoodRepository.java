package com.zhy.mytest.repository;

import com.zhy.mytest.pojo.Good;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author: zhy
 * @Date:2020/11/2
 * @Description:
 **/
@Repository
public interface GoodRepository extends ElasticsearchRepository<Good,String> {

    List<Good> findGoodByTitle(String title);


    List<Good> findAll();
}
