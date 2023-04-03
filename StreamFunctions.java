import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

//class showing different stream intermediate and terminal functions
public class StreamFunctions {

    public static class Employee{
        public String name;
        public Integer age;
        public  String city;
        public Integer salary;

        public Employee(String name, Integer age, String city, Integer salary){
            this.age=age;
            this.salary=salary;
            this.name=name;
            this.city=city;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Integer getSalary() {
            return salary;
        }

        public void setSalary(Integer salary) {
            this.salary = salary;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            if(this.name==employee.name)
                return true;
            return false;

        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
    public static void main(String[] args) {

        //1. flatMap in stream. An intermediate operation like map
        List<List<Integer>> fm=new ArrayList<>();

        List<Integer> subList= Arrays.asList(1,2,3);
        fm.add(subList);
        subList=Arrays.asList(4,5,6);
        fm.add(subList);
        subList=Arrays.asList(7,8,9);
        fm.add(subList);

        System.out.println("Flatmap Result");
        fm.stream().flatMap(subLi->Stream.of(subLi.toArray())).forEach(e-> System.out.print(e+" "));
        System.out.println(" ");
        System.out.println("Flatmap Result");
        fm.stream().flatMap(subLi->subLi.stream()).forEach(e-> System.out.print(e+" "));
        System.out.println(" ");


        //2. reduce in stream a terminal ops. It minimises a list to one single value on some condition like sum, min ,max
        List<Integer> red=new ArrayList<>();
        red.add(3);
        red.add(6);
        red.add(9);
        red.add(4);

//        Here, we are givin zero as a starting value of a and 3 as a value of b. sum of all odd numbers
        //here we are giving default zero value to start with that's why return is int.
        int v=red.stream().reduce(0,(a, b)->{
            if(b%2==1){
                return a+b;
            }
            return a;


        }); //12 output
        System.out.println("Reduce fn Result");
        System.out.println(v);


        //here no default value first two value inside list will be compared . so return type is optional
        //finding max inside list
        Optional<Integer> opt=red.stream().reduce((a, b)->{
            if(a>b)
                return a;
            return b;

        });

        System.out.println("Reduce fn Result");
        System.out.println(opt.get());

        int product = IntStream.range(2, 8)
                .reduce((num1, num2) -> num1 * num2)
                .orElse(-1);
        System.out.println("Reduce fn Result");
        System.out.println(product);



        //3.same min, max, sum like reduce (terminal ops but, it takes comparator unlike reduce which takes function )

        System.out.println("Min fn Result");
        Optional<Integer> min=red.stream().min((a,b)->{
            if(a<b)
                return -1;
            return 1;
        });
        System.out.println(min.get());

        System.out.println("max fn Result");
        Optional<Integer> max=red.stream().max((a,b)->{
            if(a<b)
                return -1;
            return 1;
        });
        System.out.println(max.get());

        // 4. sum using stream ...sum method
        int sum=red.stream().mapToInt(e->e).sum();
        System.out.println("sum fn Result");
        System.out.println(sum);

        //groupingby in Collectors class
        List<String> str=new ArrayList<>();
        str.add("yash");
        str.add("agarwal");
        str.add("kir");
        str.add("yash");

        //here collect method takes two args :-> Function.identity

        //(classifer, downstream)
        Map<String ,Long> mp=str.stream().collect(
                groupingBy(Function.identity(),Collectors.counting()));
        System.out.println("groupingBy in Collectors class fn Result");
        System.out.println(mp);

        Employee emp1=new Employee("yash",24,"Hapur",10000);
        Employee emp2=new Employee("Shubham",27,"Hapur",20000);
        Employee emp3=new Employee("Ayush",25,"Hapur",30000);
        Employee emp4=new Employee("yash",26,"Noida",60000);
        Employee emp5=new Employee("utkarsh",24,"Noida",20000);

        List<Employee> employeeList=Arrays.asList(emp1,emp2,emp3,emp4,emp5);

        //groupingby all employess on the city attribute
        // grouping by using classifier function giving city as a classifier only, no downstream provided here
        Map<String,List<Employee>> res=employeeList.stream().collect(
                groupingBy(Employee::getCity));
        System.out.println("groupingBy in Collectors class fn Result");
        System.out.println(res.values());

        // grouping by city but collecting only employee names who belongs to same city as a downstream function
        Map<String,List<String>> ress=employeeList.stream().collect(
                groupingBy(Employee::getCity,mapping(Employee::getName,toList()))
        );
        System.out.println("groupingBy in Collectors class fn Result");
        System.out.println(ress.values());

        //counting of Employee who has same city
        Map<String,Long> mpp=employeeList.stream().collect(
                groupingBy(Employee::getCity,counting()
                ));
        System.out.println("groupingBy in Collectors class fn Result");
        System.out.println(mpp);


        //finding out city wise max salary
        Map<String,Optional<Employee>> maxSal=employeeList.stream().collect(
                groupingBy(Employee::getCity,maxBy((Employee a,Employee b)->{
                    if(a.getSalary()>b.getSalary())
                        return 1;
                    return -1;
                })));

        System.out.println("max salary by city using groupingBy in Collectors class fn Result");
        maxSal.entrySet().forEach(e-> System.out.println(e.getKey()+" "+e.getValue().get().getName()));

        //distinct an intermediate ops
        //distinct uses equals & hashcode to find unique value
        List<Integer> list=new ArrayList<>(Arrays.asList(-43,-3561,2,3,4,3,2,2,1,6,53));
        System.out.println("distinct fn Result");
        list.stream().distinct().forEach(e-> System.out.println(e));

        // distinct using overriding equals & hashcode on the name parameter
        System.out.println("distinct fn Result");
        employeeList.stream().distinct().forEach(e-> System.out.println(e.getName()+" "+e.getSalary()));


        //sorted an intermediate ops, in descending order
        System.out.println("sorted fn Result");
        list.stream().sorted((a,b)->{
            if(a>b)
                return -1;
            return 1;
        }).forEach(e-> System.out.print(e+" "));
        System.out.println(" ");

        //limit an intermediate ops. to return first n elements pass into limit function
        System.out.println("limit fn Result");
        list.stream().limit(4).forEach(e-> System.out.println(e+" "));

        //anymatch an terminal ops if any of the element in a list matches -3561 it returns true
        System.out.println("anyMatch fn Result");
        System.out.println(list.stream().anyMatch(e->e==(-3561)));

        //allmatch a terminal ops if all of the element in a list matches -3561 it returns true
        System.out.println("allMatch fn Result");
        System.out.println(list.stream().allMatch(e->e<53));


    }
}