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
import servlets.UserRegisterServlet;
import sql.IUserContants;
public class UserRegisterServletTest{

    private ServletRequest srq ;
    private ServletResponse srp;
    private RequestDispatcher rd;
    @BeforeEach
    public void init(){
        srq = mock(ServletRequest.class);
        srp = mock(ServletResponse.class);
        rd = mock(RequestDispatcher.class);
    }
    static String getAlphaNumericString(int n)
    {
  
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }
    @Test
    public void test1(){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(srq.getParameter(IUserContants.COLUMN_PASSWORD)).thenReturn(getAlphaNumericString(10));
        when(srq.getParameter(IUserContants.COLUMN_USERNAME)).thenReturn(getAlphaNumericString(5));
        when(srq.getParameter(IUserContants.COLUMN_FIRSTNAME)).thenReturn("amirhossein");
        when(srq.getParameter(IUserContants.COLUMN_LASTNAME)).thenReturn("torkanloo");
        when(srq.getParameter(IUserContants.COLUMN_ADDRESS)).thenReturn("mashhad");
        when(srq.getParameter(IUserContants.COLUMN_PHONE)).thenReturn("09010896793");
        when(srq.getParameter(IUserContants.COLUMN_MAILID)).thenReturn("torkanlooa@gmail.com");

        when(srq.getRequestDispatcher("Sample.html")).thenReturn(rd);
        try {
            when(srp.getWriter()).thenReturn(pw);   
            UserRegisterServlet abs = new UserRegisterServlet();   
            abs.service(srq, srp);
            System.out.println(sw.getBuffer().toString().trim());
            assertTrue(sw.getBuffer().toString().trim().equals("<h3 class='tab'>User Registered Successfully</h3>"));
        } catch (IOException e) {
        
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ServletException e){
            e.printStackTrace();
        }
        // assertEquals(20,20,0);
    }

    
    @Test
    public void test2(){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(srq.getParameter(IUserContants.COLUMN_PASSWORD)).thenReturn(getAlphaNumericString(10));
        when(srq.getParameter(IUserContants.COLUMN_USERNAME)).thenReturn(getAlphaNumericString(5));
        when(srq.getParameter(IUserContants.COLUMN_FIRSTNAME)).thenReturn("1");
        when(srq.getParameter(IUserContants.COLUMN_LASTNAME)).thenReturn("1");
        when(srq.getParameter(IUserContants.COLUMN_ADDRESS)).thenReturn("1");
        when(srq.getParameter(IUserContants.COLUMN_PHONE)).thenReturn("1");
        when(srq.getParameter(IUserContants.COLUMN_MAILID)).thenReturn("1");

        when(srq.getRequestDispatcher("Sample.html")).thenReturn(rd);
        try {
            when(srp.getWriter()).thenReturn(pw);   
            UserRegisterServlet abs = new UserRegisterServlet();   
            abs.service(srq, srp);
            System.out.println(sw.getBuffer().toString().trim());
            assertTrue(sw.getBuffer().toString().trim().equals("<h3 class='tab'>User Registered Successfully</h3>"));
        } catch (IOException e) {
        
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ServletException e){
            e.printStackTrace();
        }
        // assertEquals(20,20,0);
    }
}
