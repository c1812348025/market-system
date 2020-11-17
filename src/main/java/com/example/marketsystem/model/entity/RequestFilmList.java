package com.example.marketsystem.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * RequestFilmList
 *返回电影列表实体类
 * @author chuyx
 * @date 2020/11/3 16:54
 */
@Data
public class RequestFilmList {
    private Integer filmId;
    private String filmName;
    private String filmPicture;
    private Date filmBeOnTime;
}
