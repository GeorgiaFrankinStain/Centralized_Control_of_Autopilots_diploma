package com.alamutra.CCoAWeb.Core.ViewSettingRenderingTasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkinsCapacitorTest {
    @Test
    void findAllTypesFromTypeMachinesBody() {
        for (TypeMachinesBody typeMachinesBody : TypeMachinesBody.values()) {
            boolean isNotFound = SkinsCapacitor.convertFrom(typeMachinesBody) == null;
            if (isNotFound) {
                assertTrue(false, "convert error");
            }
        }
    }


    @Test
    void findAllTypesFromTypeLandscapeBody() {
        for (TypeLandscapeBody typeLandscapeBody : TypeLandscapeBody.values()) {
            boolean isNotFound = SkinsCapacitor.convertFrom(typeLandscapeBody) == null;
            if (isNotFound) {
                assertTrue(false, "convert error");
            }
        }
    }
}