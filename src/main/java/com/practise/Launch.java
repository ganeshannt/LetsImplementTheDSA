package com.practise;

import com.practise.commons.ReadmeUpdater;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Launch class to update the README file with Java file names and GitHub links.
 */
public class Launch {

    private static final Logger logger = Logger.getLogger(Launch.class.getName());

    public static void main(String[] args) {
        try {
            ReadmeUpdater.updateReadMeFile();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error updating README file", e);
        }
    }
}
