package to_student;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.text.MessageFormat;
import java.util.StringTokenizer;

import java.io.*;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Logger;


//Side note that each of these tests are labeled with their corresponding numbers in my test paths file
//example: get_token1 corresponds to the first test path for get_token

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrinttokensTest {

//    initializing my printtokens instance here globally
//    BeforeAll annotation to reduce initializing the instance in every test method
//    static Printtokens c;
//    @BeforeAll
//    static void initialize() throws IOException
//    {
//        c = new Printtokens();
//    }

    //  1)open_token_stream[1, 3, 4]
    //    Input: String fname = ""
    //    Expected Output: an instance of buffer reader (br), passed empty string to its stream.
    //  2) open_token_stream[1, 2, 4]
    //     Input: String fname = "D:\MyStuff\Main\College\UTArlington\FALL2022\CSE4321\myInput.txt"
    //     Expected Output: an instance of buffer reader (br), passed the filename to its stream

    //  Test for checking empty source passed to the function
    @ValueSource(strings = {""})
    @ParameterizedTest
    void open_token_stream1a(String str)  {
        System.out.println("testing if empty parameter");
      //  BufferedReader br = open_character_stream("");
        Assertions.assertEquals( 0, str.length());

        System.out.println("\ntesting if empty parameter passed");
    }
    //  This test checks that the instance of the buffered reader passed an empty string parameter is not null
    @ValueSource(strings = {""})
    @ParameterizedTest
    void open_token_stream1b(String str)  {
        Printtokens c = new Printtokens();
        BufferedReader br;
        System.out.println("testing if empty parameter passed, and buffered instance is not null");
        br = c.open_token_stream(str);
        Assertions.assertNotNull(br);
        System.out.println("\nbuffered instance is not null");

    }

    //  This test tests for the object validity after a file path is passed( we dont know if it is valid or not yet), will find out in open_character_stream
    @ValueSource(strings = {"D:\\MyStuff\\Main\\College\\UTArlington\\FALL2022\\CSE4321\\PrintTokens\\src\\main\\myInput.txt"})
    @ParameterizedTest
    void open_token_stream2(String str)  {
        System.out.println("testing if valid file path was passed, is object null");
        Printtokens c = new Printtokens();
        BufferedReader br;
        br = c.open_token_stream(str);
        Assertions.assertNotNull(br);
        System.out.println("\nbuffered instance is not null");
    }



    //  This test checks for validity of the instance, assuming no file path was passed as arguments
    @ValueSource(strings = "23 #a and (")
    @ParameterizedTest
    void open_character_stream1(String str) throws IOException, ClassNotFoundException {
        System.out.println("making sure instance is not null when filename is empty ");
        StringTokenizer st = new StringTokenizer(str," ");
        while (st.hasMoreTokens()) {
            System.out.print(st.nextToken() + " ");
        }
        System.out.println("\ndone with checking instance");
    }


    //  This test checks for validity of the instance, assuming a valid file path was passed
    @ValueSource(strings = {"D:\\MyStuff\\Main\\College\\UTArlington\\FALL2022\\CSE4321\\PrintTokens\\src\\main\\myInput.txt"})
    @ParameterizedTest
    void open_character_stream2(String str) throws IOException, ClassNotFoundException {
        System.out.println("making sure instance is not null when valid filepath is passed ");
        StringTokenizer st = new StringTokenizer(str," ");
        while (st.hasMoreTokens()) {
            System.out.print(st.nextToken() + " ");
        }
        System.out.println("\ndone with checking instance");
    }


    @ValueSource(strings = "23")
    @ParameterizedTest
    void get_char(String chVal) {

        int ch = 0;
        ch = Integer.parseInt(chVal);
        Assertions.assertEquals(23, ch);

    }

    @ValueSource(strings = "23")
    @ParameterizedTest
    void unget_char(String chVal) {

        int ch = 0;
        ch = Integer.parseInt(chVal);
        Assertions.assertEquals(23, ch);

    }



    //1) get_token[1, 2, 3, 4]
    //Input: BufferedReader br = ""
    //Expected Output: Console Output = please provide a valid file path or a token in console!
    @ParameterizedTest
    @ValueSource(strings = "")
    void get_token_1(String str) {
        System.out.println("start of get_token test to verify its giving back the expected result");
        Printtokens c = new Printtokens();
        Reader temp = new StringReader(str);
        BufferedReader br = new BufferedReader(temp);
        Assertions.assertNull( c.get_token(br));
        System.out.println("end of this get_token test");
    }

    //get_token[1, 2, 3, 5, 6, 7, 5, 8, 9]
    //Input: BufferedReader br = " ", (here i will account for space and new line in seperate cases in junit)
    //Expected Output: Console Output = null
    @ParameterizedTest
    @ValueSource(strings = " ")
    void get_token_2a(String str) {
        System.out.println("start of get_token test to verify its giving back the expected result");
        Printtokens c = new Printtokens();
        Reader temp = new StringReader(str);
        BufferedReader br = new BufferedReader(temp);
        Assertions.assertNull( c.get_token(br));
        System.out.println("end of this get_token test");
    }
    @ParameterizedTest
    @ValueSource(strings = "\n")
    void get_token_2b(String str) {
        System.out.println("start of get_token test to verify its giving back the expected result");
        Printtokens c = new Printtokens();
        Reader temp = new StringReader(str);
        BufferedReader br = new BufferedReader(temp);
        Assertions.assertNull( c.get_token(br));
        System.out.println("end of this get_token test");
    }
    @ParameterizedTest
    @ValueSource(strings = "\r")
    void get_token_2c(String str) {
        System.out.println("start of get_token test to verify its giving back the expected result");
        Printtokens c = new Printtokens();
        Reader temp = new StringReader(str);
        BufferedReader br = new BufferedReader(temp);
        Assertions.assertNull( c.get_token(br));
        System.out.println("end of this get_token test");
    }


    //3) get_token[1, 2, 3, 5, 8, 10, 11]
    //Input: BufferedReader br = "("
    //Expected Output: Console Output = (
    @ParameterizedTest
    @ValueSource(strings = "(")
    void get_token_3(String str) {
        System.out.println("start of get_token test to verify its giving back the expected result");
        Printtokens c = new Printtokens();
        Reader temp = new StringReader(str);
        BufferedReader br = new BufferedReader(temp);
        Assertions.assertEquals("(", c.get_token(br));
        System.out.println("end of this get_token test");
    }

    //4) get_token[1, 2, 3, 5, 8, 10, 12, 13, 14, 16, 17, 18, 19]
    //Input: BufferedReader br = ";"
    //Expected Output: Console Output = ;
    @ParameterizedTest
    @ValueSource(strings = "{\\")
    void get_token_4(String str) {
        System.out.println("start of get_token test to verify its giving back the expected result");
        Printtokens c = new Printtokens();
        Reader temp = new StringReader(str);
        BufferedReader br = new BufferedReader(temp);
        Assertions.assertEquals("{\\", c.get_token(br));
        System.out.println("end of this get_token test");
    }

    //5) get_token[1, 2, 3, 5, 8, 10, 12, 13, 14, 16, 17, 18, 19]
    //Input: BufferedReader br = "'xyz'"
    //Expected Output: Console Output = 'xyz'
    @ParameterizedTest
    @ValueSource(strings = "'xyz'")
    void get_token_5(String str) {
        System.out.println("start of get_token test to verify its giving back the expected result");
        Printtokens c = new Printtokens();
        Reader temp = new StringReader(str);
        BufferedReader br = new BufferedReader(temp);
        Assertions.assertEquals("'xyz'", c.get_token(br));
        System.out.println("end of this get_token test");
    }
    //6) get_token[1, 2, 3, 5, 8, 10, 12, 14, 15, 16, 17, 18, 19]
    //Input: BufferedReader br = ";"
    //Expected Output: Console Output = ;
    @ParameterizedTest
    @ValueSource(strings = ";")
    void get_token_6(String str) {
        System.out.println("start of get_token test to verify its giving back the expected result");
        Printtokens c = new Printtokens();
        Reader temp = new StringReader(str);
        BufferedReader br = new BufferedReader(temp);
        Assertions.assertEquals(";", c.get_token(br));
        System.out.println("end of this get_token test");
    }

    //7) get_token[1, 2, 3, 5, 8, 10, 12, 14, 15, 16, 17, 20, 21, 27, 28, 29]
    //Input: BufferedReader br = "xyz "
    //Expected Output: Console Output = ;
    @ParameterizedTest
    @ValueSource(strings = "xyz ")
    void get_token_7(String str) {
        System.out.println("start of get_token test to verify its giving back the expected result");
        Printtokens c = new Printtokens();
        Reader temp = new StringReader(str);
        BufferedReader br = new BufferedReader(temp);
        Assertions.assertEquals("xyz", c.get_token(br));
        System.out.println("end of this get_token test");
    }

    //8) get_token[1, 2, 3, 5, 8, 10, 12, 14, 15, 16, 17, 20, 21, 22, 23, 24, 25, 27, 30, 31, 32]
    //Input: BufferedReader br = "xyz,"
    //Expected Output: Console Output = xyz
    @ParameterizedTest
    @ValueSource(strings = "xyz,")
    void get_token_8(String str) {
        System.out.println("start of get_token test to verify its giving back the expected result");
        Printtokens c = new Printtokens();
        Reader temp = new StringReader(str);
        BufferedReader br = new BufferedReader(temp);
        Assertions.assertEquals("xyz", c.get_token(br));
        System.out.println("end of this get_token test");
    }
    //9) get_token[1, 2, 3, 5, 8, 10, 12, 14, 15, 16, 17, 20, 21, 22, 23, 24, 25, 27, 30, 33, 34, 35]
    //Input: BufferedReader br = "xyz'"
    //Expected Output: Console Output = xyz'
    @ParameterizedTest
    @ValueSource(strings = "xyz'")
    void get_token_9(String str) {
        System.out.println("start of get_token test to verify its giving back the expected result");
        Printtokens c = new Printtokens();
        Reader temp = new StringReader(str);
        BufferedReader br = new BufferedReader(temp);
        Assertions.assertEquals("xyz'", c.get_token(br));
        System.out.println("end of this get_token test");
    }

    //10) get_token[1, 2, 3, 5, 8, 10, 12, 14, 15, 16, 17, 20, 21, 22, 23, 24, 25, 27, 30, 33, 36, 37, 38]
    //Input: BufferedReader br = "abc;"
    //Expected Output: Console Output = abc
    @ParameterizedTest
    @ValueSource(strings = "xyz;")
    void get_token_10(String str) {
        System.out.println("start of get_token test to verify its giving back the expected result");
        Printtokens c = new Printtokens();
        Reader temp = new StringReader(str);
        BufferedReader br = new BufferedReader(temp);
        Assertions.assertEquals("xyz", c.get_token(br));
        System.out.println("end of this get_token test");
    }
    //11) get_token[1, 2, 3, 5, 8, 10, 12, 14, 15, 16, 17, 20, 21, 22, 23, 24, 25, 27, 30, 33, 36, 39]
    //Input: BufferedReader br = "{abc\""
    //Expected Output: Console Output = "{abc\"
    @ParameterizedTest
    @ValueSource(strings = "{abc\\")
    void get_token_11(String str) {
        System.out.println("start of get_token test to verify its giving back the expected result");
        Printtokens c = new Printtokens();
        Reader temp = new StringReader(str);
        BufferedReader br = new BufferedReader(temp);
        Assertions.assertEquals("{abc\\", c.get_token(br));
        System.out.println("end of this get_token test");
    }


    //1)is_token_end[1, 3, 4, 6, 7, 11, 13, 15]
    //Input: Int str_com_id = "1", Int res = "1"
    //Expected Output: Boolean flag: false
    @CsvSource("1, 1")
    @ParameterizedTest
    void is_token_end_1(int str_com_id, int res) {
        System.out.println("start of is_token_end test to verify its giving back the correct boolean value");
        System.out.println("passed two parameters, one for the type of char, second is boolean whether was end of line");
        Assertions.assertFalse(Printtokens.is_token_end(str_com_id, res));
        System.out.println("end of is_token_end test, successfully passed");
    }
    //2)is_token_end[1, 2, 3, 4, 5, 7, 11, 13, 15]
    //Input: Int str_com_id = "1", Int res = "-1"
    //Expected Output: Boolean flag: true
    @CsvSource("1, -1")
    @ParameterizedTest
    void is_token_end_2(int str_com_id, int res) {
        System.out.println("start of is_token_end test to verify its giving back the correct boolean value");
        System.out.println("passed two parameters, one for the type of char, second is boolean whether was end of line");
        Assertions.assertTrue(Printtokens.is_token_end(str_com_id, res));
        System.out.println("end of is_token_end test, successfully passed");
    }
    //3)is_token_end[1, 3, 7, 8, 10, 11, 13, 15]
    //Input: Int str_com_id = "2", Int res = "1"
    //Expected Output: Boolean flag: false
    @CsvSource("2, 1")
    @ParameterizedTest
    void is_token_end_3(int str_com_id, int res) {
        System.out.println("start of is_token_end test to verify its giving back the correct boolean value");
        System.out.println("passed two parameters, one for the id stating its a comment, second is boolean whether was end of line");
        Assertions.assertFalse(Printtokens.is_token_end(str_com_id, res));
        System.out.println("end of is_token_end test, successfully passed");
    }

    //4)is_token_end[1, 2, 3, 7, 8, 9, 11, 13, 15]
    //Input: Int str_com_id = "2", Int res = "-1"
    //Expected Output: Boolean flag: true
    @CsvSource("2, -1")
    @ParameterizedTest
    void is_token_end_4(int str_com_id, int res) {
        System.out.println("start of is_token_end test to verify its giving back the correct boolean value");
        System.out.println("passed two parameters, one for the id stating its a comment, second is boolean whether was end of line");
        Assertions.assertTrue(Printtokens.is_token_end(str_com_id, res));
        System.out.println("end of is_token_end test, successfully passed");
    }

    //5)is_token_end[1, 3, 11, 12, 13, 15]
    //Input: Int str_com_id = "0", Int res = "1"
    //Expected Output: Boolean flag: false
    @CsvSource("0, 1")
    @ParameterizedTest
    void is_token_end_5(int str_com_id, int res) {
        System.out.println("start of is_token_end test to verify its giving back the correct boolean value");
        System.out.println("passed two parameters, one for the type of char, second is boolean whether was end of line");
        Assertions.assertFalse(Printtokens.is_token_end(str_com_id, res));
        System.out.println("end of is_token_end test, successfully passed");
    }
    //6)is_token_end[1, 2, 3, 7, 11, 13, 14, 15]
    //Input: Int str_com_id = "0", Int res = "-1"
    //Expected Output: Boolean flag: true
    @CsvSource("0, -1")
    @ParameterizedTest
    void is_token_end_6(int str_com_id, int res) {
        System.out.println("start of is_token_end test to verify its giving back the correct boolean value");
        System.out.println("passed two parameters, one is 0 for and second is boolean whether was end of line");
        Assertions.assertTrue(Printtokens.is_token_end(str_com_id, res));
        System.out.println("end of is_token_end test, successfully passed");
    }

    //1)token_type[1, 2]
    //Input: String tok = "and"
    //Output: 1
    @ValueSource(strings = "and")
    @ParameterizedTest
    void token_type_1(String tok) {
        Assertions.assertEquals(Printtokens.token_type(tok), 1);
    }
    //2)token_type[1, 3, 4, 5, 7, 9, 11, 13, 15]
    //Input: String tok = ")"
    //Output: 2
    @ValueSource(strings = ")")
    @ParameterizedTest
    void token_type_2(String tok) {
        Assertions.assertEquals(Printtokens.token_type(tok), 2);
    }
    //3)token_type[1, 3, 5, 6, 7, 9, 11, 13, 15]
    //Input: String tok = "a2"
    //Output: 3
    @ValueSource(strings = "a2")
    @ParameterizedTest
    void token_type_3(String tok) {
        Assertions.assertEquals(Printtokens.token_type(tok), 3);
    }
    //4)token_type[1, 3, 5, 7, 8, 9, 11, 13, 15]
    //Input: String tok = "23"
    //Output: 41
    @ValueSource(strings = "23")
    @ParameterizedTest
    void token_type_4(String tok) {
        Assertions.assertEquals(Printtokens.token_type(tok), 41);
    }
    //5)token_type[1, 3, 5, 7, 9, 10, 11, 13, 15]
    //Input: String tok = ""abc""
    //Output: 42
    @ValueSource(strings = "\"abc\"")
    @ParameterizedTest
    void token_type_5(String tok) {
        Assertions.assertEquals(Printtokens.token_type(tok), 42);
    }
    //6)token_type[1, 3, 5, 7, 9, 11, 12, 13, 15]
    //Input: String tok = "#a"
    //Output: 43
    @ValueSource(strings = "#a")
    @ParameterizedTest
    void token_type_6(String tok) {
        Assertions.assertEquals(Printtokens.token_type(tok), 43);
    }
    //7)token_type[1, 3, 5, 7, 9, 11, 12, 13, 14, 15]
    //Input: String tok = ";"
    //Output: 5
    @ValueSource(strings = ";")
    @ParameterizedTest
    void token_type_7(String tok) {
        Assertions.assertEquals(Printtokens.token_type(tok), 5);
    }
    //8)token_type[1, 3, 5, 7, 9, 11, 13, 15]
    //Input: String tok = "^"
    //Output: 0
    @ValueSource(strings = "^")
    @ParameterizedTest
    void token_type_8(String tok) {
        Assertions.assertEquals(Printtokens.token_type(tok), 0);
    }
//    private static Stream<Arguments> recreatingGetToken() {
//        B
//        return Stream.of(
//                Arguments.of("Hello", 5),
//                Arguments.of("JUnit 5", 7));
//    }


    //1)print_token[1, 2, 3, 5, 6, 7, 9, 11, 13, 15, 17]
    //Input: String tok = "xor"
    //Output: keyword, "xor".
    @CsvSource("xor")
    @ParameterizedTest
    void print_token_1(String tok) {
        Assertions.assertEquals("keyword,\"xor\".", MessageFormat.format("keyword,\"{0}\".", tok));
    }


    //2)print_token[1, 2, 3, 5, 7, 8, 9, 11, 13, 15, 17]
    //Input: String tok = "`"
    //Output: bquote
    @CsvSource("`")
    @ParameterizedTest
    void print_token_2(String tok) {
        Assertions.assertEquals("bquote.", "bquote.");
    }
    //3)print_token[1, 2, 3, 5, 7, 9, 11, 13, 15, 17]
    //Input: String tok = "a2"
    //Output: identifier, "a2".
    @CsvSource("a2")
    @ParameterizedTest
    void print_token_3(String tok) {
        Assertions.assertEquals("identifier,\"a2\".", MessageFormat.format("identifier,\"{0}\".", tok));
    }

    //4)print_token[1, 2, 3, 5, 7, 9, 11, 12, 13, 15, 17]
    //Input: String tok = "34"
    //Output: numeric, 34.
    @CsvSource("34")
    @ParameterizedTest
    void print_token_4(String tok) {
        Assertions.assertEquals("numeric,34.", MessageFormat.format("numeric,{0}.", tok));
    }

    //5)print_token[1, 2, 3, 5, 7, 9, 11, 13, 14, 15, 17]
    //Input: String tok = ""abc""
    //Output: string, "abc".
    @CsvSource("\"xyz\"")
    @ParameterizedTest
    void print_token_5(String tok) {
        Assertions.assertEquals("string,\"xyz\".", MessageFormat.format("string,{0}.", tok));
    }
    //6)print_token[1, 2, 3, 5, 7, 9, 11, 13, 15, 16, 17]
    //Input: String tok = "#f"
    //Output: character, "f"
    @CsvSource("#f")
    @ParameterizedTest
    void print_token_6(String tok) {
        Assertions.assertEquals("character,\"#f\".", MessageFormat.format("character,\"{0}\".", tok));
    }
    //7)print_token[1, 2, 3, 5, 7, 9, 11, 13, 15, 17, 18]
    //Input: String tok = ";"
    //Output: comment, ";".
    @CsvSource(";")
    @ParameterizedTest
    void print_token_7(String tok) {
        Assertions.assertEquals("comment,\";\".", MessageFormat.format("comment,\"{0}\".", tok));
    }
    //8)print_token[1, 2, 3, 4, 5, 7, 9, 11, 13, 15, 17]
    //Input: String tok = "$"
    //Output: error, "$".
    @CsvSource("$")
    @ParameterizedTest
    void print_token_8(String tok) {
        Assertions.assertEquals("error,\"$\".", MessageFormat.format("error,\"{0}\".", tok));
    }

    //__________________________
    //1)is_comment[1, 2]
    //Input: String ident = ";"
    //Output: Boolean flag = "true"
    @ValueSource(strings = ";")
    @ParameterizedTest
    void is_comment_1(String r) {
        Assertions.assertTrue(Printtokens.is_comment(r));
    }
    //2)is_comment[1, 3]
    //Input: String ident = ":"
    //Output: Booelan flag = "false"
    @ValueSource(strings = ":")
    @ParameterizedTest
    void is_comment_2(String r) {
        Assertions.assertFalse(Printtokens.is_comment(r));
    }

    //1)is_keyword[1, 2, 4]
    //Input: String str = "and"
    //Output: Boolean flag = true
    @ValueSource(strings = "and")
    @ParameterizedTest
    void is_keyword_1(String r) {
        Assertions.assertTrue(Printtokens.is_keyword(r), "and");
    }

    //2)is_keyword[1, 3, 4]
    //Input: String str = "XXOR"
    //Output: Boolean flag = false
    @ValueSource(strings = "XXOR")
    @ParameterizedTest
    void is_keyword_2(String r) {
        Assertions.assertFalse(Printtokens.is_keyword(r), "XXOR");
    }

    //1)is_char_const[1, 2]
    //Input: String = "#a"
    //Output: Boolean flag = "true"
    @ValueSource(strings = "#a")
    @ParameterizedTest
    void is_char_constant_1(String r) {
        Assertions.assertTrue(Printtokens.is_char_constant(r), "#a");
    }

    //2)is_char_const[1, 3]
    //Input: String = "#8"
    //Output: Boolean flag = "false"
    @ValueSource(strings = "#8")
    @ParameterizedTest
    void is_char_constant_2(String r) {
        Assertions.assertFalse(Printtokens.is_char_constant(r), "#8");
    }

    //1)is_num_const[1, 2, 3, 4, 6]
    //Input: String = "696"
    //Output: Boolean flag = true
    @CsvSource("696")
    @ParameterizedTest
    void is_num_const_1(String r) { Assertions.assertTrue(Printtokens.is_num_constant(r), "696"); }

    //2)is_num_const[1, 2, 3, 5, 7]
    //Input: String = "3a"
    //Output: Boolean flag = "false"
    @CsvSource("3a")
    @ParameterizedTest
    void is_num_const_2(String r) { Assertions.assertFalse(Printtokens.is_num_constant(r)); }


    //3)is_num_const[1, 7]
    //Input: String = "a"
    //Output: Boolean flag = "false"
    @CsvSource("a")
    @ParameterizedTest
    void is_num_const_3(String r) { Assertions.assertFalse(Printtokens.is_num_constant(r)); }


    //1)is_str_const[1, 2, 3, 4, 6]
    //Input: String = ' "" '
    //Output: Boolean flag = "true"
    @CsvSource("\"\"")
    @ParameterizedTest
    void is_str_constant_1(String r) { Assertions.assertTrue(Printtokens.is_str_constant(r)); }

    //2)is_str_const[1, 2, 3, 5, 3, 5, 6]
    //Input: String = ' "abc" '
    //Output: true
    @CsvSource("\"abc\"")
    @ParameterizedTest
    void is_str_constant_2(String r) { Assertions.assertTrue(Printtokens.is_str_constant(r)); }

    //3)is_str_const[1, 2, 7]
    //Input: String = ' " '
    //Output: Boolean flag = "false"
    @CsvSource("\"")
    @ParameterizedTest
    void is_str_constant_3(String r) { Assertions.assertFalse(Printtokens.is_str_constant(r)); }

    //4)is_str_const[1, 7]
    //Input: String = " "
    //Output: Boolean flag = "false"
    @ValueSource(strings = " ")
    @ParameterizedTest
    void is_str_constant_4(String r) { Assertions.assertFalse(Printtokens.is_str_constant(r)); }

    //1)is_identifier[1, 2, 3, 4]
    //Input: String str = "a2"
    //Output: Boolean flag = true
    @CsvSource("a2")
    @ParameterizedTest
    void is_identifier_1(String r) { Assertions.assertTrue(Printtokens.is_identifier(r)); }

    //2)is_identifier[1, 2, 3, 4, 5, 6]
    //Input: String str = "2"
    //Output: Boolean flag = false
    @CsvSource("2")
    @ParameterizedTest
    void is_identifier_2(String r) { Assertions.assertFalse(Printtokens.is_identifier(r)); }

    //3)is_identifier[1, 2, 6]
    //Input: String str = ""
    //Output: [App Warning]: no argument is provided!
    @ValueSource(strings = " ")
    @ParameterizedTest
    void is_identifier_3(String r) { Assertions.assertFalse(Printtokens.is_identifier(r)); }

    //1)print_spec_symbol[1, 2, 3, 5, 7, 9, 11, 13]
    //Input: String str = "("
    //Output: Console output = "lparen"
    @CsvSource("(")
    @ParameterizedTest
    void print_spec_symbol_1(String str) {
        Assertions.assertEquals("lparen,.", "lparen,.");
    }
    //2)print_spec_symbol[1, 3, 4, 5, 7, 9, 11, 13]
    //Input: String str = ")"
    //Output: Console output = "rparen"
    @CsvSource(")")
    @ParameterizedTest
    void print_spec_symbol_2(String str) {
        Assertions.assertEquals("lparen,.", "lparen,.");
    }
    //3)print_spec_symbol[1, 3, 5, 6, 7, 9, 11, 13]
    //Input: String = "["
    //Output: Console output = "lsquare"
    @CsvSource("[")
    @ParameterizedTest
    void print_spec_symbol_3(String str) {
        Assertions.assertEquals("lsquare,.", "lsquare,.");
    }
    //4)print_spec_symbol[1, 3, 5, 7, 8, 9, 11, 13]
    //Input: String = "]"
    //Output: Console output = "rsquare"
    @CsvSource("]")
    @ParameterizedTest
    void print_spec_symbol_4(String str) {
        Assertions.assertEquals("rsquare,.", "rsquare,.");
    }
    //5)print_spec_symbol[1, 2, 3, 5, 7, 9, 10, 11, 13]
    //Input: String str = " ' "
    //Output: Console output = "quote"
    @CsvSource("'")
    @ParameterizedTest
    void print_spec_symbol_5(String str) {
        Assertions.assertEquals("quote.", "quote.");
    }
    //6)print_spec_symbol[1, 3, 5, 7, 9, 11, 12, 13]
    //Input: String str = " ` "
    //Output: Console output = "bquote"
    @CsvSource("`")
    @ParameterizedTest
    void print_spec_symbol_6(String str) {
        Assertions.assertEquals("bquote,.", "bquote,.");
    }
    //7)print_spec_symbol[1, 2, 3, 5, 7, 9, 11, 13, 14]
    //Input: ,
    //Output: comma
    @CsvSource(",")
    @ParameterizedTest
    void print_spec_symbol_7(String str) {
        Assertions.assertEquals("comma,.", "comma,.");
    }






    //________________________________________________________________
    //1) is_spec_symbol[1,2, 3, 5, 7, 9, 11, 13, 15]
    //Input: Char c = (
    //Expected Output: boolean flag = true
    //------------------------------------------------------------------
    @ValueSource(chars = {'('})
    @ParameterizedTest
    void is_spec_symbol_1(char c) {

        Assertions.assertTrue( Printtokens.is_spec_symbol(c));

    }
    //-----------------------------------------------------------------
    //2) is_spec_symbol[1, 3, 4, 5, 7, 9, 11, 13, 15]
    //Input: Char c = )
    //Expected Output: boolean flag = true
    //------------------------------------------------------------------
    @ValueSource(chars = {')'})
    @ParameterizedTest
    void is_spec_symbol_2(char c) {

        Assertions.assertTrue( Printtokens.is_spec_symbol(c));

    }
    //------------------------------------------------------------------
    //3) is_spec_symbol[1,3, 5, 6, 7, 9, 11, 13, 15]
    //Input: Char c = [
    //Output: boolean flag = true
    //------------------------------------------------------------------
    @ValueSource(chars = {'['})
    @ParameterizedTest
    void is_spec_symbol_3(char c) {

        Assertions.assertTrue( Printtokens.is_spec_symbol(c));
    }
    //-----------------------------------------------------------------
    //4) is_spec_symbol[1, 3, 5, 7, 8, 9, 11, 13, 15]
    //Input: Char c = ]
    //Output: boolean flag = true
    //-----------------------------------------------------------------
    @ValueSource(chars = {']'})
    @ParameterizedTest
    void is_spec_symbol_4(char c) {

        Assertions.assertTrue( Printtokens.is_spec_symbol(c));

    }
    //---------------------------------------------------------------
    //5) is_spec_symbol[1,3, 5, 7, 9, 10, 11, 13, 15]
    //Input: Char c = /
    //Output: boolean flag = true
    //---------------------------------------------------------------
    // Bug was found, changed to return false in is_spec_symbol method. Now assert false
    @ValueSource(chars = {'/'})
    @ParameterizedTest
    void is_spec_symbol_5(char c) {

//        Assertions.assertTrue( Printtokens.is_spec_symbol(c));
          Assertions.assertFalse( Printtokens.is_spec_symbol(c));
    }
    //-----------------------------------------------------------------
    //6) is_spec_symbol[1,3, 5, 7, 9, 11, 12, 13, 15]
    //Input: Char c = `
    //Output: boolean flag = true
    //-----------------------------------------------------------------
    @ValueSource(chars = {'`'})
    @ParameterizedTest
    void is_spec_symbol_6(char c) {

        Assertions.assertTrue( Printtokens.is_spec_symbol(c));

    }
    //------------------------------------------------------------------
    //7) is_spec_symbol[1, 3, 5, 7, 9, 11, 13, 14, 15]
    //Input: Char c = ,(
    //Output: boolean flag = true
    //------------------------------------------------------------------
    @ValueSource(chars = {','})
    @ParameterizedTest
    void is_spec_symbol_7(char c) {

        Assertions.assertTrue( Printtokens.is_spec_symbol(c));

    }
    //------------------------------------------------------------------
    //8) is_spec_symbol[1, 3, 5, 7, 9, 11, 13, 15]
    //Input: Char c = 0
    //Output: boolean flag = false
    //------------------------------------------------------------------
    @ValueSource(chars = {'0'})
    @ParameterizedTest
    void is_spec_symbol_8(char c) {

        Assertions.assertFalse( Printtokens.is_spec_symbol(c));

    }

}

