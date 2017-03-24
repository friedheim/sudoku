/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friedheim.sudoku.solver;

//import junit.framework.Assert;

import java.util.Arrays;
import org.junit.Assert;

public class Assertx extends Assert {

    /** A private constructor because we don't want any Assertx object */
    private Assertx() {}

    /**
     * This method test two "2 dimensional" arrays to see if they are the same
     * size and if the items inside are the same.
     * @param message The message that will be outputed if the test fail.
     * @param expected The expected 2 dimensional array.
     * @param actual The actual 2 dimensional array.
     */
    static public void assertMatrixEquals(String message,
                                         byte[][] expected,
                                         byte[][] actual) {

        // If both arrays are null, then we consider they are equal
        if (expected == null || actual == null) {
            fail(message +
            ". The input contains a null matrix");
            return; // We get out of the function as everything is fine.
        }

        // We test to see if the first dimension is the same.
        if (expected.length != actual.length) {
            fail(message +
            ". The array lengths of the first dimensions aren't the same.");
        }

        // We test every array inside the 'outer' array.
        for (int i=0; i<expected.length; i++) {
            // Can also use (with JUnit 4.3, but never tried
            // it) assertArrayEquals(actual, expected);
            
            assertArrayEquals(actual,expected);
//            assertTrue(message + ". Array no." + i +
//            " in expected and actual aren't the same.",
//                       Arrays.equals(expected[i], actual[i]));
        }
    }

    /**
     * This method test two "2 dimensional" arrays to see if they are the same
     * size and if the items inside are the same.
     * @param expected The expected 2 dimensional array.
     * @param actual The actual 2 dimensional array.
     */
    static public void assertMatrixEquals(byte[][] expected, byte[][] actual) {
        assertMatrixEquals(null, expected, actual);
    }   
}