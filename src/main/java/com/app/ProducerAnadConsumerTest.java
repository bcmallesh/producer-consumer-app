package com.app;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerAnadConsumerTest {

	public static void main(String[] args) {
		BlockingQueue<AssetParameter> buffer = new LinkedBlockingQueue<AssetParameter>(); 
		int maxSize = 10; 
		Thread producer = new AppProducer(buffer, maxSize, "Asset AppProducer");
		Thread consumer = new AppConsumer(buffer,maxSize , "Asset AppConsumer");
		producer.start(); 
		consumer.start();
	}

}
