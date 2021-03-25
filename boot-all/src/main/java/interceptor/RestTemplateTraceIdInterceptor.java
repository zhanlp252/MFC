package interceptor;

import io.github.lovelybowen.log.constant.TraceConstant;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @description: 添加 traceId拦截器
 * @author: verity zhan
 * @time: 2021/3/24 20:00
 */
public class RestTemplateTraceIdInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        String traceId = MDC.get(TraceConstant.LOG_TRACE_ID);
        if (traceId != null) {
            httpRequest.getHeaders().add(TraceConstant.LOG_TRACE_ID, traceId);
        }

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}

