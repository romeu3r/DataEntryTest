package support;

import support.execute.ActionsExecute;
import util.Util;


public class FactoryActions {
    public static Actions Actions() {
        return new ActionsExecute(new Util());
    }
}
