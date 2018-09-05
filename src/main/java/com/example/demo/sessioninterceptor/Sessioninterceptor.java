package com.example.demo.sessioninterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.servlet.HandlerInterceptor;


public class Sessioninterceptor implements HandlerInterceptor {
	// 目标方法执行之前
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			//获取作登陆用户的对象
			Object user = request.getSession().getAttribute("stu2");
			if(user == null){//判断是否为空
				// 未登录，给出错误信息，
	            // 获取request返回页面到登录页
				System.out.println("拦截-------未登录");
				response.sendRedirect("/jpa/tologin");
	            return false;
			}else{
				return true;
			}
		}

}
