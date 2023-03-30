package education.top;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

/**
 * messageSource 빈등록: https://derveljunit.tistory.com/338
 * ReloadableResourceBundleMessageSource 설명잘나와있는곳: https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=rock1191&logNo=60195762412
 */
@Slf4j
@Configuration
public class WebConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        Locale.setDefault(Locale.KOREAN);

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:errors");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(Locale.getDefault());
//        messageSource.setUseCodeAsDefaultMessage(true);
//        messageSource.setCacheSeconds(60); //-> 애플리케이션 서버는 클래스패스에 있는 모든 리소스를 캐싱하기 때문에 파일을 변경하더라도 반영되지 않는다.

        log.info("messageSource Bean Created. Default Charset is {} and Default Locale is {}",
                Encoding.DEFAULT_CHARSET.toString(), Locale.getDefault());

        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor(@Autowired ReloadableResourceBundleMessageSource messageSource){
        return new MessageSourceAccessor(messageSource);
    }
}
