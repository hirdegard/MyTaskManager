package test;

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
 * Servlet implementation class UpdateTest
 */
@WebServlet("/UpdateTest")
public class UpdateTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var task = new NewTask("hoge", false, null, false, "fuga");
		int id = 2;
		try {
			var taskDao = DaoFactory.createTaskDao();
			taskDao.updateTask(id, task);
			System.out.println("hoge");
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
		
	}

}
