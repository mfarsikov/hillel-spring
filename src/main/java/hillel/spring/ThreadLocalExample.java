package hillel.spring;

import lombok.SneakyThrows;

public class ThreadLocalExample {

    static ThreadLocal<String> global  = new ThreadLocal<>();

    public static void main(String[] args) {

        global.set("A");

        new Thread(() -> {
            global.set("B");
            run();
        }).start();

        run();

    }

    @SneakyThrows
    public static void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " sees: " + global.get());
            Thread.sleep(1000);
        }
    }
}
