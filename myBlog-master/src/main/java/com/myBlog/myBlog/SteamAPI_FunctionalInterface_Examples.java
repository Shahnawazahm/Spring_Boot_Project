package com.myBlog.myBlog;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class SteamAPI_FunctionalInterface_Examples {

    //testing stream API
    public static void main(String[] args){
        //WORKING WITH FUNCTIONAL INTERFACES

        //1: predicate functional interface: takes an input and generates boolean value [test()]

        Predicate<Integer> val = y->y%2==0; //y value is substituted in next line via test()
        boolean resInt = val.test(10); // test() is present in Predicate; //takes input
        System.out.println("Int result: "+resInt);

        Predicate <String> stVal= str->str.equals("mike");
        boolean resStr=stVal.test("help");
        System.out.println("String resul: "+resStr);

        List<Integer> listVal = Arrays.asList(10,20,25,28,30); //creates a mutable List
        //List<Integer> originalList = List.of(1, 2, 3, 4, 5, 6); //creates immutable List
        System.out.println("Value before filtration:");
        for(Integer a:listVal){
            System.out.println(a);
        }

       /*
            ArrayList<Integer> al = new ArrayList();
            al.add(10);
            al.add(20);
            for(Integer a:al){
                System.out.println(a);
            }
        */

        List<Integer> evenNumber = listVal.stream() //stream() should be applied on data structure
                                    .filter(x->x%2==0) //then use filter method (can be differenct) which takes predicate and returns boolean
                                    .toList();  // and stores the filtered information in the list

        /*
         Stream api takes a value from list, filters the value (depending upon the condition) according to the condition provided, and returns
         boolean value, if true, will collect the data and store in the new list (evenNumbers here) if false won't store at all. So, will store
         the data.
         */

        System.out.println("Value after filtration:");
        for(Integer a:evenNumber){
            System.out.println(a);
        }
        List<String> listName = Arrays.asList("mike","adam","muz","stallion","muz"); //creates a mutable List

        List<String> strFil = listName.stream()
                                      .filter(str->str.startsWith("m"))
                                      .toList(); //filters names starting with m

        List<String> nameCheck = listName.stream()
                                         .filter(str->str.equals("muz"))
                                          .toList(); //filters names starting with m

        System.out.println("Names starting with m");
        for(String stFil:strFil){
            System.out.println(stFil);
        }

        System.out.println("Name with muz");
        for(String stFil:nameCheck){
            System.out.println(stFil);
        }

        //2: Function functional interface: Takes an input and generates an output [apply() ]

        Function<String,Integer> result = str->str.length(); //or String::length; --> via method reference
        Integer strLength = result.apply("mike"); //takes input
        System.out.println("Length is "+strLength);

        Function<Integer,Integer> res = i->i+10; //i value is substituted in next line via apply()
        Integer resVal=res.apply(30);
        System.out.println(resVal);

        List<Integer> data = Arrays.asList(1,27,21,12,21,1,58,70); //creates a mutable List
        List<Integer> da = data
                           .stream()
                           .map(i->i+10)
                           .toList(); //map() takes function functional interface, should take input and produce output without filtration
        System.out.println(da);

        List<String> names = Arrays.asList("mike","adam","muz","stallion","muz"); //creates a mutable List
        List<String> upperCase = names
                                .stream()
                                .map(name->name.toUpperCase()) //or String::toUpperCase --> via method reference
                                .toList();
        System.out.println("Upper case Names:"+upperCase);

        List<Integer> sortedData = data
                                .stream()
                                .sorted()
                                .toList();
        System.out.println("Sorted data list: "+sortedData);

        List<Integer> distinctValues = data
                                   .stream()
                                   .distinct()
                                   .toList();
        System.out.println("distinct Values are: "+distinctValues);


        //3: Consumer functional interface: takes an input produces no output (mostly used to initializes a number) [accept()]
        Consumer<Integer> resNum = number-> System.out.println(number); //System.out::println; --> via method reference
        resNum.accept(100); //takes input

        List<String > namesList = Arrays.asList("mike","adam","sam");
        Consumer<String> nameList = name-> System.out.println(name); //System.out::println; --> via method reference
        //nameList here is a Consumer functional interface
        namesList.forEach(nameList); //here namesList is supplied to name in the previous line of code and forEach is printing names from that sout()

        //4: Supplier functional interface: takes no input but produces output [get()]
        Supplier<Integer> x = ()->new Random().nextInt(500);
        Integer y = x.get(); //takes no input
        System.out.println("Value is y: "+y);


        //More streamAPI examples

        //for Login and LoginDTO class
        List<Login> logins = Arrays.asList(
                new Login("mike","password"),
                new Login("stallion","password"),
                new Login("adam","password")
        );
        System.out.println(logins); //Login objects

        List<LoginDTO> dtos =logins.stream().map(SteamAPI_FunctionalInterface_Examples::mapToDTO).toList(); // method reference:--> TestClass1::mapToDTO
        System.out.println(dtos); //loginDTO objects


        //for Employee class

        //list of employee details but stored in list as objects
        List<Employee> employees = Arrays.asList(
                new Employee("mike","hyd",30),
                new Employee("adam,","blr",20),
                new Employee("aslam","chn",35),
                new Employee("Stallion","chn",30)
        );
        //loading employees with age>30
        List<Employee> emps = employees.stream().filter(emp -> emp.getAge() > 30).toList();
        System.out.println("Employees whose age is above 30");
        for(Employee e:emps){
            System.out.println("Name: "+e.getName());
            System.out.println("City: "+e.getCity());
            System.out.println("Age: "+e.getAge());
        }

        //loading employees starting with 's'
        List<Employee> empsWithS = employees.stream().filter(emp->emp.getName().toLowerCase().startsWith("s")).toList();
        System.out.println("Employees whose name start with s");
        for(Employee e:empsWithS){
            System.out.println("Name: "+e.getName());
            System.out.println("City: "+e.getCity());
            System.out.println("Age: "+e.getAge());
        }

        //get employee age whose age falls in an even number, square them and find sum of squared values
        List<Integer> empAgesSquared = employees.stream()
                .filter(e -> e.getAge()%2 == 0) //get only even ages
                .map(e -> e.getAge() * e.getAge()) //square those ages (even)
                .toList(); //java 16+ feature or java 8+ feature-->  .collect(Collectors.toList() //and store to list again

        int total = empAgesSquared.stream()
                .reduce(0, (employee, ageSum) -> { //get employee squared age, and ageSum =0 to store squared ages
                    return ageSum + employee;  //via method reference:--> .reduce(0, Integer::sum); // return the aged sum of square
                });

        System.out.println(total);

        //grouping employee by their ages

        Map<Integer, List<Employee>> collectGroupByAge = employees.stream().collect(Collectors.groupingBy(Employee::getAge)); //or e->e.getAge()
        for(Map.Entry<Integer,List<Employee>> entry :collectGroupByAge.entrySet()){
            int age = entry.getKey();
            List<Employee> empWithAge = entry.getValue();
            System.out.println("----Age "+ age + "----");
            int i = 1;
            for(Employee e:empWithAge){
                System.out.println("Details of emp "+i);
                System.out.println("City: "+e.getCity());
                System.out.println("Name: "+e.getName());
                i++;
            }
        }

        //grouping employee by their cities
        Map<String, List<Employee>> collectGroupByCity = employees.stream().collect(Collectors.groupingBy(Employee::getCity)); //or e->e.getAge()
        for(Map.Entry<String,List<Employee>> entry :collectGroupByCity.entrySet()){
            String city = entry.getKey();

            List<Employee> empWithCity = entry.getValue();
            System.out.println("----Age "+ city + "----");
            int i = 1;
            for(Employee e:empWithCity){
                System.out.println("Details of emp "+i);
                System.out.println("City: "+e.getAge());
                System.out.println("Name: "+e.getName());
                i++;
            }
        }

    }

    static LoginDTO mapToDTO(Login login){
        LoginDTO dto = new LoginDTO();
        dto.setUsername(login.getUsername());
        dto.setPassword(login.getPassword());

        return dto;
    }
}