package com.lm.bookstore.web.filter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * @author LM_Code
 * @create 2019-03-02-7:53
 */
@WebFilter("/*")//过滤器注解。/*表示拦截所有的请求路径
public class MyEncodingFilter implements Filter{
    //有无Override没影响
    public void init(FilterConfig config) throws ServletException {}
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //1.类型转换
        HttpServletRequest hsr = (HttpServletRequest) request;

        /*//2.更改request
        MyRequest myreuqest = new MyRequest(hsr);

        // 处理响应乱码
        response.setContentType("text/html;charset=utf-8");*/
        if(hsr.getMethod().equalsIgnoreCase("post")){
            request.setCharacterEncoding("UTF-8");
        }
        //3.放行
//        chain.doFilter(myreuqest, response);
        chain.doFilter(request, response);
    }
}

class MyRequest extends HttpServletRequestWrapper{

    HttpServletRequest request;
    public MyRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        // TODO Auto-generated method stub
        String[] values = getParameterMap().get(name);
        if(values != null){
            return values[0];
        }
        return null;
    }
    @Override
    public String[] getParameterValues(String name) {
        // TODO Auto-generated method stub
        return getParameterValues(name);
    }
    @Override
    public Map<String, String[]> getParameterMap() {

        String method = request.getMethod();
        if(method.equalsIgnoreCase("post")){//post请求
            try {
                request.setCharacterEncoding("utf-8");
                return request.getParameterMap();
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else if(method.equalsIgnoreCase("get")){//如果是tomcat8版本以上的就不用写这个了
            // TODO Auto-generated method stub
            Map<String, String[]>  map =  request.getParameterMap();
            //遍历，然后解码回UTF-8
			/*for(Entry<String, String[]> entry : map.entrySet()){
				String[] values = entry.getValue();
				for(int i=0;i<values.length;i++){
					try {
						values[i] = new String(values[i].getBytes("ISO-8859-1"),"utf-8");
						System.out.println(values[i]);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}*/


            return map;
        }

        return super.getParameterMap();

    }
}