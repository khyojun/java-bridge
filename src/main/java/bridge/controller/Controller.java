package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();
    private int size = 0;
    private BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
    private BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
    private List<String> mapBridge = new ArrayList<>();


    public Controller() {
        setMap();
    }

    public void setMap() {
        size = inputView.readBridgeSize();
        mapBridge = bridgeMaker.makeBridge(size);
    }

    public void play() {
        GameController gameController = new GameController();
        gameController.start(mapBridge, size);
    }


}
