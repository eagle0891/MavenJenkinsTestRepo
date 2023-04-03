package Concepts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class ArrayListDemo {

    public static void main(String[] args){
//        arrayListIteration();
//        arrayListContainingCollection();
//        arrayListMethods();
    }

    //static array
    public static void staticArrayExample() {
        int i[] = new int[4];

        i[0] = 100;
        i[3] = 200;
        i[4] = 500; //outside the size of the static array. This will cause the "array index out of bounds" exception

        System.out.println(i[3]);
    }

    public static void dynamicArrayListExample(){

        //this is raw and not recommended
        ArrayList rawList = new ArrayList();
        rawList.add(1);
        rawList.add(300);
        rawList.add("test");
        rawList.add(2.5);
        System.out.println(rawList);

        //need to add an object type to fix the "raw array" issue
        //default VIRTUAL capacity of dynamic ArrayList is 10 set by Java. Each item in th elist set to null by default until populated by code
        //PHYSICAL capacity is the size of the array written in code
        ArrayList<Object> list = new ArrayList<>(20); //The virtual capacity size can be amended by adding the initialCapacity as value e.g. 20
        list.add(1);
        list.add(300);
        list.add("test");
        list.add(2.5);
        System.out.println("Size of Array is: " + list.size()); //Physical size
        System.out.println(list.get(2));
        list.add(800);
        System.out.println("Size of Array is: " + list.size()); //Physical size
    }

    public static void genericArrayListExample(){
        ArrayList<Integer> genericIntegerList = new ArrayList<>(); //only accepts Integers
        ArrayList<Double> genericDoubleList = new ArrayList<>(); //only accepts Double
        ArrayList<String> genericStringList = new ArrayList<>(); //only accepts Strings
    }

    public static void arrayListIteration(){
        ArrayList<String> list = new ArrayList<>();

        list.add("Tom");
        list.add("Mohammed");
        list.add("jake");
        list.add("Daniel");
        list.add("Ade");
        list.add("Mr Schmidt");

        //Typical for loop
        for (int i=0; i<list.size(); i++) {
            System.out.println("Typical for loop: " + list.get(i));
        }

        System.out.println("-----------------");

        //Enhanced for loop equivalent
        for (String s : list) {
            System.out.println("Enhanced for loop: " + s);
        }

        System.out.println("-----------------");

        //streams with lambda
        list.stream().forEach(ele -> System.out.println("Streams with lambda: " + ele));

        System.out.println("-----------------");

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println("Iterator: " + it.next());
        }
    }

    public static void arrayListContainingCollection(){
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        System.out.println(numbers.size() + " numbers in the list: " + numbers);

        ArrayList<String> footballTeams = new ArrayList<>(Arrays.asList("Arsenal", "Liverpool", "Everton", "Newcastle", "Aston Villa"));
        System.out.println(footballTeams.size() + " football teams in the list: " + footballTeams);
    }

    public static void arrayListMethods(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Mohammed");
        list.add("jake");
        list.add("Daniel");
        list.add("Ade");
        list.add("Mr Schmidt");
        System.out.println(list);

        ArrayList<String> vehicles = new ArrayList<>();
        vehicles.add("Bus");
        vehicles.add("car");
        vehicles.add("bike");
        vehicles.add("Skateboard");
        vehicles.add("unicycle");
        vehicles.add("pogo stick");
        System.out.println(vehicles);

        list.addAll(vehicles);
        System.out.println(list);

        list.addAll(2, vehicles); //ad "vehicles" list into the 2nd index of the "list"
        System.out.println(list);

        ArrayList<String> clonedList = (ArrayList<String>)list.clone();
        System.out.println("Cloned list is: " + clonedList);
        System.out.println("List contains pogo stick: " + list.contains("pogo stick"));
        System.out.println("List contains surfboard: " + list.contains("surfboard"));
        System.out.println("List contains jack: " + list.contains("jack"));
        System.out.println("Tom is the first in the list: " + (list.indexOf("Tom")==0));
        System.out.println("Mohammed is the first in the list: " + (list.indexOf("Mohammed")==0));
        System.out.println("Mohammed is first or second in the list: " + (list.indexOf("Mohammed")<2));
        System.out.println("What is the last index of the list? " + (list.lastIndexOf("pogo stick")));
        System.out.println("Which index of the list is TestName in? " + (list.lastIndexOf("TestName"))); //NOTE: this will return -1 (and not error)

        list.remove("pogo stick"); //removes first found in the list
        System.out.println(list);
        list.remove("pogo stick");
        System.out.println(list);

        list.removeIf(item -> item=="Tom");
        System.out.println(list);

        ArrayList<Integer> digits = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        System.out.println(digits);
        digits.removeIf(num -> num%2 == 0); //"->" is a lambda function //remove all even numbers
        System.out.println(digits);

        ArrayList<String> carMakes = new ArrayList<>(Arrays.asList(
                "Ford", "Peugeot", "BMW", "Seat", "Volkswagen", "Volvo", "Ferrari", "Mini", "BMW", "Toyota"));
        System.out.println(carMakes);
        carMakes.retainAll(Collections.singleton("BMW"));
        System.out.println(carMakes);

        ArrayList<Integer> moreDigits = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        System.out.println();
        ArrayList<Integer> subList = new ArrayList<>(moreDigits.subList(2,7)); //up to and not including the 7th index
        System.out.println(subList);

        ArrayList<String> trainers = new ArrayList<>(Arrays.asList(
                "Nike", "Adidas", "Puma", "Fila", "Gola", "Converse", "Vans", "Hummel", "New Balance", "Asics"));
        System.out.println(trainers);

        Object[] arr = trainers.toArray();
        System.out.println(Arrays.toString(arr));

        for (Object o : arr) {
            System.out.println(o);
        }

    }
}