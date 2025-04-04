package co.edu.uniquindio.task.task_app.factory;

import co.edu.uniquindio.task.task_app.model.TaskScholar;

public class ModelFactory {

    TaskScholar taskScholar;

    private static ModelFactory modelFactory;

    private ModelFactory(){
        taskScholar = new TaskScholar();
    }

    public static ModelFactory getInstance() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }
    
}
