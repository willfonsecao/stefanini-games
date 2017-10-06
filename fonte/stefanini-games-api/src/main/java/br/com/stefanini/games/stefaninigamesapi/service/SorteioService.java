package br.com.stefanini.games.stefaninigamesapi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SorteioService {

	public static <T> List<T> sortear(List<T> list) {
		List<T> listCopy = new ArrayList<>(list);
		int listSize = listCopy.size();
		for (int i = 0; i < listSize - 1; i++) {
			int rand = getRandomEntre(i + 1, listSize - 1);
			Collections.swap(listCopy, i, rand);
		}
		return listCopy;
	}

	private static int getRandomEntre(int num1, int num2) {
		if (num2 >= num1) {
			return (int) Math.floor((Math.random() * (num2 - num1 + 1)) + num1);
		} else {
			return (int) Math.floor((Math.random() * (num1 - num2 + 1)) + num2);
		}
	}
}
