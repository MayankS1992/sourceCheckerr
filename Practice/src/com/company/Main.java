package com.company;

import java.util.Arrays;
import java.util.function.Predicate;

public class Main {
    static String separator;
    static Predicate<String> containsDigit;

    static {
        containsDigit = data -> data.matches("[0-9]+");
        separator = "/";
    }

    /**
     * Method to change format of policy number
     * @param paragraph
     * @return
     */
    public static String changeFormat(String paragraph) {
        String policyNumber = getPolicyNumber(paragraph);
        try {
            String[] input = policyNumber.split("-");
            if (checkFormat(input, containsDigit)) {
                return input[0] + separator + input[2] + separator + input[1];
            } else {
                throw new UnsupportedOperationException("Contains unwanted charecters = " + policyNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieve the policy Number from the input string
     *
     * @param paragraph input paragraph
     * @return The policy number
     */
    private static String getPolicyNumber(String paragraph) {
        return paragraph
                .replace("Please quote your policy number: ", "")
                .replace(".", "");
    }

    /**
     * Validating of policy Number format and size
     *
     * @param input Input array of strings
     * @param predicate Condition which is being validated
     * @return if the format is correct true returned
     */
    private static boolean checkFormat(String[] input, Predicate<String> predicate) {
        if (input.length == 3) {
            return Arrays.stream(input)
                    .allMatch(predicate);
        } else {
            throw new UnsupportedOperationException("Incorrect Size");
        }
    }

    public static void main(String[] args) {
            System.out.println(changeFormat("Please quote your policy number: 122-39-8552."));
            System.out.println(changeFormat("Please quote your policy number: 1a2-39-8552."));
            System.out.println(changeFormat("Please quote your policy number: 1a2-39-ssd"));
    }
}
