package control.singelton;

import geschaeftslogik.Model;

//https://www.baeldung.com/java-singleton
//Klasse damit alle Events nach der deserialsierung auf das aktuallisierte Model zugreifen
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
