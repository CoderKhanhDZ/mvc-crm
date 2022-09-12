package mvcproject.java11.crm.services;

import mvcproject.java11.crm.model.Status;
import mvcproject.java11.crm.repository.StatusRepository;

import java.util.List;

public class StatusServiceImp implements IStatusService {
    private static StatusRepository statusRepository;
    private static StatusServiceImp INSTANCE;

    private StatusServiceImp() {
        statusRepository = new StatusRepository();
    }

    public static StatusServiceImp getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new StatusServiceImp();
        }
        return INSTANCE;
    }

    @Override
    public List<Status> getAllStatus() {
        return statusRepository.getAllStatus();
    }

    @Override
    public Status getStatusById(int id) {
        return statusRepository.getStatusById(id);
    }
}
