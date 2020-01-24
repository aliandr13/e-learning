package by.it.academy.elearning.service;

import by.it.academy.elearning.model.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAll();

    Group create(Group group);

    void delete(Long id);
}
