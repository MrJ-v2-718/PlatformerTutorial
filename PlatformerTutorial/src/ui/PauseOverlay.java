package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constants.UI.PauseButtons.*;
import static utils.Constants.UI.URMButtons.*;
import static utils.Constants.UI.VolumeButtons.*;

public class PauseOverlay {

    private Playing playing;
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuB, replayB, unpauseB;
    private VolumeButton volumeButton;

    public PauseOverlay(Playing playing) {
        this.playing = playing;
        loadBackground();
        createSoundButtons();
        createUrmButtons();
        createVolumeButton();
    }

    private void createVolumeButton() {
        int vX = (int) (309 * Game.SCALE);
        int vY = (int) (278 * Game.SCALE);
        volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void createUrmButtons() {
        int menuX = (int) (330 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);
        int bY = (int) (325 * Game.SCALE);

        menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
        unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
    }

    private void createSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
        bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (25 * Game.SCALE);
    }

    public void update() {
        musicButton.update();
        sfxButton.update();

        menuB.update();
        replayB.update();
        unpauseB.update();

        volumeButton.update();
    }

    public void draw(Graphics g) {
        // Background
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

        // Sound Buttons
        musicButton.draw(g);
        sfxButton.draw(g);

        // Urm Buttons
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);

        // Volume Buttons
        volumeButton.draw(g);
    }

    public void mouseDragged(MouseEvent mouseEvent) {
        if (volumeButton.isMousePressed()) {
            volumeButton.changeX(mouseEvent.getX());
        }
    }

    public void mousePressed(MouseEvent mouseEvent) {
        if (isIn(mouseEvent, musicButton)) {
            musicButton.setMousePressed(true);
        } else if (isIn(mouseEvent, sfxButton)) {
            sfxButton.setMousePressed(true);
        } else if (isIn(mouseEvent, menuB)) {
            menuB.setMousePressed(true);
        } else if (isIn(mouseEvent, replayB)) {
            replayB.setMousePressed(true);
        } else if (isIn(mouseEvent, unpauseB)) {
            unpauseB.setMousePressed(true);
        } else if (isIn(mouseEvent, volumeButton)) {
            volumeButton.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        if (isIn(mouseEvent, musicButton)) {
            if (musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted());
            }
        } else if (isIn(mouseEvent, sfxButton)) {
            if (sfxButton.isMousePressed()) {
                sfxButton.setMuted(!sfxButton.isMuted());
            }
        } else if (isIn(mouseEvent, menuB)) {
            if (menuB.isMousePressed()) {
                Gamestate.state = Gamestate.MENU;
                playing.unpauseGame();
            }

        } else if (isIn(mouseEvent, replayB)) {
            if (replayB.isMousePressed()) {
                System.out.println("replay lvl!");
            }
        } else if (isIn(mouseEvent, unpauseB)) {
            if (unpauseB.isMousePressed()) {
                playing.unpauseGame();
            }
        }
        musicButton.resetBools();
        sfxButton.resetBools();
        menuB.resetBools();
        replayB.resetBools();
        unpauseB.resetBools();
        volumeButton.resetBools();
    }

    public void mouseMoved(MouseEvent mouseEvent) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);
        volumeButton.setMouseOver(false);

        if (isIn(mouseEvent, musicButton)) {
            musicButton.setMouseOver(true);
        } else if (isIn(mouseEvent, sfxButton)) {
            sfxButton.setMouseOver(true);
        } else if (isIn(mouseEvent, menuB)) {
            menuB.setMouseOver(true);
        } else if (isIn(mouseEvent, replayB)) {
            replayB.setMouseOver(true);
        } else if (isIn(mouseEvent, unpauseB)) {
            unpauseB.setMouseOver(true);
        } else if (isIn(mouseEvent, volumeButton)) {
            volumeButton.setMouseOver(true);
        }
    }

    private boolean isIn(MouseEvent mouseEvent, PauseButton b) {
        return b.getBounds().contains(mouseEvent.getX(), mouseEvent.getY());
    }

}
