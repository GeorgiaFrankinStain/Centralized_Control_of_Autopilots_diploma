package Logic.FootprintSpaceTime;

import Logic.Position;

public class FootprintClass implements Footprint {
    private int idTrack;
    private Position position;

    public FootprintClass(int idTrack, Position position) {
        this.idTrack = idTrack;
        this.position = position;
    }

    //==== <start> <Getter_and_Setter> ==================================================

    @Override
    public int getIdTrack() {
        return idTrack;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    //==== <end> <Getter_and_Setter> ==================================================
}
