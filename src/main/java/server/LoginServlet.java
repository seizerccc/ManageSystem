package server;

import DAO.*;
import basic.Shopapt;
import model.Car;
import model.Shopuser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        //建立数据库连接

        String userid = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(userid);System.out.println(password);

        String access = request.getParameter("submit");
        RequestDispatcher view;
        if(access.equals("立即注册")){
            view = request.getRequestDispatcher("WEB-INF/register.jsp");
        }
        else if(access.equals("忘记密码"))
        {
            view = request.getRequestDispatcher("WEB-INF/forget.jsp");
        }
        else
        {
            if(userid.equals(""))
            {
                request.setAttribute("msg0", "请输入用户名");
                view  = request.getRequestDispatcher("index.jsp");
            }
            else if(password.equals(""))
            {
                request.setAttribute("msg1", "请输入密码");
                view  = request.getRequestDispatcher("index.jsp");
            }
            else {
                //rs = stmt.executeQuery("SELECT * FROM user where usr_name = '"+userid+"'and password =  '"+password+"'");
                //rs.next();
                HttpSession session = request.getSession();
                session.setAttribute("userid",userid);
                ShopuserDAO shopuserdao = DAOFactory.getShopuserDAO();
                Shopuser shopuser = shopuserdao.findById(userid);
                if (shopuser.getPswd()!=null&&shopuser.getPswd().equals(password)) {
                    //request.setAttribute("msg","登录失败");
                    //view=request.getRequestDispatcher("index.jsp");
                    CarDAO cardao = DAOFactory.getCarDAO();
                    List<Car> cars = cardao.findAll();
                    Map<String,String> maps = new HashMap<String,String>();
                    for(Car car:cars) {
                        maps.put(car.getBrand(),car.getModel());
                    }
                    request.setAttribute("maps",maps);

                    request.setAttribute("userid",userid);//显示用户id

                    CarinfoDAO carinfoDAO = DAOFactory.getCarinfoDAO();
                    request.setAttribute("cars",carinfoDAO.findAllByShopId(userid));

                    view = request.getRequestDispatcher("WEB-INF/firstpage.jsp");
                } else {
                    request.setAttribute("msg2", "登录失败");
                    view = request.getRequestDispatcher("index.jsp");

                }
            }
        }
        view.forward(request,response);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);

    }
}