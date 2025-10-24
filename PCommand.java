import java.util.Stack;

interface Command {
    void execute();
    void undo();
}

class Light {
    public void on() { System.out.println("Свет включен."); }
    public void off() { System.out.println("Свет выключен."); }
}

class Door {
    public void open() { System.out.println("Дверь открыта."); }
    public void close() { System.out.println("Дверь закрыта."); }
}

class Thermostat {
    private int temperature = 19;

    public void increase() {
        temperature++;
        System.out.println("Температура повышена до " + temperature + "°C");
    }

    public void decrease() {
        temperature--;
        System.out.println("Температура понижена до " + temperature + "°C");
    }
}

class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
    public void undo() { light.off(); }
}

class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) { this.light = light; }
    public void execute() { light.off(); }
    public void undo() { light.on(); }
}

class DoorOpenCommand implements Command {
    private Door door;
    public DoorOpenCommand(Door door) { this.door = door; }
    public void execute() { door.open(); }
    public void undo() { door.close(); }
}

class DoorCloseCommand implements Command {
    private Door door;
    public DoorCloseCommand(Door door) { this.door = door; }
    public void execute() { door.close(); }
    public void undo() { door.open(); }
}

class TemperatureUpCommand implements Command {
    private Thermostat thermostat;
    public TemperatureUpCommand(Thermostat thermostat) { this.thermostat = thermostat; }
    public void execute() { thermostat.increase(); }
    public void undo() { thermostat.decrease(); }
}

class TemperatureDownCommand implements Command {
    private Thermostat thermostat;
    public TemperatureDownCommand(Thermostat thermostat) { this.thermostat = thermostat; }
    public void execute() { thermostat.decrease(); }
    public void undo() { thermostat.increase(); }
}

class SmartHomeController {
    private Stack<Command> history = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
    }

    public void undoLastCommand() {
        if (history.isEmpty()) {
            System.out.println("Нет команд для отмены!");
        } else {
            Command last = history.pop();
            last.undo();
        }
    }
}

public class PCommand {
    public static void main(String[] args) {
        Light light = new Light();
        Door door = new Door();
        Thermostat thermostat = new Thermostat();

        SmartHomeController controller = new SmartHomeController();

        controller.executeCommand(new LightOnCommand(light));
        controller.executeCommand(new DoorOpenCommand(door));
        controller.executeCommand(new TemperatureUpCommand(thermostat));

        System.out.println("\nОтмена последней команды:");
        controller.undoLastCommand();
    }
}
