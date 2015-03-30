package com.sistem.dagitik;

public class ThreadRunnable implements Runnable {
	
	public matris matrix;
	
	public ThreadRunnable(matris m) {
		this.matrix = m;
	}
		
	@Override
	public void run() {
		try {
			matrix.dagitikSistem();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
