package com.alamutra.CCoAWeb.Core.ViewSettingRenderingTasks;

public enum SkinsCapacitor {
    SIMPLE_CAR,
    TEST_SQUARE_20,
    TRUCK,
    TANK,
    RACING_CAR,
    NIER_AUTOMATA_2B,
    TURTLE,
    STAR_DESTROYER,
    WALL_CAR,
    WALL_SQUARE,

    SWAMP,
    ASPHALT,
    WATER,
    GRASS,
    BUILDING;

    public static SkinsCapacitor convertFrom(TypeMachinesBody typeMachinesBody) {
        String nameTypeMachineBody = typeMachinesBody.name();
        for (SkinsCapacitor skin : SkinsCapacitor.values()) {
            if (skin.name().equals(nameTypeMachineBody)) {
                return skin;
            }
        }

        assert (false);
        return null;
    }
    public static SkinsCapacitor convertFrom(TypeLandscapeBody typeLandscapeBody) {
        String nameTypeLandscapeBody = typeLandscapeBody.name();
        for (SkinsCapacitor skin : SkinsCapacitor.values()) {
            if (skin.name().equals(nameTypeLandscapeBody)) {
                return skin;
            }
        }

        assert (false);
        return null;
    }
}
