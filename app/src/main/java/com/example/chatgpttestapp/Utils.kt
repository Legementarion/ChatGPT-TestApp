package com.example.chatgpttestapp

/**
Documentation:

The regex starts by ensuring that we have at least one of each required character using positive lookaheads.
To ensure no whitespace, a negative lookahead is used to match any whitespace character and negate the result.
The actual matching is done by .{1,max}, which ensures that the string length is between 1 and the maximum length.
Note: This regex works for ASCII characters. If your input can contain Unicode or non-ASCII characters,
the behavior might be different. Adjustments may be necessary.
 */
fun isValidString(input: String, maxLength: Int): Boolean {
    val regex = """^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!"#$%&'()*+,-./:;<=>?@\[\\\]^_`{|}~])(?!.*\s).{1,$maxLength}$""".toRegex()
    return regex.matches(input)
}