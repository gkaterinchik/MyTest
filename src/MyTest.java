import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MyTest {
   public static void start(Class testClass) throws InvocationTargetException, IllegalAccessException {
      List<Method> TestingMethods = new ArrayList<>();
      Method[] allMethod = testClass.getDeclaredMethods();

      for (Method m:allMethod) {
         if(m.isAnnotationPresent(Test.class))
            TestingMethods.add(m);

      }
      TestingMethods.sort(Comparator.comparingInt((Method i)-> i.getAnnotation(Test.class).prior()));

      for (Method m:allMethod) {
         if(m.isAnnotationPresent(BeforeSuite.class)) {
            if(!TestingMethods.get(0).isAnnotationPresent(BeforeSuite.class))
            {TestingMethods.add(0, m);}
            else {
               throw new RuntimeException("более одного метода с аннотацией @BeforeSuite");
            }

         }
      }

      for (Method m:allMethod) {
         if(m.isAnnotationPresent(AfterSuite.class)) {
            if(!TestingMethods.get(TestingMethods.size()-1).isAnnotationPresent(AfterSuite.class))
            {TestingMethods.add(TestingMethods.size(), m);}
            else {
               throw new RuntimeException("более одного метода с аннотацией @AfterSuite");
            }

         }
      }
      for (Method m:TestingMethods) {
         m.invoke(null);

      }
   }


}
