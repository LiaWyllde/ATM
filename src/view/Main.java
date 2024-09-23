package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.ATMs;


public class Main {

	public static void main(String[] args) {
		
		Random rand = new Random();
		Semaphore getMoney = new Semaphore(1);
		Semaphore deposit = new Semaphore(1);
		
		for (int i = 0; i < 20; i++) {
			
			int transationType = rand.nextInt(1,3);
			int balance = rand.nextInt(0,90001); 
			int code = rand.nextInt(100,12500);
			int value = rand.nextInt(0,100000);
			
			ATMs atm = new ATMs(transationType, getMoney, deposit, balance, code, value);
			atm.start();
		}

	}
}
