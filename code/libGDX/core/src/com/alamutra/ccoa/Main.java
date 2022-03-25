package com.alamutra.ccoa;

import com.alamutra.ccoa.Core.Logic.AreasBenchmarkPaths.AreasBenchmarkPaths;
import com.alamutra.ccoa.Core.Logic.AreasBenchmarkPaths.StraightLineEstimatedClass;
import com.alamutra.ccoa.Core.Logic.ControllerMachines.AStarSpaceTimePlanarGraphClass;
import com.alamutra.ccoa.Core.Logic.ControllerMachines.AlhorithmFastFindPath;
import com.alamutra.ccoa.Core.Logic.ControllerMachines.NetworkNodes;
import com.alamutra.ccoa.Core.Logic.ControllerMachines.SquareNetworkNodes;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTimeClass;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.GeneratorFlowCars.RunnerSynchronizationGeneratorsCars;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.GeneratorFlowCars.StandardRunnerGeneratorsCars;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoAClass;
import com.alamutra.ccoa.Core.Logic.GameTime;
import com.alamutra.ccoa.Core.Logic.GameTimeClass;
import com.alamutra.ccoa.Core.Logic.IndexLayer;
import com.alamutra.ccoa.Core.Logic.IndexLayerClass;
import com.alamutra.ccoa.Core.Logic.MovingBody.*;
import com.alamutra.ccoa.Core.SettingRenderingTasks.PoolDataFootprintForRendering;
import com.alamutra.ccoa.Core.SettingRenderingTasks.PoolDataFootprintForRenderingClass;
import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeMachinesBody;
import com.alamutra.ccoa.ExecutionRenderingTasksLibgdxView.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Main extends Game {

	private Screen gameScreen;

	@Override
	public void create() {


		FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(); //create FootprintsSpaceTime (Landscape) //PUNKT_1


		PoolDataFootprintForRendering poolDataFootprintForRendering = new PoolDataFootprintForRenderingClass(onlyFootprintsSpaceTime);


		IndexLayer indexLayer = new IndexLayerClass(0);

		FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
		ParametersMovingUnique parametersMovingUnique = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20);
		NetworkNodes networkNodesFabrica = new SquareNetworkNodes(parametersMovingUnique);
		AlhorithmFastFindPath fastFinderPath = new AStarSpaceTimePlanarGraphClass(networkNodesFabrica, onlyFootprintsSpaceTime);


		{
			PointCCoA from = new PointCCoAClass(0, 131); //FIXME BAG don't multipoly 20 (size car)
			PointCCoA to = new PointCCoAClass(0, 300);


			double timeAdding = 0.0;
			PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMovingUnique, timeAdding);
			try {
				parametersMovingUnique.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer); //FIXME bag sequense time adding
			} catch (СrashIntoAnImpassableObjectException ex) {
			}
		}
/*

		ParametersMoving wall = fabricParametersMoving.getMoving(TypeMachinesBody.WALL_CAR);
		PathCCoA wallPath = new PathCCoAClass();
		wallPath.addPoint(new PointCCoAClass(120, 160));
		wallPath.deposeOn(wall.getVectorFromTopLeftToAppliedCoordinates());
		try {
			wall.mark(onlyFootprintsSpaceTime, wallPath, 0.0, indexLayer);
		} catch (СrashIntoAnImpassableObjectExeption ex) {
		}*/
