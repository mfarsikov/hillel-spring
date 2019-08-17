package hillel.spring;

import javax.transaction.Transactional;

public class ProxyExample {
    public static void main(String[] args) {
        Myservice myservice = myService();

        myservice.doWithoutTransaction();
    }

    public static Myservice myService() {
        return new ProxyMyservice(new Myservice());
    }
}

class Myservice {

    @Transactional
    public void doInTransaction() {
        System.out.println("In transaction");
    }

    public void doWithoutTransaction() {
        System.out.println("Without transaction");
        doInTransaction();
    }
}

class ProxyMyservice extends Myservice {
    private Myservice delegate;

    public ProxyMyservice(Myservice delegate) {
        this.delegate = delegate;
    }

    @Override
    public void doInTransaction() {
        System.out.println("Start transaction");
        delegate.doInTransaction();
        System.out.println("Close transaction");
    }

    @Override
    public void doWithoutTransaction() {
        delegate.doWithoutTransaction();
    }

}
