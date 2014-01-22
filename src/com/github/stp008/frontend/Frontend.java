package com.github.stp008.frontend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;

import com.github.stp008.accountService.AccountService;
import com.github.stp008.html.pages.HTMLPageImplJava8Features;
import com.github.stp008.html.pages.HTMLPageImplId;
import com.github.stp008.html.pages.HTMLPageImplJava8;
import com.github.stp008.messageSystem.Abonent;
import com.github.stp008.messageSystem.Address;
import com.github.stp008.messageSystem.MessageSystem;
import com.github.stp008.messageSystem.messages.MessageGetUserId;
import com.github.stp008.utils.ThreadSleep;
 
public class Frontend extends AbstractHandler implements Runnable, Abonent
{
    /* (non-Javadoc)
     * @see org.eclipse.jetty.server.Handler#handle(java.lang.String, org.eclipse.jetty.server.Request, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
	private static AtomicInteger newId = new AtomicInteger();
	private AtomicInteger handleCount = new AtomicInteger();
	private Logger log = Logger.getLogger(Frontend.class.getCanonicalName());
	private HttpSession session;
	private Address address;
	private MessageSystem MS;
	private Map<String, Integer> innerID = new HashMap<String, Integer>();
	
	
	public Frontend(MessageSystem MS){
		this.address = new Address();
		this.MS = MS;
		this.MS.addService(this);
	}
	
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        handleCount.incrementAndGet();
        
        session = request.getSession();
        
        if (request.getPathInfo().equals("/java8features")) {
        	response.getWriter().println(new HTMLPageImplJava8Features().getPage());
        	response.flushBuffer();
            return;
        } 
        
        if(request.getPathInfo().equals("/auth")){        
        	String name = request.getParameter("name");   
        	Integer id = innerID.get(name);        	
        	if (id == null) {
        		MS.sendMessage(new MessageGetUserId(this.getAddress(), 
        									MS.getAddressService().getAddress(AccountService.class), 
    										name));
        		response.getWriter().println("Пожалуйста, подождите. Происходит авторизация.");
        	} else if (id == -1){        		
        		response.getWriter().println("Извините, но вам отказано в доступе.");    
        		innerID.remove(name);
        	} else {
        		response.getWriter().println("Авторизация проведена успешно. Ваш id:" + innerID.get(name) + "\r\n Ваше имя: " + name);
        	}                         	
        	response.flushBuffer();
        	return;
        }
        
        if (request.getPathInfo().equals("/id")) {    
        	Integer sessionId = (Integer)session.getAttribute("id");
        	 if (sessionId == null)  {
         		session.setAttribute("id", newId.getAndIncrement());       		
         	}  
        	response.getWriter().println(new HTMLPageImplId().getPage((int)session.getAttribute("id")));
        	response.flushBuffer();
            return;
        }       
       
    	response.getWriter().println(new HTMLPageImplJava8().getPage());
    	response.flushBuffer();
    	
 	
    }
 
    /**
     * @param args
     * @throws Exception
     */

	@Override
	public void run() {
		while (true){
			log.info(this.handleCount.toString());
			MS.execForAbonent(this);
			ThreadSleep.sleep(1000);						
		}
	}

	public void updateUserId(String name, int userId) {
		innerID.put(name, userId);
	}

	@Override
	public Address getAddress() {
		return address;
	}
	
	 public static void main(String[] args) throws Exception
	    {
	        Server server = new Server(8080);
	        MessageSystem ms = new MessageSystem();
	        AccountService as = new AccountService(ms);	   
	        Frontend frontend = new Frontend(ms);
	        
       
	        new Thread(as).start();
	        new Thread(frontend).start();
	        
	        HashSessionIdManager idmanager = new HashSessionIdManager();
	        server.setSessionIdManager(idmanager);
	        
	        ContextHandler context = new ContextHandler("/");
	        server.setHandler(context);
	        
	        HashSessionManager manager = new HashSessionManager();
	        SessionHandler sessions = new SessionHandler(manager);
	        
	        context.setHandler(sessions);      
	        sessions.setHandler(frontend);
	        	        
	                
	        server.start();
	        server.join();
	        System.out.println();
	            
	    }
	 
}