package com.example.marketsystem.service;

import com.example.marketsystem.model.entity.CinemaDetail;
import com.example.marketsystem.model.entity.FilmShowList;
import com.example.marketsystem.model.entity.RequestFilmList;
import com.example.marketsystem.model.entity.SlideShow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * PubAccessAreaService
 *
 * @author chuyx
 * @date 2020/11/3 11:11
 */
@Service
public interface PubAccessAreaService {
    //返回轮播图列表
    List<SlideShow> getSlideShow() throws ExecutionException;
    //返回影院列表
    List<CinemaDetail> getCinEnaList()throws ExecutionException;
    //返回电影list展示
    List<FilmShowList> getfilmList() throws ExecutionException;
    //返回电影数据列表
    List<RequestFilmList> getFilmDetailpresell() throws ExecutionException;
}
