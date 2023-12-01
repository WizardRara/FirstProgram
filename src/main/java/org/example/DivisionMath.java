package org.example;

import java.util.LinkedList;

public class DivisionMath {

    public void longDivisionCalculation(int dividend, int divider) {
        if (divider == 0) {
            throw new DivisionExeption("Ошибка, попытка деления на 0");
        } else if (dividend % divider == 0) {
            if (dividend >= divider) {
                division(dividend, divider);
            } else {
                throw new DivisionExeption("Делимое должно быть больше делителя");
            }
        } else {
            int dividendx = dividend;
            int remainderOfIntegerDivision = dividend % divider;
            dividend -= remainderOfIntegerDivision;
            if (dividend >= divider) {
                division(dividend, divider);
                System.out.printf("Остаток от целочисленного деления числа %d на %d = %d%n", dividendx, divider, remainderOfIntegerDivision);
            } else {
                throw new DivisionExeption("Делимое должно быть больше делителя");
            }
        }
    }

    private void division(int dividend, int divider) {
        LinkedList<Integer> listDividend = new LinkedList<>();
        fillDividendList(listDividend, dividend);
        LinkedList<Integer> partOfTheDividend = new LinkedList<>();
        LinkedList<Integer> subtractedPart = new LinkedList<>();
        LinkedList<Integer> divisionRemainders = new LinkedList<>();
        StringBuilder result = new StringBuilder();

        divisionCalculations(listDividend, partOfTheDividend, subtractedPart, divisionRemainders, result, dividend, divider);
        divisionOutput(partOfTheDividend, subtractedPart, divisionRemainders, result, dividend, divider);
    }

    private void fillDividendList(LinkedList<Integer> listDividend, int dividend) {
        while (dividend > 0) {
            listDividend.push(dividend % 10);
            dividend /= 10;
        }
    }

    private void divisionCalculations(LinkedList<Integer> listDividend, LinkedList<Integer> partOfTheDividend,
                                      LinkedList<Integer> subtractedPart, LinkedList<Integer> divisionRemainders,
                                      StringBuilder result, int dividend, int divider) {
        StringBuilder temp = new StringBuilder();
        while (!listDividend.isEmpty()) {
            increasePartFromSubtract(listDividend, temp);
            int partFromSubtract = Integer.parseInt(temp.toString());
            while (partFromSubtract < divider) {
                if (!partOfTheDividend.isEmpty()) {
                    result.append(0);
                }
                increasePartFromSubtract(listDividend, temp);
                partFromSubtract = Integer.parseInt(temp.toString());
            }
            int remainderOfTheDivision = partFromSubtract % divider;
            int subtractionNumber = partFromSubtract - (remainderOfTheDivision);
            int factor = subtractionNumber / divider;
            partOfTheDividend.add(partFromSubtract);
            subtractedPart.add(subtractionNumber);
            divisionRemainders.add(remainderOfTheDivision);
            result.append(factor);
            temp.delete(0, dividend + 1);
            if (remainderOfTheDivision != 0) {
                temp.append(remainderOfTheDivision);
            } else {
                transferringZerosFromDividendToResult(listDividend, partOfTheDividend, subtractedPart,
                        divisionRemainders, result);
            }
        }
    }

    private void transferringZerosFromDividendToResult(LinkedList<Integer> listDividend,
                                                       LinkedList<Integer> partOfTheDividend,
                                                       LinkedList<Integer> subtractedPart,
                                                       LinkedList<Integer> divisionRemainders,
                                                       StringBuilder result) {
        while (!listDividend.isEmpty() && listDividend.element() == 0) {
            result.append(listDividend.removeFirst());
            partOfTheDividend.add(0);
            subtractedPart.add(0);
            divisionRemainders.add(0);
            if (listDividend.isEmpty()) {
                return;
            }
        }
    }

    private void increasePartFromSubtract(LinkedList<Integer> listDividend, StringBuilder temp) {
        temp.append(listDividend.removeFirst());
    }

