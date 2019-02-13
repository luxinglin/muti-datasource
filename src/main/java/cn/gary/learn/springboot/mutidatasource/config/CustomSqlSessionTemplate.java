package cn.gary.learn.springboot.mutidatasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.util.Assert;

public class CustomSqlSessionTemplate extends SqlSessionTemplate {
    
    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory targetSqlSessionFactory = targetSqlSessionFactorys.get(DataSourceContextHolder.getDatasourceType());
        if (targetSqlSessionFactory != null) {
            return targetSqlSessionFactory;
        } else if (defaultTargetSqlSessionFactory != null) {
            return defaultTargetSqlSessionFactory;
        } else {
            Assert.notNull(targetSqlSessionFactorys, "Property 'targetSqlSessionFactorys' or 'defaultTargetSqlSessionFactory' are required");
            Assert.notNull(defaultTargetSqlSessionFactory, "Property 'defaultTargetSqlSessionFactory' or 'targetSqlSessionFactorys' are required");
        }
        return this.sqlSessionFactory;
    }

}
