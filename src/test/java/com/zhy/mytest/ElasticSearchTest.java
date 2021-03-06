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

    //??????1
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;


    //??????2
    @Autowired
    private GoodRepository goodRepository;


  //  --------------??????????????????????????????es client??????es------------------------

    //????????????
    @Test
    public void test01() throws IOException {
        //??????????????????
        CreateIndexRequest indexRequest = new CreateIndexRequest("student");
        //???????????????????????????  ?????????????????????
        CreateIndexResponse createIndexResponse = client.indices().create(indexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.isAcknowledged());
    }


    //????????????????????????
    @Test
    public void test02() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("student");
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);

    }

    //????????????
    @Test
    public void test03() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("student");
        AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }


    //????????????????????????
    @Test
    public void testAddDocument() throws IOException {

        //??????????????????????????????????????????
        IndexRequest indexRequest = new IndexRequest("student");

        //?????????????????????????????????
        indexRequest.id("1");
        indexRequest.timeout(TimeValue.timeValueSeconds(1L));

        //????????????????????????????????????
        Student student = new Student("1", "??????", "13");
        //???????????????
        indexRequest.source(JSON.toJSONString(student), XContentType.JSON);

        //????????????
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println(response.toString());
        System.out.println(response.status());
    }


    //??????????????????????????????
    @Test
    public void testDocIsExist() throws IOException {

        GetRequest getRequest = new GetRequest("student","1");

        //????????????es??????????????????
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");

        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);

        System.out.println(exists);
    }

    //??????????????????????????????
    @Test
    public void testGetDocInfo() throws IOException {
        GetRequest getRequest = new GetRequest("student","1");


        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
        Map<String, Object> sourceMap = response.getSource();

        System.out.println(sourceMap.get("name"));
        System.out.println(response);
    }

    //????????????????????????
    @Test
    public void testUpdateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("student", "1");

        //??????????????????????????????
        Student student = new Student("1", "??????", "18");
        updateRequest.doc(JSON.toJSONString(student),XContentType.JSON);

        //??????????????????
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);

        System.out.println(updateResponse.status());

        System.out.println(updateResponse.getVersion());
        System.out.println(updateResponse.getId());
    }



    //?????????????????? ?????????????????????
    @Test
    public void testSearch() throws IOException {
        //??????????????????
        SearchRequest searchRequest = new SearchRequest("student");

        //?????????????????????
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //sourceBuilder.highlighter(new HighlightBuilder());

        //???????????????????????????
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("age", "18");

        //???????????????????????? ?????????????????????  ??????????????????????????????
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(TimeValue.timeValueSeconds(60L));

        //????????????????????????????????????
        searchRequest.source(sourceBuilder);

        //??????????????????
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        //??????????????????
        System.out.println(response.getHits());
        System.out.println(JSON.toJSONString(response.getHits()));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");

        for (SearchHit hit : response.getHits().getHits()) {
             System.out.println(hit.getSourceAsMap().toString());
        }
    }



// ---------------------------??????????????? ??????springdata?????????es----------------------

    @Test
    public void testSave(){
        List<Student> list = new ArrayList<>();
        goodRepository.save(new Good("2","??????222","??????","??????1",2d,"url1"));
    }


    @Test
    public void test05(){
        List<Student> list = new ArrayList<>();
        goodRepository.save(new Good("3","????????????","????????????","???????????????",2d,"url1"));
    }


    @Test
    public void test06(){

        //List<Good> goods = goodRepository.findGoodByTitle("??????");
        List<Good> goods = goodRepository.findAll();
        goods.forEach(System.out::println);
    }

    @Test
    public void test07(){

//        QueryBuilder queryBuilder= QueryBuilders.nestedQuery("studentList",
//                QueryBuilders.termQuery("studentList.name","?????????"), ScoreMode.Avg);
//
//        NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
//        goodRepository.
//        List<Good> list= goodRepository.search(nativeSearchQuery).getContent();
//        list.forEach(System.out::println);

//        NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery("studentList", QueryBuilders.termQuery("studentList.name", "?????????"), ScoreMode.Avg);
//
//        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(nestedQueryBuilder).build();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("category", "??????");
        Iterable<Good> search = goodRepository.search(matchQueryBuilder);
        search.forEach(System.out::println);

    }


    @Test
    public void test08(){
        //????????????
        BoolQueryBuilder nestedBoolQueryBuilder  = QueryBuilders.boolQuery();
        nestedBoolQueryBuilder.must(QueryBuilders.matchQuery("studentList.name", "??????"));
        //nestedBoolQueryBuilder.must(QueryBuilders.matchQuery("studentList.id", "1"));
        NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery("studentList", nestedBoolQueryBuilder, ScoreMode.None);


        //????????????
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("category", "??????");


        //???????????????
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(nestedQueryBuilder);
        boolQueryBuilder.must(matchQueryBuilder);

        //??????
        Iterable<Good> search = goodRepository.search(boolQueryBuilder);
        search.forEach(System.out::println);

    }


}
