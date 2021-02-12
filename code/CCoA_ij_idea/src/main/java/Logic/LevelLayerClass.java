package Logic;

import Logic.LevelLayer;

public class LevelLayerClass implements LevelLayer, Comparable<LevelLayer> {
    private int zIndex;

    public LevelLayerClass(int zIndex) {
        this.zIndex = zIndex;
    }

    @Override
    public String toString() {
        return "zIndex: " + zIndex;
    }

    @Override
    public int hashCode() {
        int  twoPow32 = 2147483647;
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.getZIndex() % twoPow32);
        result = prime * result + (int) (this.getZIndex() / twoPow32);
        result = prime * result + prime;
        return result;
    }

    @Override
    public int getZIndex() {
        return this.zIndex;
    }

    @Override
    public int compareTo(LevelLayer o) {
        if (this.getZIndex() == o.getZIndex()) {
            return 0;
        } else if (this.getZIndex() < o.getZIndex()){
            return 1;
        } else {
            return -1;
        }
    }

    public boolean equals(Object obj) { //FIXME ADD_TEST
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;


        LevelLayer other = (LevelLayer) obj;

        return this.getZIndex() == other.getZIndex();
    }
}
