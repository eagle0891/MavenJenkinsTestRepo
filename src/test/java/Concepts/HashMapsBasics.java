package Concepts;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapsBasics {
    public static void main(String[] args){
        /*
        HashMaps no order - no indexing
        stores values - i.e. Key-Value <k,v>
        key can not be duplicate
        HashMap is not threadsafe
        */

//        basicHashMap();
    }

    public static void basicHashMap(){
        HashMap<String, String> capitalCitiesMap = new HashMap<>();
        capitalCitiesMap.put("UK", "London");
        capitalCitiesMap.put("France", "Paris");
        capitalCitiesMap.put("Nigeria", "Abuja");
        capitalCitiesMap.put("Brazil",  "Brasillia");
        capitalCitiesMap.put("USA", "Washington DC");
        capitalCitiesMap.put("USA", "Washington DC 123");
        capitalCitiesMap.put(null, "Lisbon");
        capitalCitiesMap.put(null, "Lisbon");
        capitalCitiesMap.put("Russia", "Moscow");
        capitalCitiesMap.remove("Russia");


        System.out.println(capitalCitiesMap.get("UK"));
        System.out.println(capitalCitiesMap.get("Germany")); // will return NULL if key is not found
        System.out.println(capitalCitiesMap.get("USA")); //will return the latest value if duplicate keys are present
        System.out.println(capitalCitiesMap.get(null));

        System.out.println(capitalCitiesMap.keySet());
        System.out.println(capitalCitiesMap.values());

        System.out.println("----------------------");

        //iterate HashMap
        //Iterator: over the keys: by using keySet()
        //while loop example
        Iterator<String> it =  capitalCitiesMap.keySet().iterator();

        while (it.hasNext()){
            String key = it.next();
            String value = capitalCitiesMap.get(key);
            System.out.println("Iterate over keys - while loop: Key = " + key + ", and value = " + value);
        }

        System.out.println("----------------------");

        //enhanced for loop equivalent
        for (String key : capitalCitiesMap.keySet()) {
            String value = capitalCitiesMap.get(key);
            System.out.println("Iterate over keys - enhanced for loop: Key = " + key + ", and value = " + value);
        }

        System.out.println("----------------------");

        //Iterator: over the set (pair): by using entrySet
        Iterator <Map.Entry<String, String>> it1 = capitalCitiesMap.entrySet().iterator();

        while (it1.hasNext()){
            Map.Entry<String, String> entry = it1.next();
            System.out.println("Iterate over the set with entrySet - while loop: Key = " + entry.getKey() + ", and value = " + entry.getValue());
        }

        System.out.println("----------------------");

        //enhanced for loop equivalent
        for (Map.Entry<String, String> entry : capitalCitiesMap.entrySet()) {
            System.out.println("Iterate over the set with entrySet - enhanced for loop: Key = " + entry.getKey() + ", and value = " + entry.getValue());
        }

        System.out.println("----------------------");
        //Iterate hashMap using lambda for each (Java 8)
        capitalCitiesMap.forEach((k,v) -> System.out.println("With lambda for each: Key = " + k + ", and value = " + v));
    }
}
