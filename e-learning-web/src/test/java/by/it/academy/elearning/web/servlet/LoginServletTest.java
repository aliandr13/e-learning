package by.it.academy.elearning.web.servlet;

import by.it.academy.elearning.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private UserService userService;

    @Test
    public void ShouldSetErrorMessageInRequestAndForward() throws Exception {
        // given
        Mockito.when(request.getRequestDispatcher(LoginServlet.LOGIN_JSP)).thenReturn(dispatcher);
        LoginServlet loginServlet = new LoginServlet(userService);

        // when
        loginServlet.doPost(request, response);

        // then
        Mockito.verify(request).setAttribute(LoginServlet.ERROR_STRING_ATTR_NAME, LoginServlet.SHOULD_NOT_BE_EMPTY);
        Mockito.verify(request).setAttribute(LoginServlet.USER_ATTR_NAME, null);
        Mockito.verify(dispatcher).forward(request, response);
    }
}
