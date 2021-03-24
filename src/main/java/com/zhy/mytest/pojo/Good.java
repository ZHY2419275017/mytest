package com.zhy.mytest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @author: zhy
 * @Date:2020/11/2
 * @Description:
 **/
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = "good",shards = 2,replicas = 3)
public class Good {

    @Id
    @Field(type = FieldType.Text)
    private String id;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String category;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String brand;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Keyword)
    private String images;

}
