package utils;
import io.github.lovelybowen.log.constant.TraceConstant;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @description:线程traceId封装工具类
 * @author: verity zhan
 * @time: 2021/3/24 19:31
 */
public class ThreadMdcUtil {

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            TraceUtil.getTrace();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return new Runnable() {
            @Override
            public void run() {
                if (context == null) {
                    MDC.clear();
                } else {
                    MDC.setContextMap(context);
                }
                TraceUtil.getTrace();
                try {
                    runnable.run();
                } finally {
                    MDC.clear();
                }
            }
        };
    }
}


