package org.lanit.validate;

public class CheckSnils {

    private boolean isValid;

    public boolean getIsValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void checkSnils(String numbers, int checkSumm)  {
        int temp = 0;
        int summ = 0;

        //checking snils based on the algorithm for check summ
        for (int i = 0; i < numbers.length(); i++) {

            char symbol = numbers.charAt(i);
            temp = numbers.length()  - i;
            summ += Character.getNumericValue(symbol) * (temp);

        }

        if (summ % 101 == checkSumm) {
            setValid(true);
        } else setValid(false);

    }
}
