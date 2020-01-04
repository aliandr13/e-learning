package by.it.academy.elearning.web.tags.custom;

import by.it.academy.elearning.model.User;
import by.it.academy.elearning.web.dto.UserAccount;
import by.it.academy.elearning.web.util.SessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.ConditionalTagSupport;
import java.util.Optional;

public class AuthTag extends ConditionalTagSupport {

    private String path = "";

    @Override
    protected boolean condition() {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpSession session = request.getSession();
        Optional<UserAccount> user = SessionUtils.getUserAccount(request);

        return user.isPresent() && (
                user.get().getRole().equals("ADMIN")
        );
    }

    public void setPath(String path) {
        this.path = path;
    }
}
