package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@TableName("banner_item")
@Getter
@Setter
public class BannerItemDO extends BaseModel{

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String img;

    private String keyword;

    private String type;


    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    @TableLogic //软删除
    private Date deleteTime;

    private Long bannerId;
}
