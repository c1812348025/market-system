package com.example.marketsystem.mapper;

/**
 * PubAccessArea
 *  公共访问区   未登录状态可访问的
 * @author chuyx
 * @date 2020/11/3 10:10
 */

import com.example.marketsystem.model.entity.CinemaDetail;
import com.example.marketsystem.model.entity.FilmShowList;
import com.example.marketsystem.model.entity.RequestFilmList;
import com.example.marketsystem.model.entity.SlideShow;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PubAccessAreaMapper {

    //获取轮播图
    List<SlideShow> getSlideShow();
    //获取影院列表
    List<CinemaDetail> getCinEnaList();
    //获取电影展示list
    List<FilmShowList> getfilmList();
    //返回电影数据列表
    List<RequestFilmList> getFilmDetailpresell();
}
