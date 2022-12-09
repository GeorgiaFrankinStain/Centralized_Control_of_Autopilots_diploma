package com.alamutra.CCoAWeb.Core.Controller;

import com.alamutra.CCoAWeb.PercistanceDataAccessObjects.Exception.NotEnoughDataException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface ManagerRoom {


    public String createNewRoom(String titleRoom);

    public void toApplicationOrders(String jsonOrders) throws Exception;

}
