package control.singelton;

import view.gui.controller.sceneController;


public class SingeltionSceneController {

    private static SingeltionSceneController instance;
    private sceneController SceneController;

    private SingeltionSceneController() {}

    public static SingeltionSceneController getInstance() {
        if (instance == null) {
            instance = new SingeltionSceneController();
        }
        return instance;
    }

    public sceneController getSceneController() {
        return SceneController;
    }

    public void setSceneController(sceneController SceneController) {
        this.SceneController = SceneController;
    }
}

