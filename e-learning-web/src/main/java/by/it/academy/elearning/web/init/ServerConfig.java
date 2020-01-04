package by.it.academy.elearning.web.init;

import by.it.academy.elearning.dao.GroupDao;
import by.it.academy.elearning.dao.StudentDao;
import by.it.academy.elearning.dao.impl.GroupDaoImpl;
import by.it.academy.elearning.dao.impl.StudentDaoImpl;
import by.it.academy.elearning.service.GroupService;
import by.it.academy.elearning.service.StudentService;
import by.it.academy.elearning.service.impl.GroupServiceImpl;
import by.it.academy.elearning.service.impl.StudentServiceImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ServerConfig {

    private static GroupService groupService;
    private static StudentService studentService;
    private static GroupDao groupDao;
    private static StudentDao studentDao;

    private static final Map<Class<?>, Object> LOCKS = new ConcurrentHashMap<>();


    public static GroupService getGroupService() {
        if (groupService == null) {
            synchronized (LOCKS.computeIfAbsent(GroupService.class, k -> new Object())) {
                if (groupService == null) groupService = new GroupServiceImpl(getGroupDao());
            }
        }
        return groupService;
    }

    public static GroupDao getGroupDao() {
        if (groupDao == null) {
            synchronized (LOCKS.computeIfAbsent(GroupDao.class, k -> new Object())) {
                if (groupDao == null) groupDao = new GroupDaoImpl();
            }
        }
        return groupDao;
    }


    public static StudentService getStudentService() {
        if (studentService == null) {
            synchronized (LOCKS.computeIfAbsent(StudentService.class, k -> new Object())) {
                if (studentService == null) studentService = new StudentServiceImpl(getStudentDao());
            }
        }
        return studentService;
    }

    public static StudentDao getStudentDao() {
        if (studentDao == null) {
            synchronized (LOCKS.computeIfAbsent(StudentDao.class, k -> new Object())) {
                if (studentDao == null) studentDao = new StudentDaoImpl();
            }
        }
        return studentDao;
    }
}
