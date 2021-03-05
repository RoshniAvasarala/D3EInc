package com.d3e.profitloss.client;

import javax.persistence.Entity;

import com.d3e.profitloss.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class ProfitLoss implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		final Button sendButton = new Button("Profit/Loss");
		final TextBox invoice_name = new TextBox();
		final TextBox invoice_qty = new TextBox();
		final TextBox invoice_price = new TextBox();
		final TextBox expense_name = new TextBox();
		final TextBox expense_qty = new TextBox();
		final TextBox expense_price = new TextBox();
		
		
		final Label errormsg = new Label();
		final Label Invoice_Total = new Label();
		final Label Expense_Total = new Label();
		final TextBox nameField = new TextBox();
		
		
		
		
		RootPanel.get("error").add(errormsg);
		RootPanel.get("invoice_name").add(invoice_name);
		RootPanel.get("invoice_qty").add(invoice_qty);
		RootPanel.get("invoice_price").add(invoice_price);
		RootPanel.get("invoice_total").add(Invoice_Total);
		RootPanel.get("expense_name").add(expense_name);
		RootPanel.get("expense_qty").add(expense_qty);
		RootPanel.get("expense_price").add(expense_price);
		RootPanel.get("expense_total").add(Expense_Total);
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		
		invoice_name.setFocus(true);
		
		nameField.setText("Enter your name");
		
		invoice_price.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				String inprice = invoice_price.getText();
				if(!inprice.matches("[0-9]*")) {
					errormsg.setText("Enter valid price");
					invoice_price.setText("");
					
					return;
				}
				errormsg.setText("");
				
				expense_name.setText(invoice_name.getText());
				expense_qty.setText(invoice_qty.getText());
				Invoice_Total.setText(""+Integer.parseInt(invoice_qty.getText())* Integer.parseInt(invoice_price.getText())+ "$");
				if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					expense_price.setFocus(true);
				}
				
			}
			
			
		});
		
		expense_price.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				String exprice = expense_price.getText();
				Expense_Total.setText(""+Integer.parseInt(expense_qty.getText()) * Integer.parseInt(expense_price.getText())+"$");
				if(!exprice.matches("[0-9]*")) {
					errormsg.setText("Enter valid price");
					expense_price.setText("");
					return;
				}
				errormsg.setText("");
				if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					nameField.setFocus(true);
					nameField.selectAll();
				}
			}
		});
		invoice_qty.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				String qty = invoice_qty.getText();
					if(!qty.matches("[0-9]*")) {
						errormsg.setText("Enter a valid quantity");
						invoice_qty.setText("");
						return;
				}
					errormsg.setText("");
				
				
			}
			
		});
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Saving Billing Info");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML productname = new HTML();
		final HTML unit = new HTML();
		final HTML exprice = new HTML();
		final HTML inprice = new HTML();
		final HTML totalbill = new HTML();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Customer Name</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br>Purchase details<b></b>"));
		dialogVPanel.add(new HTML("<br>Product Name"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.add(new HTML("<br>Unit"));
		dialogVPanel.add(unit);
		dialogVPanel.add(new HTML("<br>Expense Price"));
		dialogVPanel.add(exprice);
		dialogVPanel.add(new HTML("<br>Invoice Price"));
		dialogVPanel.add(inprice);
				
		dialogVPanel.add(new HTML("<br>Profit/Loss<b></b>"));
		dialogVPanel.add(totalbill);
		
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				
				invoice_name.setText("");
				invoice_qty.setText("");
				invoice_price.setText("");
				expense_name.setText("");
				expense_qty.setText("");
				expense_price.setText("");
				Invoice_Total.setText("");
				Expense_Total.setText("");
				nameField.setText("");
				invoice_name.setFocus(true);
			}
		});
		
class MyHandler implements ClickHandler, KeyUpHandler {
			
			public void onClick(ClickEvent event) {
				sendNameToServer();
				
				
				
				
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			private void sendNameToServer() {
				// TODO Auto-generated method stub
				String texttoserver = nameField.getText();
				textToServerLabel.setText("Hi "+texttoserver);
				
				
				String In_name = invoice_name.getText();
				int  In_qty = Integer.parseInt(invoice_qty.getText());
				Double In_price = Double.parseDouble(invoice_price.getText()) ;
				String Ex_name = expense_name.getText();
				int Ex_qty = Integer.parseInt(expense_qty.getText());
				Double Ex_price = Double.parseDouble(expense_price.getText());
				
				sendButton.setEnabled(false);
				
				productname.setText(In_name);
				unit.setText(""+In_qty);
				exprice.setText(""+Ex_price);
				inprice.setText(""+In_price);
				Double total = (Double.parseDouble(invoice_price.getText())*Double.parseDouble(invoice_qty.getText())) - (Double.parseDouble(expense_price.getText())* Double.parseDouble(expense_qty.getText()));
				totalbill.setText(total + " is the difference in amount");
				
				serverResponseLabel.setText("");
				
				greetingService.greetServer(Ex_name, Ex_qty, Ex_price, In_name, In_qty, In_price, texttoserver, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						dialogBox.setText("Saving Bill - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						dialogBox.setText("Saving Info");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
					
				
			}
		
		
			
		
		
		
	}
		

		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
		
		
	
	}
}

