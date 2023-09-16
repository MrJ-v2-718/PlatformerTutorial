package gamestates;

import main.Game;
import ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {
    protected Game game;

    public State(Game game) {

        this.game = game;
    }

    public boolean isIn(MouseEvent mouseEvent, MenuButton mb) {
        return mb.getBounds().contains(mouseEvent.getX(), mouseEvent.getY());
    }

    public Game getGame() {

        return game;
    }
}
