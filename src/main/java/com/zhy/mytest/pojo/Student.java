package com.zhy.mytest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author: zhy
 * @Date:2020/10/29
 * @Description:
 **/
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    @Field(type = FieldType.Keyword)
    @Id
    private String id;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;

    @Field(type = FieldType.Text)
    private String age;
}
