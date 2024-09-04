package domain;

import java.util.Date;

public record NewTask(String name, Boolean hasSubtask, Date deadline, Boolean deleteFlag, String description) {

}
