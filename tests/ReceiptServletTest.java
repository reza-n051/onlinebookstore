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
import servlets.ReceiptServlet;
 
public class ReceiptServletTest{

    private ServletRequest srq ;
    private ServletResponse srp;
    private RequestDispatcher rd;
    //Test 1
    private BuyBook[] input = {
        new BuyBook(),
        new BuyBook(),
        new BuyBook(),
        new BuyBook()
    };
    private String output = "0";
    //Test 2
    // private BuyBook[] input = {
    //     new BuyBook(),
    //     new BuyBook(0),
    //     new BuyBook("pay",2),
    //     new BuyBook()
    // };
    // private String output = "180";
    //Test 3
    // private BuyBook[] input = {
    //     new BuyBook("pay",20),
    //     new BuyBook(),
    //     new BuyBook(0),
    //     new BuyBook()
    // };
    // private String output = "Please Select the Qty less than Available Books Quantity";
    //Test 4
    // private BuyBook[] input = {
    //     new BuyBook(),
    //     new BuyBook("pay",200),
    //     new BuyBook("pay",2),
    //     new BuyBook()
    // };
    // private String output = "Please Select the Qty less than Available Books Quantity";
    //Test 5
    // private BuyBook[] input = {
    //     new BuyBook(),
    //     new BuyBook(),
    //     new BuyBook("pay",2),
    //     new BuyBook("pay",200)
    // };
    // private String output = "Please Select the Qty less than Available Books Quantity";
    //Test 6
    // private BuyBook[] input = {
    //     new BuyBook(20),
    //     new BuyBook(),
    //     new BuyBook("pay",2),
    //     new BuyBook()
    // };
    // private String output = "180";
    //Test 7
    // private BuyBook[] input = {
    //     new BuyBook(),
    //     new BuyBook(),
    //     new BuyBook("pay",2),
    //     new BuyBook(),
    //     new BuyBook()
    // };
    // private String output = "180";
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
        for(int i=0;i<input.length;i++){
            when(srq.getParameter("checked" + Integer.toString(i+1))).thenReturn(input[i].type);
            when(srq.getParameter("qty" + Integer.toString(i+1))).thenReturn(Integer.toString(input[i].quantity));
        }
        when(srq.getRequestDispatcher("ViewBooks.html")).thenReturn(rd);
        try {
            when(srp.getWriter()).thenReturn(pw);   
            ReceiptServlet rs = new ReceiptServlet();   
            rs.service(srq, srp);
            System.out.println(sw.getBuffer().toString().trim());
            assertTrue(sw.getBuffer().toString().trim().contains(output));
        } catch (IOException | ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // assertEquals(20,20,0);
    }
    private class BuyBook{
        public BuyBook(String type, int quantity){
            this.type = type;
            this.quantity = quantity;
        }
        public BuyBook(){
            this.type=null;
            this.quantity = 0;
        }
        public BuyBook(int quantity){
            this.type=null;
            this.quantity = quantity;
        }

        public String type;
        public int quantity;
    }

    private class TestCase{
        public BuyBook[] input;
        public String output;

        public TestCase(BuyBook[] input, String output){
            this.input= input;
            this.output = output;
        }
    }
}
