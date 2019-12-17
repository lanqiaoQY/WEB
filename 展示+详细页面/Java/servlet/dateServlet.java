package servlet;

import bean.PageBean;
import bean.indoor;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/list.do")
public class dateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding("utf-8");
            System.out.println("hololive!");

            int currPage = Integer.parseInt(req.getParameter("currPage"));
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            String area = req.getParameter("area");
            int sort = Integer.parseInt(req.getParameter("sort"));

            ProductService service = new ProductService();
            PageBean pageBean;
            
            if ("全部".equals(area)) {
                pageBean = service.productsByCurrPage(currPage,pageSize,sort);
            }else {
                pageBean = service.areas(area,currPage,pageSize,sort);
            }

            int pages = pageBean.getTotalCount()/pageSize;
            if (pageBean.getTotalCount()%pageSize!=0) {
                pages += 1;
            }
            pageBean.setTotalCount(pages);

            ObjectMapper mapper = new ObjectMapper();
            String response = mapper.writeValueAsString(pageBean);
            resp.getWriter().write(response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
