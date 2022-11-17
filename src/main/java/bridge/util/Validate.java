package bridge.util;

import bridge.constant.ERROR;

public class Validate {

    private static final int MIN_RANGE=3;
    private static final int MAX_RANGE=20;

    public void validateBridgeSize(String size){
        try{
            int sizeInt = Integer.parseInt(size);
            isSizeInRange(sizeInt);
        }
        catch(Exception e){
            throw new IllegalArgumentException(ERROR.sizeException.getException());
        }
    }

    private void isSizeInRange(int sizeInt) {
        if(!(sizeInt >=MIN_RANGE && sizeInt <=MAX_RANGE)){
            throw new IllegalArgumentException();
        }
    }

    public void validateMoving(String size){
        try{
            if(!size.equals("D")  && !size.equals("U")){
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException(ERROR.moveException.getException());
        }
    }

    public void validateGameDefinition(String definition){
        try{
            if(!definition.equals("Q")  && !definition.equals("R")){
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("[ERROR] 선택은 R 또는 Q로 결정시킬 수 있습니다!");
        }
    }



}
