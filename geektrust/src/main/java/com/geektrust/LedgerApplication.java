package com.geektrust;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LedgerApplication {

	public static void main(String[] args) throws Exception {
		LoanOperation loanOperation = new LoanOperation();
		String filePath = args[0];
		List<String> dataFromFile = getDataFromFile(filePath);
		for (String line : dataFromFile)
		{
			loanOperation.performOperation(line);
		}

		for(String balance : loanOperation.getBalances())
		{
			System.out.println(balance);
		}
	}
	private static List <String> getDataFromFile(String fileName) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(fileName));
		return lines;
	}

}
