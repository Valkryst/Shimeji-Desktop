package com.group_finity.mascot.display.window.generic;

import com.group_finity.mascot.image.NativeImage;
import lombok.Getter;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * An image with alpha value that can be used for {@link GenericTranslucentWindow}.
 *
 * @author Yuki Yamada of <a href="http://www.group-finity.com/Shimeji/">Group Finity</a>
 * @author Shimeji-ee Group
 */
class GenericNativeImage implements NativeImage {

    /**
     * Java image object.
     */
    private final BufferedImage managedImage;

    @Getter private final Icon icon;

    public GenericNativeImage(final BufferedImage image) {
        managedImage = image;
        icon = new ImageIcon(image);
    }

    BufferedImage getManagedImage() {
        return managedImage;
    }
}
