package by.it.academy.elearning.service.impl;

import by.it.academy.elearning.dao.GroupDao;
import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    @Autowired
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Group create(Group group) {
        log.info("Creating group {}", group);
        Group saved = groupDao.create(group);
        log.debug("Create group result {}", saved);
        return saved;
    }

    @Override
    public Optional<Group> findById(Number id) {
        log.info("Find by id {}", id);
        Optional<Group> group = groupDao.find(id);
        log.debug("Find by id result {}", group);
        return group;
    }

    @Override
    public void delete(Group group) {
        log.info("delete group {}", group);
        groupDao.delete(group.getId());
    }

    @Override
    public List<Group> findAll() {
        log.info("Get all groups");
        List<Group> groups = groupDao.findAll();
        log.debug("find all result: {}", groups);
        return groups;
    }
}
