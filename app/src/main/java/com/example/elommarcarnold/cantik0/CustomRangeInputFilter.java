package com.example.elommarcarnold.cantik0;

import android.text.InputFilter;
import android.text.Spanned;

public class CustomRangeInputFilter implements InputFilter {
    private int minValue;
    private int maxValue;

    public CustomRangeInputFilter(int minVal, int maxVal) {
        this.minValue = minVal;
        this.maxValue = maxVal;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dStart, int dEnd) {
        try {
            // Remove the string out of destination that is to be replaced
            String newVal = dest.toString().substring(0, dStart) + dest.toString().substring(dEnd, dest.toString().length());
            System.out.println("newva"+newVal);
            newVal = newVal.substring(0, dStart) + source.toString() + newVal.substring(dStart, newVal.length());
            System.out.println("newval2"+newVal);
            Integer input = Integer.parseInt(newVal);

            if (isInRange(minValue, maxValue, input)) {
                return null;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";
    }

    private boolean isInRange(double a, double b, double c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}