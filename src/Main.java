
public class Main {
    public static void main(String[] args) {
        // Создаем базовое число = 1
        Int one = new Int();
        one.increment();

        // Удвоение: 1 → 2 → 4 → 8 → 16 → 32 → 64 → 128 → 256 → 512
        Int two = new Int();
        two.add(one);
        two.add(one);

        Int four = new Int();
        four.add(two);
        four.add(two);

        Int eight = new Int();
        eight.add(four);
        eight.add(four);

        Int sixteen = new Int();
        sixteen.add(eight);
        sixteen.add(eight);

        Int thirtyTwo = new Int();
        thirtyTwo.add(sixteen);
        thirtyTwo.add(sixteen);

        Int sixtyFour = new Int();
        sixtyFour.add(thirtyTwo);
        sixtyFour.add(thirtyTwo);

        Int oneHundredTwentyEight = new Int();
        oneHundredTwentyEight.add(sixtyFour);
        oneHundredTwentyEight.add(sixtyFour);

        Int twoHundredFiftySix = new Int();
        twoHundredFiftySix.add(oneHundredTwentyEight);
        twoHundredFiftySix.add(oneHundredTwentyEight);

        Int fiveHundredTwelve = new Int();
        fiveHundredTwelve.add(twoHundredFiftySix);
        fiveHundredTwelve.add(twoHundredFiftySix);

        // Складываем: 1000 = 512 + 256 + 128 + 64 + 32 + 8
        Int result = new Int();
        result.add(fiveHundredTwelve);
        result.add(twoHundredFiftySix);
        result.add(oneHundredTwentyEight);
        result.add(sixtyFour);
        result.add(thirtyTwo);
        result.add(eight);

        System.out.println(result);

        System.out.println("Делим на 2");

        result.division(2);

        System.out.println(result);

        System.out.println("Делим на 3 (в ответе должно прийти целое число. Остаток отбрасываем)");

        result.division(3);

        System.out.println(result);

        System.out.println("попытка деления на 0 (ответ не должен измениться)");

        result.division(0);

        System.out.println(result);

    }
}