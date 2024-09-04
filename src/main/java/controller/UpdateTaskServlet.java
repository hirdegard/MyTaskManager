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
import domain.ExistingTask;
import domain.NewTask;

/**
 * Servlet implementation class UpdateTaskServlet
 */
@WebServlet("/UpdateTaskServlet")
public class UpdateTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
			var taskDao = DaoFactory.createTaskDao();
			ExistingTask task = taskDao.getTaskById(id);
			request.setAttribute("oldTask", task);
			request.getRequestDispatcher("/WEB-INF/view/updatetask.jsp")
			.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//newTaskとIdでupdateする
				Integer id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				Date deadline = null;
				try {
					SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
					String deadlineString = request.getParameter("deadline");
					deadline = fmt.parse(deadlineString);
				} catch (ParseException e) {
					deadline = null;
				}
				String description = request.getParameter("description");
				var task = new NewTask(name, false, deadline, false, description);
				try {
					var taskDao = DaoFactory.createTaskDao();
					taskDao.updateTask(id, task);
					response.sendRedirect("TaskListServlet");
				} catch (Exception e) {
					throw new ServletException(e);
				}
	}

}
