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
import servlets.AddBookServlet;
import sql.IBookConstants;
public class AddBookServletTest{

    private ServletRequest srq ;
    private ServletResponse srp;
    private RequestDispatcher rd;
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
        
        when(srq.getParameter(IBookConstants.COLUMN_NAME)).thenReturn("arg0");
        when(srq.getParameter(IBookConstants.COLUMN_BARCODE)).thenReturn("nj00hg");
        when(srq.getParameter(IBookConstants.COLUMN_PRICE)).thenReturn("100");
        when(srq.getParameter(IBookConstants.COLUMN_QUANTITY)).thenReturn("2");
        when(srq.getParameter(IBookConstants.COLUMN_AUTHOR)).thenReturn("A");
        when(srq.getRequestDispatcher("AddBook.html")).thenReturn(rd);
        try {
            when(srp.getWriter()).thenReturn(pw);   
            AddBookServlet abs = new AddBookServlet();   
            abs.service(srq, srp);
            System.out.println(sw.getBuffer().toString().trim());
            assertTrue(sw.getBuffer().toString().trim().equals("<div class=\"tab\">Book Detail Updated Successfully!<br/>Add More Books</div>"));
        } catch (IOException | ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // assertEquals(20,20,0);
    }

}
