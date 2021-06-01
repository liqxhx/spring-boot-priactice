package com.lqh.practice.sb.mybatis;

import com.lqh.practice.sb.mybatis.mapper.CoffeeMapper;
import com.lqh.practice.sb.mybatis.model.Coffee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * <p> 类描述: MybatisTests
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/01 20:53
 * @since 2021/06/01 20:53
 */
public class MybatisTests {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void prepare() throws IOException, SQLException {
        // 解析配置文件
        // sql语句解析为 MappedStatement
        // mapper/namespace 解析为了 MapperProxyFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();

        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .addScripts("data.sql")
                .build();

//        new Console().runTool();
//        new RunScript().runTool("jdbc:h2:mem:testdb", "sa","", "schema.sql");
    }

    @Test
    public void testSelectByPrimaryKey() {

        try (SqlSession session = sqlSessionFactory.openSession()) { // session.close()
            // MapperProxy
            CoffeeMapper mapper = session.getMapper(CoffeeMapper.class);

            // MapperMethod
            Coffee coffee = mapper.selectByPrimaryKey(1L);

        }
    }
}