    private void divisionOutput(LinkedList<Integer> partOfTheDividend, LinkedList<Integer> subtractedPart,
                                LinkedList<Integer> divisionRemainders, StringBuilder result, int dividend,
                                int divider) {
        String space = " ";
        String hyphen = "-";
        String minus = "_";
        StringBuilder partOfTheDividendOutput = new StringBuilder();
        StringBuilder subtractedPartOutput = new StringBuilder(space);
        StringBuilder lineOutput = new StringBuilder(space);
        System.out.println(minus + dividend + "|" + divider);
        outputTheRemainingLines(partOfTheDividend, subtractedPart, divisionRemainders, partOfTheDividendOutput,
                subtractedPartOutput, lineOutput, result, space, minus, hyphen, dividend);
        lastLineOutput(partOfTheDividend, lineOutput, space);
    }

    private void outputTheRemainingLines(LinkedList<Integer> partOfTheDividend, LinkedList<Integer> subtractedPart,
                                         LinkedList<Integer> divisionRemainders, StringBuilder partOfTheDividendOutput,
                                         StringBuilder subtractedPartOutput, StringBuilder lineOutput,
                                         StringBuilder result, String space, String minus, String hyphen, int dividend) {
        int dividendLength = String.valueOf(dividend).length();
        for (int count = 0; count <= partOfTheDividend.size() - 1; count++) {
            String temp1 = String.valueOf(partOfTheDividend.get(count));
            int smallDividendLength = temp1.length();
            String temp2 = String.valueOf(subtractedPart.get(count));
            int subtractedPartLength = temp2.length();
            String temp3 = String.valueOf(divisionRemainders.get(count));
            int remainderLength = temp3.length();
            if (partOfTheDividend.get(count) == 0) {
                partOfTheDividendOutput.append(space);
                subtractedPartOutput.append(space);
                lineOutput.append(space);
                continue;
            }
            partOfTheDividendOutput.append(minus);
            partOfTheDividendOutput.append(partOfTheDividend.get(count));
            if (subtractedPartLength < smallDividendLength) {
                subtractedPartOutput.append(space);
            }
            subtractedPartOutput.append(subtractedPart.get(count));
            lineOutput.append(hyphen.repeat(smallDividendLength));
            if (count == 0) {
                subtractedPartOutput.append(space.repeat(dividendLength - smallDividendLength));
                subtractedPartOutput.append("|");
                subtractedPartOutput.append(hyphen.repeat(result.length()));
                lineOutput.append(space.repeat(dividendLength - smallDividendLength));
                lineOutput.append("|");
                lineOutput.append(result);
                System.out.println(subtractedPartOutput);
                System.out.println(lineOutput);
                subtractedPartOutput.delete((smallDividendLength + 1), subtractedPartOutput.length());
                lineOutput.delete((smallDividendLength + 1), lineOutput.length());
            } else {
                System.out.println(partOfTheDividendOutput);
                System.out.println(subtractedPartOutput);
                System.out.println(lineOutput);
            }
            preparingStringsForNextOutput(divisionRemainders, partOfTheDividendOutput, subtractedPartOutput, lineOutput,
                    space, smallDividendLength, remainderLength, count);
        }
    }

    private void preparingStringsForNextOutput(LinkedList<Integer> divisionRemainders,
                                               StringBuilder partOfTheDividendOutput,
                                               StringBuilder subtractedPartOutput, StringBuilder lineOutput,
                                               String space, int smallDividendLength, int remainderLength, int count) {
        if (divisionRemainders.get(count) == 0) {
            partOfTheDividendOutput.replace(0, partOfTheDividendOutput.length(), space.repeat(partOfTheDividendOutput.length() - 1));
            subtractedPartOutput.replace(0, subtractedPartOutput.length(), space.repeat(subtractedPartOutput.length()));
            lineOutput.replace(0, lineOutput.length(), space.repeat(lineOutput.length()));
        } else {
            if (remainderLength == smallDividendLength) {
                partOfTheDividendOutput.delete(partOfTheDividendOutput.length() - (smallDividendLength + 1), partOfTheDividendOutput.length());
                subtractedPartOutput.delete(subtractedPartOutput.length() - smallDividendLength, subtractedPartOutput.length());
                lineOutput.delete(lineOutput.length() - smallDividendLength, lineOutput.length());
            } else {
                partOfTheDividendOutput.replace(0, partOfTheDividendOutput.length(), space.repeat(partOfTheDividendOutput.length()));
                subtractedPartOutput.replace(0, subtractedPartOutput.length(), space.repeat(subtractedPartOutput.length()));
                lineOutput.replace(0, lineOutput.length(), space.repeat(lineOutput.length()));
                partOfTheDividendOutput.delete(partOfTheDividendOutput.length() - (remainderLength + 1), partOfTheDividendOutput.length());
                subtractedPartOutput.delete(subtractedPartOutput.length() - remainderLength, subtractedPartOutput.length());
                lineOutput.delete(lineOutput.length() - remainderLength, lineOutput.length());
            }
        }
    }

