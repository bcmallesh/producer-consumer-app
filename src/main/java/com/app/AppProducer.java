package com.app;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class AppProducer extends Thread {
	private BlockingQueue<AssetParameter> queue;
	private int maxSize;
	private String name;

	public AppProducer(BlockingQueue<AssetParameter> queue, int maxSize, String name) {
		super(name);
		this.queue = queue;
		this.maxSize = maxSize;
		this.name=name;
	}

	public void run() {
		while (true) {
			synchronized (queue) {
				while (queue.size() == maxSize) {
					try {
						System.out.println(name+" waiting for producing values");

						queue.wait(20000);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				Random random = new Random();
				int i = random.nextInt();
				AssetParameter ap = new AssetParameter();
				ap.setParamName("Asset " + i);
				ap.setParamValue(i);
				try {
					System.out.println(name+" :: " + ap.getParamName()+" :: "+ap.getParamValue());

					queue.put(ap);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				queue.notifyAll();
			}
		}

	}
}
