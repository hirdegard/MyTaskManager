package dao;

import java.util.List;

import domain.ExistingTask;
import domain.NewTask;

public interface TaskDao {

	void createTask (NewTask task) throws Exception;
	ExistingTask getTaskById (Integer id) throws Exception ;
	List<ExistingTask> getAllTasks() throws Exception;
	void updateTask (int id, NewTask task) throws Exception;
	void deleteTask(int id) throws Exception;
}