    private void lastLineOutput(LinkedList<Integer> partOfTheDividend, StringBuilder lineOutput, String space) {
        while (partOfTheDividend.peekLast() == 0) {
            partOfTheDividend.removeLast();
            lineOutput.deleteCharAt(lineOutput.length() - 1);
        }
        lineOutput.replace(0, lineOutput.length(), space.repeat(lineOutput.length() - 1));
        lineOutput.append(0);
        System.out.println(lineOutput);
    }
}


//for testing
//200040/4=50010 -> 20/4(5)=(%0) -> 5+0+0 -> 4>=4 -> 4/4(1)=(%0) -> 500+1+0 -> return;
//200020/4=50005 -> 20/4(5)=(%0) -> 5+0+0 -> 2<4 -> 500+0 -> 20/4(5)=(%0) -> 5000+5 -> return;
//400400/40=10010 -> 40/40(1)=(%0) -> 1+0 -> 4<40 -> 10+0 -> 40>=40 -> 40/40(1)=(%0) -> 100+1+0 -> return;
//400200/40=10005 -> 40/40(1)=(%0) -> 1+0 -> 2<40 -> 10+0 -> 20<40 -> 100+0 -> 200/40(5)=(%0) -> 1000+5 -> return;
//40004000/400=100010 -> 400/400(1)=(%0) -> 1+0 -> 4<400 -> 10+0 -> 40<400 -> 100+0 -> 400>=400 -> 400/400(1)=(%0) -> 1000+1+0 -> return;
//40002000/400=100005 -> 400/400(1)=(%0) -> 1+0 -> 2<400 -> 10+0 -> 20<400 -> 100+0 -> 200<400 -> 1000+0 -> 2000>=400 -> 2000/400(5)=(%0) -> 1000+0+5 -> return;
//1610/5=322 -> 16/5(3)=(%1) -> 3 -> (%1)+1>=5 -> 11/5(2)=(%1) -> 3+2 -> (%1)+0>=5 -> 10/5(2)=(%0) -> return;
//144/12=12 -> 14/12(1)=(%2) -> (%2)+4>=12 -> 1+2 -> return;
//4510/22=205 -> 45/44(2)=(%1) -> 2 -> (%1)+1<22 -> 2+0 -> 110>=22 -> 110/22(5)=(%0) -> 20+5 -> return;
//219010/110=1991 -> 219/110(1)+(%109) -> 1 -> (%109)+0>=219 -> 1090/110(9)=(%100) -> 1+9 -> (%100)+1>=110 -> 1001/110(9)=(%11) -> 19+9 -> (%11)+0>=110 -> 110/110(1)=(%0) -> 199+1 -> return;
//32100/300=107 -> 321/300(1)=(%21) -> 1 -> (%21)+0<300 -> 1+0 -> 210+0>=300 -> 2100/300(7)=(%0) -> 10+7 -> return;
//1342/122=11 -> 134/112(1)=(%12) -> 1 -> (%12)+2>=122 -> 122/122(1)=(%0) -> 1+1 -> return;
//33411/111=301 -> 334/111(3)=(%1) -> 3 -> (%1)+1<111 -> 3+0 -> 11+1>=111 -> 111/111(1)=(%0) -> 30+1 -> return;