package com.example.marketsystem.model.entity;

import lombok.Data;

/**
 * FilmDetail
 *轮播图返回实体类
 * @author chuyx
 * @date 2020/11/3 10:30
 */
@Data
public class SlideShow {
    public Integer slideId;//轮播id
    public Integer filmId;//电影id
    public String filmName;//电影名称
    public String filmUrl;//电影url

}
