package com.alamutra.ccoa.Core.SettingRenderingTasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkinsTest {
    @Test
    void findAllTypesFromTypeMachinesBody() {
        for (TypeMachinesBody typeMachinesBody : TypeMachinesBody.values()) {
            boolean isNotFound = Skins.convertFrom(typeMachinesBody) == null;
            if (isNotFound) {
                assertTrue(false, "convert error");
            }
        }
    }


    @Test
    void findAllTypesFromTypeLandscapeBody() {
        for (TypeLandscapeBody typeLandscapeBody : TypeLandscapeBody.values()) {
            boolean isNotFound = Skins.convertFrom(typeLandscapeBody) == null;
            if (isNotFound) {
                assertTrue(false, "convert error");
            }
        }
    }
}