package com.example.chatgpttestapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testTooShortInput() {
        assertTrue(isValidString("aA1!", 10))
    }

    @Test
    fun testTooLongInput() {
        assertFalse(isValidString("aA1!aA1!aA1!aA1!", 10))
    }

    @Test
    fun testMatchingInputLength() {
        assertTrue(isValidString("Abc1234!", 10))
    }

    @Test
    fun testMissingUppercase() {
        assertFalse(isValidString("abc1234!", 10))
    }

    @Test
    fun testMissingLowercase() {
        assertFalse(isValidString("ABC1234!", 10))
    }

    @Test
    fun testMissingSpecialCharacter() {
        assertFalse(isValidString("Abc1234", 10))
    }

    @Test
    fun testMissingDigit() {
        assertFalse(isValidString("Abcdefg!", 10))
    }

    @Test
    fun testWhitespaceAtBeginning() {
        assertFalse(isValidString(" Abc1234!", 10))
    }

    @Test
    fun testWhitespaceInMiddle() {
        assertFalse(isValidString("Abc 1234!", 10))
    }

    @Test
    fun testWhitespaceAtEnd() {
        assertFalse(isValidString("Abc1234! ", 10))
    }
}