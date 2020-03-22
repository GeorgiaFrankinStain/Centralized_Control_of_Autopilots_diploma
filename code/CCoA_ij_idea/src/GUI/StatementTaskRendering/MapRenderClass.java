package GUI.StatementTaskRendering;

import GUI.ExecutionTaskRendering.MapRender;
import Logic.FootprintSpaceTime.PhisicalBody;

import java.util.List;

public class MapRenderClass implements MapRender {

    @Override
    public void renderingZone(int xOriginRendering, int yOriginRendering) {
        //FIXME получаем полигоны от FootprintSpaceTime из заданной нам зоны за последнее некоторое время (колебание таймера)

        GUI.Rendering.PhisicalBodysFromWhen mapPhisicalBodysFromWhen = (GUI.Rendering.PhisicalBodysFromWhen) this.mapFootprintSpaceTime; //STACK 00000
        List<PhisicalBody> objectsOfRendering =
                mapPhisicalBodysFromWhen.getPhisicalBodysFromWhen(this.getAreaOfRendering(), 1); //FIXME CRITICAL add time variable


        //сравниваем время последнего обновления ландшафта и время последнего рендеринга ландшафта //FIXME realised comments
        //если устарело, то обновляем ландшафт (по началу будем все отрисовывать

        //отрисовываем изменившиеся части (сначала будем отрисовывать все подряд, а потом то, у чего хешь свойств изменился

        for (PhisicalBody currentPolygon : objectsOfRendering) {
            this.fillPolygon(currentPolygon.getPolygonExtended(), g);

        }
    }
}
