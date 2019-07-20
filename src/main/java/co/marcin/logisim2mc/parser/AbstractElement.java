package co.marcin.logisim2mc.parser;

public abstract class AbstractElement implements LogisimElement {
    protected final int y;
    protected final int x;

    public AbstractElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
