package com.d3e.profitloss.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	  String greetServer(String ex_name, int ex_qty, double ex_price, String in_name, int in_qty, double in_price, String input) throws IllegalArgumentException;
		// TODO Auto-generated method stub
		
	
}
