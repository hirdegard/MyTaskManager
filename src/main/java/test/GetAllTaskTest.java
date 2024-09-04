package test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import domain.ExistingTask;

/**
 * Servlet implementation class GetAllTaskTest
 */
@WebServlet("/GetAllTaskTest")
public class GetAllTaskTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllTaskTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			var taskDao = DaoFactory.createTaskDao();
			List<ExistingTask> taskList = taskDao.getAllTasks();
			taskList.stream().forEach(task -> {
				System.out.println(task.id() + " " + task.name() + " " + task.hasSubtask()
				+ " " + task.deadline() + " " + task.delete_flag() + " "
				+ task.description());
			});
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
