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
    // private String[] inputs = {"bookName","BarCodeee","100","2","A"};
    // private String output = "<div class=\"tab\">Book Detail Updated Successfully!<br/>Add More Books</div>";
    private String[] inputs = {"bookName","abc","10s0","2","A"};
    private String output = "";
    // private String[] inputs = {"bookName","BarCodeee","100","2","A"};
    // private String output = "<div class=\"tab\">Failed to Add Books! Fill up CareFully</div>";
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
        when(srq.getParameter(IBookConstants.COLUMN_NAME)).thenReturn(inputs[0]);
        when(srq.getParameter(IBookConstants.COLUMN_BARCODE)).thenReturn(inputs[1]);
        when(srq.getParameter(IBookConstants.COLUMN_PRICE)).thenReturn(inputs[2]);
        when(srq.getParameter(IBookConstants.COLUMN_QUANTITY)).thenReturn(inputs[3]);
        when(srq.getParameter(IBookConstants.COLUMN_AUTHOR)).thenReturn(inputs[4]);
        when(srq.getRequestDispatcher("AddBook.html")).thenReturn(rd);
        try {
            when(srp.getWriter()).thenReturn(pw);   
            AddBookServlet abs = new AddBookServlet();   
            abs.service(srq, srp);
            System.out.println(sw.getBuffer().toString().trim());
            assertTrue(sw.getBuffer().toString().trim().equals(output));
        } catch (IOException | ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // assertEquals(20,20,0);
    }

}
