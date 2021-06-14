import java.lang.reflect.InvocationTargetException;

public class TestingClass {
    @BeforeSuite
    public static void test1(){
        System.out.println("test 1");
    }
@Test(prior=1)
    public static void test2(){
        System.out.println("test 2");
    }
    @Test(prior=3)
    public static void test3(){
        System.out.println("test 3");
    }
    @AfterSuite
    //@Test(prior=2)
    public static void test4(){
        System.out.println("test 4");
    }
@AfterSuite
    public static void test5(){
        System.out.println("test 5");
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        MyTest.start(TestingClass.class);
    }
}
