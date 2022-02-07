package com.alamutra.ccoa.Core.SettingRenderingTasks;

public enum Skins {
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

    public static Skins convertFrom(TypeMachinesBody typeMachinesBody) { //FIXME add tests
        String nameTypeMachineBody = typeMachinesBody.name();
        for (Skins skin : Skins.values()) {
            if (skin.name().equals(nameTypeMachineBody)) {
                return skin;
            }
        }

        assert (false);
        return null;
    }
    public static Skins convertFrom(TypeLandscapeBody typeLandscapeBody) { //FIXME add tests
        String nameTypeLandscapeBody = typeLandscapeBody.name();
        for (Skins skin : Skins.values()) {
            if (skin.name().equals(nameTypeLandscapeBody)) {
                return skin;
            }
        }

        assert (false);
        return null;
    }
}
