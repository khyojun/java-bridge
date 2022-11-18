package bridge.controller;

import bridge.domain.JudgeDestination;
import bridge.domain.MapPrinting;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class GameController {


    private List<String> moving = new ArrayList<>();
    private List<Boolean> upPrint = new ArrayList<>();
    private List<Boolean> downPrint = new ArrayList<>();
    private OutputView outputView = new OutputView();
    static final int UP=1;
    static final int DOWN=0;
    JudgeDestination judgeDestination = new JudgeDestination();
    private int count=1;
    private static int idx=0;
    private InputView inputView = new InputView();



    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */

    public void start(List<String> mapBridge, int bridgeSize){
        move(mapBridge, bridgeSize);
        afterMove(mapBridge, bridgeSize);
    }
    public void move(List<String> mapBridge, int bridgeSize) {
        clearInfo();
        while(idx<bridgeSize && !MapPrinting.isMoveStop()){
            System.out.println("mapBridge = " + mapBridge); // 출력시 어디가 갈 수 있는 칸인지 확인하기 위한 역할
            moving.add(inputView.readMoving());
            setPrintBool(upPrint, downPrint, convertNowIndex(mapBridge.get(idx)));
            makeBridgeMap(upPrint, downPrint, moving.get(idx));
            idx++;
        }
    }

    public void clearInfo(){
        moving.clear();
        upPrint.clear();
        downPrint.clear();
        idx=0;
        MapPrinting.clearUpDownLocation();
    }

    private void makeBridgeMap(List<Boolean> upPrint, List<Boolean> downPrint,
        String nowIndex) {
        MapPrinting mapPrinting = new MapPrinting(upPrint, downPrint,convertNowIndex(nowIndex));
        mapPrinting.makeList();
    }

    private int convertNowIndex(String nowIndex) {
        if(nowIndex.equals("U")){
            return UP;
        }
        return DOWN;
    }

    private void setPrintBool(List<Boolean> upPrint, List<Boolean> downPrint, int upDown) {
        if(upDown==UP){
            upPrint.add(true);
            downPrint.add(false);
        }
        if(upDown==DOWN){
            upPrint.add(false);
            downPrint.add(true);
        }
    }

    public void afterMove( List<String> mapBridge, int bridgeSize){
        if (isReachFinal(bridgeSize)) {
            return;
        }
        if (judgementRetry()) {
            retry(bridgeSize,mapBridge);
            return;
        }
        setQuit(bridgeSize);
    }


    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry(int bridgeSize, List<String> mapBridge) { // 수정 부분 체크 retry 는 조금 별로 더 수정해야할 거 같음 retry말고 이전 작업에서처리
        count++;
        MapPrinting.initRestart();
        start(mapBridge, bridgeSize);
        return;
    }



    private boolean isReachFinal(int bridgeSize) {
        if(!MapPrinting.isMoveStop()) {
            setQuit(bridgeSize);
            return true;
        }
        return false;
    }

    private boolean judgementRetry() {
        return judgeDestination.judgeRestartOrOver(inputView.readGameCommand());
    }

    private void setQuit(int bridgeSize) {
        int nowIndex = convertNowIndex(moving.get(idx - 1));
        MapPrinting resultMapPrinting = new MapPrinting(upPrint,downPrint,nowIndex);
        if(bridgeSize == idx && !MapPrinting.isMoveStop()){
            outputView.printResult(count, resultMapPrinting, true);
            return;
        }
        outputView.printResult(count, resultMapPrinting, false);
    }

}
