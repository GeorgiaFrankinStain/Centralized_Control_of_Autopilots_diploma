package Logic.FootprintSpaceTime;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingBody;
import Logic.Position;

public class FootprintClass implements Footprint {
    private int idTrack;
    private Position position;
    private RenderingBody renderingBody;

    public FootprintClass(int idTrack, Position position, RenderingBody renderingBody) {
        this.idTrack = idTrack;
        this.position = position;
        this.renderingBody = renderingBody;
    }

    //==== <start> <Getter_and_Setter> ==================================================

    @Override
    public int getIdTrack() {
        return idTrack;
    }

    @Override
    public RenderingBody getRenderingBody() {
        return renderingBody;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    //==== <end> <Getter_and_Setter> ==================================================
}
