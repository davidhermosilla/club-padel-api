package com.club.padel.exception.model;

import java.util.ArrayList;
import java.util.List;

public class MSErrorResponse {

	private List<MSMessage> messages;

	private MSError error;

	public List<MSMessage> getMessages() {
		return messages;
	}


	public void setMessage(MSMessage MSErrorMessage) {
		this.messages = new ArrayList<MSMessage>();
		this.messages.add(MSErrorMessage);
	}

	public void setMessages(List<MSMessage> messages) {
		this.messages = messages;
	}

	public MSError getError() {
		return error;
	}

	public void setError(MSError error) {
		this.error = error;
	}
	
	
}
