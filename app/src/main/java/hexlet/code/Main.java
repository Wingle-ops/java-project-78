//package hexlet.code;
//
//import hexlet.code.schemas.MapSchema;
//import hexlet.code.schemas.StringSchema;
//import hexlet.code.schemas.NumberSchema;
//import hexlet.code.schemas.BaseSchema;
//import java.util.Map;
//import java.util.HashMap;
//
//public class Main {
//    public static void main(String[] args) {
//        Validator v = new Validator();
//        StringSchema schemaStr = v.string();
//        schemaStr.required().minLength(2).minLength(5).contains("hi");
//        System.out.println(schemaStr.isValid(null));                             // false
//        System.out.println(schemaStr.isValid("Hello, mister Mihail"));           // false
//        System.out.println(schemaStr.isValid("Hi, mister Mihail"));              // true
//        System.out.println();
//
//        NumberSchema schemaNum = v.number();
//        schemaNum.required().positive().range(5, 10);
//        System.out.println(schemaNum.isValid(null));                            // false
//        System.out.println(schemaNum.isValid(1));                               // false
//        System.out.println(schemaNum.isValid(7));                               // true
//        System.out.println();
//
//        MapSchema schemaMap = v.map();
//        Map<String, String> map = new HashMap<>();
//        schemaMap.required().sizeof(2);
//        System.out.println(schemaMap.isValid(null));                              // false
//        map.put("Hello", "One");
//        System.out.println(schemaMap.isValid(map));                                    // false
//        map.put("Hi", "Two");
//        System.out.println(schemaMap.isValid(map));                                    // true
//        System.out.println();
//
//        MapSchema schemaMap1 = v.map();
//        Map<String, BaseSchema<String>> schemas = new HashMap<>();
//        schemas.put("firstName", v.string().required());
//        schemas.put("lastName", v.string().required().minLength(2));
//        schemaMap1.shape(schemas);
//
//        Map<String, String> human1 = new HashMap<>();
//        human1.put("firstName", "John");
//        human1.put("lastName", "Smith");
//        System.out.println(schemaMap1.isValid(human1));                                // true
//
//        Map<String, String> human2 = new HashMap<>();
//        human2.put("firstName", "John");
//        human2.put("lastName", null);
//        System.out.println(schemaMap1.isValid(human2));                                // false
//
//        Map<String, String> human3 = new HashMap<>();
//        human3.put("firstName", "Anna");
//        human3.put("lastName", "B");
//        System.out.println(schemaMap1.isValid(human3));                                // false
//
//    }
//}
