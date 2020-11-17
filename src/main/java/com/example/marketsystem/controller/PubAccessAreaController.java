package com.example.marketsystem.controller;

import com.example.marketsystem.exception.PubGetException;
import com.example.marketsystem.model.entity.CinemaDetail;
import com.example.marketsystem.model.entity.FilmShowList;
import com.example.marketsystem.model.entity.RequestFilmList;
import com.example.marketsystem.model.entity.SlideShow;
import com.example.marketsystem.service.PubAccessAreaService;
import com.example.marketsystem.utils.JosnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * PubAccessArea
 *  公共访问区   未登录状态可访问的
 *
 * 333333@author chuyx
 * @date 2020/11/3 10:10
 */
@RestController
@RequestMapping("/api/v1/pub/all")
public class PubAccessAreaController {

    @Autowired
    private PubAccessAreaService pubAccessAreaService;

    /**
     * 返回轮播图列表
     * @return  Q
     */
    @PostMapping("/getSlideShow")
    public JosnData getSlideShow() throws ExecutionException {
        List<SlideShow> slideShows = pubAccessAreaService.getSlideShow();
        if (slideShows==null){
            return JosnData.buildError(-1,"数据轮播图获取异常");
        }
        return JosnData.buildSuccess(slideShows);
    }

    /**
     * 返回展示影院列表
     * @return
     */
    @PostMapping("/getCinEnaList")
    public JosnData getCinEnaList() throws ExecutionException {
        List<CinemaDetail> cinemaDetails = pubAccessAreaService.getCinEnaList();
        if (cinemaDetails==null){
            return JosnData.buildError(-1,"影院列表获取异常");
        }
        return JosnData.buildSuccess(cinemaDetails);
    }

    /**
     * 展示电影list
     */
    @PostMapping("/getfilmList")
    public JosnData getfilmList() throws  ExecutionException {
        List<FilmShowList> filmShowLists =pubAccessAreaService.getfilmList();
        if (filmShowLists ==null){
            return JosnData.buildError(-1,"展示电影list出现异常");
        }
        return JosnData.buildSuccess(filmShowLists);
    }
    /**
     * 返回预售电影详情
     */
    @PostMapping("/getFilmDetailpresell")
    public JosnData getFilmDetailpresell() throws ExecutionException{
        List<RequestFilmList> requestFilmLists = pubAccessAreaService.getFilmDetailpresell();
        if (requestFilmLists == null){
            return JosnData.buildError(-1,"获取数据列表出问题");
        }
        return JosnData.buildSuccess(requestFilmLists);
    }
}
