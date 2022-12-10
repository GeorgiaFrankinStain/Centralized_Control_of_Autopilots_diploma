package com.CCoABackendCalculate.CCoA.Core.Controller;

import com.CCoABackendCalculate.CCoA.PercistanceDataAccessObjects.Exception.NotEnoughDataException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface ManagerRoom {


    public String createNewRoom();

    public void toApplicationOrders(String jsonOrders) throws Exception;

}
