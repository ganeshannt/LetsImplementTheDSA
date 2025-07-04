package com.practise.designpattern.creational;


import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * This is eager initialization concept where
 * as soon as JVM start the object will be created irrespective whether it got accessed by
 * any code in application or not.
 * When to use: One possible usage can be let say your application has some static cache which is required to be loaded.
 * Drawback: As mention consumes resource even if application does not use it.
 */
class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
        // Do your init work here
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}


/**
 * Here we described creating singleton using enum but why?
 * Let's say in Lazy Init method you access constructor by reflection (Reason being you can access private constructor using reflection!!)
 * and create the object which eventually creates the problem by having multiple instances.
 * <p>
 * ENUM's constructor gets invoked by JVM not by User who is using so it is safe to use.
 * Another advantage of using enum is, we don't need to worry about threads as it is thread safe.
 * It also solved the problem of Serialization as JVM takes care to return the same object.
 */
enum EnumSingleton {
    INSTANCE;

    public void doSomething() {
        System.out.println("Cool");
    }
}


/**
 * Lazy initialization mean application will create instance when it is requested.
 * However, this can be used when you have non-thread-safe application. If used in multi-threading it might break,
 * Why? because your getinstance method if invoked by two threads at the same time then!!!!
 * <p>
 * When to use?  Non-thread safe and creating common resource like db connection.
 */
class LazySingleton implements Serializable {
    private static LazySingleton instance = null;

    private LazySingleton() {

    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}


/**
 * As mentioned in Lazy Initialization if our get instance method invoked by two threads at the
 * same time, then there are chances that two objects created, and we violate a singleton pattern.
 * To avoid, we have two choices:
 * 1. Create getinstance synchronized so that only one instance can invoke that method. However, disadvantage is lets say
 * there are 3 thread t1 is inside getinstance and t2,t3 waiting. Now t2 will get into method and simply return instance created by
 * t1 and t3 still waiting. So it had lead to unnecessary of locks.
 * <p>
 * 2. To avoid we have synchronized block which we will implement here.
 */
class MultithreadSingleton {
    private static volatile MultithreadSingleton instance = null;

    private MultithreadSingleton() {

    }

    public static MultithreadSingleton getInstance() {
        // Question arise why we have two null check here.
        // Reason for first null check is same as explained in method level synchronization why create lock if our object is already created.
        if (instance == null) {
            // Our method is static, so we have class level locking here
            synchronized (MultithreadSingleton.class) {
                //Reason for second null check is lets say two objects are come inside first null at same time
                // One call has taken lock and proceeds for creating an object first time. Now once lock is released for t1
                // t2 should not create an object because its already created, and that's why we have a second null check.
                if (instance == null) {
                    instance = new MultithreadSingleton();
                }
            }
        }
        return instance;
    }
}


/**
 * Let's say your singleton has implemented serialization. Now what will happen if you serialize object and deserialize?
 * During deserialization, it will create a new object every time if we go in traditional way.
 * To resolve it, add readResolve method which will ensure that during deserializing we return the same instance.
 * <p>
 * Check Main class for violation example
 */
class SerializableSingleton implements Serializable {
    private static SerializableSingleton instance = null;

    private SerializableSingleton() {

    }

    public static SerializableSingleton getInstance() {
        if (instance == null) {
            instance = new SerializableSingleton();
        }
        return instance;
    }

    /**
     * This is the key method which is responsible during deserialization process
     * This method gets invoked, and we are simply returning an already created object
     *
     * @return
     */
    protected Object readResolve() {
        return instance;
    }
}


// References  https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples
//https://www.geeksforgeeks.org/java-singleton-design-pattern-practices-examples/


//Before jumping into a pattern just explain what is lazy loading and eager loading
// Mainly this class used to show violations using serializable and reflection.

public class SingletonDesignPattern {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        exampleSerialization();
        //exampleReflection();
    }

    private static void exampleSerialization() throws IOException, ClassNotFoundException {
        LazySingleton lazySingleton = LazySingleton.getInstance();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("object.obj"));
        objectOutputStream.writeObject(lazySingleton);
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("object.obj"));
        LazySingleton deserializedLazy = (LazySingleton) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println("Object 1 :" + lazySingleton.hashCode());
        System.out.println("Object 2 :" + deserializedLazy.hashCode());

        SerializableSingleton serializableSingleton = SerializableSingleton.getInstance();
        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream("object1.obj"));
        objectOutputStream2.writeObject(serializableSingleton);
        objectOutputStream2.close();
        ObjectInputStream objectInputStream2 = new ObjectInputStream(new FileInputStream("object1.obj"));
        SerializableSingleton deserializedInstance = (SerializableSingleton) objectInputStream2.readObject();
        objectInputStream2.close();
        System.out.println("SerializableSingleton Object 1 :" + serializableSingleton.hashCode());
        System.out.println(" SerializableSingleton Object 2 :" + deserializedInstance.hashCode());
    }

    private static void exampleReflection() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor[] constructors = LazySingleton.class.getDeclaredConstructors();
        //Knowing only one constructor taking it using index
        Constructor constructor = constructors[0];
        constructor.setAccessible(true);
        LazySingleton lazySingleton = (LazySingleton) constructor.newInstance();
        LazySingleton instance = LazySingleton.getInstance();
        System.out.println("Reflected hashcode singleton :" + lazySingleton.hashCode());
        System.out.println("Singleton instance : " + instance.hashCode());

        //The Solution to this is go by enum
        EnumSingleton.INSTANCE.doSomething();
    }

}
