import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import servlets.BuyBooksServlet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class BuyBookServletTest {
    private ServletRequest sreq;
    private ServletResponse srep;
    private RequestDispatcher rd;
    private Book[] output = {
        new Book("10101","Programming in C","James k Wick",500,10),
        new Book("10102","Learn Java","Scott Mayers",150,7),
        new Book("10104","Let us c++","Steve Macclen",90,40),
        new Book("10105","Success Key","Shashi Raj",5000,9)
    };
    
    @BeforeEach
    public void init(){
        sreq = mock(ServletRequest.class);
        srep = mock(ServletResponse.class);
        rd = mock(RequestDispatcher.class);
    }

    @Test
    public void testService(){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            when(sreq.getRequestDispatcher("ViewBooks.html")).thenReturn(rd);
            when(srep.getWriter()).thenReturn(pw);
            BuyBooksServlet vbs = new BuyBooksServlet();
            vbs.service(sreq, srep);
            String out = sw.getBuffer().toString().trim();
            Book[] bookOut = extractInfo(out);
            assertEquals(output.length, bookOut.length);
            for(int i=0;i<bookOut.length;i++){
                assertEquals(output[i].name, bookOut[i].name);
                assertEquals(output[i].author, bookOut[i].author);
                assertEquals(output[i].code, bookOut[i].code);
                assertEquals(output[i].price, bookOut[i].price);
                assertEquals(output[i].quantity, bookOut[i].quantity);
            }
        } catch (IOException | ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Finish !!!!!!!!");
        
    }

    private class Book{
        public Book(String code,String name,String author,int price,int quantity){
            this.price = price;
            this.author = author;
            this.code= code;
            this.name= name;
            this.quantity = quantity;
        }
        public String code;
        public String name;
        public String author;
        public int price;
        public int quantity;
    }

    private Book[] extractInfo(String str){
    
        String[] strs = str.split("<tr>");
        Book[] books = new Book[strs.length-2];
        for(int i=2;i<strs.length;i++){
            String row = strs[i].substring(0, strs[i].lastIndexOf("</tr>"));
            String code = row.split("</td>")[1].substring(6);
            String name = row.split("</td>")[2].substring(6);
            String author = row.split("</td>")[3].substring(6);
            String price = row.split("</td>")[4].substring(6);
            String qty = row.split("</td>")[5].substring(6);
            books[i-2] = new Book(code,name,author,Integer.parseInt(price),Integer.parseInt(qty));
        }
        return books;
    }
}
