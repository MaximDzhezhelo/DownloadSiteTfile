package ua.kiev.makson.timer;

public class RandomTime {
    private int x = 0;
    private int y = 0;

    public int getX() {
        return x;
    }

    public int getRandomTime() {
        x++;
        y += (int) (Math.random() * (50 - 1) + 1);
        return (int) (Math.random() * (y - 1) + 1);
    }

}
