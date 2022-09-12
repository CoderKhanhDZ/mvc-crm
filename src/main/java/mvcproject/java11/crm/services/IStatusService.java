package mvcproject.java11.crm.services;

import mvcproject.java11.crm.model.Status;

import java.util.List;

public interface IStatusService {
    List<Status> getAllStatus();

    Status getStatusById(int id);
}
