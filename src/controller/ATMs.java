package controller;

import java.util.concurrent.Semaphore;

public class ATMs extends Thread{
	
	private int transationType;
	private Semaphore getMoney;
	private Semaphore deposit;
	private int balance;
	private int bankAccount;
	private int value;
	
	public ATMs(int transationType, Semaphore getMoney, Semaphore deposit, int balance, int bankAccount, int value) {
		this.transationType = transationType;
		this.getMoney = getMoney;
		this.deposit = deposit;
		this.balance = balance;
		this.bankAccount = bankAccount;
		this.value = value;
	}
	
	private void drawOut() {
		
		if (balance < value) {
			System.out.println("\nNot enough money to drawout " + value + "!" + "\nMoney avaialable: " + balance );
		} else if(balance == value) {
			System.out.println("\nNow you're poor!");
		} else {
			balance -= value;
			System.out.println("\nSaque da conta: " + bankAccount + " efetuado com sucesso. Valor de saque: " + value + " . Valor restante na conta: " + balance  );
		}
	}
	
	private void deposit() {
		
		if (value <= 0) {
			System.out.println("Invalid value!");
		} else {
			balance += value;
			System.out.println("\nDeposited value: " + value + "\nOld balance: " + (balance-value) + "\nNew balance: " + balance);
		}
	}

	@Override
	public void run() {
		try {
			if (transationType == 2) {
				getMoney.acquire();
				drawOut();
			} else {
				deposit.acquire();
				deposit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMoney.release();
			deposit.release();
		}
	}
}

	