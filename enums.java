

enum TrafficLight {
    RED {
        @Override
        public void action() {
            System.out.println("Stop!");
        }
    },
    GREEN {
        @Override
        public void action() {
            System.out.println("Go!");
        }
    },
    YELLOW {
        @Override
        public void action() {
            System.out.println("Caution!");
        }
    };

    public abstract void action();
}

public class enums {
    public static void main(String[] args) {
        TrafficLight light = TrafficLight.RED;
        light.action();
    }
}
