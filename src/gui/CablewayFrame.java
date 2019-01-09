/*
 * The MIT License
 *
 * Copyright 2018 giuliobosco.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package gui;

import cableway.CablewayManager;

import javax.swing.*;
import java.awt.*;

/**
 * Cableway frame.
 *
 * @author giuliobosco
 * @version 1.0
 */
public class CablewayFrame extends JFrame implements Runnable {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Cabin 0 panel.
     */
    private CabinPanel cabin0Panel;

    /**
     * Cabin 1 panel.
     */
    private CabinPanel cabin1Panel;

    /**
     * Cableway manager.
     */
    private CablewayManager cablewayManager;

    // --------------------------------------------------------------------------- Getters & Setters
    // -------------------------------------------------------------------------------- Constructors

    /**
     * Cableway frame constructor.
     */
    public CablewayFrame() {
        super("Cableway");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(200, 200);
        this.setMinimumSize(new Dimension(800, 400));

        this.getContentPane().setLayout(new GridLayout(2, 1));
        try {
            this.cablewayManager = new CablewayManager();

            this.cabin0Panel = cablewayManager.getCabin0Panel();
            this.cabin1Panel = cablewayManager.getCabin1Panel();
            this.cablewayManager.getCablewayActionManager().getActionListeners().add(cabin0Panel);
            this.cablewayManager.getCablewayActionManager().getActionListeners().add(cabin1Panel);

            this.getContentPane().add(cabin0Panel);
            this.getContentPane().add(cabin1Panel);
            pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods

    /**
     * Runner.
     */
    @Override
    public void run() {
        try {
            while (cabin0Panel.getCabin().getCable().getPosition() < cabin0Panel.getCabin().getCable().getLength()) {
                cabin0Panel.getCabin().getCable().incrementPosition(10);
                System.out.println(cabin0Panel.getCabin().getCable().getPosition());
                Thread.sleep(50);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // --------------------------------------------------------------------------- Static Components

    /**
     * Main method, run the app.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CablewayFrame cf = new CablewayFrame();
                cf.setVisible(true);
                Thread t = new Thread(cf);
                t.start();
            }
        });
    }

}
