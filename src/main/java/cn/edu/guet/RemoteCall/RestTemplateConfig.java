package cn.edu.guet.RemoteCall;

import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 将休息模板交付给spring管理，JDK自带的HttpURLConnection作为底层HTTP客户端实现。
 * 当然，我们还可以修改RestTemplate默认的客户端，例如将其改成HttpClient客户端，
 */
@Configuration
public class RestTemplateConfig {


    /**
     * 没有实例化RestTemplate时，初始化RestTemplate
     *
     * @return
     */
    @ConditionalOnMissingBean(RestTemplate.class)
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        return restTemplate;
    }

    /**
     * 使用OkHttpClient作为底层客户端
     * RestTemplate最大的特色就是对各种网络请求方式做了包装，能极大的简化开发人员的工作量，
     * 实现：GET、POST、PUT、DELETE、文件上传与下载为例
     *
     * @return
     */
    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        return new OkHttp3ClientHttpRequestFactory(okHttpClient);
    }

}