import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.util.ArrayList;

/**
 * Created by 1 on 20/06/2017.
 */

/**
 * .
 * @param <T> - gets a T.
 */
public class MenuAnimation<T> implements Menu<T> {

    private ArrayList<String> key;
    private ArrayList<String> message;
    private ArrayList<T> returnVal;
    private ArrayList<Menu> subMenu;
    private T typeVal;
    private boolean stop;
    private KeyboardSensor keyboard;
    private String name;
    /**
     *  constructor.
     * @param keyboard - gets a keyboard.
     * @param name - gets a name.
     */
    public MenuAnimation(KeyboardSensor keyboard, String name) {
        this.keyboard = keyboard;
        this.name = name;
        this.key = new ArrayList<String>();
        this.message = new ArrayList<String>();
        this.returnVal = new ArrayList<T>();
        this.stop = false;
        this.subMenu =  new ArrayList<Menu>();
    }

    /**
     * The function draws the screen.
     * @param d  - A screen to draw.
     * @param dt - gets double dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        int i = 0;
        d.setColor(new java.awt.Color(250, 255, 172));
        d.fillRectangle(0, 20, 800, 580);
       // d.setColor(new java.awt.Color(221, 185, 255));
        d.setColor(java.awt.Color.black);
        d.drawText(220, 200, "" + this.name, 50);
        d.setColor(new java.awt.Color(255, 134, 123));
        for (i = 0; i < this.key.size(); i++) {
            d.drawText(290, 2 * d.getHeight() / 4 + (i * 30), "" + this.key.get(i), 30);
            d.drawText(315, d.getHeight() / 2 + (i * 30), "" + this.message.get(i), 25);

        }
//        if (this.keyboard.isPressed(this.key.get(i))) { this.stop = true; }
    }

    /**
     * A function that returns a boolean variable of true or false.
     *
     * @return -  returns a boolean variable of true or false.
     */
    public boolean shouldStop() {
        for (int i = 0; i < this.key.size(); i++) {
            if (this.keyboard.isPressed(this.key.get(i))) {
                this.typeVal = this.returnVal.get(i);
                return true;
            }

        }
        return false;
    }

    /**
     * The function add to menu.
     * @param key2 - gets a string.
     * @param message2 - gets a string.
     * @param returnVal2 - gets a type.
     */
    public void addSelection(String key2, String message2, T returnVal2) {
        this.key.add(key2);
        this.message.add(message2);
        this.returnVal.add(returnVal2);
    }

    /**
     * The function return the type of the menu.
     *
     * @return - return the type of the menu.
     */
    public T getStatus() {
        return this.typeVal;
    }
    /**
     * The function builds a secondary menu.
     * @param key1 - gets a string of key.
     * @param message1 - gets a string of message.
     * @param subMenu1 - gets a submenu.
     */
    public void addSubMenu(String key1, String message1, Menu<T> subMenu1) {
        this.key.add(key1);
        this.message.add(message1);
        //this.returnVal.add(null);
        this.subMenu.add(subMenu1);
    }
}
