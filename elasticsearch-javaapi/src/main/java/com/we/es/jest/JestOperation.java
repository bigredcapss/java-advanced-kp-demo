package com.we.es.jest;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试JestClient Elasticsearch API jar包
 * @author we
 * @date 2021-09-15 14:48
 **/
public class JestOperation {
    /**
     * 操作ES的客户端
     */
    private static JestClient jestClient;
    /**
     * 索引名，相当于database
     */
    private static String indexName = "userindex";
    /**
     * 类型名，相当于table
     */
    private static String typeName = "user";
    /**
     * ES的URL地址
     */
    private static String elasticIps = "http://localhost:9200";

    public static void main(String[] args) throws IOException {
        jestClient = getJestClient();
        insertBatchTest();
        allTextserachTest();
        termSerachTest();
        rangeSerachTest();
        jestClient.close();

    }

    /**
     * 测试insertBatch方法
     */
    public static void insertBatchTest() {
        List<Object> objs = new ArrayList<>();
        objs.add(new User(1L, "张三", 20, "张三是个开发工程师","2018-4-25 11:07:42"));
        objs.add(new User(2L, "李四", 24, "李四是个测试工程师","1980-2-15 19:01:32"));
        objs.add(new User(3L, "王五", 25, "王五是个运维工程师","2016-8-21 06:11:32"));
        boolean result = false;
        try {
            result = insertBatch(jestClient,indexName, typeName,objs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("批量新增:"+result);
    }

    /**
     * 测试全文搜索--search方法
     */
    public static void allTextserachTest() {
        String query ="工程师";
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.queryStringQuery(query));
            //分页设置
            searchSourceBuilder.from(0).size(2);
            System.out.println("全文搜索查询语句:"+searchSourceBuilder.toString());
            System.out.println("全文搜索返回结果:"+search(jestClient,indexName, typeName, searchSourceBuilder.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试精确搜索--search方法
     */
    public static void termSerachTest() {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termQuery("age", 24));
            System.out.println("精确搜索查询语句:"+searchSourceBuilder.toString());
            System.out.println("精确搜索返回结果:"+search(jestClient,indexName, typeName, searchSourceBuilder.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试区间搜索--search方法
     */
    public static void rangeSerachTest() {
        String createtime="createtime";
        String from="2016-8-21 06:11:32";
        String to="2018-8-21 06:11:32";
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.rangeQuery(createtime).gte(from).lte(to));
            System.out.println("区间搜索语句:"+searchSourceBuilder.toString());
            System.out.println("区间搜索返回结果:"+search(jestClient,indexName, typeName, searchSourceBuilder.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 获取JestClient客户端
     * @return
     */
    private static JestClient getJestClient(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder(elasticIps).
                connTimeout(60000).readTimeout(60000).multiThreaded(true).build());
        return factory.getObject();
    }

    /**
     * 创建索引
     * @param jestClient
     * @param indexName
     * @return
     * @throws IOException
     */
    public boolean createIndex(JestClient jestClient,String indexName) throws IOException {
        JestResult jr = jestClient.execute(new CreateIndex.Builder(indexName).build());
        return jr.isSucceeded();
    }

    /**
     * 新增数据
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param source
     * @return
     * @throws IOException
     */
    public boolean insert(JestClient jestClient,String indexName,String typeName,String source) throws IOException {
        PutMapping putMapping = new PutMapping.Builder(indexName, typeName, source).build();
        JestResult jr = jestClient.execute(putMapping);
        return jr.isSucceeded();
    }

    /**
     * 查询数据
     * @param jestClient
     * @param indexName
     * @param typeName
     * @return
     * @throws IOException
     */
    public static String getIndexMapping(JestClient jestClient,String indexName,String typeName) throws IOException {
        GetMapping getMapping = new GetMapping.Builder().addIndex(indexName).addType(typeName).build();
        JestResult jr = jestClient.execute(getMapping);
        return jr.getJsonString();
    }

    /**
     * 批量新增数据
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param objects
     * @return
     * @throws IOException
     */
    public static boolean insertBatch(JestClient jestClient, String indexName, String typeName, List<Object> objects) throws IOException {
        Bulk.Builder bulk = new Bulk.Builder().defaultIndex(indexName).defaultType(typeName);
        for (Object obj : objects) {
            Index index = new Index.Builder(obj).build();
            bulk.addAction(index);
        }
        BulkResult br = jestClient.execute(bulk.build());
        return br.isSucceeded();
    }

    /**
     * 全文搜索
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param query
     * @return
     * @throws IOException
     */
    public static String search(JestClient jestClient,String indexName,String typeName,String query) throws IOException {
        Search search = new Search.Builder(query).addIndex(indexName)
                .addType(typeName).build();
        SearchResult jr = jestClient.execute(search);
        System.out.println("--"+jr.getJsonString());
	    System.out.println("--"+jr.getSourceAsObject(User.class));
        return jr.getSourceAsString();
    }

    /**
     * 删除索引
     * @param jestClient
     * @param indexName
     * @return
     * @throws IOException
     */
    public boolean delete(JestClient jestClient,String indexName) throws IOException {
        JestResult jr = jestClient.execute(new DeleteIndex.Builder(indexName).build());
        return jr.isSucceeded();
    }

    /**
     * 删除数据
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param id
     * @return
     * @throws IOException
     */
    public boolean delete(JestClient jestClient,String indexName,String typeName,String id) throws IOException {
        DocumentResult dr = jestClient.execute(new Delete.Builder(id).index(indexName).type(typeName).build());
        return dr.isSucceeded();
    }



}

class User implements Serializable {
    /**
     * 序列化版本ID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private String createtime;


    public User() {
    }


    public User(Long id, String name, Integer age, String description, String createtime) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.description = description;
        this.createtime = createtime;
    }


    /**
     * 获取编号
     * @return id
     */
    public Long getId() {
        return id;
    }


    /**
     * 设置编号
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * 获取姓名
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * 设置姓名
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取年龄
     * @return age
     */
    public Integer getAge() {
        return age;
    }


    /**
     * 设置年龄
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }


    /**
     * 获取描述
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * 设置描述
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * 获取创建时间
     * @return createtime
     */
    public String getCreatetime() {
        return createtime;
    }


    /**
     * 设置创建时间
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + ", description=" + description + ", createtm="
                + createtime + "]";
    }
}
