package com.d3e.profitloss.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String ex_name, int ex_qty, double ex_price, String in_name, int in_qty, double in_price,
			String input, AsyncCallback<String> callback);
}
