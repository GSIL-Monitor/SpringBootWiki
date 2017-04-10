package net.liuxuan.SprKi.entity;


import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Copyright (c) 2010-2016.  by Liuxuan   All rights reserved. <br/>
* ***************************************************************************
* 源文件名:  net.liuxuan.SprKi.entity.SliderPics
* 功能: 首页轮播图片
* 版本:	@version 1.0
* 编制日期: 2017/03/27 13:42
* 修改历史: (主要历史变动原因及说明)
* YYYY-MM-DD |    Author      |	 Change Description
* 2017-03-27  |    Moses        |     Created
*/
@Data
@NoArgsConstructor
@Entity  //实体类
@Table(name = "Sprki_SliderPics")
public class SliderPics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false,length = 200)
    String sliderPicsName;

    @Column(nullable = false,length = 200)
    String sliderPicsNameCN;

    @Column(nullable = false,length = 200)
    String comment;

    @Column(nullable = false,length = 400)
    String url;

    @Column(name = "disabled", nullable = false)
    boolean disabled=false;
}