package com.thelkl;

import java.util.Comparator;

public class TaskReportComperator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        if (o1.getTimeFromZero() < o2.getTimeFromZero())
            return -1;
        else if (o1.getRequired() == o2.getRequired()) {
            return Integer.compare(o1.getId(), o2.getId());
        } else
            return 1;
    }
}
