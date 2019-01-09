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

import cableway.cabin.Cabin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Cabin Panel.
 * 
 * @author giuliobosco
 * @version 1.0
 */
public class CabinPanel extends JPanel implements ActionListener {
    // ------------------------------------------------------------------------------------ Costants

    private static final String CABIN_IMAGE_SRC = "src/res/cabin.png";

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Panel cabin.
     */
    private Cabin cabin;

    /**
     * Panel cabin image.
     */
    private BufferedImage cabinImage;

    // --------------------------------------------------------------------------- Getters & Setters

    public Cabin getCabin() {
        return cabin;
    }


    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create cabin panel.
     *
     * @param cabin Panel cabin.
     */
    public CabinPanel(Cabin cabin) {
        this.cabin = cabin;

        try {
            this.loadImg(Paths.get(CABIN_IMAGE_SRC));
        } catch (IOException ignored) {

        }

    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Load the image in the panel cabin image.
     *
     * @param path Image to load in the panel cabin image.
     * @throws IOException Error reading the file.
     */
    public void loadImg(Path path) throws IOException {
        if (Files.exists(path) && !Files.notExists(path) && Files.isReadable(path)) {
            this.cabinImage = ImageIO.read(new File(path.toUri()));
        }
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Paint the component.
     *
     * @param g Graphics of the panel.
     */
    @Override
    public void paintComponent(Graphics g) {

        double length = this.cabin.getCable().getLength();
        double width = this.getWidth() - 147;

        double size = width / length;

        int position = (int) (size * this.cabin.getCable().getPosition() + 10);
        g.drawImage(this.cabinImage, position, 20, this);
    }

    /**
     * Action performed.
     *
     * @param e Action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass() == Cabin.class) {
            if (e.getActionCommand().equals(Cabin.MOVED)) {
                this.repaint();
            }
        }
    }

    // --------------------------------------------------------------------------- Static Components
    
}
