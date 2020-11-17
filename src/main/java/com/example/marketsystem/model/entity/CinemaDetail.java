package com.example.marketsystem.model.entity;

import lombok.Data;

/**
 * CinemaDetail
 *返回影院列表展示实体类
 * @author chuyx
 * @date 2020/11/3 14:12
 */
@Data
public class CinemaDetail {
    private Integer cinemaId;//影院id
    private String cinemaName;//影院名字
    private String cinemaIntroduce;//影院介绍
    private String cinemaPicture;//影院图片

}
