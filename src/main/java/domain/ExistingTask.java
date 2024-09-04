package domain;

import java.util.Date;

public record ExistingTask(Integer id, String name, boolean hasSubtask,
		Date deadline,
		boolean delete_flag, String description) {

}
