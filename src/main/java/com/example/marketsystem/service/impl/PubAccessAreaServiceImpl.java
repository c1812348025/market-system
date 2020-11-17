package com.example.marketsystem.service.impl;

import com.example.marketsystem.config.CacheKeyManger;
import com.example.marketsystem.exception.PubGetException;
import com.example.marketsystem.mapper.PubAccessAreaMapper;
import com.example.marketsystem.model.entity.CinemaDetail;
import com.example.marketsystem.model.entity.FilmShowList;
import com.example.marketsystem.model.entity.RequestFilmList;
import com.example.marketsystem.model.entity.SlideShow;
import com.example.marketsystem.service.PubAccessAreaService;
import com.example.marketsystem.utils.BaseCache;
import com.google.common.cache.Cache;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * PubAccessArea
 * 公共访问区   未登录状态可访问的
 *
 * @author chuyx
 * @date 2020/11/3 10:10
 */
@Service("pubAccessAreaService")
@Transactional(rollbackFor=Exception.class)
public class PubAccessAreaServiceImpl implements PubAccessAreaService {

    @Autowired
    private PubAccessAreaMapper pubAccessAreaMapper;

    @Autowired
    private BaseCache baseCache;

    private static final Logger logger = LoggerFactory.getLogger(PubAccessAreaServiceImpl.class);


    /***
     * 轮播图列表获取
     * @return
     */
    @Override
    public List<SlideShow> getSlideShow() throws ExecutionException{
            Object BaseCache = baseCache.getTenMinuteCache().get(CacheKeyManger.Slide_show, () -> {
                List<SlideShow> slideShows = pubAccessAreaMapper.getSlideShow();
                return slideShows;
            });
            if (BaseCache instanceof List) {
                List<SlideShow> bannerList = (List<SlideShow>) BaseCache;
                return bannerList;
            }
        return null;
    }

    /***
     * 获取影院列表
     * @return
     */
    @Override
    public List<CinemaDetail> getCinEnaList() throws ExecutionException{
            Object BaseCache = baseCache.getTenMinuteCache().get(CacheKeyManger.Cinema_list, () -> {
                List<CinemaDetail> cinemaDetails = pubAccessAreaMapper.getCinEnaList();
                return cinemaDetails;
            });
            if (BaseCache instanceof List) {
                List<CinemaDetail> cinemaDetails = (List<CinemaDetail>) BaseCache;
                return cinemaDetails;
            }
        return null;
    }

    /***
     * 返回电影数据列表
     * @return
     */
    @Override
    public List<RequestFilmList> getFilmDetailpresell()  throws ExecutionException{
        Object BaseCache = baseCache.getTenMinuteCache().get(CacheKeyManger.film_detail_list_presell,()->{
            List<RequestFilmList> requestFilmLists = pubAccessAreaMapper.getFilmDetailpresell();
            return requestFilmLists;
        });
        if (BaseCache instanceof List){
            List<RequestFilmList> requestFilmLists = (List<RequestFilmList>)BaseCache;
            return requestFilmLists;
        }
        return null;
    }

    /**
     * 获取电影展示list
     *
     * @return
     */
    @Override
    public List<FilmShowList> getfilmList() throws ExecutionException{
        Object BaseCache = baseCache.getTenMinuteCache().get(CacheKeyManger.Film_list_show, () -> {
            List<FilmShowList> filmShowLists = pubAccessAreaMapper.getfilmList();
            return filmShowLists;
        });
        if (BaseCache instanceof List) {
            List<FilmShowList> filmShowLists = (List<FilmShowList>) BaseCache;
            return filmShowLists;
        }
        return null;
    }
}
