package WoWSFT.config;

import WoWSFT.model.BlockIp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import static WoWSFT.model.Constant.*;

@Slf4j
@Configuration
@EnableConfigurationProperties(CustomProperties.class)
@ComponentScan(basePackages = {"WoWSFT"})
public class CustomFilter implements Filter
{
    private final HashMap<String, Integer> loadFinish;
    private final CustomProperties customProperties;

    public CustomFilter(@Qualifier(value = LOAD_FINISH) HashMap<String, Integer> loadFinish,
                        CustomProperties customProperties)
    {
        this.loadFinish = loadFinish;
        this.customProperties = customProperties;
    }

    private static HashSet<String> blockIP = new HashSet<>();
    private static HashMap<String, BlockIp> ipMap = new HashMap<>();
    private static HashSet<String> ignoreUri = new HashSet<>();

    private static final String headerSrc = "'self' https://cdn.wowsft.com/";
    private static final String googleSrc = "https://tagmanager.google.com/".concat(" ")
            .concat("https://www.googletagmanager.com/").concat(" ")
            .concat("https://www.gstatic.com/").concat(" ")
            .concat("fonts.googleapis.com/").concat(" ")
            .concat("https://www.google-analytics.com/");
//    private static final String headerUnsafe = "";
    private static final String headerUnsafe = "'unsafe-inline'";
    private static final String none = "'none'";

    static {
        ignoreUri.add("/favicon");
        ignoreUri.add("/js");
        ignoreUri.add("/css");
        ignoreUri.add("/images");
        ignoreUri.add("/sitemap");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Content-Security-Policy",
                "default-src".concat(" ").concat(none).concat(";") +
                "object-src".concat(" ").concat(none).concat(";") +
                "connect-src".concat(" ").concat("'self';") +
                "base-uri".concat(" ").concat("'self';") +
                "img-src".concat(" ").concat(headerSrc).concat(" ")
                        .concat("https://ssl.gstatic.com/ https://www.google-analytics.com/;") +
                "script-src".concat(" ").concat(headerUnsafe).concat(" ").concat(headerSrc).concat(" ").concat(googleSrc).concat(" ").concat("data:;") +
                "style-src".concat(" ").concat(headerUnsafe).concat(" ").concat(headerSrc).concat(" ").concat(googleSrc).concat(";") +
                "font-src".concat(" ").concat("https://tagmanager.google.com/ https://fonts.gstatic.com/;") +
                "form-action".concat(" ").concat(none).concat(";") +
                "frame-ancestors".concat(" ").concat(none));

        if (isRelease()) {
            response.setHeader("Strict-Transport-Security", "max-age=15768000; includeSubDomains");
            response.setHeader("X-Content-Type-Options", "nosniff");
            response.setHeader("X-Frame-Options", "DENY");
            response.setHeader("X-XSS-Protection", "1; mode=block");
        }


        if (!HttpMethod.GET.name().equalsIgnoreCase(request.getMethod()) && !HttpMethod.POST.name().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();

//        if (isRelease() && !url.startsWith("https://")) {
//            url = url.replaceFirst(".*://", "https://") + (StringUtils.isNotEmpty(queryString) ? "?" + queryString : "");
//            response.setContentType("text/html");
//            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
//            response.setHeader("Location", url);
//            chain.doFilter(request, response);
//            return;
//        }

        if (loadFinish.get(LOAD_FINISH) == 0 && !SLASH.equalsIgnoreCase(uri) && isNotIgnore(uri)) {
            request.getRequestDispatcher(SLASH).forward(request, response);
            return;
        }

//        if (request.getServerName().startsWith("kr.")) {
//            String tempQS = "";
//            int count = 0;
//            for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
//                if (entry.getValue() != null && entry.getValue().length > 0 && !entry.getKey().toLowerCase().equalsIgnoreCase("lang")) {
//                    if (count != 0) {
//                        tempQS = tempQS.concat("&");
//
//                    }
//                    tempQS = tempQS.concat(entry.getKey()).concat("=").concat(entry.getValue()[0]);
//                    count++;
//                }
//            }
//            tempQS = tempQS.concat(count > 0 ? "&" : "").concat("lang=ko");
//
//            response.setContentType("text/html");
//            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
//            response.setHeader("Location", url.replace("kr.", "") + "?" + tempQS);
//            chain.doFilter(request, response);
//            return;
//        }

//        if (StringUtils.isNotEmpty(queryString) && request.getQueryString().contains("/images/Icon/WoWSFT_Icon.png")) {
//            response.sendRedirect(request.getRequestURI() + "?" + queryString.replace("/images/Icon/WoWSFT_Icon.png", ""));
//            return;
//        }

        String ipAddress = getClientIPAddress(request);

        if (isRelease() && isNotIgnore(request.getRequestURI()) && loadFinish.get(LOAD_FINISH) != 0) {
            if (!ipMap.containsKey(ipAddress)) {
                ipMap.put(ipAddress, new BlockIp(ipAddress));
            } else {
                if (ipMap.get(ipAddress).getBlockCount() < 3) {
                    if (ipMap.get(ipAddress).getCount() < 10) {
                        ipMap.get(ipAddress).doCount();
                    } else {
                        if (System.currentTimeMillis() - ipMap.get(ipAddress).getCreated().getTime() < 15 * 1000) {
                            if (!blockIP.contains(ipAddress)) {
                                ipMap.get(ipAddress).setCreated(new Date()).setBlockCreated(new Date()).addBlockCount();
                                blockIP.add(ipAddress);

                                log.error("Blocked: " + ipAddress + ", count: " + ipMap.get(ipAddress).getBlockCount());
                            }
                        } else {
                            blockIP.remove(ipAddress);
                            ipMap.get(ipAddress).reset();
                        }
                    }
                } else {
                    if (System.currentTimeMillis() - ipMap.get(ipAddress).getBlockCreated().getTime() > 60 * 60 * 1000) {
                        blockIP.remove(ipAddress);
                        ipMap.get(ipAddress).resetBlock();
                    }
                }
            }
        }

        if (blockIP.contains(ipAddress)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy()
    {

    }

    private boolean isNotIgnore(String address)
    {
        return ignoreUri.stream().noneMatch(ig -> address.toLowerCase().contains(ig));
    }

    private String getClientIPAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        if (ipAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            return "localhost";
        }

        return ipAddress;
    }
    
    private boolean isRelease()
    {
        return "release".equalsIgnoreCase(customProperties.getEnv());
    }
}
