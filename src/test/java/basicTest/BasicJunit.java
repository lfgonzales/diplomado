package basicTest;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BasicJunit {

    @BeforeEach
    public void setup(){
    System.out.println("Before");
    }
    @AfterEach
    public void cleanup(){
        System.out.println("after");
    }

    @Test
    public void ThisAsTest(){
        System.out.println("TESTTT");
    }
    @Test
    public void ThisAsTest2(){
        System.out.println("TESTTT2");
    }
}
