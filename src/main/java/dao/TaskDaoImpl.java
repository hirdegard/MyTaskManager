package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import domain.ExistingTask;
import domain.NewTask;

public class TaskDaoImpl implements TaskDao {

	private final DataSource ds;

	public TaskDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void createTask(NewTask task) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO tasks " + "(name, has_subtask, deadline, delete_flag, description) "
					+ "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, task.name());
			stmt.setObject(2, task.hasSubtask(), Types.BOOLEAN);
			stmt.setObject(3, task.deadline(), Types.DATE);
			stmt.setObject(4, task.deleteFlag(), Types.BOOLEAN);
			stmt.setString(5, task.description());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public ExistingTask getTaskById (Integer id) throws Exception{
		var taskList = new ArrayList<ExistingTask>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM tasks WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				taskList.add(mapToExistingTask(rs));
			}
			
			return taskList.get(0);
			
		} catch (Exception e) {
			throw e;}
		
		
	}

	@Override
	public List<ExistingTask> getAllTasks() throws Exception{
		var taskList = new ArrayList<ExistingTask>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM tasks";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				taskList.add(mapToExistingTask(rs));
			}
			
		} catch (Exception e) {
			throw e;
		}
		return taskList;
	}

	@Override
	public void updateTask(int id, NewTask task) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE tasks SET name = ?, has_subtask = ?, deadline = ?, delete_flag = ?, description = ? "
					+ "WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, task.name());
			stmt.setObject(2, task.hasSubtask(), Types.BOOLEAN);
			stmt.setObject(3, task.deadline(), Types.DATE);
			stmt.setObject(4, task.deleteFlag(), Types.BOOLEAN);
			stmt.setString(5, task.description());
			stmt.setObject(6, id, Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;

		}

	}

	public void deleteTask(int id) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM tasks WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setObject(1, id, Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	// ResultSetからExistingTaskオブジェクトへ変換

	private ExistingTask mapToExistingTask(ResultSet rs) throws Exception {
		Integer id = (Integer) rs.getObject("id");
		String name = rs.getString("name");
		boolean hasSubtask = (Boolean) rs.getObject("has_subtask");
		Date deadLine = (Date) rs.getObject("deadline");
		boolean deleteFlag = (Boolean) rs.getObject("delete_flag");
		String description = rs.getString("description");

		return new ExistingTask(id, name, hasSubtask, deadLine, deleteFlag, description);
	}
}
