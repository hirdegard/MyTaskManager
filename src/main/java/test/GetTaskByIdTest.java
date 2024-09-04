package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import domain.ExistingTask;

/**
 * Servlet implementation class GetTaskByIdTest
 */
@WebServlet("/GetTaskByIdTest")
public class GetTaskByIdTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTaskByIdTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			var taskDao = DaoFactory.createTaskDao();
			ExistingTask task = taskDao.getTaskById(5);
			System.out.println(task.name() + " " + task.hasSubtask() + " "
					+ task.deadline() + " " + task.delete_flag() + " " + 
					task.description());
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
