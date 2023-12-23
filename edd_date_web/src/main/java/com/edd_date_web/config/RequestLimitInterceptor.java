package com.edd_date_web.config;

import com.edd.date.constants.WebConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RequestLimitInterceptor implements HandlerInterceptor {

    private final ConcurrentHashMap<String, AtomicInteger> requestCountMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> blockedIpMap = new ConcurrentHashMap<>();


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String ipAddress = getClientIP(request);

        if (isIpBlocked(ipAddress)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("IP is temporarily blocked. Please try again later.");
            return false;
        }

        AtomicInteger requestCount = requestCountMap.computeIfAbsent(ipAddress, k -> new AtomicInteger(0));


        if (requestCount.incrementAndGet() > WebConstants.MAX_REQUESTS) {
            blockIp(ipAddress);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Too many requests. IP blocked for a short duration.");
            return false;
        }


        resetRequestCountAfterTimeWindow(ipAddress);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

    private String getClientIP(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        }
        return xForwardedForHeader.split(",")[0].trim();
    }

    private void resetRequestCountAfterTimeWindow(String ipAddress) {
        new Thread(() -> {
            try {
                Thread.sleep(WebConstants.TIME_WINDOW_MILLISECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            requestCountMap.remove(ipAddress);
        }).start();
    }

    private void blockIp(String ipAddress) {
        blockedIpMap.put(ipAddress, System.currentTimeMillis() + WebConstants.BLOCKING_TIME_MILLISECONDS);
    }

    private boolean isIpBlocked(String ipAddress) {
        Long blockingEndTime = blockedIpMap.get(ipAddress);
        return blockingEndTime != null && System.currentTimeMillis() < blockingEndTime;
    }
}