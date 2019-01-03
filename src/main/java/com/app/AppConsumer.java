package com.app;

import java.util.concurrent.BlockingQueue;

public class AppConsumer extends Thread {
	private BlockingQueue<AssetParameter> queue;
	private String name;

	public AppConsumer(BlockingQueue<AssetParameter> queue, int maxSize, String name) {
		super(name);
		this.queue = queue;
		this.name = name;

	}

	@Override
	public void run() {
		while (true) {
			synchronized (queue) {
				while (queue.isEmpty()) {

					try {
						System.out.println(name+" waiting for consuming values");
						queue.wait(20000);

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				try {
					AssetParameter ap=queue.take();
					System.out.println(name+" :: " + ap.getParamName()+" :: "+ap.getParamValue());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				queue.notifyAll();
			}
		}
	}
}
