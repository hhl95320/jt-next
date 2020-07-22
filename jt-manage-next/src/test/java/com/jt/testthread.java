package com.jt;

import java.util.concurrent.locks.ReentrantLock;

import com.jt.testItem.inter;

public class testthread {

	public static void main(String[] args) {
		thread thread = new thread();
		
		Thread thread1 = new Thread(thread);
		Thread thread2 = new Thread(thread);
	
		thread1.setName("thread1");
		thread2.setName("thread2");
		thread1.start();
		thread2.start();
	
	}
	
}
class thread implements Runnable{
	ReentrantLock ulock=new ReentrantLock();
	ReentrantLock lock=new ReentrantLock(true);
	int stack=1000;
	@Override
	public void run() {
		
		
		
		while( stack >0) {
			try {
				Thread.sleep(5);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(lock.isLocked())
				try {
					lock.lockInterruptibly();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		lock.lock();
			//synchronized (thread.class) {
				
			
//			Thread thread = Thread.currentThread();
//			System.out.println(thread.getId()+"|"+thread.getName()+"票数"+(stack--));
//			try {
//				thread.sleep(5);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			if(stack>0) {
				Thread thread = Thread.currentThread();
				System.out.println(thread.getId()+"|"+thread.getName()+"票数"+(stack--));
				try {
					thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//}
			lock.unlock();
		}
		
	}
	
}