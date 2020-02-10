package by.it.academy.elearning.dao;

import by.it.academy.elearning.dao.DAO;
import by.it.academy.elearning.model.Role;
import by.it.academy.elearning.model.RoleEnum;

public interface RoleDao extends DAO<Role> {

    Role getByName(RoleEnum name);

}
