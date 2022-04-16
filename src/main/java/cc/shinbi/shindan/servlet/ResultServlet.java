package cc.shinbi.shindan.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cc.shinbi.shindan.model.Result;
import cc.shinbi.shindan.model.Shindan;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResultServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		Shindan shindan = (Shindan)session.getAttribute("shindan");
		
		String name = request.getParameter("name");
		
		String jsp = null;
		if(name == null || name.isEmpty()) {
			request.setAttribute("message", "名前を入力してください。");
			request.setAttribute("shindan",  shindan);
			jsp = "/WEB-INF/jsp/top.jsp";
		}
		else {
			String[] keys = {"q1", "q2", "q3", "q4", "q5"};
			List<Integer> answers = new ArrayList<Integer>();
			for(String key : keys) {
				String value = request.getParameter(key);
				answers.add(Integer.parseInt(value));
			}
			Result result = shindan.check(answers);
			
			request.setAttribute("result", result);
			request.setAttribute("name", name);
			jsp = "/WEB-INF/jsp/result.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

}

