import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import servlets.RemoveBookServlet;
public class RemoveBookServletTest{

    private ServletRequest srq ;
    private ServletResponse srp;
    private RequestDispatcher rd;
    // private String input = "10103";
    // private String output = "Book Removed Successfully";
    private String input = "FFF";
    private String output = "Book Not Available In The Store";
    @BeforeEach
    public void init(){
        srq = mock(ServletRequest.class);
        srp = mock(ServletResponse.class);
        rd = mock(RequestDispatcher.class);
    }

    @Test
    public void testService(){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(srq.getParameter("barcode")).thenReturn(input);
        when(srq.getRequestDispatcher("Sample.html")).thenReturn(rd);
        try {
            when(srp.getWriter()).thenReturn(pw);   
            RemoveBookServlet rbs = new RemoveBookServlet();   
            rbs.service(srq, srp);
            System.out.println(sw.getBuffer().toString().trim());
            assertTrue(sw.getBuffer().toString().trim().contains(output));
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

}
