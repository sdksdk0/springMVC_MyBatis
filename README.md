# springMVC_MyBatis
springmvc基础，通过spring整合mybatis的案例

1、mybatis的适用场景。
mybatis适用于需求不固定，需求变化频繁，例如互联网项目、新领域项目。对于需求较为固定的中小型项目也是可以使用的。

2、如何在项目中使用mybatis。
对于单表的增删改查可以使用mybatis逆向工程生成mapper.xml、po类。对于多表关联的需要自定义mapper.
使用mapper代理方式开发持久层dao接口。

3、mybatis二级缓存的优点。
可以提高系统性能，每次去查数据的时候，先从缓存中查找，如果有数据就直接获取出来。适用于对数据时效性不高的数据。

4、电商系统中商品信息缓存不适用。因为这样使用命中率低。所以我们可以在service中有针对性的对商品信息进行缓存处理，根据商品id获取商品信息的service方法中，先去redis缓存中查询是否有该商品信息，如果有就直接缓存，如果不存在就从数据库中取出来，并存到缓存中，返回数据。

5、分布式缓存需要解决的问题。 存在数据共享的需求，采用单独缓存服务器（redis、memcache)将需要共享的需求统一管理起来。

6、mybatis在存取数据通过cache接口进行的，可以实现cache接口，cache接口实现中与redis通信存取缓存策略。


7、重复执行逆向工程，mapper.xml中的内容不是完全覆盖而是追加，所以mapper文件会解析异常。


#springmvc框架原理
前端控制器、处理器映射器、处理器适配器。



