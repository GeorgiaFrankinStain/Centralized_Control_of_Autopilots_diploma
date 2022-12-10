package com.CCoABackendCalculate.CCoA.SpringModel;

public class StatusResponseDTO {
    private boolean isSuccessful = false;

    public StatusResponseDTO() {
    }

    public StatusResponseDTO(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
