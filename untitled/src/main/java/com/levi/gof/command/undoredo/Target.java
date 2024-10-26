package com.levi.gof.command.undoredo;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Getter
@Setter
public abstract class Target {

    protected static final Logger LOGGER = LoggerFactory.getLogger(Target.class);

    private Size size;

    private Visibility visibility;

    public void printStatus() {
        LOGGER.info("{}, [size={}] [visibility={}]", this, getSize(), getVisibility());
    }

    public void changeSize() {
        var oldSize = getSize() == Size.NORMAL ? Size.SMALL : Size.NORMAL;
        setSize(oldSize);
    }

    public void changeVisibility() {
        var visible = getVisibility() == Visibility.INVISIBLE
                ? Visibility.VISIBLE : Visibility.INVISIBLE;
        setVisibility(visible);
    }
}