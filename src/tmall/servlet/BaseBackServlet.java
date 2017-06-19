package tmall.servlet;

//import java.io.InputStream;

import java.lang.reflect.Method;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
import tmall.dao.CategoryDAO;
import tmall.dao.OrderDAO;
import tmall.dao.OrderItemDAO;
import tmall.dao.ProductDAO;
import tmall.dao.ProductImageDAO;
import tmall.dao.PropertyDAO;
import tmall.dao.PropertyValueDAO;
import tmall.dao.ReviewDAO;
import tmall.dao.UserDAO;
import tmall.util.Page;

public abstract class BaseBackServlet extends HttpServlet {
	
	public abstract String add(HttpServletRequest request, HttpServletResponse response, Page page);
    public abstract String delete(HttpServletRequest request, HttpServletResponse response, Page page);
    public abstract String edit(HttpServletRequest request, HttpServletResponse response, Page page);
    public abstract String update(HttpServletRequest request, HttpServletResponse response, Page page);
    public abstract String list(HttpServletRequest request, HttpServletResponse response, Page page);
     
    protected CategoryDAO categoryDAO = new CategoryDAO();
    protected OrderDAO orderDAO = new OrderDAO();
    protected OrderItemDAO orderItemDAO = new OrderItemDAO();
    protected ProductDAO productDAO = new ProductDAO();
    protected ProductImageDAO productImageDAO = new ProductImageDAO();
    protected PropertyDAO propertyDAO = new PropertyDAO();
    protected PropertyValueDAO propertyValueDAO = new PropertyValueDAO();
    protected ReviewDAO reviewDAO = new ReviewDAO();
    protected UserDAO userDAO = new UserDAO();
    
    public void Service(HttpServletRequest request, HttpServletResponse response) {
    	try {
            
            /*获取分页信息*/
            int start = 0;
            int count = 5;
            
            try {
                start = Integer.parseInt(request.getParameter("page.start"));
            } catch (Exception e) {
                 
            }
            
            try {
                count = Integer.parseInt(request.getParameter("page.count"));
            } catch (Exception e) {
            	
            }
            
            Page page = new Page(start, count);
             
            /*借助反射，调用对应的方法*/
            String method = (String) request.getAttribute("method");
            Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
                    javax.servlet.http.HttpServletResponse.class, Page.class);
            String redirect = m.invoke(this, request, response, page).toString();
             
            /*根据方法的返回值，进行相应的客户端跳转，服务端跳转，或者仅仅是输出字符串*/
             
            if(redirect.startsWith("@"))
                response.sendRedirect(redirect.substring(1));
            else if(redirect.startsWith("%"))
                response.getWriter().print(redirect.substring(1));
            else
                request.getRequestDispatcher(redirect).forward(request, response);
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
