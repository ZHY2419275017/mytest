package com.zhy.mytest;

import com.alibaba.fastjson.JSON;
import com.zhy.mytest.pojo.Good;
import com.zhy.mytest.pojo.Student;
import com.zhy.mytest.repository.GoodRepository;
import lombok.ToString;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexAction;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zhy
 * @Date:2020/10/29
 * @Description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchTest {

    //用法1
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;


    //用法2
    @Autowired
    private GoodRepository goodRepository;


  //  --------------以下操作是使用原生的es client操作es------------------------

    //创建索引
    @Test
    public void test01() throws IOException {
        //创建索引请求
        CreateIndexRequest indexRequest = new CreateIndexRequest("student");
        //执行创建索引的请求  获取返回的响应
        CreateIndexResponse createIndexResponse = client.indices().create(indexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.isAcknowledged());
    }


    //判断索引是否存在
    @Test
    public void test02() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("student");
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);

    }

    //删除索引
    @Test
    public void test03() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("student");
        AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }


    //测试导入文档对象
    @Test
    public void testAddDocument() throws IOException {

        //创建要发送创建索引的请求对象
        IndexRequest indexRequest = new IndexRequest("student");

        //设置请求的一些基础参数
        indexRequest.id("1");
        indexRequest.timeout(TimeValue.timeValueSeconds(1L));

        //创建待发送的数据体的内容
        Student student = new Student("1", "张三", "13");
        //封装请求体
        indexRequest.source(JSON.toJSONString(student), XContentType.JSON);

        //发送请求
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println(response.toString());
        System.out.println(response.status());
    }


    //测试文档对象是否存在
    @Test
    public void testDocIsExist() throws IOException {

        GetRequest getRequest = new GetRequest("student","1");

        //设置不从es里面获取数据
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");

        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);

        System.out.println(exists);
    }

    //测试获取文档对象信息
    @Test
    public void testGetDocInfo() throws IOException {
        GetRequest getRequest = new GetRequest("student","1");


        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
        Map<String, Object> sourceMap = response.getSource();

        System.out.println(sourceMap.get("name"));
        System.out.println(response);
    }

    //测试更新文档对象
    @Test
    public void testUpdateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("student", "1");

        //设置要更新的对象内容
        Student student = new Student("1", "李四", "18");
        updateRequest.doc(JSON.toJSONString(student),XContentType.JSON);

        //发送更新请求
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);

        System.out.println(updateResponse.status());

        System.out.println(updateResponse.getVersion());
        System.out.println(updateResponse.getId());
    }



    //高级查询测试 以词条查询为例
    @Test
    public void testSearch() throws IOException {
        //创建查询请求
        SearchRequest searchRequest = new SearchRequest("student");

        //创建查询构建器
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //sourceBuilder.highlighter(new HighlightBuilder());

        //构建词条查询构建器
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("age", "18");

        //将词条查询构建器 传给查询构建器  并设置其请求超时时间
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(TimeValue.timeValueSeconds(60L));

        //将查询构建器交给查询请求
        searchRequest.source(sourceBuilder);

        //发送查询请求
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        //文档命中数量
        System.out.println(response.getHits());
        System.out.println(JSON.toJSONString(response.getHits()));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");

        for (SearchHit hit : response.getHits().getHits()) {
             System.out.println(hit.getSourceAsMap().toString());
        }
    }



// ---------------------------以下操作是 使用springdata来操作es----------------------

    @Test
    public void testSave(){
        List<Student> list = new ArrayList<>();
        goodRepository.save(new Good("2","标题222","垃圾","商标1",2d,"url1"));
    }


    @Test
    public void test05(){
        List<Student> list = new ArrayList<>();
        goodRepository.save(new Good("3","厉害标题","垃圾分类","不知名商标",2d,"url1"));
    }


    @Test
    public void test06(){

        //List<Good> goods = goodRepository.findGoodByTitle("标题");
        List<Good> goods = goodRepository.findAll();
        goods.forEach(System.out::println);
    }

    @Test
    public void test07(){

//        QueryBuilder queryBuilder= QueryBuilders.nestedQuery("studentList",
//                QueryBuilders.termQuery("studentList.name","哈哈哈"), ScoreMode.Avg);
//
//        NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
//        goodRepository.
//        List<Good> list= goodRepository.search(nativeSearchQuery).getContent();
//        list.forEach(System.out::println);

//        NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery("studentList", QueryBuilders.termQuery("studentList.name", "哈哈哈"), ScoreMode.Avg);
//
//        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(nestedQueryBuilder).build();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("category", "分类");
        Iterable<Good> search = goodRepository.search(matchQueryBuilder);
        search.forEach(System.out::println);

    }


    @Test
    public void test08(){
        //子项查询
        BoolQueryBuilder nestedBoolQueryBuilder  = QueryBuilders.boolQuery();
        nestedBoolQueryBuilder.must(QueryBuilders.matchQuery("studentList.name", "测试"));
        //nestedBoolQueryBuilder.must(QueryBuilders.matchQuery("studentList.id", "1"));
        NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery("studentList", nestedBoolQueryBuilder, ScoreMode.None);


        //主项查询
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("category", "分类");


        //组合在一起
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(nestedQueryBuilder);
        boolQueryBuilder.must(matchQueryBuilder);

        //查询
        Iterable<Good> search = goodRepository.search(boolQueryBuilder);
        search.forEach(System.out::println);

    }


}
