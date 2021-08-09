package mcc53.com;

import mcc53.com.utils.Counter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringProjectApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void counterTest() {
        Counter counter1 = applicationContext.getBean(Counter.class);
        Counter counter2 = applicationContext.getBean(Counter.class);
        Counter counter3 = applicationContext.getBean(Counter.class);

        counter1.counter();
        counter2.counter();
        counter3.counter();

        System.out.println(counter1.getValue());
        System.out.println(counter2.getValue());
        System.out.println(counter3.getValue());
    }

//    @Test
//    void prototypeTest() {
//        Produk produk1 = applicationContext.getBean("produkSmartPhone", Produk.class);
//        produk1.setName("Samsung");
//        produk1.setCategory("smartphone");
//        System.out.println(produk1.getHarga());
//
//
//    }

    @Test
    void builderTest() {
//        Produk produk = new Produk()
//                .builder()
//                .name("Samsung")
//                .build();
//
//        System.out.println(produk.toString());
    }
}
