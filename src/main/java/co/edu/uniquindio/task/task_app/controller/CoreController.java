package co.edu.uniquindio.task.task_app.controller;

import co.edu.uniquindio.task.task_app.factory.ModelFactory;

public abstract class CoreController {
    ModelFactory ModelFactory;

    public CoreController(){
        ModelFactory = ModelFactory.getInstance();
    }


}
