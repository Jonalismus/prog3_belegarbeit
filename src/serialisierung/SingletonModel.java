package serialisierung;

import geschaeftslogik.Model;

public class SingletonModel {
    private static SingletonModel instance;
    private Model model;

    private SingletonModel() {}

    public static SingletonModel getInstance() {
        if (instance == null) {
            instance = new SingletonModel();
        }
        return instance;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
