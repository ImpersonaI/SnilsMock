package org.lanit.modelsJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SnilsRequest {

	@JsonProperty("snils")
	private String snils;
	private String numbers = "";
	private int checkSumm;

	public SnilsRequest() {

	}

	public SnilsRequest(String snils) {
		setSnils(snils);
		setNumbers(snils);
		setCheckSumm(snils);
	}

	public int getCheckSumm() {
		return checkSumm;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setCheckSumm(String checkSumm) throws IndexOutOfBoundsException {

		this.checkSumm = Integer.parseInt(getSnils().substring(12, 14));

	}

	public void setNumbers(String numbers) {

		for (int i = 0; i <= getSnils().length() - 3; i++) {

			char symbol = getSnils().charAt(i);
			if (Character.isDigit(symbol)) {

				this.numbers += symbol;

			}
		}
	}

	public void setSnils(String snils){
		this.snils = snils;
	}

	public String getSnils(){
		return snils;
	}
}