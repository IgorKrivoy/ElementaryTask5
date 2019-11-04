package task.service;

import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Service {

    String[] num_1_9 = {"один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
    String form_11_19 = "надцать";
    String[] num_10_90 = {"десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят",
            "семьдесят", "восемьдесят", "девяносто"};
    String[] num_100_900 = {"сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот",
            "восемьсот", "девятьсот"};
    String[] thousandForms = {"тысяча", "тысячи", "тысяч"};

    public List<Integer> parseToIntegerArray(int number) {
        List<Integer> listNumbers = new ArrayList<>();
        while (number != 0) {
            listNumbers.add(number % 10);
            number /= 10;
        }
        return listNumbers;
    }

    @Nullable
    public String generationNumberWord(List<Integer> numbers) {
        if (numbers.size() == 1) {
            return num_1_9[numbers.get(0) - 1];
        } else if (numbers.size() == 2) {
            return getFormDecimal(numbers.get(1), numbers.get(0));
        } else if (numbers.size() == 3) {
            return getHundreds(numbers);
        } else if (numbers.size() > 3 && numbers.size() < 7) {
            return getThousand(numbers);
        }
        return "";
    }

    public String getFormDecimal(int numberFirst, int numberSecond) {

        if (numberSecond == 0) {
            for (int i = 1; i <= 9; i++) {
                if (i == numberFirst) {
                    return num_10_90[i - 1];
                }
            }
        } else if (numberFirst == 1) {
            for (int i = 1; i <= 9; i++) {
                if (i == numberSecond) {
                    return correctRightSideWordForDecimal(num_1_9[i - 1]) + form_11_19;
                }
            }
        } else if (numberFirst == 0) {
            for (int i = 1; i <= 9; i++) {
                if (i == numberSecond) {
                    return num_1_9[i - 1];
                }
            }
        } else {
            for (int i = 2; i <= 9; i++) {
                if (i == numberFirst) {
                    for (int j = 1; j <= 9; j++) {
                        if (j == numberSecond) {
                            return num_10_90[i - 1] + " " + num_1_9[j - 1];
                        }
                    }
                }
            }
        }
        return "";
    }

    @Nullable
    public String getHundreds(List<Integer> numbers) {
        int hundred = numbers.get(2);
        for (int i = 1; i <= 9; i++) {
            if (i == hundred) {
                return num_100_900[i - 1] + " " + getFormDecimal(numbers.get(1), numbers.get(0));
            }
        }
        return "";
    }

    public String getThousand(List<Integer> numbers) {
        int thousand = 0;
        if (numbers.size() == 4) {
            thousand = numbers.get(3);
            for (int i = 1; i <= 9; i++) {
                if (i == thousand && numbers.get(2) == 0 && numbers.get(1) == 0 && numbers.get(0) == 0) {
                    return correctRightSideWordForThousand(num_1_9[i - 1]) + " " + getFormThousand(thousand);
                } else if (i == thousand && numbers.get(2) != 0) {
                    return correctRightSideWordForThousand(num_1_9[i - 1]) + " " + getFormThousand(thousand) + " " + getHundreds(numbers);
                }
            }
        } else if (numbers.size() == 5) {
            if (numbers.get(2) == 0 && numbers.get(1) == 0 && numbers.get(0) == 0) {
                return getFormDecimal(numbers.get(4), numbers.get(3)) + " " + getFormThousand(numbers.get(3));
            } else return getFormDecimal(numbers.get(4), numbers.get(3)) + " " + getFormThousand(numbers.get(3)) + " " + getHundreds(numbers);
        } else if (numbers.size() == 6) {
            return getHundredThousand(numbers) + " " + getFormThousand(Integer.parseInt(numbers.get(4)+""+numbers.get(3))) + " " +getHundreds(numbers);
        }
        return "";
    }

    public String getHundredThousand(List<Integer> numbers) {
        for (int i = 1; i <= 9; i++) {
            if (i == numbers.get(5)) {
                return num_100_900[i - 1] + " " + getFormDecimal(numbers.get(4), numbers.get(3));
            }
        }
        return "";
    }

    public String getFormThousand(int number) {
        if (number == 1) {
            return thousandForms[0];
        } else if (number > 1 && number < 5) {
            return thousandForms[1];
        } else return thousandForms[2];
        //return "";
    }


    @Nullable
    public String correctRightSideWordForThousand(String str) {
        if (str != null) {
            if (str.charAt(str.length() - 1) == 'ь' || str.charAt(str.length() - 1) == 'е') {
                return str;
            } else if (str.charAt(str.length() - 1) == 'а') {
                str = str.substring(0, str.length() - 1) + "е";
                return str;
            } else {
                str = str.substring(0, str.length() - 2) + "на";
                return str;
            }
        }

        return "";
    }

    public String correctRightSideWordForDecimal(String str) {
        if (str != null) {
            if (str.charAt(str.length() - 1) == 'ь' || str.charAt(str.length() - 1) == 'е') {
                str = str.substring(0, str.length() - 1);
                return str;
            } else if (str.charAt(str.length() - 1) == 'а') {
                str = str.substring(0, str.length() - 1) + "е";
                return str;
            }
        }
        return str;
    }


}
