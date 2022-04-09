package zad2;

import pakiet.A;
import pakiet.ListCreator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<ListCreator> c1 = ListCreator.class;
        Class<pakiet.Main> c2 = pakiet.Main.class;
        Class<pakiet.A> c3 = pakiet.A.class;
        Class<pakiet.B> c4 = pakiet.B.class;
        Class<pakiet.C> c5 = pakiet.C.class;
        printDataClass(c1);
        printDataClass(c2);
        printDataClass(c3);
        printDataClass(c4);
        printDataClass(c5);
        System.out.println("Wywolanie Metody");
        A newObjectA = A.class.getConstructor(int.class).newInstance(20);
        Method m = newObjectA.getClass().getMethod("getPoleA");
        m.invoke(newObjectA);
    }

    public static void printDataClass(Class c) {
        System.out.println(c);
        System.out.println("POLA");
        Field[] fields = c.getFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i]);
        }

        System.out.println("KONSTRUKTORY");
        for (int i = 0; i < c.getDeclaredConstructors().length; i++) {
            if (c.getDeclaredConstructors()[i].getParameterCount() > 0) {
                System.out.println(c.getDeclaredConstructors()[i]);
            }
        }

        System.out.println("METODY");
        if (c.getMethods().length != 0) {
            for (int i = 0; i < c.getMethods().length; i++) {
                System.out.println(c.getMethods()[i]);
            }
        } else {
            System.out.println("BRAK");
        }



        System.out.println("Pola NadKlasy: ");
        Field [] fieldsSubClass = c.getSuperclass().getFields();
        for (int i = 0; i < fieldsSubClass.length ; i++) {
            System.out.println(fieldsSubClass[i]);
        }

        System.out.println("Wszystkie nadKlasy:");
        Class classes = c.getSuperclass();
        while (classes != null) {
            System.out.println(classes.getName());
            classes = classes.getSuperclass();
        }
        System.out.println("//////////////////////////////////////");
    }
}
