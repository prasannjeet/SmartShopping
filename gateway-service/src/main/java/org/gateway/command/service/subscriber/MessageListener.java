package org.gateway.command.service.subscriber;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class MessageListener<T>{
	Semaphore semaphore = new Semaphore(0);
	T message = null;
	
	public void ReceiveEvent(T message){
		this.message = message;
		semaphore.release();
	}
	
	public T WaitForMessage(int timeout) throws Exception{
		boolean receiveInTime = semaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS);
		if (!receiveInTime)
			throw new Exception("Timeout: Message not received!");
		else
			return message;
	}
}
