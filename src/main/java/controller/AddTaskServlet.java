package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import domain.NewTask;

/**
 * Servlet implementation class AddTaskServlet
 */
@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addtask.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String name, Boolean hasSubtask, Date deadLine,
		//Boolean deleteFlag, String description
		String name = request.getParameter("name");
		Boolean hasSubtask = false;
		
		Date deadline = null;
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			String deadlineString = request.getParameter("deadline");
			deadline = fmt.parse(deadlineString);
		} catch (ParseException e) {
			deadline = null;
		}
		
		Boolean deleteFlag = false;
		String description = request.getParameter("description");
		var task = new NewTask(name, hasSubtask, deadline, 
				deleteFlag, description);
		try {
			var taskDao = DaoFactory.createTaskDao();
			taskDao.createTask(task);
			request.getRequestDispatcher("TaskListServlet")
				.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}

}
