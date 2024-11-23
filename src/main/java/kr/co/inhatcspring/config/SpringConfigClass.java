package kr.co.inhatcspring.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// SpringConfigClass는 Spring의 AbstractAnnotationConfigDispatcherServletInitializer를 확장하여
// DispatcherServlet 설정과 기본 웹 구성 요소를 초기화하는 역할.

public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
    	// DispatcherServlet이 매핑될 URL 패턴을 지정 , 여기서 모든 요청을 처리하도록 설정.
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
    	// 서블릿 애플리케이션 컨텍스트를 위한 설정 클래스를 지정.
        return new Class[]{ServletAppContext.class};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
    	// 루트 애플리케이션 컨텍스트를 위한 설정 클래스를 지정.
        return new Class[]{RootAppContext.class};
    }

    @Override
    protected Filter[] getServletFilters() {
    	// 서블릿 필터 설정 , UTF-8 인코딩을 적용하는 필터를 설정
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        return new Filter[]{encodingFilter};
    }
    
    // WebConfig 클래스는 Spring MVC 설정을 추가로 정의하는 내부 정적 클래스.
    @Configuration
    public static class WebConfig implements WebMvcConfigurer {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
        	// 특정 URL 요청을 별도의 컨트롤러 클래스 없이 직접 뷰 이름에 매핑.
        	// 여기서는 루트 URL("/") 요청을 "index" 뷰로 매핑
            registry.addViewController("/").setViewName("index");
        }
    }
}
