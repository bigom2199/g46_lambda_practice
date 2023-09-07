package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       TODO:  1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        storage.findMany(P -> P.getFirstName().equals("Erik")).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        storage.findMany(person -> person.getGender().equals(Gender.FEMALE)).forEach(System.out::println);



        System.out.println("----------------------");
    }

    /*
        TODO:  3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        storage.findMany(Person ->  Person.getBirthDate().isAfter(LocalDate.EPOCH.parse("1999-12-31")));



        System.out.println("----------------------");
    }

    /*
        TODO: 4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);

        System.out.println(storage.findOne(P ->P.getId() == 123));

        System.out.println("----------------------");

    }

    /*
        TODO:  5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
        System.out.println(
                storage.findOneAndMapToString(
                        person -> person.getId() == 456,
                        person -> "Name:" + person.getFirstName()+ " " + person.getLastName() + " born" + person
                )
        );
    }

    /*
        TODO:  6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here
    storage.findOneAndMapToString(
            person -> person.getGender()== Gender.MALE && person.getFirstName().startsWith("E"),
            Person::toString

    ).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        //Write your code here
      storage.findAndDo(
              person -> person.getFirstName().equals("Ulf"),
              System.out::println
      );
        System.out.println("----------------------");
    }

    /*
        TODO:  9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        //Write your code here
        storage.findAndDo(
                person -> person.getLastName().toLowerCase().contains(person.getFirstName().toLowerCase()),
                System.out::println
        );

        System.out.println("----------------------");
    }

    /*
        TODO:  10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here
    storage.findAndDo(
            person -> person.getFirstName().equalsIgnoreCase(new StringBuilder(person.getFirstName()).reverse()),
            Person -> System.out.println(Person.getFirstName() + " " + Person.getLastName())
    );
        System.out.println("----------------------");
    }

    /*
        TODO:  11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here
  storage.findAndDo(
          person -> person.getFirstName().startsWith("A"),
          Comparator.comparing(Person::getBirthDate)
  ).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here
 storage.findAndSort(
         person -> person.getBirthDate().getYear()< 1950,

 ).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here
     Comparator<Person> comparaLastName = new Comparator<Person>() {
         @Override
         public int compare(Person o1, Person o2) {
             return o1.getLastName().compareTo(o2.getLastName()) ;
         }

     };
     // with lambda
     Comparator<Person> compareFirstName =(Person o1,Person o2) -> o1.getFirstName().compareTo(o2.getFirstName()),
             // with Method Reference
             Comparator<Person> compareBirthDate = Comparator.comparing(Person::getBirthDate);
     // Stack
     Comparator<Person> all = comparaLastName.thenComparing(compareFirstName).thenComparing(compareBirthDate);
     storage.findAndSort(all).forEach(System.out::println);


        System.out.println("----------------------");
    }
}
