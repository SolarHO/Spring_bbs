package kr.co.inhatcspring.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.inhatcspring.mapper.BoardMapper;

// Spring MVC와 MyBatis 설정을 담당하는 클래스
// 이 클래스가 Spring 설정 클래스임을 나타냄.
@Configuration
// Spring MVC를 활성화.
@EnableWebMvc
// 지정된 패키지에서 Spring Bean을 스캔하여 등록
@ComponentScan("kr.co.inhatcspring.controller")
// 데이터베이스 연결 정보가 포함된 프로퍼티 파일을 로드
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer {
    @Value("${db.classname}")
    private String db_classname;
    
    // 프로퍼티 파일에서 값을 읽어와서 변수에 주입합니다.
    @Value("${db.url}")
    private String db_url;

    @Value("${db.username}")
    private String db_username;

    @Value("${db.password}")
    private String db_password;

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // JSP 뷰 리졸버 설정
        WebMvcConfigurer.super.configureViewResolvers(registry);
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 리소스 핸들러 설정 : 정적 파일의 위치를 지정
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/**").addResourceLocations("/resources/");
    }

    @Bean
    public BasicDataSource dataSource() {
        // 데이터베이스 연결 설정 : 데이터베이스 연결을 위한 BasicDataSource 빈 생성
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(db_classname);
        source.setUrl(db_url);
        source.setUsername(db_username);
        source.setPassword(db_password);
        return source;
    }

    @Bean
    public SqlSessionFactory factory(BasicDataSource source) throws Exception {
        // MyBatis SQL SessionFactory 설정
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(source);
        return factoryBean.getObject();
    }

    @Bean
    public MapperFactoryBean<BoardMapper> board_mapper(SqlSessionFactory factory) throws Exception {
        // MyBatis 매퍼 설정 ( for BoardMapper )
        MapperFactoryBean<BoardMapper> factoryBean = new MapperFactoryBean<>(BoardMapper.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }
}
