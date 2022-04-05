package com.hjd.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author 胡建德
 * @Date created in 15:55 2021/1/21
 * @Description:
 */
@Configuration
public class MybatisConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        //设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
        properties.setProperty("offsetAsPageNum","true");
        //置为true时，使用RowBounds分页会进行count查询
        properties.setProperty("rowBoundsWithCount","true");
        //合理化查询,启用合理化时，
        //如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
        //未开启时如果pageNum<1或pageNum>pages会返回空数据
        properties.setProperty("reasonable","true");
        //配置mysql数据库的方言
        properties.setProperty("dialect","mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//    }
//
//    @Bean(name="dataSource")
//    @ConfigurationProperties(prefix = "datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build(); // .type(DataSource.class).build();
//    }
//
//    @Bean(name="sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource());
//        sqlSessionFactoryBean.setTypeAliasesPackage("com.hjd.commons.data,;com.hjd.admin.data");
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/commons/*.xml"));
//        tk.mybatis.mapper.session.Configuration configuration = new tk.mybatis.mapper.session.Configuration();
//        configuration.setMapUnderscoreToCamelCase(true);
//        sqlSessionFactoryBean.setConfiguration(configuration);
//
//        // 分页插件
//        Interceptor interceptor = new PageHelper();;
//        Properties properties = new Properties();
//        //设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
////        properties.setProperty("offsetAsPageNum","true");
//        //置为true时，使用RowBounds分页会进行count查询
//        properties.setProperty("rowBoundsWithCount","true");
//        //合理化查询,启用合理化时，
//        //如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
//        //未开启时如果pageNum<1或pageNum>pages会返回空数据
//        properties.setProperty("reasonable","true");
//        //配置mysql数据库的方言
//        properties.setProperty("dialect","mysql");
//        interceptor.setProperties(properties);
//        sqlSessionFactoryBean.setPlugins(new Interceptor[] {interceptor});
//        return sqlSessionFactoryBean.getObject();
//    }
//    @Bean(name="sqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }
}