/*

		{
			PointCCoA from = new PointCCoAClass(80, 60); //FIXME BAG don't multipoly 20 (size car)
			PointCCoA to = new PointCCoAClass(60, 300);


			double timeAdding = 0.0;
			ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
			PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
			try {
				parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer); //FIXME bag sequense time adding
			} catch (СrashIntoAnImpassableObjectExeption ex) {
			}
		}

//FIXME почему у длинной стены нету огромного радиуса? как это работает?

		{

			PointCCoA from = new PointCCoAClass(120, 60);
			PointCCoA to = new PointCCoAClass(180, 260);

			double timeAdding = 0.0;

			ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
			PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
			try {
				parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
			} catch (СrashIntoAnImpassableObjectExeption ex) {
			}
		}
		{

			PointCCoA from = new PointCCoAClass(220, 400);
			PointCCoA to = new PointCCoAClass(100, 20);

			double timeAdding = 10.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

			ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
			PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
			try {
				parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
			} catch (СrashIntoAnImpassableObjectExeption ex) {
			}
		}
		{

			PointCCoA from = new PointCCoAClass(200, 360);
//            PointCCoA from = new PointCCoAClass(20, 20);
			PointCCoA to = new PointCCoAClass(60, 20);

			double timeAdding = 10.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

			ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
			PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
			try {
				parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
			} catch (СrashIntoAnImpassableObjectExeption ex) {
			}
		}
		{

			PointCCoA from = new PointCCoAClass(400, 140);
//            PointCCoA from = new PointCCoAClass(20, 20);
			PointCCoA to = new PointCCoAClass(180, 300);

			double timeAdding = 10.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

			ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
			PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
			try {
				parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
			} catch (СrashIntoAnImpassableObjectExeption ex) {
			}
		}
		{

			PointCCoA from = new PointCCoAClass(440, 140);
//            PointCCoA from = new PointCCoAClass(20, 20);
			PointCCoA to = new PointCCoAClass(140, 300);

			double timeAdding = 10.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

			ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
			PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
			try {
				parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
			} catch (СrashIntoAnImpassableObjectExeption ex) {
			}
		}
		{

			PointCCoA from = new PointCCoAClass(540, 140);
//            PointCCoA from = new PointCCoAClass(20, 20);
			PointCCoA to = new PointCCoAClass(220, 300);

			double timeAdding = 10.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

			ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
			PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
			try {
				parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
			} catch (СrashIntoAnImpassableObjectExeption ex) {
			}
		}
		{

			PointCCoA from = new PointCCoAClass(20, 20);
//            PointCCoA from = new PointCCoAClass(20, 20);
			PointCCoA to = new PointCCoAClass(100, 300);

			double timeAdding = 10.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

			ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
			PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
			try {
				parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
			} catch (СrashIntoAnImpassableObjectExeption ex) {
			}
		}
		{

			PointCCoA from = new PointCCoAClass(60, 560);
//            PointCCoA from = new PointCCoAClass(20, 20);
			PointCCoA to = new PointCCoAClass(20, 20);

			double timeAdding = 20.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

			ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
			PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
			try {
				parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
			} catch (СrashIntoAnImpassableObjectExeption ex) {
			}
		}
		{

			PointCCoA from = new PointCCoAClass(600, 100);
//            PointCCoA from = new PointCCoAClass(20, 20);
			PointCCoA to = new PointCCoAClass(20, 300);

			double timeAdding = 30.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

			BuilderParametersMoving builder = fabricParametersMoving.getBuilderMoving(TypeMachinesBody.TEST_SQUARE_20);
			builder.setSpeed(30);
			ParametersMoving parametersMoving = builder.getParametersMoving();
			PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
			try {
				parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
			} catch (СrashIntoAnImpassableObjectExeption ex) {
			}
		}
		{

			PointCCoA from = new PointCCoAClass(660, 100);
//            PointCCoA from = new PointCCoAClass(20, 20);
			PointCCoA to = new PointCCoAClass(40, 200);

			double timeAdding = 30.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

			BuilderParametersMoving builder = fabricParametersMoving.getBuilderMoving(TypeMachinesBody.TEST_SQUARE_20);
			builder.setSpeed(60);
			ParametersMoving parametersMoving = builder.getParametersMoving();
			PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
			try {
				parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
			} catch (СrashIntoAnImpassableObjectExeption ex) {
			}
		}
		{

//            PointCCoA from = new PointCCoAClass(20, 20);
			PointCCoA from = new PointCCoAClass(600, 40);
			PointCCoA to = new PointCCoAClass(20, 100);

			double timeAdding = 30.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

			ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
			PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
			try {
				parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
			} catch (СrashIntoAnImpassableObjectExeption ex) {
			}
		}

*/

/*
            MovingObject movingObject2 = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);
            LevelLayer levelLayer2 = new LevelLayerClass(1);
            try {

                PathCCoA resPathCCoA = new PathCCoAClass();
                resPath.addPoint(new PointCCoAClass(440, 440));
                resPath.addPoint(new PointCCoAClass(200, 200));
                movingObject2.mark(onlyFootprintsSpaceTime, resPath, 0.0, levelLayer2);
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
            }*/


		//PUNKT_2
		//create MovingObjects (FootprintsSpaceTime)

		//create AreasBenchmarkPaths (FootprintsSpaceTime)
		AreasBenchmarkPaths areasBenchmarkPaths = new StraightLineEstimatedClass();

		//create ControlledMachines (FootprintsSpaceTime, AreasBenchmarkPathsDijkstra) //class MovingObjects create new with (FootprintsSpaceTime)
/*        ControllerMachines controllerMachines =
                new ControllerMachinesClass(onlyFootprintsSpaceTime, 20); //FIXME magic number
        controllerMachines.bringCarToEndOfRoad(
                new PointCCoAClass(0, 0),
                new PointCCoAClass(700, 700),
                movingObject,
                0.0
        );*/


		//PUNKT_3
		//== все из данного блока создавать только после полноценного создания (следящих и расчитывающих) объектов, на которые мы будем вообще влять
		//create AutoDisainerMachines ()
		//create DisainerLandscape (Landscape)
		//create DriverMachine (MovingObjects)
		//create ObserverUpdate(AreasBenchmarkPathsDijkstra, ControlledMachines)


		//create ConsoleManagement (AutoDisainerMachines, DisainerLandscape, DriverMachine, ObserverUpdate) //создавать только после полноценного создания входящих на вход объектов


		//create UserCommandInterface (ConsoleManagement) //in future may need +(FootprintsSpaceTime, MapRender) //создавать после создания полноценного ConsoleManagement //PUNKT_4

		RunnerSynchronizationGeneratorsCars runnerGeneratorsCars = new StandardRunnerGeneratorsCars();
		runnerGeneratorsCars.runIfPossible(onlyFootprintsSpaceTime);

		GameTime gameTime = new GameTimeClass();
		this.gameScreen = new GameScreen(gameTime, poolDataFootprintForRendering);
		setScreen(this.gameScreen);
	}
}