第一种：*.action或*.do等，请求以.action或.do结尾 的由前端控制器解析
  		第二种：/，所有的请求全部由前端控制器解析，会把所有的包括静态资源 （css、html、js）也解析，前端控制器无法解析静态资源，特殊处理静态资源访问不通过前端控制器解析
  		第三种：/*，此方法存在问题，请求需要最终转发到jsp，此方法前端控制器把转发的jsp也解析了，前端控制器无法解析jsp的，所以此方法不能使用。
  	 


#springMVC和myBatis整合
##1、整合持久层
让spring管理mybatis的mapper

##整合业务层
让spring管理service接口，让service调用spring容器的mapper接口，让spring控制service的事务。


##整合表现层
使用注解的适配器、映射器开发Handler.


返回逻辑视图名：
redirect:重定向的地址,浏览器地址栏会发生变化。

    //重定向到商品的查询
		return "redirect:queryItems.action";


forward:转发地，地址栏不会改变。



###springmvc和struts2本质区别 

struts2：通过在action类中设置成员变量接收参数，所以struts2必须使用多例。

springmvc：请求数据，通过参数绑定，将参数赋值给controller的方法形参。springmvc可以单例也可以多例，建议使用单例。

1、	springmvc的入口是一个servlet即前端控制器，而struts2入口是一个filter过虑器。
2、	springmvc是基于方法开发(一个url对应一个方法，通过处理器映射器将url对象的method封装成一个HandlerMethod对象)，请求参数传递到方法的形参，可以设计为单例或多例(建议单例)，struts2是基于类开发，传递参数是通过类的属性，只能设计为多例。
3、	Struts采用值栈存储请求和响应的数据，通过OGNL存取数据， springmvc通过参数解析器是将request请求内容解析，并给方法形参赋值，将数据和视图封装成ModelAndView对象，最后又将ModelAndView中的模型数据通过reques域传输到页面。Jsp视图解析器默认使用jstl。


流程：
1、用户发起请求道DispatcherServlet
2、DispatcherServlet调用HandlerAdapter去执行Handler.执行Handler之前经过参数绑定过程，将请求参数通过类型转换，将转换后的结果赋值给controller方法传参。

#异常处理
应该把注意力放在预期异常的处理上。

    public class CustomExceptionResolver implements  HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ex.	printStackTrace();	

		CustomException customException=null;
		
		//对于系统自定义异常类型直接获取异常信息
		if(ex instanceof CustomException){
			customException=(CustomException) ex;
		}else{
			//对于运行时异常，重新构造一个系统自定义的异常类型
			customException=new CustomException("系统忙，请稍后");
		}
		String message=customException.getMessage();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("message",message);
		modelAndView.setViewName("error");
				
		//将异常信息返回到页面
		return modelAndView;
	}

    }

#json转换
如果使用了<mvc:annotation-driven。

则默认已经配置好了json。

如果没有，则需要添加消息转换器。

    <!--注解适配器 -->
	<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
		<property name="messageConverters">
		<list>
		<bean class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter"></bean>
		</list>
		</property>
	</bean>



#上传图片
springmvc提供multipart协议解析接口，本身支持fileupload解析。

需要添加：MultipartFile  pictureFile

    //上传图片
		if(pictureFile!=null && pictureFile.getOriginalFilename()!=null && !pictureFile.getOriginalFilename().equals("")){
			String newFile_path="D:\\uploadPic\\pic\\";
			
			//新文件的地址及文件名
			String originalFilename=pictureFile.getOriginalFilename();
			String newFile_name=UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
		
			File newFile_obj=new File(newFile_path+newFile_name);
			pictureFile.transferTo(newFile_obj);
			
			items.setPic(newFile_name);
		
		}




#restFul支持
表现层状态转化。是对http最全面的诠释，是一种进行互联网开发的理念、思想。

1、每一个URI代表一种资源

2、客户端和服务器之间，传递这种资源的某种表现层

3、客户端通过四个HTTP动词，对服务器端资源进行操作，实现”表现层状态转化。

		//商品查询支持rest
	@RequestMapping(value="/itemView/{type}/{id}")
	public @ResponseBody
	Items itemView(
			@PathVariable("type") Integer type,
			@PathVariable("id") Integer id) throws Exception {
		//查询商品
			Items items = itemsService.findItemById(id);
			
					
			//输出商品信息，使用@ResponseBody转成json
			return items;
		}

#拦截器（用户登录）
1、用户请求至拦截器
2、如果请求的url是不需要登录就可以访问的，就放行，
3、判断用户的身份信息是否合法，从session中获取，如果存在就放行；如果不合法就回到登录页面。


1、拦截器

    public class LoginInterceptor implements HandlerInterceptor {

	
	//执行时机：Handler执行之前，进入Handler方法之前
	//使用场景：用户认证、授权（登陆拦截、操作权限的拦截）
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String url=request.getRequestURI();
		if(url.indexOf("login.action")>=0){
			return true;
		}
		
		HttpSession session=request.getSession();
		String activeUser =  (String) session.getAttribute("activeUser");
		if(activeUser!=null){
			return true;
		}
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}

	//执行时机：进入Handler方法，在Handler返回ModelAndView之前执行
	//使用场景：通过操作modelAndView统一处理一些模型数据或视图页面
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerInterceptor2..postHandle");
	}
	
	//执行时机：Handler执行完成
	//使用场景 ：统一操作日志记录、异常处理
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//记录用户操作日志
		//内容：哪个用户？什么时间？操作什么方法？操作结果？
		System.out.println("HandlerInterceptor2..afterCompletion");
	}

    }


2、在springmvc.xml中配置拦截器

	<mvc:interceptors>
		<mvc:interceptor>
				<mvc:mapping path="/**"/>
				<bean class="cn.tf.ssm.controller.interceptor.LoginInterceptor"></bean>
			</mvc:interceptor> 
	</mvc:interceptors>


3、使用拦截器

    @Controller
    public class LoginController {
	
	@RequestMapping("/login.action")
	public String login(HttpSession session,String username,String password){
		
		session.setAttribute("activeUser", username);
		return "redirect:/items/queryItems.action";
    }
    }


 



