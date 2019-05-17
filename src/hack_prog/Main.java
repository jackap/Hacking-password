package hack_prog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
 private static Crack crack;
 private static String specialChars = new String("!\"#$&'()*+,./:;<=>?@[\\]^_`{|}~");
 public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

  BufferedReader dictionary;
  if (args.length == 1) {
   dictionary =
    new BufferedReader(new FileReader(args[0]));
  } else dictionary =
   new BufferedReader(new FileReader("words.dict.txt"));

  BufferedReader inputFile =
   new BufferedReader(new FileReader("values.txt"));

  List < String > dict = dictionary.lines().collect(Collectors.toList());
  List < Entry > entry = inputFile.lines().map(entr -> {
   String s[] = entr.split(" ");
   return extracted(s);
  }).collect(Collectors.toList());

  inputFile.close();
  dictionary.close();
  crack = new Crack();
  System.out.println("Setup completed, running...");

  System.out.println("Searching for string...");
  Search_string(entry, dict); // found 1

  System.out.println("Searching for string reversed...");
  Search_string(entry, dict.stream().map(str -> new StringBuffer(str).reverse().toString()).collect(Collectors.toList()));


  System.out.println("Searching for String...");
  Search_String(entry, dict);

  System.out.println("Searching for toupper string...");
  Search_STRING(entry, dict); //found1

  System.out.println("Searching for string + number...");
  Search_string_number(entry, dict); //found1

  System.out.println("Searching for toupper + number...");
  Search_STRING_number(entry, dict);

  System.out.println("Searching for toupper + specialchar...");
  Search_STRING_special(entry, dict);

  System.out.println("Searching for string + specialchar...");
  Search_string_special(entry, dict); //found1


  System.out.println("Searching for string + specialchar + number...");
  Search_string_special_number(entry, dict);

  System.out.println("Searching for STRING + specialchar + number...");
  Search_STRING_special_number(entry, dict);

  System.out.println("Searching for string _ string ...");
  Search_string_under_string(entry, dict);

  System.out.println("Searching for 1string ...");
  Search_number_string(entry, dict);

  System.out.println("Searching for !string ...");
  Search_special_string(entry, dict);

  System.out.println("Searching for str1ng ...");
  Search_str1ng(entry, dict);



  System.out.println("Searching for stringSxx ...");
  Search_string_special_2numbers(entry, dict);


 }


 private static Entry extracted(String[] s) {
  return new Entry(s[1], s[3], s[2]);
 }

 private static String capitalize(String name) {
  name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
  return name;


 }
 /**
  * 
  * 
  * 
  * 
  * STRING TOUPPER SPECIAL
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_special_string(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {

    try {
     for (int i = 0; i < specialChars.length(); i++)
      if (crack.testPassword(String.valueOf(specialChars.charAt(i)).concat(word), entry)) {
       System.out.println("Found Password: " + String.valueOf(specialChars.charAt(i)).concat(word));
       System.out.println(entry);
      }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     e.printStackTrace();
    }
   });
  });


 }
 /**
  * 
  * 
  * 
  * 
  * STRING TOUPPER SPECIAL
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_STRING_special(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {

    try {
     for (int i = 0; i < specialChars.length(); i++)
      if (crack.testPassword(word.toUpperCase().concat(String.valueOf(specialChars.charAt(i))), entry)) {
       System.out.println("Found Password: " + word.toUpperCase().concat(String.valueOf(specialChars.charAt(i))));
       System.out.println(entry);
      }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     e.printStackTrace();
    }
   });
  });


 }
 /**
  * 
  * 
  * 
  * 
  * STRING  SPECIAL
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_string_special_2numbers(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {

    try {
     for (int k = 0; k < 10; k++)
      for (int j = 0; j < 10; j++)
       for (int i = 0; i < specialChars.length(); i++)
        if (crack.testPassword(word
          .concat(String.valueOf(specialChars.charAt(i)))
          .concat(String.valueOf(j))
          .concat(String.valueOf(k))

          , entry)) {
         System.out.println("Found Password: " + word
          .concat(String.valueOf(specialChars.charAt(i)))
          .concat(String.valueOf(j))
          .concat(String.valueOf(k))
         );
         System.out.println(entry);
        }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     e.printStackTrace();
    }
   });
  });


 }
 /**
  * 
  * 
  * 
  * 
  * STRING  SPECIAL
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_string_special(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {

    try {
     for (int i = 0; i < specialChars.length(); i++)
      if (crack.testPassword(word.concat(String.valueOf(specialChars.charAt(i))), entry)) {
       System.out.println("Found Password: " + word.concat(String.valueOf(specialChars.charAt(i))));
       System.out.println(entry);
      }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     e.printStackTrace();
    }
   });
  });


 }
 /**
  * 
  * 
  * 
  * 
  * STRING  TOUPPER
  * 	 
  * 
  * 
  * 
  * 
  * */
 public static void Search_STRING(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {

    try {
     if (crack.testPassword(word.toUpperCase(), entry)) {
      System.out.println("Found Password: " + word.toUpperCase());
      System.out.println(entry);
     }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     e.printStackTrace();
    }
   });
  });


 }

 /**
  * 
  * 
  * 
  * 
  * STRING  
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_str1ng(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {

    String newword;
    if (word.length() > 1)
     newword = word.replace("o", "0").replace("a", "4").replace("s", "$").replace("e", "3");
    else newword = word;
    try {
     if (crack.testPassword(newword, entry)) {
      System.out.println("Found Password: " + newword);
      System.out.println(entry);
     }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     e.printStackTrace();
    }
   });
  });


 }

 /**
  * 
  * 
  * 
  * 
  * STRING  
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_String(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {

    String newword;
    if (word.length() > 1)
     newword = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    else newword = word;
    try {
     if (crack.testPassword(newword, entry)) {
      System.out.println("Found Password: " + newword);
      System.out.println(entry);
     }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    }
   });
  });


 }

 /**
  * 
  * 
  * 
  * 
  * STRING  
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_string_under_string(List < Entry > entries, List < String > dictionary) {
  List < String > mylist = new LinkedList < > (dictionary);
  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {

    mylist.stream().forEach(word2 -> {
     String newStr = new String(word + "_" + word2);
     try {
      if (crack.testPassword(newStr, entry)) {
       System.out.println("Found Password: " + newStr);
       System.out.println(entry);
      }
     } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
      e.printStackTrace();
     }
    });
   });
  });


 }
 /**
  * 
  * 
  * 
  * 
  * STRING  
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_string(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {

    try {
     if (crack.testPassword(word, entry)) {
      System.out.println("Found Password: " + word);
      System.out.println(entry);
     }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     e.printStackTrace();
    }
   });
  });


 }
 /**
  * 
  * 
  * 
  * 
  * STRING SPECIAL NUMBER 
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_STRING_number(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {
    try {

     for (int j = 0; j < 9; j++) {
      /* Create word + special char + number overhead : 20 * 10 * number of lines */
      String defword = word.concat(String.valueOf(j));

      if (crack.testPassword(defword.toUpperCase(), entry)) {
       System.out.println("Found Password: " + defword.toUpperCase());
       System.out.println(entry);
      }


     }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     e.printStackTrace();
    }
   });
  });

 }

 /**
  * 
  * 
  * 
  * 
  * STRING SPECIAL NUMBER 
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_number_string(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {
    try {

     for (int j = 0; j < 9; j++) {
      /* Create word + special char + number overhead : 20 * 10 * number of lines */
      String defword = String.valueOf(j).concat(word);

      if (crack.testPassword(defword, entry)) {
       System.out.println("Found Password: " + defword);
       System.out.println(entry);
      }


     }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     e.printStackTrace();
    }
   });
  });

 }
 /**
  * 
  * 
  * 
  * 
  * STRING SPECIAL NUMBER 
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_string_number(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {
    try {

     for (int j = 0; j < 9; j++) {
      /* Create word + special char + number overhead : 20 * 10 * number of lines */
      String defword = word.concat(String.valueOf(j));

      if (crack.testPassword(defword, entry)) {
       System.out.println("Found Password: " + defword);
       System.out.println(entry);
      }


     }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    }
   });
  });

 }
 /**
  * 
  * 
  * 
  * 
  * STRING SPECIAL NUMBER 
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_String_special_number(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {
    try {

     for (int i = 0; i < specialChars.length(); i++)
      for (int j = 0; j < 9; j++) {
       /* Create word + special char + number overhead : 20 * 10 * number of lines */
       String defword = word.concat(String.valueOf(specialChars.charAt(i))).concat(String.valueOf(j));
       String newword;
       if (defword.length() > 1)
        newword = defword.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
       else newword = defword;
       if (crack.testPassword(newword, entry)) {
        System.out.println("Found Password: " + newword);
        System.out.println(entry);
       }


      }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     e.printStackTrace();
    }
   });
  });

 }
 /**
  * 
  * 
  * 
  * 
  * STRING SPECIAL NUMBER 
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_STRING_special_number(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {
    try {

     for (int i = 0; i < specialChars.length(); i++)
      for (int j = 0; j < 9; j++) {
       /* Create word + special char + number overhead : 20 * 10 * number of lines */
       String defword = word.concat(String.valueOf(specialChars.charAt(i))).concat(String.valueOf(j));

       if (crack.testPassword(defword.toUpperCase(), entry)) {
        System.out.println("Found Password: " + defword.toUpperCase());
        System.out.println(entry);
       }


      }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     e.printStackTrace();
    }
   });
  });

 }

 /**
  * 
  * 
  * 
  * 
  * STRING SPECIAL NUMBER 
  * 
  * 
  * 
  * 
  * 
  * */
 public static void Search_string_special_number(List < Entry > entries, List < String > dictionary) {

  dictionary.parallelStream().forEach(word -> {
   entries.stream().parallel().forEach(entry -> {
    try {

     for (int i = 0; i < specialChars.length(); i++)
      for (int j = 0; j < 9; j++) {
       /* Create word + special char + number overhead : 20 * 10 * number of lines */
       String defword = word.concat(String.valueOf(specialChars.charAt(i))).concat(String.valueOf(j));

       if (crack.testPassword(defword, entry)) {
        System.out.println("Found Password: " + defword);
        System.out.println(entry);
       }


      }
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     e.printStackTrace();
    }
   });
  });

 }
}