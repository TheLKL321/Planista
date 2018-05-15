package com.thelkl;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        if (o1.getRequired() < o2.getRequired()) return -1;
        else if (o1.getRequired() == o2.getRequired()) {
            if (o1.getId() < o2.getId()) return -1;
            else if (o1.getId() == o2.getId()) return 0;
            else return 1;
        } else return 1;
    }
}
