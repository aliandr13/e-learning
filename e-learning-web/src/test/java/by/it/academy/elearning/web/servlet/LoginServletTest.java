package by.it.academy.elearning.web.servlet;

import by.it.academy.elearning.model.User;
import by.it.academy.elearning.service.UserService;
import by.it.academy.elearning.web.dto.UserAccount;
import by.it.academy.elearning.web.util.SessionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest {

    public static final String USER_NAME = "TEST_USER_NAME";
    public static final String USER_PASSWORD = "TEST_USER_PASSWORD";
    public static final long USER_ID = 1001L;
    public static final String USER_ROLE = "USER";
    public static final String CONTEXT_PATH = "";
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private UserService userService;
    @Mock
    private HttpSession session;

    private LoginServlet loginServlet;

    @Before
    public void setUp() {
        Mockito.when(request.getRequestDispatcher(LoginServlet.LOGIN_JSP)).thenReturn(dispatcher);
        loginServlet = new LoginServlet(userService);
    }

    @Test
    public void testDoGetShouldForwardToLoginJSP() throws Exception {
        // when
        loginServlet.doGet(request, response);

        // then
        Mockito.verify(dispatcher).forward(request, response);
    }

    @Test
    public void ShouldSetErrorMessageInRequestAndForward() throws Exception {
        // when
        loginServlet.doPost(request, response);

        // then
        Mockito.verify(request).setAttribute(LoginServlet.ERROR_STRING_ATTR_NAME, LoginServlet.SHOULD_NOT_BE_EMPTY);
        Mockito.verify(request).setAttribute(LoginServlet.USER_ATTR_NAME, null);
        Mockito.verify(dispatcher).forward(request, response);
    }

    @Test
    public void ShouldSetUserToSessionAndForwardToHome() throws Exception {
        // given
        Mockito.when(request.getParameter(LoginServlet.USER_ATTR_NAME)).thenReturn(USER_NAME);
        Mockito.when(request.getParameter(LoginServlet.PASSWORD)).thenReturn(USER_PASSWORD);
        User expectedUser = new User(USER_ID, USER_NAME, USER_PASSWORD, USER_ROLE);
        Mockito.when(userService.findUserByLoginAndPassword(USER_NAME, USER_PASSWORD)).thenReturn(Optional.of(expectedUser));
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getContextPath()).thenReturn(CONTEXT_PATH);

        // when
        loginServlet.doPost(request, response);

        // then
        Mockito.verify(session).setAttribute(SessionUtils.SESSION_ATTR_NAME, new UserAccount(expectedUser));
        Mockito.verify(dispatcher, Mockito.never()).forward(request, response);
        Mockito.verify(response).sendRedirect(CONTEXT_PATH + LoginServlet.HOME);
    }
}
