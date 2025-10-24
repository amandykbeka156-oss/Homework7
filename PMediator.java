import java.util.*;

interface ChatMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
}

class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        System.out.println(user.getName() + " присоединился к чату.");
    }

    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) user.receive(message, sender);
        }
    }
}

abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public String getName() { return name; }

    public abstract void send(String message);
    public abstract void receive(String message, User sender);
}

class ChatUser extends User {
    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    public void send(String message) {
        System.out.println(name + " отправил: " + message);
        mediator.sendMessage(message, this);
    }

    public void receive(String message, User sender) {
        System.out.println(name + " получил сообщение от " + sender.getName() + ": " + message);
    }
}

public class PMediator {
    public static void main(String[] args) {
        ChatMediator chat = new ChatRoom();

        User bekarys = new ChatUser(chat, "Бекарыс");
        User bauka = new ChatUser(chat, "Баука");
        User sayat = new ChatUser(chat, "Саят");
        User imok = new ChatUser(chat, "Имок");


        chat.addUser(bekarys);
        chat.addUser(bauka);
        chat.addUser(sayat);
        chat.addUser(imok);

        bekarys.send("Привет всем!");
        bauka.send("Привет, Бекарыс!");
    }
}