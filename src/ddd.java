import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hoa on 13/03/2017.
 */
public class ddd {
    public static void main(String[] args) {
        OperatingSystemMXBean operatingSystemMXBean= ManagementFactory.getOperatingSystemMXBean();
        int i=0;
        while (i<500){
            try {
                Method method =operatingSystemMXBean.getClass().getMethod("getFreePhysicalMemorySize");
                method.setAccessible(true);
                Object value=method.invoke(operatingSystemMXBean);
                System.out.println(method+" = "+((long)value/(1024*1024)));
                Thread.sleep(500);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;

        }



    }
}
