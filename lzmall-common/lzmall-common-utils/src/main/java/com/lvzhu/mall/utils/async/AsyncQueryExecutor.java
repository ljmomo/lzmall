package com.lvzhu.mall.utils.async;

import com.lvzhu.mall.utils.AsyncExecutorOptions;
import com.lvzhu.mall.utils.ThreadPoolUtil;
import com.lvzhu.mall.utils.ThreadRejectPolicyType;
import com.lvzhu.mall.utils.exception.ExceptionHandler;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * @author lvzhu.
 * Time 2021/8/13 下午6:12
 * Desc 文件描述
 */
@Slf4j
public class AsyncQueryExecutor {


    /**
     * 查询线程池, 峰值qps:20,放大倍数:6 核心线程300, 拒绝策略: 用调用线程执行
     */
    private static final ThreadPoolExecutor DEFAULT_EXECUTOR =
            ThreadPoolUtil.createPool(300, 300, "DefaultAsyncQueryPool-%d", ThreadRejectPolicyType.CALLER_RUNS);


    public static void executeQuery(List<AsyncQueryFuture> futures) {
        executeQuery(futures, AsyncExecutorOptions.DEFAULT, DEFAULT_EXECUTOR);
    }

    @SuppressWarnings("unchecked")
    public static void executeQuery(List<AsyncQueryFuture> futures, AsyncExecutorOptions options, Executor executor){
        if(CollectionUtils.isEmpty(futures)){
            return;
        }

        final Executor realExecutor = executor == null ? DEFAULT_EXECUTOR : executor;

        List<WrapFuture> queryTasks = futures.stream().map(f -> buildQuery(f, realExecutor)).collect(Collectors.toList());

        List<CompletableFuture> completableFutures = queryTasks.stream().map(WrapFuture::getCompletableFuture).collect(Collectors.toList());

        try {
            CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).get(options.getTimeout(), TimeUnit.MILLISECONDS);
        } catch (TimeoutException ex){
            log.info("异步查询超时自动结束",ex);
        } catch (ExecutionException ex) {
            log.info("异步查询失败", ex);
            //如果有被抛出的异常信息,则处理
            Throwable throwable = futures.stream().map(AsyncQueryFuture::getThrowable).filter(Objects::nonNull).findFirst().orElse(ex);
            options.getExceptionHandler().handle(throwable);
        } catch (Exception ex) {
            log.info("异步查询失败 ", ex);
            options.getExceptionHandler().handle(ex);
        }

        queryTasks.forEach(wrap -> {
            AsyncQueryFuture asyncQueryFuture = wrap.getAsyncQueryFuture();
            CompletableFuture completableFuture = wrap.getCompletableFuture();
            asyncQueryFuture.setResult(completableFuture.getNow(null));
        });
    }

    @SuppressWarnings("unchecked")
    private static WrapFuture buildQuery(AsyncQueryFuture asyncQueryFuture, Executor executor) {
        AsyncQueryOptions futureOptions = asyncQueryFuture.getOptions() == null ? AsyncQueryOptions.DEFAULT : asyncQueryFuture.getOptions();
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(asyncQueryFuture.getQuery(), executor).exceptionally(ex -> {
            //completable 异常都会包装成 CompletionException
            Throwable t = ex instanceof CompletionException ? ((CompletionException) ex).getCause() : (Throwable) ex;
            try {
                futureOptions.getExceptionHandler().handle(t);
            } catch (Throwable e) {
                asyncQueryFuture.setThrowable(e);
                throw e;
            }
            return futureOptions.getExceptionMapper().apply(t);
        });
        return new WrapFuture<>(asyncQueryFuture, completableFuture);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class WrapFuture<T> {
        private AsyncQueryFuture<T> asyncQueryFuture;
        private CompletableFuture<T> completableFuture;
    }

    private static class SimpleSinkExceptionHandler implements ExceptionHandler {
        @Override
        public void handle(Throwable throwable) {

        }
    }
}
