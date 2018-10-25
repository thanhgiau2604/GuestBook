package wp.servlet;
import wp.model.GuestBookEntry;  
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns="/Guest_Book",loadOnStartup=1)
public class Guest_Book extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Guest_Book() {
        super();

    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	    List<GuestBookEntry> entries = new ArrayList<GuestBookEntry>();
	    entries.add(new GuestBookEntry("Jenny","Hello everyone")); 
	    entries.add(new GuestBookEntry("Nobita","Nice to meet you")); 
	    getServletContext().setAttribute("entries",entries);
	  	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GuestBookEntry> entries = (List<GuestBookEntry>)getServletContext().getAttribute("entries");
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/html");
		out.println("<html><head><title>GuestBook</title></head><body>");
		out.println("<h2>GuestBook</h2>");
		out.println("<table border='1'");
		out.println("<tr><th>Name</th><th>Message</th></tr>");
		for(int i=0; i<entries.size(); i++)
		{
			GuestBookEntry entry = entries.get(i); 
			out.println("<tr><td>"+entry.getName()+"says</td><td>"+entry.getMessage()+"</td><td><a href='EditEntry?index="+i+
					"'>Edit</a></td><td><a href='DeleteEntry?index="+i+"'>Delete</a></td></tr>");
		}
			out.println("</table>");
			out.println("<p><a href='AddComment'>Leave a comment</a></p>");
			out.println("</body></html>");
		       }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
