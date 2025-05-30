package com.group_finity.mascot.animation;

import com.group_finity.mascot.Mascot;
import com.group_finity.mascot.image.ImagePair;
import com.group_finity.mascot.image.ImagePairMap;
import lombok.Getter;

import java.awt.*;

/**
 * @author Yuki Yamada of <a href="http://www.group-finity.com/Shimeji/">Group Finity</a>
 * @author Shimeji-ee Group
 */
public class Pose {
    private final String image;
    private final String rightImage;
    @Getter private final int dx;
    @Getter private final int dy;
    @Getter private final int duration;
    private final String sound;

    public Pose(final String image) {
        this(image, "", 0, 0, 1);
    }

    public Pose(final String image, final int duration) {
        this(image, "", 0, 0, duration);
    }

    public Pose(final String image, final int dx, final int dy, final int duration) {
        this(image, "", dx, dy, duration);
    }

    public Pose(final String image, final String rightImage) {
        this(image, rightImage, 0, 0, 1);
    }

    public Pose(final String image, final String rightImage, final int duration) {
        this(image, rightImage, 0, 0, duration);
    }

    public Pose(final String image, final String rightImage, final int dx, final int dy, final int duration) {
        this(image, rightImage, dx, dy, duration, null);
    }

    public Pose(final String image, final String rightImage, final int dx, final int dy, final int duration, final String sound) {
        this.image = image;
        this.rightImage = rightImage;
        this.dx = dx;
        this.dy = dy;
        this.duration = duration;
        this.sound = sound;
    }

    @Override
    public String toString() {
        return "Pose(" + (getImage() == null ? "" : getImage()) + "," + getDx() + "," + getDy() + "," + getDuration() + ", " + sound + ")";
    }

    public void next(final Mascot mascot) {
        mascot.setAnchor(new Point(mascot.getAnchor().x + (mascot.isLookRight() ? -getDx() : getDx()),
                mascot.getAnchor().y + getDy()));
        mascot.setImage(ImagePairMap.getInstance().getImage(getImageName(), mascot.isLookRight()));
        mascot.setSound(getSoundName());
    }

    public String getImageName() {
        return (image == null ? "" : image) + (rightImage == null ? "" : rightImage);
    }

    public ImagePair getImage() {
        return ImagePairMap.getInstance().get(getImageName());
    }

    public String getSoundName() {
        return sound;
    }
}