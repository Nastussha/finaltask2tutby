package strategy;

public interface Strategy {
    String env = System.getProperty("environment");

    void setStrategy ();

}
