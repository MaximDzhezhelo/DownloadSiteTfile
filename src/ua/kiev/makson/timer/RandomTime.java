package ua.kiev.makson.timer;

public class RandomTime {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRandomTime() {
        x++;
        if (x < 3) {
            y += 2;
        } else if (x >= 3 && x <= 7) {
            y += (int) (200 + (Math.random() * (300 - 200) + 1));
        } else {
            y += (int) (2000 + (Math.random() * (3000 - 2000) + 1));
        }
        return y;
    }
}
