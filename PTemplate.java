import java.util.Scanner;

abstract class Beverage {
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) addCondiments();
    }

    abstract void brew();
    abstract void addCondiments();

    void boilWater() { System.out.println("Кипятим воду"); }
    void pourInCup() { System.out.println("Наливаем в чашку"); }

    boolean customerWantsCondiments() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Добавить добавки? (y/n): ");
        String answer = sc.nextLine().trim().toLowerCase();
        return answer.startsWith("y");
    }
}

class Tea extends Beverage {
    void brew() { System.out.println("Завариваем чай"); }
    void addCondiments() { System.out.println("Добавляем сахар и лимон"); }
}

class Coffee extends Beverage {
    void brew() { System.out.println("Завариваем кофе"); }
    void addCondiments() { System.out.println("Добавляем сахар и молоко"); }
}

public class PTemplate {
    public static void main(String[] args) {
        Beverage tea = new Tea();
        System.out.println("Приготовление чая:");
        tea.prepareRecipe();

        Beverage coffee = new Coffee();
        System.out.println("\nПриготовление кофе:");
        coffee.prepareRecipe();
    }
}
