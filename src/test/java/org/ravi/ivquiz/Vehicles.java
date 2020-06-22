package org.ravi.ivquiz;

import org.junit.Test;

// dont recall the reason for this
public class Vehicles {
    class GenericEngine {
        public String type = "Generic";
    }
    class CombustionEngine extends GenericEngine {
        public String type = "Combustion";
    }
    class JetEngine extends CombustionEngine {
        public String type = "Jet";
    }

    class Car {
        public void setEngine(Object engine) {
            System.out.printf("Unknown engine type [%s]%n", engine);
        }
        public void setEngine(GenericEngine engine) {
            System.out.printf("Generic engine [%s]%n", engine.type);
        }
        public void setEngine(CombustionEngine engine) {
            System.out.printf("Combustion engine [%s]%n", engine.type);
        }
    }

    @Test
    public void jetCar() {
        JetEngine jetEngine = new JetEngine();
        new Car().setEngine(jetEngine);
    }
}
