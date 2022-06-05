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
import servlets.AdminLoginServlet;
import sql.IUserContants;
public class AdminLoginServletTest{

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
    public void test1(){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(srq.getParameter(IUserContants.COLUMN_USERNAME)).thenReturn("Admin");
        when(srq.getParameter(IUserContants.COLUMN_PASSWORD)).thenReturn("Admin");
        when(srq.getRequestDispatcher("Sample.html")).thenReturn(rd);
        try {
            when(srp.getWriter()).thenReturn(pw);   
            AdminLoginServlet abs = new AdminLoginServlet();   
            abs.service(srq, srp);
            System.out.println(sw.getBuffer().toString().trim());
            assertTrue(sw.getBuffer().toString().trim().contains("Admin login Successful"));
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
        when(srq.getParameter(IUserContants.COLUMN_USERNAME)).thenReturn("arg0");
        when(srq.getParameter(IUserContants.COLUMN_PASSWORD)).thenReturn("nj00hg");
        when(srq.getRequestDispatcher("AdminLogin.html")).thenReturn(rd);
        try {
            when(srp.getWriter()).thenReturn(pw);   
            AdminLoginServlet abs = new AdminLoginServlet();   
            abs.service(srq, srp);
            System.out.println(sw.getBuffer().toString().trim());
            assertTrue(sw.getBuffer().toString().trim().contains("Admin login Successful"));
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
    public void test3(){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(srq.getParameter(IUserContants.COLUMN_USERNAME)).thenReturn("45");
        when(srq.getParameter(IUserContants.COLUMN_PASSWORD)).thenReturn("45");
        when(srq.getRequestDispatcher("AdminLogin.html")).thenReturn(rd);
        try {
            when(srp.getWriter()).thenReturn(pw);   
            AdminLoginServlet abs = new AdminLoginServlet();   
            abs.service(srq, srp);
            System.out.println(sw.getBuffer().toString().trim());
            assertTrue(sw.getBuffer().toString().trim().contains("Admin login Successful"));
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
