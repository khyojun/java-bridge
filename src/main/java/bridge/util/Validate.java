package bridge.util;

import bridge.constant.ERROR;
import bridge.constant.GameState;

public class Validate {


    public void validateBridgeSize(String size) {
        try {
            int sizeInt = Integer.parseInt(size);
            isSizeInRange(sizeInt);
        } catch (Exception e) {
            throw new IllegalArgumentException(ERROR.sizeException.getException());
        }
    }

    private void isSizeInRange(int sizeInt) {
        if (!(sizeInt >= GameState.MIN_RANGE && sizeInt <= GameState.MAX_RANGE)) {
            throw new IllegalArgumentException();
        }
    }

    public void validateMoving(String size) {
        try {
            if (!size.equals(GameState.DOWN) && !size.equals(GameState.UP)) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ERROR.moveException.getException());
        }
    }

    public void validateGameDefinition(String definition) {
        try {
            if (!definition.equals(GameState.QUIT) && !definition.equals(GameState.RETRY)) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ERROR.DEFINITION_EXCEPTION.getException());
        }
    }


}
