package sample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LifeCycle() {
        super();
        System.out.println("LifeCycle 실행..");
    }
    
	@Override
	public void init() throws ServletException {
		System.out.println("init() 호출됨...");
	}

	@Override
	public void destroy() {
		System.out.println("destroy() 호출됨...");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() 호출됨");
	}
}
