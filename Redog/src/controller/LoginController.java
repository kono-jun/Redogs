package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommonService;
import service.LoginService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Object loginSession = session.getAttribute("LoginId");

		// ログイン判定
		if (loginSession != null) {
			response.sendRedirect("Main");
		} else{
			String view = "/WEB-INF/view/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			request.setAttribute("userCheckFlg", true);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		boolean userCheckFlg = true;
        String hashPassword = null;

        CommonService commonService = new CommonService();
        LoginService loginService = new LoginService();

			// パスワードをハッシュ化
			hashPassword = commonService.hashPassword(userId, password);

			// ログインユーザー認証
			userCheckFlg = loginService.userAuthentication(userId, hashPassword);

		// 認証失敗
		if(!userCheckFlg) {
			String view = "/WEB-INF/view/login.jsp";
			request.setAttribute("userCheckFlg", userCheckFlg);
			request.setAttribute("formUserId", userId);
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);

			dispatcher.forward(request, response);

		}else {			// 認証成功
			HttpSession session = request.getSession(true);
			session.setAttribute("LoginId",userId);
			response.sendRedirect("Main");
		}
	}
}
